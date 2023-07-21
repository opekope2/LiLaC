package opekope2.lilac.stub;

import opekope2.lilac.api.ILilacApi;
import opekope2.lilac.api.fabric.mod_json.ICustomMetadataSerializer;
import opekope2.lilac.api.fabric.mod_json.ICustomValueFactory;
import opekope2.lilac.api.registry.IRegistryLookup;
import opekope2.lilac.api.resource.IResourceAccess;
import opekope2.lilac.api.tick.ITickNotifier;
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
    public IResourceAccess getResourceAccess() {
        throw new UnsupportedOperationException("LiLaC implementation is not available.");
    }

    @Override
    @NotNull
    public IRegistryLookup getRegistryLookup() {
        throw new UnsupportedOperationException("LiLaC implementation is not available.");
    }

    @Override
    @NotNull
    public ITickNotifier getTickNotifier() {
        throw new UnsupportedOperationException("LiLaC implementation is not available.");
    }

    @Override
    @NotNull
    public ICustomMetadataSerializer getCustomMetadataSerializer() {
        throw new UnsupportedOperationException("LiLaC implementation is not available.");
    }

    @Override
    public @NotNull ICustomValueFactory getCustomValueFactory() {
        throw new UnsupportedOperationException("LiLaC implementation is not available.");
    }
}
