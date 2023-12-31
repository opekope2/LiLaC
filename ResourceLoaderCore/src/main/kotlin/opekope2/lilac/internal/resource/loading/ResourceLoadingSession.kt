package opekope2.lilac.internal.resource.loading

import net.fabricmc.api.ModInitializer
import opekope2.lilac.util.Util.*
import opekope2.lilac.api.resource.loading.IResourceLoader
import opekope2.lilac.api.resource.loading.IResourceLoadingSession
import opekope2.lilac.exception.EntrypointException

class ResourceLoadingSession : IResourceLoadingSession, AutoCloseable {
    private var currentModId: String? = null
    private var currentFactory: IResourceLoader.IFactory? = null

    var stage: IResourceLoadingSession.Stage = IResourceLoadingSession.Stage.INACTIVE
        set(value) {
            field = value
            lifecycleListeners.forEach { listener -> listener.onStageChanged(this) }
        }

    init {
        lifecycleListeners.forEach { listener -> listener.onCreated(this) }
    }

    fun createResourceLoader(modId: String, resourceLoaderFactory: IResourceLoader.IFactory): IResourceLoader =
        synchronized(lock) {
            currentModId = modId
            currentFactory = resourceLoaderFactory

            val loader = resourceLoaderFactory.createResourceLoader(this)

            currentModId = null
            currentFactory = null

            loader
        }

    override fun getExtension(modId: String): Any? {
        if (stage != IResourceLoadingSession.Stage.INIT) {
            throw IllegalStateException("LiLaC resource loading session extensions can only be obtained during the initialization stage. Current stage: `$stage`")
        }

        return sessionExtensionFactories[modId]?.createSessionExtension(
            currentModId ?: return null,
            currentFactory ?: return null,
            this
        )
    }

    override fun close() {
        stage = IResourceLoadingSession.Stage.INACTIVE
        lifecycleListeners.forEach { listener -> listener.onClosed(this) }
    }

    companion object : ModInitializer {
        private val lock = Any()

        val lifecycleListeners = getEntrypoints(IResourceLoadingSession.ILifecycleListener::class.java)

        lateinit var sessionExtensionFactories: Map<String, IResourceLoadingSession.IExtensionFactory>
            private set

        override fun onInitialize() {
            val factories = getEntrypointContainers(IResourceLoadingSession.IExtensionFactory::class.java)
                .groupBy { container -> container.provider.metadata.id }

            val excess = factories
                .filterValues { modFactories -> modFactories.size > 1 }
                .map { (key) -> key }

            if (excess.any()) {
                throw EntrypointException(
                    "Each mod can only provide up to one `${getEntrypointName(IResourceLoadingSession.IExtensionFactory::class.java)}` entrypoint. " +
                            "The following mod(s) declare more than one: " +
                            excess.joinToString(", ", prefix = "`", postfix = "`")
                )
            }

            sessionExtensionFactories = factories.mapValues { (_, containers) -> containers.first().entrypoint }
        }
    }
}
