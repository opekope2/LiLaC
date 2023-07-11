package opekope2.lilac.api;

import org.jetbrains.annotations.NotNull;

/**
 * Implementation loader for LiLaC interfaces.
 * <br>
 * This is used internally by LiLaC API for loading interface implementations, and likely isn't needed by 3rd party mods.
 */
public interface IImplementationLoader {
    /**
     * Loads the implementation for a class specified with {@code type}.
     * <br>
     * Note to implementors: this method should return the same implementation instance for a given {@link Class}
     *
     * @param type The interface to load the implementation of
     * @param <T>  The type of the interface
     * @return The implementation of the interface
     */
    @NotNull <T> T loadImplementation(@NotNull Class<T> type);
}
