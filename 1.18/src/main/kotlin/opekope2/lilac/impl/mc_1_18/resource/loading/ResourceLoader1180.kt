package opekope2.lilac.impl.mc_1_18.resource.loading

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.minecraft.resource.ResourceManager
import net.minecraft.resource.ResourceReloader
import net.minecraft.resource.ResourceType
import net.minecraft.util.Identifier
import net.minecraft.util.profiler.Profiler
import opekope2.lilac.annotation.RequiresMinecraftVersion
import opekope2.lilac.api.resource.IResourceReader
import opekope2.lilac.api.resource.loading.IResourceLoader
import opekope2.lilac.api.resource.loading.IResourceLoadingSession
import opekope2.lilac.impl.mc_1_18.resource.ResourceReader1180
import opekope2.lilac.internal.resource.loading.IResourceLoadingSessionHolder
import opekope2.lilac.internal.resource.loading.ResourceLoadingSession
import opekope2.lilac.util.MinecraftVersion
import opekope2.lilac.util.Util.getEntrypointContainers
import org.slf4j.LoggerFactory
import java.io.IOException
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor

@RequiresMinecraftVersion(
    minVersion = MinecraftVersion.MINECRAFT_1_18,
    maxVersion = MinecraftVersion.MINECRAFT_1_18_2
)
object ResourceLoader1180 : IResourceLoadingSessionHolder, ClientModInitializer, IdentifiableResourceReloadListener {
    private val logger = LoggerFactory.getLogger("LiLaC/ResourceLoader")
    private val sessions = mutableSetOf<ResourceLoadingSession>()

    override fun getResourceLoadingSessionProperties(session: IResourceLoadingSession): IResourceLoadingSession.IProperties =
        IResourceLoadingSession.IProperties {
            if (session is ResourceLoadingSession && session in sessions) session.stage
            else IResourceLoadingSession.Stage.INACTIVE
        }

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
        val resourceLoaderPlugins = getEntrypointContainers(IResourceLoader.IFactory::class.java)
        if (resourceLoaderPlugins.isEmpty()) {
            logger.info("No resource loader plugins were found, no resources to load")
            return synchronizer.whenPrepared(Unit).thenRunAsync({ }, applyExecutor)
        }

        logger.info("Reloading resources")

        val session = ResourceLoadingSession()
        sessions += session

        val loadingFutures = mutableListOf<CompletableFuture<*>>()

        session.stage = IResourceLoadingSession.Stage.INIT

        val identifiedResourceLoaders = resourceLoaderPlugins.map { container ->
            container.provider.metadata.id to session.createResourceLoader(
                container.provider.metadata.id,
                container.entrypoint
            )
        }

        val prepareStart = CompletableFuture.supplyAsync(
            { session.stage = IResourceLoadingSession.Stage.PREPARE },
            prepareExecutor
        )
        val applyWait = synchronizer.whenPrepared(Unit).thenApplyAsync(
            { session.stage = IResourceLoadingSession.Stage.APPLY },
            applyExecutor
        )

        for ((id, loader) in identifiedResourceLoaders) {
            val loadedResources = prepareStart
                .thenApplyAsync(
                    { manager.findResources(loader.startingPath, loader::canLoad) },
                    prepareExecutor
                )
                .thenApplyAsync(
                    { resources -> resources.map { id -> ResourceReader1180(id) } },
                    prepareExecutor
                )
                .thenApplyAsync(
                    { resources -> resources.mapNotNull { resource -> loader.loadResource(resource, id) } },
                    prepareExecutor
                )

            val applyStart = loadedResources.thenCompose(synchronizer::whenPrepared)
                .thenCombineAsync(applyWait, { resources, _ -> resources }, applyExecutor)

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
                            "Resource loader `$loader` by mod `$id` threw an exception. Closing the loader and rethrowing",
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
                session.close()
                sessions -= session
                logger.info("Resources reloaded successfully")
            },
            applyExecutor
        )
    }

    private fun <T> IResourceLoader<T>.loadResource(resource: IResourceReader, modId: String): T? = try {
        loadResource(resource)
    } catch (e: IOException) {
        logger.warn(
            "Resource loader `$this` by mod `$modId` threw an exception trying to load `${resource.id}`. Continuing reload",
            e
        )
        null
    }
}
