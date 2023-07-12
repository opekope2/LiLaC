package opekope2.lilac.stub;

import opekope2.lilac.api.IImplementationHolder;
import opekope2.lilac.api.ILilacApi;
import org.jetbrains.annotations.NotNull;

/**
 * @hidden
 */
public final class ApiStub implements ILilacApi {
    private static final ILilacApi API = new ApiStub();

    private ApiStub() {
    }

    @NotNull
    public static ILilacApi getApiImplementation() {
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
    public IImplementationHolder getImplementations() {
        throw new UnsupportedOperationException("LiLaC implementation is not loaded.");
    }
}
