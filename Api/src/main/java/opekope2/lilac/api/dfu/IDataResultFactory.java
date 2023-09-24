package opekope2.lilac.api.dfu;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.Lifecycle;
import opekope2.lilac.annotation.RequiresImplementation;
import opekope2.lilac.api.ILilacApi;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * A factory class for {@link DataResult}.
 */
public interface IDataResultFactory {
    /**
     * Creates a successful {@link DataResult} with the given result. The lifecycle of the result is
     * {@linkplain Lifecycle#experimental() experimental}.
     *
     * @param result The result
     * @param <T>    The type of the result
     * @see DataResult#success(Object)
     */
    @NotNull <T> DataResult<T> success(@NotNull T result);

    /**
     * Creates a successful {@link DataResult} with the given results and lifecycle.
     *
     * @param result    The result
     * @param lifecycle The lifecycle to use
     * @param <T>       The type of the result
     * @see DataResult#success(Object, Lifecycle)
     */
    @NotNull <T> DataResult<T> success(@NotNull T result, @NotNull Lifecycle lifecycle);

    /**
     * Creates an error {@link DataResult} with the given error message supplier and no partial result.
     *
     * @param message The error message supplier
     * @param <T>     The type of the result a successful {@link DataResult} would return
     * @see DataResult#error(Supplier)
     */
    @NotNull <T> DataResult<T> error(@NotNull Supplier<String> message);

    /**
     * Creates an error {@link DataResult} with the given error message supplier and a partial result.
     *
     * @param message       The error message supplier
     * @param partialResult The partial or fallback result
     * @param <T>           The type of the result a successful {@link DataResult} would return
     * @see DataResult#error(Supplier, Object)
     */
    @NotNull <T> DataResult<T> error(@NotNull Supplier<String> message, @NotNull T partialResult);

    /**
     * Creates an error {@link DataResult} with the given error message supplier and lifecycle.
     *
     * @param message   The error message supplier
     * @param lifecycle The lifecycle to use
     * @param <T>       The type of the result a successful {@link DataResult} would return
     * @see DataResult#error(Supplier, Lifecycle)
     */
    @NotNull <T> DataResult<T> error(@NotNull Supplier<String> message, @NotNull Lifecycle lifecycle);

    /**
     * Creates an error {@link DataResult} with the given error message supplier, partial result, and lifecycle.
     *
     * @param message       The error message supplier
     * @param partialResult The partial or fallback result
     * @param lifecycle     The lifecycle to use
     * @param <T>           The type of the result a successful {@link DataResult} would return
     * @see DataResult#error(Supplier, Object, Lifecycle)
     */
    @NotNull <T> DataResult<T> error(@NotNull Supplier<String> message, @NotNull T partialResult, @NotNull Lifecycle lifecycle);

    /**
     * Returns the implementation of {@link IDataResultFactory}.
     */
    @NotNull
    @RequiresImplementation
    static IDataResultFactory getInstance() {
        return ILilacApi.getImplementation().getDataResultFactory();
    }
}
