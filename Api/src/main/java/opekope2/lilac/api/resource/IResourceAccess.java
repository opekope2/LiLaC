package opekope2.lilac.api.resource;

import net.minecraft.util.Identifier;
import opekope2.lilac.annotation.RequiresImplementation;
import opekope2.lilac.api.ILilacApi;
import org.jetbrains.annotations.NotNull;

/**
 * A wrapper interface to access Minecraft resources.
 */
public interface IResourceAccess {
    /**
     * Gets a representation of a Minecraft resource. The resource may not exist, it should be checked with
     * {@link IResourceReader#exists()}.
     * <br>
     *
     * @param resourcePath The path to the resource to get a reader of
     * @implNote If the resource doesn't exist, return an {@link IResourceReader} instance,
     * which returns {@code false} for {@link IResourceReader#exists()}.
     */
    @NotNull
    IResourceReader getResource(@NotNull Identifier resourcePath);

    /**
     * Returns the implementation of {@link IResourceAccess}.
     */
    @NotNull
    @RequiresImplementation
    static IResourceAccess getInstance() {
        return ILilacApi.getImplementation().getResourceAccess();
    }
}
