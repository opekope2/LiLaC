package opekope2.lilac.api.gui;

import net.minecraft.item.ItemStack;
import opekope2.lilac.annotation.RequiresMinecraftVersion;
import opekope2.lilac.util.MinecraftVersion;

/**
 * A wrapper interface for Minecraft 1.20.3 DrawContext.
 *
 * @see IDrawContext1180
 */
public interface IDrawContext1203 extends IDrawContext1202 {
    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_20_3)
    void drawItemWithoutEntity(ItemStack stack, int x, int y, int seed);
}
