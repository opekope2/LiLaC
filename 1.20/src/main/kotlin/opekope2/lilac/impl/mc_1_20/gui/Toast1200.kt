package opekope2.lilac.impl.mc_1_20.gui

import net.minecraft.client.gui.DrawContext
import net.minecraft.client.toast.Toast
import net.minecraft.client.toast.ToastManager
import opekope2.lilac.annotation.RequiresMinecraftVersion
import opekope2.lilac.api.gui.IDrawContext1200
import opekope2.lilac.api.gui.IToast
import opekope2.lilac.api.gui.IToastManager
import opekope2.lilac.util.MinecraftVersion

class Toast1200(private val toast: IToast) : Toast {
    override fun getType(): Any = toast.type

    override fun getWidth(): Int = toast.width

    override fun getHeight(): Int = toast.height

    override fun getRequiredSpaceCount(): Int = toast.requiredSpaceCount

    override fun draw(context: DrawContext, manager: ToastManager, startTime: Long): Toast.Visibility {
        return toast.draw(
            IDrawContext1200.create(context),
            IToastManager.get(manager),
            startTime
        )
    }

    @RequiresMinecraftVersion(
        minVersion = MinecraftVersion.MINECRAFT_1_20,
    )
    companion object Factory : (IToast) -> Toast by ::Toast1200
}
