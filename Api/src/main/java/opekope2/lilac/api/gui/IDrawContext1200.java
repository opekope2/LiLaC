package opekope2.lilac.api.gui;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.TooltipPositioner;
import net.minecraft.client.item.TooltipData;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.OrderedText;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import opekope2.lilac.annotation.RequiresImplementation;
import opekope2.lilac.annotation.RequiresMinecraftVersion;
import opekope2.lilac.api.ILilacApi;
import opekope2.lilac.util.MinecraftVersion;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

/**
 * A wrapper interface for Minecraft 1.20 DrawContext.
 *
 * @see IDrawContext1180
 */
public interface IDrawContext1200 extends IDrawContext1194 {
    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    void draw();

    /**
     * @deprecated Deprecated in Minecraft's code
     */
    @Deprecated
    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    void draw(Runnable drawCallback);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    void drawHorizontalLine(RenderLayer layer, int x1, int x2, int y, int color);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    void drawHoverEvent(TextRenderer textRenderer, Style style, int x, int y);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    void drawItem(LivingEntity entity, ItemStack stack, int x, int y, int seed);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    void drawItem(ItemStack item, int x, int y);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    void drawItem(ItemStack stack, int x, int y, int seed);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    void drawItem(ItemStack stack, int x, int y, int seed, int z);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    void drawItemInSlot(TextRenderer textRenderer, ItemStack stack, int x, int y);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    void drawItemInSlot(TextRenderer textRenderer, ItemStack stack, int x, int y, String countOverride);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    void drawItemTooltip(TextRenderer textRenderer, ItemStack stack, int x, int y);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    void drawItemWithoutEntity(ItemStack stack, int x, int y);

    @Override
    @Deprecated(since = "Minecraft 1.20")
    default void drawNineSlicedTexture(int x, int y, int width, int height, int outerSliceSize, int centerSliceWidth, int centerSliceHeight, int u, int v) {
        throw new UnsupportedOperationException("This method is unavailable since Minecraft 1.20");
    }

    @Override
    @Deprecated(since = "Minecraft 1.20")
    default void drawNineSlicedTexture(int x, int y, int width, int height, int sideSliceWidth, int sideSliceHeight, int centerSliceWidth, int centerSliceHeight, int u, int v) {
        throw new UnsupportedOperationException("This method is unavailable since Minecraft 1.20");
    }

    @Override
    @Deprecated(since = "Minecraft 1.20")
    default void drawNineSlicedTexture(int x, int y, int width, int height, int leftSliceWidth, int topSliceHeight, int rightSliceWidth, int bottomSliceHeight, int centerSliceWidth, int centerSliceHeight, int u, int v) {
        throw new UnsupportedOperationException("This method is unavailable since Minecraft 1.20");
    }

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20, maxVersion = MinecraftVersion.MINECRAFT_1_20_1)
    void drawNineSlicedTexture(Identifier texture, int x, int y, int width, int height, int outerSliceSize, int centerSliceWidth, int centerSliceHeight, int u, int v);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20, maxVersion = MinecraftVersion.MINECRAFT_1_20_1)
    void drawNineSlicedTexture(Identifier texture, int x, int y, int width, int height, int outerSliceWidth, int outerSliceHeight, int centerSliceWidth, int centerSliceHeight, int u, int v);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20, maxVersion = MinecraftVersion.MINECRAFT_1_20_1)
    void drawNineSlicedTexture(Identifier texture, int x, int y, int width, int height, int leftSliceWidth, int topSliceHeight, int rightSliceWidth, int bottomSliceHeight, int centerSliceWidth, int centerSliceHeight, int u, int v);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    void drawOrderedTooltip(TextRenderer textRenderer, List<? extends OrderedText> text, int x, int y);

    @Override
    @Deprecated(since = "Minecraft 1.20")
    default void drawRepeatingTexture(int x, int y, int width, int height, int u, int v, int textureWidth, int textureHeight) {
        throw new UnsupportedOperationException("This method is unavailable since Minecraft 1.20");
    }

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20, maxVersion = MinecraftVersion.MINECRAFT_1_20_1)
    void drawRepeatingTexture(Identifier texture, int x, int y, int width, int height, int u, int v, int textureWidth, int textureHeight);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    int drawText(TextRenderer textRenderer, String text, int x, int y, int color, boolean shadow);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    int drawText(TextRenderer textRenderer, OrderedText text, int x, int y, int color, boolean shadow);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    int drawText(TextRenderer textRenderer, Text text, int x, int y, int color, boolean shadow);

    @Override
    @Deprecated(since = "Minecraft 1.20")
    default void drawTexture(int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {
        throw new UnsupportedOperationException("This method is unavailable since Minecraft 1.20");
    }

    @Override
    @Deprecated(since = "Minecraft 1.20")
    default void drawTexture(int x, int y, int z, float u, float v, int width, int height, int textureWidth, int textureHeight) {
        throw new UnsupportedOperationException("This method is unavailable since Minecraft 1.20");
    }

    @Override
    @Deprecated(since = "Minecraft 1.20")
    default void drawTexture(int x, int y, int width, int height, float u, float v, int regionWidth, int regionHeight, int textureWidth, int textureHeight) {
        throw new UnsupportedOperationException("This method is unavailable since Minecraft 1.20");
    }

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    void drawTexture(Identifier texture, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    void drawTexture(Identifier texture, int x, int y, int z, float u, float v, int width, int height, int textureWidth, int textureHeight);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    void drawTexture(Identifier texture, int x, int y, int width, int height, float u, float v, int regionWidth, int regionHeight, int textureWidth, int textureHeight);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    void drawTexture(Identifier texture, int x, int y, int u, int v, int width, int height);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    void drawTextWrapped(TextRenderer textRenderer, StringVisitable text, int x, int y, int width, int color);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    void drawTooltip(TextRenderer textRenderer, List<OrderedText> text, TooltipPositioner positioner, int x, int y);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    void drawTooltip(TextRenderer textRenderer, List<Text> text, int x, int y);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    void drawTooltip(TextRenderer textRenderer, List<Text> text, Optional<TooltipData> data, int x, int y);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    void drawTooltip(TextRenderer textRenderer, Text text, int x, int y);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    void drawVerticalLine(RenderLayer layer, int x, int y1, int y2, int color);

    @Override
    @Deprecated(since = "Minecraft 1.20")
    default void drawWithOutline(int x, int y, BiConsumer<Integer, Integer> renderAction) {
        throw new UnsupportedOperationException("This method is unavailable since Minecraft 1.20");
    }

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    void fill(RenderLayer layer, int x1, int y1, int x2, int y2, int color);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    void fill(RenderLayer layer, int x1, int y1, int x2, int y2, int z, int color);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    void fillGradient(int startX, int startY, int endX, int endY, int colorStart, int colorEnd);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    void fillGradient(RenderLayer layer, int startX, int startY, int endX, int endY, int colorStart, int colorEnd, int z);

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    int getScaledWindowHeight();

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    int getScaledWindowWidth();

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    VertexConsumerProvider.Immediate getVertexConsumers();

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    void setShaderColor(float red, float green, float blue, float alpha);

    /**
     * Creates an instance of {@link IDrawContext1200}, which wraps the given draw context.
     *
     * @param drawContext The backing draw context
     */
    @NotNull
    @RequiresImplementation
    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20)
    static IDrawContext1200 create(@NotNull DrawContext drawContext) {
        return ILilacApi.getImplementation().createDrawContext(drawContext);
    }
}
