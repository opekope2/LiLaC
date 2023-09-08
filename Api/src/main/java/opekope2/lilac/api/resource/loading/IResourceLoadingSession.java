package opekope2.lilac.api.resource.loading;

import opekope2.lilac.annotation.EntrypointName;
import opekope2.lilac.api.ILilacApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a resource loading session.
 *
 * @see IResourceLoader.IFactory#createResourceLoader(IResourceLoadingSession)
 */
public interface IResourceLoadingSession {
    /**
     * Asks the mod with the given ID for a session extension object.
     *
     * @param modId The ID of the mod to get a session extension from
     * @apiNote Can only be called during the {@link Stage#INIT initialization stage}
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
     * Queries information about the given resource loading session.
     *
     * @param session The resource loading session to query
     * @return The query result
     */
    @NotNull
    static IProperties getProperties(@NotNull IResourceLoadingSession session) {
        return ILilacApi.getImplementation().getResourceLoadingSessionProperties(session);
    }

    /**
     * Represents a factory, which creates extension objects requested by {@link #getExtension(String)}.
     */
    @EntrypointName("lilac-resourceloadingsession-extensionfactory") // Bruh
    interface IExtensionFactory {
        /**
         * Creates a session extension object.
         *
         * @param modId   The mod, which asked for a session extension object
         * @param factory The factory instance, which asked for a session extension object
         * @param session The resource loading session, in which the session extension object was asked
         */
        @Nullable
        Object createSessionExtension(@NotNull String modId, @NotNull IResourceLoader.IFactory factory, @NotNull IResourceLoadingSession session);
    }

    /**
     * Information about resource loading sessions.
     *
     * @see opekope2.lilac.api.ILilacApi#getResourceLoadingSessionProperties(IResourceLoadingSession)
     */
    interface IProperties {
        /**
         * Gets the stage the resource loading session is currently in.
         * For non-LiLaC created sessions, it will always be {@link Stage#INACTIVE}.
         */
        @NotNull
        Stage getStage();
    }

    /**
     * Resource loading session stages
     */
    enum Stage {
        /**
         * Indicates that the resource loading session is not in progress.
         */
        INACTIVE,
        /**
         * The stage, when LiLaC configures {@link IResourceLoader.IFactory resource loader factories}.
         */
        INIT,
        /**
         * Indicates that the resource loading session is in the
         * <a href="https://fabricmc.net/wiki/tutorial:resource#prepare_stage">Prepare stage</a>.
         */
        PREPARE,
        /**
         * Indicates that the resource loading session is in the
         * <a href="https://fabricmc.net/wiki/tutorial:resource#apply_stage">Apply stage</a>.
         */
        APPLY
    }

    /**
     * Interface, which gets notified about {@link IResourceLoadingSession} creation, closure, and stage changes.
     */
    @EntrypointName("lilac-resourceloadingsession-lifecyclelistener")
    interface ILifecycleListener {
        /**
         * Called when an {@link IResourceLoadingSession} is created.
         *
         * @param session The created session
         */
        void onCreated(@NotNull IResourceLoadingSession session);

        /**
         * Called when the stage of {@link IResourceLoadingSession} changes.
         *
         * @param session The session, whose stage was changed
         */
        void onStageChanged(@NotNull IResourceLoadingSession session);

        /**
         * Called when an {@link IResourceLoadingSession} is ended.
         *
         * @param session The ended session
         */
        void onClosed(@NotNull IResourceLoadingSession session);
    }
}
