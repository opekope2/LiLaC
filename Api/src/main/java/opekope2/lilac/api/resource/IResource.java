package opekope2.lilac.api.resource;

import net.minecraft.util.Identifier;
import opekope2.lilac.exception.ResourceNotFoundException;
import org.jetbrains.annotations.NotNull;

/**
 * A wrapper interface for Minecraft's Resource class.
 */
public interface IResource {
    /**
     * Returns whether the given resource exists.
     */
    boolean exists();

    /**
     * Returns the ID of the given resource.
     */
    @NotNull
    Identifier getId();

    /**
     * Returns the resource pack the given resource is loaded from.
     * The loading order is specified in Minecraft Resource Pack settings.
     *
     * @throws ResourceNotFoundException If the given resource doesn't exist. Check with {@link #exists()}
     */
    @NotNull
    IResourcePack getResourcePack() throws ResourceNotFoundException;
}
