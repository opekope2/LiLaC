package opekope2.lilac.api.gui;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import opekope2.lilac.annotation.RequiresMinecraftVersion;
import opekope2.lilac.util.MinecraftVersion;

/**
 * A wrapper interface for Minecraft 1.19.4 DrawableHelper.
 *
 * @see IDrawContext1180
 */
public interface IDrawContext1194 extends IDrawContext1191 {
    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_19_4)
    void drawBorder(int x, int y, int width, int height, int color);

    @Override
    @Deprecated(since = "Minecraft 1.19.4")
    default void drawCenteredText(TextRenderer textRenderer, String text, int centerX, int y, int color) {
        throw new UnsupportedOperationException("This method is unavailable since Minecraft 1.19.4");
    }

    @Override
    @Deprecated(since = "Minecraft 1.19.4")
    default void drawCenteredText(TextRenderer textRenderer, Text text, int centerX, int y, int color) {
        throw new UnsupportedOperationException("This method is unavailable since Minecraft 1.19.4");
    }

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_19_4)
    void drawCenteredTextWithShadow(TextRenderer textRenderer, String text, int centerX, int y, int color);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_19_4)
    void drawCenteredTextWithShadow(TextRenderer textRenderer, Text text, int centerX, int y, int color);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_19_4, maxVersion = MinecraftVersion.MINECRAFT_1_19_4)
    void drawNineSlicedTexture(int x, int y, int width, int height, int outerSliceSize, int centerSliceWidth, int centerSliceHeight, int u, int v);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_19_4, maxVersion = MinecraftVersion.MINECRAFT_1_19_4)
    void drawNineSlicedTexture(int x, int y, int width, int height, int sideSliceWidth, int sideSliceHeight, int centerSliceWidth, int centerSliceHeight, int u, int v);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_19_4, maxVersion = MinecraftVersion.MINECRAFT_1_19_4)
    void drawNineSlicedTexture(int x, int y, int width, int height, int leftSliceWidth, int topSliceHeight, int rightSliceWidth, int bottomSliceHeight, int centerSliceWidth, int centerSliceHeight, int u, int v);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_19_4, maxVersion = MinecraftVersion.MINECRAFT_1_19_4)
    void drawRepeatingTexture(int x, int y, int width, int height, int u, int v, int textureWidth, int textureHeight);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_19_4)
    void drawSprite(int x, int y, int z, int width, int height, Sprite sprite, float red, float green, float blue, float alpha);

    @Override
    @Deprecated(since = "Minecraft 1.19.4")
    default void drawStringWithShadow(TextRenderer textRenderer, String text, int x, int y, int color) {
        throw new UnsupportedOperationException("This method is unavailable since Minecraft 1.19.4");
    }

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_19_4)
    int drawTextWithShadow(TextRenderer textRenderer, String text, int x, int y, int color);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_19_4)
    int drawTextWithShadow(TextRenderer textRenderer, OrderedText text, int x, int y, int color);

    @Override
    @Deprecated(since = "Minecraft 1.19.4")
    default void drawWithShadow(TextRenderer textRenderer, OrderedText text, int x, int y, int color) {
        throw new UnsupportedOperationException("This method is unavailable since Minecraft 1.19.4");
    }

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_19_4)
    void fill(int x1, int y1, int x2, int y2, int z, int color);
}
