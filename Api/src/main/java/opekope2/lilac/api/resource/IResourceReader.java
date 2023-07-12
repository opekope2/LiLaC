package opekope2.lilac.api.resource;

import opekope2.lilac.exception.ResourceNotFoundException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Represents a readable Minecraft resource.
 */
public interface IResourceReader extends IResource {
    /**
     * Returns the input stream the resource can be read from.
     * After using the resource, it should be closed with {@link InputStream#close()}.
     *
     * @throws IOException               If Minecraft throws it
     * @throws ResourceNotFoundException If the given resource doesn't exist. Check with {@link #exists()}
     */
    InputStream getInputStream() throws IOException, ResourceNotFoundException;
}
