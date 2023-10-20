package opekope2.lilac.impl

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.loader.api.FabricLoader
import net.fabricmc.loader.api.Version
import opekope2.lilac.api.ILilacApi
import opekope2.lilac.api.dfu.IDataResultFactory
import opekope2.lilac.api.fabric.mod_json.ICustomMetadataSerializer
import opekope2.lilac.api.fabric.mod_json.ICustomValueFactory
import opekope2.lilac.api.registry.IRegistryLookup
import opekope2.lilac.api.resource.IResourceAccess
import opekope2.lilac.api.resource.loading.IResourceLoadingSession
import opekope2.lilac.api.tick.ITickNotifier
import opekope2.lilac.impl.tick.ClientTickNotifier
import opekope2.lilac.impl.tick.ServerTickNotifier
import opekope2.lilac.internal.fabric.mod_json.CustomMetadataSerializer
import opekope2.lilac.internal.fabric.mod_json.CustomValueFactory
import opekope2.lilac.internal.resource.loading.IResourceLoadingSessionHolder
import opekope2.lilac.util.Util.checkModVersion
import opekope2.lilac.impl.mc_1_18.dfu.DataResultFactory as DataResultFactory1180
import opekope2.lilac.impl.mc_1_18.registry.RegistryLookup as RegistryLookup1180
import opekope2.lilac.impl.mc_1_18.resource.ResourceReader as ResourceReader1180
import opekope2.lilac.impl.mc_1_18.resource.loading.ResourceLoader as ResourceLoader1180
import opekope2.lilac.impl.mc_1_18_2.registry.RegistryLookup as RegistryLookup1182
import opekope2.lilac.impl.mc_1_19.resource.ResourceReader as ResourceReader1190
import opekope2.lilac.impl.mc_1_19.resource.loading.ResourceLoader as ResourceLoader1190
import opekope2.lilac.impl.mc_1_19_3.registry.RegistryLookup as RegistryLookup1193
import opekope2.lilac.impl.mc_1_19_3.resource.ResourceReader as ResourceReader1193
import opekope2.lilac.impl.mc_1_19_4.dfu.DataResultFactory as DataResultFactory1194

object LilacApi : ClientModInitializer, ILilacApi {
    private val resourceAccess = when {
        checkModVersion("minecraft") { it >= Version.parse("1.19.3") } -> ResourceReader1193.ResourceAccess
        checkModVersion("minecraft") { it >= Version.parse("1.19") } -> ResourceReader1190.ResourceAccess
        else -> ResourceReader1180.ResourceAccess
    }
    private val registryLookup = when {
        checkModVersion("minecraft") { it >= Version.parse("1.19.3") } -> RegistryLookup1193
        checkModVersion("minecraft") { it >= Version.parse("1.18.2") } -> RegistryLookup1182
        else -> RegistryLookup1180
    }
    private val tickNotifier: ITickNotifier =
        if (FabricLoader.getInstance().environmentType == EnvType.SERVER) ServerTickNotifier
        else ClientTickNotifier
    private val customMetadataSerializer = CustomMetadataSerializer()
    private val customValueFactory = CustomValueFactory()
    private val resourceLoadingSessionHolder: IResourceLoadingSessionHolder =
        if (checkModVersion("minecraft") { it >= Version.parse("1.19") }) ResourceLoader1190
        else ResourceLoader1180
    private val dataResultFactory =
        if (checkModVersion("minecraft") { it >= Version.parse("1.19.4") }) DataResultFactory1194()
        else DataResultFactory1180()

    override fun onInitializeClient() {
        (resourceLoadingSessionHolder as ClientModInitializer).onInitializeClient()
    }

    override fun isAvailable(): Boolean = true

    override fun getImplementationModId(): String = "lilac"

    override fun getResourceAccess(): IResourceAccess = resourceAccess

    override fun getRegistryLookup(): IRegistryLookup = registryLookup

    override fun getTickNotifier(): ITickNotifier = tickNotifier

    override fun getCustomMetadataSerializer(): ICustomMetadataSerializer = customMetadataSerializer

    override fun getCustomValueFactory(): ICustomValueFactory = customValueFactory

    override fun getResourceLoadingSessionProperties(session: IResourceLoadingSession): IResourceLoadingSession.IProperties =
        resourceLoadingSessionHolder.getResourceLoadingSessionProperties(session)

    override fun getDataResultFactory(): IDataResultFactory = dataResultFactory
}
