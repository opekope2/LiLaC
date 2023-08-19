package opekope2.lilac.internal.resource.loading

import net.fabricmc.api.ModInitializer
import opekope2.lilac.api.Util.getEntrypointContainers
import opekope2.lilac.api.Util.getEntrypointName
import opekope2.lilac.api.resource.loading.IResourceLoader
import opekope2.lilac.api.resource.loading.IResourceLoaderPlugin
import opekope2.lilac.api.resource.loading.IResourceLoadingSession
import opekope2.lilac.exception.EntrypointException

class ResourceLoadingSession : IResourceLoadingSession {
    private var currentModId: String? = null
    private var currentPlugin: IResourceLoaderPlugin? = null

    fun createResourceLoader(modId: String, resourceLoaderPlugin: IResourceLoaderPlugin): IResourceLoader {
        currentModId = modId
        currentPlugin = resourceLoaderPlugin

        val loader = resourceLoaderPlugin.createResourceLoader(this)

        currentModId = null
        currentPlugin = null

        return loader
    }

    override fun getExtension(modId: String): Any? {
        return sessionExtensionFactories[modId]?.createSessionExtension(
            currentModId ?: return null,
            currentPlugin ?: return null,
            this
        )
    }

    companion object : ModInitializer {
        lateinit var sessionExtensionFactories: Map<String, IResourceLoadingSession.IExtensionFactoryPlugin>
            private set

        override fun onInitialize() {
            val factories = getEntrypointContainers(IResourceLoadingSession.IExtensionFactoryPlugin::class.java)
                .groupBy { container -> container.provider.metadata.id }

            val excess = factories
                .filterValues { modFactories -> modFactories.size > 1 }
                .map { (key) -> key }

            if (excess.any()) {
                throw EntrypointException(
                    "Each mod can only provide up to one ${getEntrypointName(IResourceLoadingSession.IExtensionFactoryPlugin::class.java)} entrypoint. " +
                            "The following mod(s) declare more than one: ${excess.joinToString(", ")}"
                )
            }

            sessionExtensionFactories = factories.mapValues { (_, containers) -> containers.first().entrypoint }
        }
    }
}