package opekope2.lilac.impl

import opekope2.lilac.api.ILilacApi
import opekope2.lilac.api.fabric.mod_json.ICustomMetadataSerializer
import opekope2.lilac.api.fabric.mod_json.ICustomValueFactory
import opekope2.lilac.api.registry.IRegistryLookup
import opekope2.lilac.api.resource.IResourceAccess
import opekope2.lilac.api.tick.ITickNotifier
import opekope2.lilac.impl.registry.RegistryLookup
import opekope2.lilac.impl.resource.ResourceReader
import opekope2.lilac.impl.tick.TickNotifier
import opekope2.lilac.internal.fabric.mod_json.CustomMetadataSerializer
import opekope2.lilac.internal.fabric.mod_json.CustomValueFactory

object LilacApi : ILilacApi {
    private val customValueFactory = CustomValueFactory()
    private val customMetadataSerializer = CustomMetadataSerializer()

    override fun isAvailable(): Boolean = true

    override fun getImplementationModId(): String = "lilac"

    override fun getResourceAccess(): IResourceAccess = ResourceReader

    override fun getRegistryLookup(): IRegistryLookup = RegistryLookup

    override fun getTickNotifier(): ITickNotifier = TickNotifier

    override fun getCustomMetadataSerializer(): ICustomMetadataSerializer = customMetadataSerializer

    override fun getCustomValueFactory(): ICustomValueFactory = customValueFactory
}
