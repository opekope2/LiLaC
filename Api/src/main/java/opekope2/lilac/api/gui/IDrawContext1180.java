package opekope2.lilac.api.gui;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import opekope2.lilac.annotation.RequiresImplementation;
import opekope2.lilac.annotation.RequiresMinecraftVersion;
import opekope2.lilac.api.ILilacApi;
import opekope2.lilac.util.MinecraftVersion;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

/**
 * A wrapper interface for Minecraft 1.18 DrawableHelper.
 * Its usage is similar to Minecraft 1.20 DrawContext, and has the same method names.
 *
 * @apiNote Before using deprecated methods, check for the current Minecraft version.
 * Different Minecraft versions may need different method calls.
 * The order of the methods' parameters reflect the latest supported version of Minecraft.
 */
public interface IDrawContext1180 {
    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_18)
    MatrixStack getMatrices();

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_18, maxVersion = MinecraftVersion.MINECRAFT_1_19_3)
    void drawCenteredText(TextRenderer textRenderer, String text, int centerX, int y, int color);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_18, maxVersion = MinecraftVersion.MINECRAFT_1_19_3)
    void drawCenteredText(TextRenderer textRenderer, Text text, int centerX, int y, int color);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_18)
    void drawCenteredTextWithShadow(TextRenderer textRenderer, OrderedText text, int centerX, int y, int color);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_18)
    void drawHorizontalLine(int x1, int x2, int y, int color);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_18)
    void drawSprite(int x, int y, int z, int width, int height, Sprite sprite);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_18, maxVersion = MinecraftVersion.MINECRAFT_1_19_3)
    void drawStringWithShadow(TextRenderer textRenderer, String text, int x, int y, int color);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_18, maxVersion = MinecraftVersion.MINECRAFT_1_19_4)
    void drawTexture(int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_18, maxVersion = MinecraftVersion.MINECRAFT_1_19_4)
    void drawTexture(int x, int y, int z, float u, float v, int width, int height, int textureWidth, int textureHeight);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_18, maxVersion = MinecraftVersion.MINECRAFT_1_19_4)
    void drawTexture(int x, int y, int width, int height, float u, float v, int regionWidth, int regionHeight, int textureWidth, int textureHeight);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_18)
    int drawTextWithShadow(TextRenderer textRenderer, Text text, int x, int y, int color);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_18)
    void drawVerticalLine(int x, int y1, int y2, int color);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_18, maxVersion = MinecraftVersion.MINECRAFT_1_19_4)
    void drawWithOutline(int x, int y, BiConsumer<Integer, Integer> renderAction);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_18, maxVersion = MinecraftVersion.MINECRAFT_1_19_3)
    void drawWithShadow(TextRenderer textRenderer, OrderedText text, int x, int y, int color);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_18)
    void fill(int x1, int y1, int x2, int y2, int color);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_18)
    void fillGradient(int startX, int startY, int endX, int endY, int z, int colorStart, int colorEnd);

    /**
     * Creates an instance of {@link IDrawContext1180}, which operates on the given matrix stack.
     *
     * @param matrixStack The first parameter of most {@code DrawableHelper} methods take
     */
    @NotNull
    @RequiresImplementation
    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_18, maxVersion = MinecraftVersion.MINECRAFT_1_19_4)
    static IDrawContext1180 create(@NotNull MatrixStack matrixStack) {
        return ILilacApi.getImplementation().createDrawContext(matrixStack);
    }
}
