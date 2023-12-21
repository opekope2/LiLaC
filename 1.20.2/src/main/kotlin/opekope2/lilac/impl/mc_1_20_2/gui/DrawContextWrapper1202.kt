package opekope2.lilac.impl.mc_1_20_2.gui

import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.tooltip.TooltipPositioner
import net.minecraft.client.item.TooltipData
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.texture.Sprite
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.text.OrderedText
import net.minecraft.text.StringVisitable
import net.minecraft.text.Style
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import opekope2.lilac.annotation.RequiresMinecraftVersion
import opekope2.lilac.api.gui.IDrawContext1202
import opekope2.lilac.util.MinecraftVersion
import java.util.*

class DrawContextWrapper1202(private val drawContext: DrawContext) : IDrawContext1202 {
    override fun getMatrices(): MatrixStack = drawContext.matrices

    override fun drawCenteredTextWithShadow(
        textRenderer: TextRenderer?,
        text: String?,
        centerX: Int,
        y: Int,
        color: Int
    ) {
        drawContext.drawCenteredTextWithShadow(textRenderer, text, centerX, y, color)
    }

    override fun drawCenteredTextWithShadow(
        textRenderer: TextRenderer?,
        text: Text?,
        centerX: Int,
        y: Int,
        color: Int
    ) {
        drawContext.drawCenteredTextWithShadow(textRenderer, text, centerX, y, color)
    }

    override fun drawCenteredTextWithShadow(
        textRenderer: TextRenderer?,
        text: OrderedText?,
        centerX: Int,
        y: Int,
        color: Int
    ) {
        drawContext.drawCenteredTextWithShadow(textRenderer, text, centerX, y, color)
    }

    override fun drawHorizontalLine(layer: RenderLayer?, x1: Int, x2: Int, y: Int, color: Int) {
        drawContext.drawHorizontalLine(layer, x1, x2, y, color)
    }

    override fun drawHorizontalLine(x1: Int, x2: Int, y: Int, color: Int) {
        drawContext.drawHorizontalLine(x1, x2, y, color)
    }

    override fun drawSprite(
        x: Int,
        y: Int,
        z: Int,
        width: Int,
        height: Int,
        sprite: Sprite?,
        red: Float,
        green: Float,
        blue: Float,
        alpha: Float
    ) {
        drawContext.drawSprite(x, y, z, width, height, sprite, red, green, blue, alpha)
    }

    override fun drawSprite(x: Int, y: Int, z: Int, width: Int, height: Int, sprite: Sprite?) {
        drawContext.drawSprite(x, y, z, width, height, sprite)
    }

    override fun drawTexture(
        texture: Identifier?,
        x: Int,
        y: Int,
        u: Float,
        v: Float,
        width: Int,
        height: Int,
        textureWidth: Int,
        textureHeight: Int
    ) {
        drawContext.drawTexture(texture, x, y, u, v, width, height, textureWidth, textureHeight)
    }

    override fun drawTexture(
        texture: Identifier?,
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
        drawContext.drawTexture(texture, x, y, z, u, v, width, height, textureWidth, textureHeight)
    }

    override fun drawTexture(
        texture: Identifier?,
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
        drawContext.drawTexture(
            texture,
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

    override fun drawTexture(texture: Identifier?, x: Int, y: Int, u: Int, v: Int, width: Int, height: Int) {
        drawContext.drawTexture(texture, x, y, u, v, width, height)
    }

    override fun drawTextWithShadow(textRenderer: TextRenderer?, text: String?, x: Int, y: Int, color: Int): Int {
        return drawContext.drawTextWithShadow(textRenderer, text, x, y, color)
    }

    override fun drawTextWithShadow(textRenderer: TextRenderer?, text: OrderedText?, x: Int, y: Int, color: Int): Int {
        return drawContext.drawTextWithShadow(textRenderer, text, x, y, color)
    }

    override fun drawTextWithShadow(textRenderer: TextRenderer?, text: Text?, x: Int, y: Int, color: Int): Int {
        return drawContext.drawTextWithShadow(textRenderer, text, x, y, color)
    }

    override fun drawVerticalLine(layer: RenderLayer?, x: Int, y1: Int, y2: Int, color: Int) {
        drawContext.drawVerticalLine(layer, x, y1, y2, color)
    }

    override fun drawVerticalLine(x: Int, y1: Int, y2: Int, color: Int) {
        drawContext.drawVerticalLine(x, y1, y2, color)
    }

    override fun fill(layer: RenderLayer?, x1: Int, y1: Int, x2: Int, y2: Int, color: Int) {
        drawContext.fill(layer, x1, y1, x2, y2, color)
    }

    override fun fill(layer: RenderLayer?, x1: Int, y1: Int, x2: Int, y2: Int, z: Int, color: Int) {
        drawContext.fill(layer, x1, y1, x2, y2, z, color)
    }

    override fun fill(x1: Int, y1: Int, x2: Int, y2: Int, z: Int, color: Int) {
        drawContext.fill(x1, y1, x2, y2, z, color)
    }

    override fun fill(x1: Int, y1: Int, x2: Int, y2: Int, color: Int) {
        drawContext.fill(x1, y1, x2, y2, color)
    }

    override fun fillGradient(startX: Int, startY: Int, endX: Int, endY: Int, colorStart: Int, colorEnd: Int) {
        drawContext.fillGradient(startX, startY, endX, endY, colorStart, colorEnd)
    }

    override fun fillGradient(
        layer: RenderLayer?,
        startX: Int,
        startY: Int,
        endX: Int,
        endY: Int,
        colorStart: Int,
        colorEnd: Int,
        z: Int
    ) {
        drawContext.fillGradient(layer, startX, startY, endX, endY, colorStart, colorEnd, z)
    }

    override fun fillGradient(startX: Int, startY: Int, endX: Int, endY: Int, z: Int, colorStart: Int, colorEnd: Int) {
        drawContext.fillGradient(startX, startY, endX, endY, z, colorStart, colorEnd)
    }

    override fun disableScissor() {
        drawContext.disableScissor()
    }

    override fun enableScissor(x1: Int, y1: Int, x2: Int, y2: Int) {
        drawContext.enableScissor(x1, y1, x2, y2)
    }

    override fun drawBorder(x: Int, y: Int, width: Int, height: Int, color: Int) {
        drawContext.drawBorder(x, y, width, height, color)
    }

    override fun draw() {
        drawContext.draw()
    }

    @Deprecated("Deprecated in Minecraft's code")
    override fun draw(drawCallback: Runnable?) {
        drawContext.draw(drawCallback)
    }

    override fun drawHoverEvent(textRenderer: TextRenderer?, style: Style?, x: Int, y: Int) {
        drawContext.drawHoverEvent(textRenderer, style, x, y)
    }

    override fun drawItem(entity: LivingEntity?, stack: ItemStack?, x: Int, y: Int, seed: Int) {
        drawContext.drawItem(entity, stack, x, y, seed)
    }

    override fun drawItem(item: ItemStack?, x: Int, y: Int) {
        drawContext.drawItem(item, x, y)
    }

    override fun drawItem(stack: ItemStack?, x: Int, y: Int, seed: Int) {
        drawContext.drawItem(stack, x, y, seed)
    }

    override fun drawItem(stack: ItemStack?, x: Int, y: Int, seed: Int, z: Int) {
        drawContext.drawItem(stack, x, y, seed, z)
    }

    override fun drawItemInSlot(textRenderer: TextRenderer?, stack: ItemStack?, x: Int, y: Int) {
        drawContext.drawItemInSlot(textRenderer, stack, x, y)
    }

    override fun drawItemInSlot(
        textRenderer: TextRenderer?,
        stack: ItemStack?,
        x: Int,
        y: Int,
        countOverride: String?
    ) {
        drawContext.drawItemInSlot(textRenderer, stack, x, y, countOverride)
    }

    override fun drawItemTooltip(textRenderer: TextRenderer?, stack: ItemStack?, x: Int, y: Int) {
        drawContext.drawItemTooltip(textRenderer, stack, x, y)
    }

    override fun drawItemWithoutEntity(stack: ItemStack?, x: Int, y: Int) {
        drawContext.drawItemWithoutEntity(stack, x, y)
    }

    override fun drawOrderedTooltip(textRenderer: TextRenderer?, text: MutableList<out OrderedText>?, x: Int, y: Int) {
        drawContext.drawOrderedTooltip(textRenderer, text, x, y)
    }

    override fun drawText(
        textRenderer: TextRenderer?,
        text: String?,
        x: Int,
        y: Int,
        color: Int,
        shadow: Boolean
    ): Int {
        return drawContext.drawText(textRenderer, text, x, y, color, shadow)
    }

    override fun drawText(
        textRenderer: TextRenderer?,
        text: OrderedText?,
        x: Int,
        y: Int,
        color: Int,
        shadow: Boolean
    ): Int {
        return drawContext.drawText(textRenderer, text, x, y, color, shadow)
    }

    override fun drawText(textRenderer: TextRenderer?, text: Text?, x: Int, y: Int, color: Int, shadow: Boolean): Int {
        return drawContext.drawText(textRenderer, text, x, y, color, shadow)
    }

    override fun drawTextWrapped(
        textRenderer: TextRenderer?,
        text: StringVisitable?,
        x: Int,
        y: Int,
        width: Int,
        color: Int
    ) {
        drawContext.drawTextWrapped(textRenderer, text, x, y, width, color)
    }

    override fun drawTooltip(
        textRenderer: TextRenderer?,
        text: MutableList<OrderedText>?,
        positioner: TooltipPositioner?,
        x: Int,
        y: Int
    ) {
        drawContext.drawTooltip(textRenderer, text, positioner, x, y)
    }

    override fun drawTooltip(textRenderer: TextRenderer?, text: MutableList<Text>?, x: Int, y: Int) {
        drawContext.drawTooltip(textRenderer, text, x, y)
    }

    override fun drawTooltip(
        textRenderer: TextRenderer?,
        text: MutableList<Text>?,
        data: Optional<TooltipData>?,
        x: Int,
        y: Int
    ) {
        drawContext.drawTooltip(textRenderer, text, data, x, y)
    }

    override fun drawTooltip(textRenderer: TextRenderer?, text: Text?, x: Int, y: Int) {
        drawContext.drawTooltip(textRenderer, text, x, y)
    }

    override fun getScaledWindowHeight(): Int = drawContext.scaledWindowHeight

    override fun getScaledWindowWidth(): Int = drawContext.scaledWindowWidth

    override fun getVertexConsumers(): VertexConsumerProvider.Immediate = drawContext.vertexConsumers

    override fun setShaderColor(red: Float, green: Float, blue: Float, alpha: Float) {
        drawContext.setShaderColor(red, green, blue, alpha)
    }

    override fun drawGuiTexture(texture: Identifier?, x: Int, y: Int, width: Int, height: Int) {
        drawContext.drawGuiTexture(texture, x, y, width, height)
    }

    override fun drawGuiTexture(texture: Identifier?, x: Int, y: Int, z: Int, width: Int, height: Int) {
        drawContext.drawGuiTexture(texture, x, y, z, width, height)
    }

    override fun drawGuiTexture(
        texture: Identifier?,
        i: Int,
        j: Int,
        k: Int,
        l: Int,
        x: Int,
        y: Int,
        width: Int,
        height: Int
    ) {
        drawContext.drawGuiTexture(texture, i, j, k, l, x, y, width, height)
    }

    override fun drawGuiTexture(
        texture: Identifier?,
        i: Int,
        j: Int,
        k: Int,
        l: Int,
        x: Int,
        y: Int,
        z: Int,
        width: Int,
        height: Int
    ) {
        drawContext.drawGuiTexture(texture, i, j, k, l, x, y, z, width, height)
    }

    @RequiresMinecraftVersion(
        minVersion = MinecraftVersion.MINECRAFT_1_20_2,
        maxVersion = MinecraftVersion.MINECRAFT_1_20_2
    )
    companion object Factory : (DrawContext) -> IDrawContext1202 by ::DrawContextWrapper1202
}
