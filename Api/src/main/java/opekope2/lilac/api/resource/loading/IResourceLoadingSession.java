package opekope2.lilac.api.resource.loading;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a resource loading session.
 *
 * @see IResourceLoaderPlugin#createResourceLoader(IResourceLoadingSession)
 */
public interface IResourceLoadingSession {
    /**
     * Asks the mod with the given ID for a session extension object.
     *
     * @param modId The ID of the mod to get a session extension from
     */
    @Nullable
    Object getExtension(@NotNull String modId);

    /**
     * Asks the mod with the given ID for a session extension object, but with Kotlin syntax.
     *
     * @param modId The ID of the mod to get a session extension from
     */
    @Nullable
    default Object get(@NotNull String modId) {
        return getExtension(modId);
    }

    /**
     * Represents a factory, which creates extension objects requested by {@link #getExtension(String)}.
     */
    interface IExtensionFactory {
        /**
         * Creates a session extension object.
         *
         * @param modId   The mod, which asked for a session extension object
         * @param plugin  The plugin instance, which asked for a session extension object
         * @param session The resource loading session, in which the session extension object was asked
         */
        @Nullable
        Object createSessionExtension(@NotNull String modId, @NotNull IResourceLoaderPlugin plugin, @NotNull IResourceLoadingSession session);
    }
}
