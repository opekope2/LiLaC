package opekope2.lilac.api.gui;

import opekope2.lilac.annotation.RequiresMinecraftVersion;
import opekope2.lilac.util.MinecraftVersion;

/**
 * A wrapper interface for Minecraft 1.19.1 DrawableHelper.
 *
 * @see IDrawContext1180
 */
public interface IDrawContext1191 extends IDrawContext1180 {
    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_19_1)
    void disableScissor();

    @RequiresMinecraftVersion(minVersion = MinecraftVersion.MINECRAFT_1_19_1)
    void enableScissor(int x1, int y1, int x2, int y2);
}
