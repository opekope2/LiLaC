package opekope2.lilac.impl.mc_1_18.gui

import net.minecraft.client.toast.Toast
import net.minecraft.client.toast.ToastManager
import net.minecraft.client.util.math.MatrixStack
import opekope2.lilac.annotation.RequiresMinecraftVersion
import opekope2.lilac.api.gui.IDrawContext1180
import opekope2.lilac.api.gui.IToast
import opekope2.lilac.api.gui.IToastManager
import opekope2.lilac.util.MinecraftVersion

class Toast1180(private val toast: IToast) : Toast {
    override fun getType(): Any = toast.type

    override fun getWidth(): Int = toast.width

    override fun getHeight(): Int = toast.height

    override fun draw(matrices: MatrixStack, manager: ToastManager, startTime: Long): Toast.Visibility {
        return toast.draw(
            IDrawContext1180.create(matrices),
            IToastManager.get(manager),
            startTime
        )
    }

    @RequiresMinecraftVersion(
        minVersion = MinecraftVersion.MINECRAFT_1_18,
        maxVersion = MinecraftVersion.MINECRAFT_1_19
    )
    companion object Factory : (IToast) -> Toast by ::Toast1180
}
