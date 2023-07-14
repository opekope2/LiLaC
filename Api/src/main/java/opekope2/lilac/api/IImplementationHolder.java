package opekope2.lilac.api;

import opekope2.lilac.api.registry.IRegistryLookup;
import opekope2.lilac.api.resource.IResourceAccess;
import opekope2.lilac.api.tick.ITickNotifier;
import org.jetbrains.annotations.NotNull;

/**
 * This is used internally by LiLaC API for loading interface implementations, and likely isn't needed by 3rd party mods.
 */
public interface IImplementationHolder {
    /**
     * Gets the implementation of {@link IResourceAccess}.
     */
    @NotNull IResourceAccess getResourceAccess();

    /**
     * Gets the implementation of {@link IRegistryLookup}.
     */
    @NotNull IRegistryLookup getRegistryLookup();

    /**
     * Gets the implementation of {@link ITickNotifier}.
     */
    @NotNull ITickNotifier getTickNotifier();
}
