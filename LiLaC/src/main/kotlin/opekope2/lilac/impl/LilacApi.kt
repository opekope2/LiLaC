package opekope2.lilac.impl

import net.fabricmc.api.ClientModInitializer
import opekope2.lilac.api.ILilacApi
import opekope2.lilac.api.dfu.IDataResultFactory
import opekope2.lilac.api.fabric.mod_json.ICustomMetadataSerializer
import opekope2.lilac.api.fabric.mod_json.ICustomValueFactory
import opekope2.lilac.api.registry.IRegistryLookup
import opekope2.lilac.api.resource.IResourceAccess
import opekope2.lilac.api.resource.loading.IResourceLoadingSession
import opekope2.lilac.api.tick.ITickNotifier
import opekope2.lilac.impl.tick.TickNotifier
import opekope2.lilac.internal.fabric.mod_json.CustomMetadataSerializer
import opekope2.lilac.internal.fabric.mod_json.CustomValueFactory
import opekope2.lilac.impl.mc_1_19_4.dfu.DataResultFactory as DataResultFactory1194
import opekope2.lilac.impl.mc_1_19_4.registry.RegistryLookup as RegistryLookup1194
import opekope2.lilac.impl.mc_1_19_4.resource.ResourceReader.ResourceAccess as ResourceAccess1194
import opekope2.lilac.impl.mc_1_19_4.resource.loading.ResourceLoader as ResourceLoader1194

object LilacApi : ClientModInitializer, ILilacApi {
    private val customValueFactory = CustomValueFactory()
    private val customMetadataSerializer = CustomMetadataSerializer()
    private val dataResultFactory = DataResultFactory1194()

    override fun onInitializeClient() {
        ResourceLoader1194.onInitializeClient()
    }

    override fun isAvailable(): Boolean = true

    override fun getImplementationModId(): String = "lilac"

    override fun getResourceAccess(): IResourceAccess = ResourceAccess1194

    override fun getRegistryLookup(): IRegistryLookup = RegistryLookup1194

    override fun getTickNotifier(): ITickNotifier = TickNotifier

    override fun getCustomMetadataSerializer(): ICustomMetadataSerializer = customMetadataSerializer

    override fun getCustomValueFactory(): ICustomValueFactory = customValueFactory

    override fun getResourceLoadingSessionProperties(session: IResourceLoadingSession): IResourceLoadingSession.IProperties =
        ResourceLoader1194.getResourceLoadingSessionProperties(session)

    override fun getDataResultFactory(): IDataResultFactory = dataResultFactory
}
