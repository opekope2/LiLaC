package opekope2.lilac.impl

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.toast.ToastManager
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.resource.ResourceType
import opekope2.lilac.api.ILilacApi
import opekope2.lilac.api.dfu.IDataResultFactory
import opekope2.lilac.api.fabric.mod_json.ICustomMetadataSerializer
import opekope2.lilac.api.fabric.mod_json.ICustomValueFactory
import opekope2.lilac.api.gui.IDrawContext1180
import opekope2.lilac.api.gui.IDrawContext1200
import opekope2.lilac.api.gui.IToastManager
import opekope2.lilac.api.registry.IRegistryLookup
import opekope2.lilac.api.resource.IResourceAccess
import opekope2.lilac.api.resource.loading.IResourceLoadingSession
import opekope2.lilac.impl.gui.DummyNewDrawContextFactory
import opekope2.lilac.impl.gui.DummyOldDrawContextFactory
import opekope2.lilac.impl.mc_1_18.dfu.DataResultFactory1180
import opekope2.lilac.impl.mc_1_18.gui.DrawableHelperDrawContext1180
import opekope2.lilac.impl.mc_1_18.gui.ToastManager1180
import opekope2.lilac.impl.mc_1_18.registry.RegistryLookup1180
import opekope2.lilac.impl.mc_1_18.resource.ResourceReader1180
import opekope2.lilac.impl.mc_1_18.resource.loading.ResourceLoader1180
import opekope2.lilac.impl.mc_1_18_2.registry.RegistryLookup1182
import opekope2.lilac.impl.mc_1_19.resource.ResourceReader1190
import opekope2.lilac.impl.mc_1_19.resource.loading.ResourceLoader1190
import opekope2.lilac.impl.mc_1_19_1.gui.DrawableHelperDrawContext1191
import opekope2.lilac.impl.mc_1_19_3.registry.RegistryLookup1193
import opekope2.lilac.impl.mc_1_19_3.resource.ResourceReader1193
import opekope2.lilac.impl.mc_1_19_4.dfu.DataResultFactory1194
import opekope2.lilac.impl.mc_1_19_4.gui.DrawableHelperDrawContext1194
import opekope2.lilac.impl.mc_1_19_4.gui.ToastManager1194
import opekope2.lilac.impl.mc_1_20.gui.DrawContextWrapper1200
import opekope2.lilac.impl.mc_1_20_2.gui.DrawContextWrapper1202
import opekope2.lilac.impl.mc_1_20_3.gui.DrawContextWrapper1203
import opekope2.lilac.internal.fabric.mod_json.CustomMetadataSerializer
import opekope2.lilac.internal.fabric.mod_json.CustomValueFactory
import opekope2.lilac.internal.resource.loading.IResourceLoadingSessionHolder
import opekope2.lilac.util.Util.chooseByRequiredMinecraftVersion
import java.util.*

object LilacApi : ClientModInitializer, ILilacApi {
    private val resourceAccess = chooseByRequiredMinecraftVersion(
        ResourceReader1193.ResourceAccess::class.java,
        ResourceReader1190.ResourceAccess::class.java,
        ResourceReader1180.ResourceAccess::class.java
    )!!.kotlin.objectInstance!!
    private val registryLookup = chooseByRequiredMinecraftVersion(
        RegistryLookup1193::class.java,
        RegistryLookup1182::class.java,
        RegistryLookup1180::class.java
    )!!.kotlin.objectInstance!!
    private val customMetadataSerializer = CustomMetadataSerializer()
    private val customValueFactory = CustomValueFactory()
    private val resourceReloadListener: IdentifiableResourceReloadListener = chooseByRequiredMinecraftVersion(
        ResourceLoader1190::class.java,
        ResourceLoader1180::class.java
    )!!.kotlin.objectInstance!!
    private val resourceLoadingSessionHolder: IResourceLoadingSessionHolder =
        resourceReloadListener as IResourceLoadingSessionHolder
    private val dataResultFactory = chooseByRequiredMinecraftVersion(
        DataResultFactory1194::class.java,
        DataResultFactory1180::class.java
    )!!.kotlin.objectInstance!!

    private val oldDrawContextFactory = chooseByRequiredMinecraftVersion(
        DrawableHelperDrawContext1194.Factory::class.java,
        DrawableHelperDrawContext1191.Factory::class.java,
        DrawableHelperDrawContext1180.Factory::class.java,
        DummyOldDrawContextFactory::class.java
    )!!.kotlin.objectInstance!!
    private val newDrawContextFactory = chooseByRequiredMinecraftVersion(
        DrawContextWrapper1203.Factory::class.java,
        DrawContextWrapper1202.Factory::class.java,
        DrawContextWrapper1200.Factory::class.java,
        DummyNewDrawContextFactory::class.java
    )!!.kotlin.objectInstance!!

    private val toastManagerFactory = chooseByRequiredMinecraftVersion(
        ToastManager1194.Factory::class.java,
        ToastManager1180.Factory::class.java
    )!!.kotlin.objectInstance!!
    private val toastManagers = WeakHashMap<ToastManager, IToastManager>()

    override fun onInitializeClient() {
        (resourceLoadingSessionHolder as ClientModInitializer).onInitializeClient()
    }

    override fun isAvailable(): Boolean = true

    override fun getImplementationModId(): String = "lilac"

    override fun getResourceAccess(): IResourceAccess = resourceAccess

    override fun getRegistryLookup(): IRegistryLookup = registryLookup

    override fun getCustomMetadataSerializer(): ICustomMetadataSerializer = customMetadataSerializer

    override fun getCustomValueFactory(): ICustomValueFactory = customValueFactory

    override fun getResourceLoadingSessionProperties(session: IResourceLoadingSession): IResourceLoadingSession.IProperties =
        resourceLoadingSessionHolder.getResourceLoadingSessionProperties(session)

    override fun getDataResultFactory(): IDataResultFactory = dataResultFactory

    override fun createDrawContext(matrixStack: MatrixStack): IDrawContext1180 = oldDrawContextFactory(matrixStack)

    override fun createDrawContext(drawContext: DrawContext): IDrawContext1200 = newDrawContextFactory(drawContext)

    override fun getToastManager(toastManager: ToastManager): IToastManager =
        toastManagers.getOrPut(toastManager) { toastManagerFactory(toastManager) }
}
