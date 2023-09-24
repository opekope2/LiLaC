package opekope2.lilac.api;

import opekope2.lilac.annotation.RequiresImplementation;
import opekope2.lilac.api.dfu.IDataResultFactory;
import opekope2.lilac.api.fabric.mod_json.ICustomMetadataSerializer;
import opekope2.lilac.api.fabric.mod_json.ICustomValueFactory;
import opekope2.lilac.api.registry.IRegistryLookup;
import opekope2.lilac.api.resource.IResourceAccess;
import opekope2.lilac.api.resource.loading.IResourceLoadingSession;
import opekope2.lilac.api.tick.ITickNotifier;
import org.jetbrains.annotations.NotNull;

/**
 * The core LiLaC API.
 */
public interface ILilacApi {
    /**
     * Tells whether the LiLaC implementation is loaded or not.
     * This should be checked before using any other LiLaC API if LiLaC is an optional dependency.
     * Such APIs are marked with {@link RequiresImplementation}.
     */
    boolean isAvailable();

    /**
     * Returns the ID of the mod, which provides LiLaC implementation.
     */
    @NotNull
    String getImplementationModId();

    /**
     * Returns the implementation of {@link IResourceAccess}.
     *
     * @see IResourceAccess#getInstance()
     */
    @NotNull
    @RequiresImplementation
    IResourceAccess getResourceAccess();

    /**
     * Returns the implementation of {@link IRegistryLookup}.
     *
     * @see IRegistryLookup#getInstance()
     */
    @NotNull
    @RequiresImplementation
    IRegistryLookup getRegistryLookup();

    /**
     * Returns the implementation of {@link ITickNotifier}.
     *
     * @see ITickNotifier#getInstance()
     */
    @NotNull
    @RequiresImplementation
    ITickNotifier getTickNotifier();

    /**
     * Returns the implementation of {@link ICustomMetadataSerializer}.
     */
    @NotNull
    @RequiresImplementation
    ICustomMetadataSerializer getCustomMetadataSerializer();

    /**
     * Returns the implementation of {@link ICustomValueFactory}.
     */
    @NotNull
    @RequiresImplementation
    ICustomValueFactory getCustomValueFactory();

    /**
     * Queries information about the given resource loading session.
     *
     * @param session The resource loading session to query
     * @return The query result
     */
    @NotNull
    @RequiresImplementation
    IResourceLoadingSession.IProperties getResourceLoadingSessionProperties(@NotNull IResourceLoadingSession session);

    /**
     * Returns the implementation of {@link IDataResultFactory}.
     *
     * @see IDataResultFactory#getInstance()
     */
    @NotNull
    @RequiresImplementation
    IDataResultFactory getDataResultFactory();

    /**
     * Returns the implementation of {@link ILilacApi}.
     */
    @NotNull
    static ILilacApi getImplementation() {
        return ILilacApi$Instance.get();
    }
}

/**
 * @hidden
 */
final class ILilacApi$Instance implements ILilacApi {
    @NotNull
    private static final ILilacApi API = new ILilacApi$Instance();

    private ILilacApi$Instance() {
    }

    @NotNull
    public static ILilacApi get() {
        return API;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    @NotNull
    public String getImplementationModId() {
        return "lilac-api";
    }

    @Override
    @NotNull
    public IResourceAccess getResourceAccess() {
        throw new UnsupportedOperationException("LiLaC implementation is not available");
    }

    @Override
    @NotNull
    public IRegistryLookup getRegistryLookup() {
        throw new UnsupportedOperationException("LiLaC implementation is not available");
    }

    @Override
    @NotNull
    public ITickNotifier getTickNotifier() {
        throw new UnsupportedOperationException("LiLaC implementation is not available");
    }

    @Override
    @NotNull
    public ICustomMetadataSerializer getCustomMetadataSerializer() {
        throw new UnsupportedOperationException("LiLaC implementation is not available");
    }

    @Override
    @NotNull
    public ICustomValueFactory getCustomValueFactory() {
        throw new UnsupportedOperationException("LiLaC implementation is not available");
    }

    @Override
    @NotNull
    public IResourceLoadingSession.IProperties getResourceLoadingSessionProperties(@NotNull IResourceLoadingSession session) {
        throw new UnsupportedOperationException("LiLaC implementation is not available");
    }

    @Override
    @NotNull
    public IDataResultFactory getDataResultFactory() {
        throw new UnsupportedOperationException("LiLaC implementation is not available");
    }
}
