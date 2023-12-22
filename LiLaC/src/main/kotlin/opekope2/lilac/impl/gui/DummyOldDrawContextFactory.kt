package opekope2.lilac.impl.gui

import net.minecraft.client.util.math.MatrixStack
import opekope2.lilac.annotation.RequiresMinecraftVersion
import opekope2.lilac.api.gui.IDrawContext1180
import opekope2.lilac.util.MinecraftVersion

@RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
internal object DummyOldDrawContextFactory : (MatrixStack) -> IDrawContext1180 {
    override fun invoke(matrixStack: MatrixStack): Nothing {
        throw UnsupportedOperationException("${IDrawContext1180::class.java.name} creation from MatrixStack is not supported on Minecraft 1.20+")
    }
}
