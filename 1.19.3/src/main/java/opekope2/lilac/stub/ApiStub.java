package opekope2.lilac.stub;

import opekope2.lilac.api.ILilacApi;
import opekope2.lilac.impl.LilacApi;
import org.jetbrains.annotations.NotNull;

public final class ApiStub {
    private ApiStub() {
    }

    @SuppressWarnings("unused")
    @NotNull
    public static ILilacApi getApiImplementation() {
        return LilacApi.INSTANCE;
    }
}
