package opekope2.lilac.internal.resource.loading

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.loader.api.entrypoint.EntrypointContainer
import net.minecraft.resource.ResourceManager
import net.minecraft.resource.ResourceReloader
import net.minecraft.resource.ResourceType
import net.minecraft.util.Identifier
import net.minecraft.util.profiler.Profiler
import opekope2.lilac.api.Util
import opekope2.lilac.api.resource.IResourceReader
import opekope2.lilac.api.resource.loading.IResourceLoader
import opekope2.lilac.api.resource.loading.IResourceLoaderPlugin
import opekope2.lilac.api.resource.loading.IResourceLoadingSession
import opekope2.lilac.impl.resource.ResourceReader
import org.slf4j.LoggerFactory
import java.io.IOException
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor

@Suppress("unused")
object ResourceLoader : ClientModInitializer, IdentifiableResourceReloadListener {
    private val logger = LoggerFactory.getLogger("LiLaC/ResourceLoader")
    private val sessions = mutableSetOf<IResourceLoadingSession>()

    fun getResourceLoadingSessionProperties(session: IResourceLoadingSession): IResourceLoadingSession.IProperties =
        IResourceLoadingSession.IProperties { session in sessions }

    override fun onInitializeClient() {
        ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(this)
    }

    override fun getFabricId(): Identifier = Identifier("lilac", "lilac_resource_loader")

    override fun reload(
        synchronizer: ResourceReloader.Synchronizer,
        manager: ResourceManager,
        prepareProfiler: Profiler,
        applyProfiler: Profiler,
        prepareExecutor: Executor,
        applyExecutor: Executor
    ): CompletableFuture<Void> {
        val resourceLoaderPlugins = Util.getEntrypointContainers(IResourceLoaderPlugin::class.java)
        if (resourceLoaderPlugins.isEmpty()) {
            logger.info("No resource loader plugins were found, not loading resources.")
            return synchronizer.whenPrepared(Unit).thenRunAsync({ }, applyExecutor)
        }

        logger.info("Reloading resources.")

        val session = ResourceLoadingSession()
        sessions += session

        val loadingFutures = mutableListOf<CompletableFuture<*>>()

        fun createResourceLoader(container: EntrypointContainer<IResourceLoaderPlugin>): Pair<String, IResourceLoader> =
            container.provider.metadata.id to session.createResourceLoader(
                container.provider.metadata.id,
                container.entrypoint
            )

        for ((id, loader) in resourceLoaderPlugins.map(::createResourceLoader)) {
            val loadedResources = CompletableFuture
                .supplyAsync(
                    { manager.findResources(loader.startingPath, loader::canLoad) },
                    prepareExecutor
                )
                .thenApplyAsync(
                    { resources -> resources.map { (id) -> ResourceReader(id) } },
                    prepareExecutor
                )
                .thenApplyAsync(
                    { resources ->
                        resources.mapNotNull { resource ->
                            loader.loadResource(resource, id)
                        }
                    },
                    prepareExecutor
                )

            val applyStart = loadedResources.thenCompose(synchronizer::whenPrepared)

            loadingFutures += applyStart
                .thenAcceptAsync(
                    { resources -> resources.forEach(loader::processResource) },
                    applyExecutor
                ).thenAcceptAsync(
                    { loader.close() },
                    applyExecutor
                ).exceptionallyAsync(
                    { throwable ->
                        logger.error(
                            "Resource loader '$loader' (provided by mod '$id') threw an exception, closing the loader and rethrowing.",
                            throwable
                        )
                        loader.close()
                        throw throwable
                    },
                    applyExecutor
                )
        }

        return CompletableFuture.allOf(*loadingFutures.toTypedArray()).thenRunAsync(
            {
                logger.info("Resources reloaded successfully.")
                sessions -= session
            },
            applyExecutor
        )
    }

    private fun IResourceLoader.loadResource(resource: IResourceReader, modId: String): Any? = try {
        loadResource(resource)
    } catch (e: IOException) {
        logger.warn(
            "Resource loader '$this' (provided by mod '$modId') threw an exception trying to load $resource, continuing reload.",
            e
        )
        null
    }
}
