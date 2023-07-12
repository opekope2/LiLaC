package opekope2.lilac.api.resource;

import net.minecraft.resource.ResourcePack;

/**
 * A wrapper interface for Minecraft's ResourcePack class.
 */
public interface IResourcePack {
    /**
     * Gets the name of the resource pack.
     */
    String getName();

    /**
     * Returns the original Minecraft Resource Pack instance.
     *
     * @deprecated A minecraft update may break your code if you use this method
     */
    @Deprecated
    ResourcePack getResourcePack();
}
