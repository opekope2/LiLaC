package opekope2.lilac.internal.dfu_compat;

import com.mojang.serialization.DataResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.function.Supplier;

public final class DataResultFactory {
    @Nullable
    private static final MethodHandle errorWithSupplier = findMethod("error", Supplier.class);
    @Nullable
    private static final MethodHandle errorWithString = findMethod("error", String.class);

    static {
        if (errorWithSupplier == null && errorWithString == null) {
            throw new RuntimeException("No suitable error() method was found in %s.".formatted(DataResult.class.getName()));
        }
    }

    @Nullable
    private static MethodHandle findMethod(@NotNull String name, @NotNull Class<?>... paramTypes) {
        try {
            return MethodHandles.publicLookup().findStatic(
                    DataResult.class,
                    name,
                    MethodType.methodType(DataResult.class, paramTypes)
            );
        } catch (NoSuchMethodException e) {
            return null;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("The requested method is inaccessible.", e);
        }
    }

    public static <R> DataResult<R> createSuccess(R result) {
        return DataResult.success(result);
    }

    @SuppressWarnings("unchecked")
    public static <R> DataResult<R> createError(Supplier<String> message) {
        try {
            if (errorWithSupplier != null) return (DataResult<R>) errorWithSupplier.invokeExact(message);
            else if (errorWithString != null) return (DataResult<R>) errorWithString.invokeExact(message.get());
        } catch (Throwable e) {
            throw new RuntimeException("Exception occurred whilst creating %s:".formatted(DataResult.class.getName()), e);
        }
        throw new IllegalStateException("No suitable error() method is available. Maybe it was unset with reflection?");
    }

    private DataResultFactory() {
        throw new UnsupportedOperationException("%s can't be instantiated.".formatted(DataResultFactory.class.getName()));
    }
}
