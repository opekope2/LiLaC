package opekope2.lilac.api.resource.loading;

import net.minecraft.util.Identifier;
import opekope2.lilac.api.resource.IResourceReader;

import java.io.IOException;
import java.io.InputStream;

/**
 * A wrapper interface around Minecraft and the Fabric Resource Loader API.
 *
 * @param <T> The type of the loaded resource returned by {@link #loadResource(IResourceReader)}
 * @see net.fabricmc.fabric.api.resource
 */
public interface IResourceLoader<T> extends AutoCloseable {
    /**
     * Returns the starting folder where the resource search should be started.
     * <br>
     * For example, the return value {@code "textures"} represents {@code /assets/?/textures} folders,
     * where {@code ?} may be any folder (it's the namespace of the resource to be loaded).
     * Further filtering takes place in {@link #canLoad(Identifier)}.
     */
    String getStartingPath();

    /**
     * Returns whether the current resource loader can load a resource with a given file name.
     * Called only on Minecraft 1.18.2 or lower.
     * Further filtering takes place in {@link #canLoad(Identifier)}.
     *
     * @param resourceName The file name of the resource. <br>
     *                     For example, the file name of {@code /assets/minecraft/textures/texture.png} is {@code texture.png}
     * @return {@code true}, if the resource may be loaded, and should be checked with its full path, {@code false} otherwise
     * @see #canLoad(Identifier)
     */
    @Deprecated
    boolean canLoad(String resourceName);

    /**
     * Returns whether the current resource loader can load a resource at the given path.
     *
     * @param resourcePath The path to the resource to load
     * @return {@code true}, if the resource can be loaded, {@code false} otherwise
     */
    boolean canLoad(Identifier resourcePath);

    /**
     * Loads the given resource and returns its in-memory representation in the
     * <a href="https://fabricmc.net/wiki/tutorial:resource">Prepare stage</a>.
     * <br>
     * Note to implementors: after reading the resource, its input stream should be closed with {@link InputStream#close()}
     * to avoid resource leaks.
     *
     * @param resource The resource to be loaded
     * @return The loaded resource, which will be passed to {@link #processResource(Object)}
     * @throws IOException If Minecraft throws it
     */
    T loadResource(IResourceReader resource) throws IOException;

    /**
     * Processes the loaded resource from {@link #loadResource(IResourceReader)} in the
     * <a href="https://fabricmc.net/wiki/tutorial:resource">Apply stage</a>.
     *
     * @param resource The loaded resource
     */
    void processResource(T resource);
}
