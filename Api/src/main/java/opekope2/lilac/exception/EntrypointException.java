package opekope2.lilac.exception;

/**
 * Thrown, when an entry point is configured incorrectly.
 * This is distinct from {@link net.fabricmc.loader.api.EntrypointException}, because that's for internal use only.
 */
public final class EntrypointException extends RuntimeException {
    /**
     * Creates a new {@link EntrypointException} with the given message.
     *
     * @param message The message to pass to {@link RuntimeException#RuntimeException(String)}
     */
    public EntrypointException(String message) {
        super(message);
    }
}
