package opekope2.lilac.impl.mc_1_19_1.gui

import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.DrawableHelper
import net.minecraft.client.texture.Sprite
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.OrderedText
import net.minecraft.text.Text
import opekope2.lilac.annotation.RequiresMinecraftVersion
import opekope2.lilac.api.gui.IDrawContext1180
import opekope2.lilac.api.gui.IDrawContext1191
import opekope2.lilac.util.MinecraftVersion
import java.util.function.BiConsumer

class DrawableHelperDrawContext1191(private val matrices: MatrixStack) : IDrawContext1191 {
    override fun getMatrices(): MatrixStack = matrices

    override fun disableScissor() {
        DrawableHelper.disableScissor()
    }

    override fun enableScissor(x1: Int, y1: Int, x2: Int, y2: Int) {
        DrawableHelper.enableScissor(x1, y1, x2, y2)
    }

    override fun drawCenteredText(textRenderer: TextRenderer?, text: String?, centerX: Int, y: Int, color: Int) {
        DrawableHelper.drawCenteredText(matrices, textRenderer, text, centerX, y, color)
    }

    override fun drawCenteredText(textRenderer: TextRenderer?, text: Text?, centerX: Int, y: Int, color: Int) {
        DrawableHelper.drawCenteredText(matrices, textRenderer, text, centerX, y, color)
    }

    override fun drawCenteredTextWithShadow(
        textRenderer: TextRenderer?,
        text: OrderedText?,
        centerX: Int,
        y: Int,
        color: Int
    ) {
        DrawableHelper.drawCenteredTextWithShadow(matrices, textRenderer, text, centerX, y, color)
    }

    override fun drawHorizontalLine(x1: Int, x2: Int, y: Int, color: Int) {
        DrawableHelperWrapper.drawHorizontalLine(matrices, x1, x2, y, color)
    }

    override fun drawSprite(x: Int, y: Int, z: Int, width: Int, height: Int, sprite: Sprite?) {
        DrawableHelper.drawSprite(matrices, x, y, z, width, height, sprite)
    }

    override fun drawStringWithShadow(textRenderer: TextRenderer?, text: String?, x: Int, y: Int, color: Int) {
        DrawableHelper.drawStringWithShadow(matrices, textRenderer, text, x, y, color)
    }

    override fun drawTexture(
        x: Int,
        y: Int,
        u: Float,
        v: Float,
        width: Int,
        height: Int,
        textureWidth: Int,
        textureHeight: Int
    ) {
        DrawableHelper.drawTexture(matrices, x, y, u, v, width, height, textureWidth, textureHeight)
    }

    override fun drawTexture(
        x: Int,
        y: Int,
        z: Int,
        u: Float,
        v: Float,
        width: Int,
        height: Int,
        textureWidth: Int,
        textureHeight: Int
    ) {
        DrawableHelper.drawTexture(matrices, x, y, z, u, v, width, height, textureWidth, textureHeight)
    }

    override fun drawTexture(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        u: Float,
        v: Float,
        regionWidth: Int,
        regionHeight: Int,
        textureWidth: Int,
        textureHeight: Int
    ) {
        DrawableHelper.drawTexture(
            matrices,
            x,
            y,
            width,
            height,
            u,
            v,
            regionWidth,
            regionHeight,
            textureWidth,
            textureHeight
        )
    }

    override fun drawTextWithShadow(textRenderer: TextRenderer, text: Text?, x: Int, y: Int, color: Int): Int {
        return textRenderer.drawWithShadow(matrices, text, x.toFloat(), y.toFloat(), color)
    }

    override fun drawVerticalLine(x: Int, y1: Int, y2: Int, color: Int) {
        DrawableHelperWrapper.drawVerticalLine(matrices, x, y1, y2, color)
    }

    override fun drawWithOutline(x: Int, y: Int, renderAction: BiConsumer<Int, Int>?) {
        DrawableHelperWrapper.drawWithOutline(x, y, renderAction)
    }

    override fun drawWithShadow(textRenderer: TextRenderer?, text: OrderedText?, x: Int, y: Int, color: Int) {
        DrawableHelper.drawWithShadow(matrices, textRenderer, text, x, y, color)
    }

    override fun fill(x1: Int, y1: Int, x2: Int, y2: Int, color: Int) {
        DrawableHelper.fill(matrices, x1, y1, x2, y2, color)
    }

    override fun fillGradient(startX: Int, startY: Int, endX: Int, endY: Int, z: Int, colorStart: Int, colorEnd: Int) {
        DrawableHelperWrapper.fillGradientWrapper(matrices, startX, startY, endX, endY, colorStart, colorEnd, z)
    }

    // If this is companion, build fails because DrawableHelper is not available in :LiLaC
    private object DrawableHelperWrapper : DrawableHelper() {
        // Wrapper suffix to avoid "accidental override"
        fun fillGradientWrapper(
            matrices: MatrixStack,
            startX: Int,
            startY: Int,
            endX: Int,
            endY: Int,
            z: Int,
            colorStart: Int,
            colorEnd: Int
        ) {
            fillGradient(matrices, startX, startY, endX, endY, colorStart, colorEnd, z)
        }

        public override fun drawHorizontalLine(matrices: MatrixStack?, x1: Int, x2: Int, y: Int, color: Int) {
            super.drawHorizontalLine(matrices, x1, x2, y, color)
        }

        public override fun drawVerticalLine(matrices: MatrixStack?, x: Int, y1: Int, y2: Int, color: Int) {
            super.drawVerticalLine(matrices, x, y1, y2, color)
        }
    }

    @RequiresMinecraftVersion(
        minVersion = MinecraftVersion.MINECRAFT_1_19_1,
        maxVersion = MinecraftVersion.MINECRAFT_1_19_3
    )
    companion object Factory : (MatrixStack) -> IDrawContext1180 by ::DrawableHelperDrawContext1191
}
