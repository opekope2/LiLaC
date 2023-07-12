package opekope2.lilac.api;

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
     * Gets the implementation loader instance, which is used to load the implementation of some other interfaces.
     * This is used internally by LiLaC API, and likely isn't needed by 3rd party mods.
     */
    @NotNull
    IImplementationHolder getImplementations();

    /**
     * Returns the implementation of {@link ILilacApi}.
     */
    @NotNull
    static ILilacApi getImplementation() {
        return ApiStub.getApiImplementation();
    }
}
