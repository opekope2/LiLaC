package opekope2.lilac.api.resource;

import net.minecraft.util.Identifier;
import opekope2.lilac.api.ILilacApi;

/**
 * A wrapper interface to access Minecraft resources.
 */
public interface IResourceAccess {
    /**
     * Gets a representation of a Minecraft resource. The resource may not exist, it should be checked with
     * {@link IResourceReader#exists()}.
     * <br>
     * Note to implementors: if the resource doesn't exist, return an {@link IResourceReader} instance,
     * which returns {@code false} for {@link IResourceReader#exists()}.
     *
     * @param resourcePath The path to the resource to get a reader of
     */
    IResourceReader getResource(Identifier resourcePath);

    /**
     * Returns the implementation of {@link IResourceAccess}.
     */
    static IResourceAccess getInstance() {
        return ILilacApi.getImplementation().getImplementations().getResourceAccess();
    }
}
