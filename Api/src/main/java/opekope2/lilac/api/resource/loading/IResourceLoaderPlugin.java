package opekope2.lilac.api.resource.loading;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a LiLaC resource loader plugin.
 */
public interface IResourceLoaderPlugin {
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
    IResourceLoader createResourceLoader(@NotNull IResourceLoadingSession session);
}
