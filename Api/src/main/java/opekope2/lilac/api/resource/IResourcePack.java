package opekope2.lilac.api.resource;

import net.minecraft.resource.ResourcePack;
import org.jetbrains.annotations.NotNull;

/**
 * A wrapper interface for Minecraft's ResourcePack class.
 */
public interface IResourcePack {
    /**
     * Gets the name of the resource pack.
     */
    @NotNull
    String getName();

    /**
     * Returns the original Minecraft Resource Pack instance.
     *
     * @deprecated A minecraft update may break your code if you use this method
     */
    @Deprecated
    @NotNull
    ResourcePack getResourcePack();
}
