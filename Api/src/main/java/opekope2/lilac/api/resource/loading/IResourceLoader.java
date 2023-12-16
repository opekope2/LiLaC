package opekope2.lilac.api.resource.loading;

import net.minecraft.util.Identifier;
import opekope2.lilac.annotation.EntrypointName;
import opekope2.lilac.api.resource.IResourceReader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;

/**
 * A wrapper interface around Minecraft and the Fabric Resource Loader API.
 *
 * @param <T> The type of the resource passed from {@link #loadResource(IResourceReader)} to {@link #processResource(Object)}
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
    @NotNull
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
    boolean canLoad(@NotNull String resourceName);

    /**
     * Returns whether the current resource loader can load a resource at the given path.
     *
     * @param resourcePath The path to the resource to load
     * @return {@code true}, if the resource can be loaded, {@code false} otherwise
     */
    boolean canLoad(@NotNull Identifier resourcePath);

    /**
     * Loads the given resource and returns its in-memory representation in the
     * <a href="https://fabricmc.net/wiki/tutorial:resource#prepare_stage">Prepare stage</a>.
     * <br>
     * This method may be called multiple times.
     *
     * @param resource The resource to be loaded
     * @return The loaded resource, which will be passed to {@link #processResource(Object)}, unless it's {@code null}
     * @throws IOException If Minecraft throws it
     * @implNote After reading the resource, its input stream should be closed with {@link InputStream#close()}
     * to avoid resource leaks.
     */
    @Nullable
    T loadResource(@NotNull IResourceReader resource) throws IOException;

    /**
     * Processes the loaded resource from {@link #loadResource(IResourceReader)} in the
     * <a href="https://fabricmc.net/wiki/tutorial:resource#apply_stage">Apply stage</a>.
     * <br>
     * This method may be called multiple times.
     *
     * @param resource The loaded resource.
     *                 This is always an object returned from {@link #loadResource(IResourceReader)}.
     *                 I can't generify it because of technical limitations
     */
    void processResource(@NotNull T resource);

    /**
     * Represents a LiLaC resource loader factory.
     */
    @EntrypointName("lilac-resourceloader-factory")
    interface IFactory {
        /**
         * Creates a new resource loader for the given resource loading session.
         * The session instance will be unique for each resource loading session.
         * <br>
         * This is called at the beginning of the resource loading process.
         * At the end of the resource loading process, {@link IResourceLoader#close()} is called.
         *
         * @param session The current resource loading session
         */
        @NotNull
        <T> IResourceLoader<T> createResourceLoader(@NotNull IResourceLoadingSession session);
    }
}
