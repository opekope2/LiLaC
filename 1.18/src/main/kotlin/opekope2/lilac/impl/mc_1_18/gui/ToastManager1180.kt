package opekope2.lilac.impl.mc_1_18.gui

import net.minecraft.client.MinecraftClient
import net.minecraft.client.toast.ToastManager
import opekope2.lilac.annotation.RequiresMinecraftVersion
import opekope2.lilac.api.gui.IToast
import opekope2.lilac.api.gui.IToastManager
import opekope2.lilac.impl.mc_1_19_1.gui.Toast1191
import opekope2.lilac.util.MinecraftVersion
import opekope2.lilac.util.Util.chooseByRequiredMinecraftVersion

class ToastManager1180(private val toastManager: ToastManager) : IToastManager {
    override fun add(toast: IToast) {
        toastManager.add(toastFactory(toast))
    }

    override fun clear() {
        toastManager.clear()
    }

    override fun getNotificationDisplayTimeMultiplier(): Double = 1.0

    override fun getClient(): MinecraftClient = toastManager.client

    @RequiresMinecraftVersion(
        minVersion = MinecraftVersion.MINECRAFT_1_18,
        maxVersion = MinecraftVersion.MINECRAFT_1_19_3
    )
    companion object Factory : (ToastManager) -> IToastManager by ::ToastManager1180 {
        private val toastFactory = chooseByRequiredMinecraftVersion(
            Toast1191.Factory::class.java,
            Toast1180.Factory::class.java
        )!!.kotlin.objectInstance!!
    }
}
