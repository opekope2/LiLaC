package opekope2.lilac.impl.gui

import net.minecraft.client.gui.DrawContext
import opekope2.lilac.annotation.RequiresMinecraftVersion
import opekope2.lilac.api.gui.IDrawContext1200
import opekope2.lilac.util.MinecraftVersion

@RequiresMinecraftVersion(
    minVersion = MinecraftVersion.MINECRAFT_1_18,
    maxVersion = MinecraftVersion.MINECRAFT_1_19_4
)
internal object DummyNewDrawContextFactory : (DrawContext) -> IDrawContext1200 {
    override fun invoke(drawContext: DrawContext): Nothing {
        throw UnsupportedOperationException("${IDrawContext1200::class.java.name} creation from DrawContext is not supported before Minecraft 1.20. How did you get here?")
    }
}
