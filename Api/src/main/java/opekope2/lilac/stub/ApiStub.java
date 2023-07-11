package opekope2.lilac.stub;

import opekope2.lilac.api.ILilacApi;
import org.jetbrains.annotations.NotNull;

public final class ApiStub implements ILilacApi {
    private static final ILilacApi API = new ApiStub();

    private ApiStub() {
    }

    @NotNull
    public static ILilacApi getApi() {
        return API;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public @NotNull String getImplementationModId() {
        return "lilac-api";
    }

    @Override
    @NotNull
    public <T> T loadImplementation(@NotNull Class<T> type) {
        throw new UnsupportedOperationException("LiLaC implementation is not loaded.");
    }
}
