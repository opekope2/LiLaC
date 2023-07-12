package opekope2.lilac.exception;

import net.minecraft.util.Identifier;
import opekope2.lilac.api.resource.IResource;
import opekope2.lilac.api.resource.IResourceReader;
import org.jetbrains.annotations.NotNull;

/**
 * Thrown when trying to access a resource, which doesn't exist.
 *
 * @see IResource#getResourcePack()
 * @see IResourceReader#getInputStream()
 */
public final class ResourceNotFoundException extends Exception {
    private final Identifier resource;

    /**
     * Creates a new {@link ResourceNotFoundException}.
     *
     * @param resource The resource ID, which was not found
     */
    public ResourceNotFoundException(@NotNull Identifier resource) {
        super("Resource '%s' doesn't exist.".formatted(resource));
        this.resource = resource;
    }

    /**
     * Returns the missing resource ID passed in the constructor.
     */
    @NotNull
    public Identifier getResourceId() {
        return resource;
    }
}
