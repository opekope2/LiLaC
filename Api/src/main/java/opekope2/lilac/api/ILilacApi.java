package opekope2.lilac.api;

import opekope2.lilac.stub.ApiStub;
import org.jetbrains.annotations.NotNull;

/**
 * Information about the LiLaC API state.
 */
public interface ILilacApi extends IImplementationLoader {
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

    @NotNull
    static ILilacApi getImplementation() {
        return ApiStub.getApiImplementation();
    }
}
