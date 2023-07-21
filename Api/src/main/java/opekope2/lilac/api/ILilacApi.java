package opekope2.lilac.api;

import opekope2.lilac.api.fabric.mod_json.ICustomMetadataSerializer;
import opekope2.lilac.api.fabric.mod_json.ICustomValueFactory;
import opekope2.lilac.api.registry.IRegistryLookup;
import opekope2.lilac.api.resource.IResourceAccess;
import opekope2.lilac.api.tick.ITickNotifier;
import opekope2.lilac.stub.ApiStub;
import org.jetbrains.annotations.NotNull;

/**
 * Information about the LiLaC API state.
 */
public interface ILilacApi {
    /**
     * Tells whether the LiLaC implementation is loaded or not.
     * This should be checked before using any other LiLaC API if LiLaC is an optional dependency.
     */
    boolean isAvailable();

    /**
     * Returns the ID of the mod, which provides LiLaC implementation.
     */
    @NotNull
    String getImplementationModId();

    /**
     * Returns the implementation of {@link IResourceAccess}.
     */
    @NotNull IResourceAccess getResourceAccess();

    /**
     * Returns the implementation of {@link IRegistryLookup}.
     */
    @NotNull IRegistryLookup getRegistryLookup();

    /**
     * Returns the implementation of {@link ITickNotifier}.
     */
    @NotNull ITickNotifier getTickNotifier();

    /**
     * Returns the implementation of {@link ICustomMetadataSerializer}.
     */
    @NotNull ICustomMetadataSerializer getCustomMetadataSerializer();

    /**
     * Returns the implementation of {@link ICustomValueFactory}.
     */
    @NotNull ICustomValueFactory getCustomValueFactory();

    /**
     * Returns the implementation of {@link ILilacApi}.
     */
    @NotNull
    static ILilacApi getImplementation() {
        return ApiStub.getApiImplementation();
    }
}
