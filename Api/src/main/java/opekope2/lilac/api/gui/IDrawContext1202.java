package opekope2.lilac.api.gui;

import net.minecraft.util.Identifier;
import opekope2.lilac.annotation.RequiresMinecraftVersion;
import opekope2.lilac.util.MinecraftVersion;

/**
 * A wrapper interface for Minecraft 1.20.2 DrawContext.
 *
 * @see IDrawContext1180
 */
public interface IDrawContext1202 extends IDrawContext1200 {
    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20_2)
    void drawGuiTexture(Identifier texture, int x, int y, int width, int height);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20_2)
    void drawGuiTexture(Identifier texture, int x, int y, int z, int width, int height);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20_2)
    void drawGuiTexture(Identifier texture, int i, int j, int k, int l, int x, int y, int width, int height);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20_2)
    void drawGuiTexture(Identifier texture, int i, int j, int k, int l, int x, int y, int z, int width, int height);

    @Override
    @Deprecated(since = "Minecraft 1.20.2")
    default void drawNineSlicedTexture(Identifier texture, int x, int y, int width, int height, int outerSliceSize, int centerSliceWidth, int centerSliceHeight, int u, int v) {
        throw new UnsupportedOperationException("This method is unavailable since Minecraft 1.20.2");
    }

    @Override
    @Deprecated(since = "Minecraft 1.20.2")
    default void drawNineSlicedTexture(Identifier texture, int x, int y, int width, int height, int outerSliceWidth, int outerSliceHeight, int centerSliceWidth, int centerSliceHeight, int u, int v) {
        throw new UnsupportedOperationException("This method is unavailable since Minecraft 1.20.2");
    }

    @Override
    @Deprecated(since = "Minecraft 1.20.2")
    default void drawNineSlicedTexture(Identifier texture, int x, int y, int width, int height, int leftSliceWidth, int topSliceHeight, int rightSliceWidth, int bottomSliceHeight, int centerSliceWidth, int centerSliceHeight, int u, int v) {
        throw new UnsupportedOperationException("This method is unavailable since Minecraft 1.20.2");
    }

    @Override
    @Deprecated(since = "Minecraft 1.20.2")
    default void drawRepeatingTexture(Identifier texture, int x, int y, int width, int height, int u, int v, int textureWidth, int textureHeight) {
        throw new UnsupportedOperationException("This method is unavailable since Minecraft 1.20.2");
    }
}
