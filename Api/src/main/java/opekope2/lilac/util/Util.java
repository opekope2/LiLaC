package opekope2.lilac.util;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.Version;
import net.fabricmc.loader.api.VersionParsingException;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.fabricmc.loader.api.metadata.version.VersionPredicate;
import opekope2.lilac.annotation.EntrypointName;
import opekope2.lilac.annotation.RequiresMinecraftVersion;
import opekope2.lilac.exception.EntrypointException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * The must-have {@code Utilities} class for miscellaneous functions.
 */
public final class Util {
    /**
     * @hidden
     */
    private Util() {
    }

    /**
     * Checks if the appropriate version if the given mod is loaded.
     *
     * @param modId     The mod ID to check
     * @param predicate The predicate to decide if the version is appropriate
     * @return {@code true}, if the given mod is loaded, and its version matches the given predicate,
     * {@code false} otherwise (including the case, when the mod isn't loaded)
     */
    public static boolean checkModVersion(@NotNull String modId, @NotNull Function<Version, Boolean> predicate) {
        FabricLoader loader = FabricLoader.getInstance();
        Optional<ModContainer> mod = loader.getModContainer(modId);

        if (mod.isEmpty()) {
            return false;
        } else {
            ModMetadata metadata = mod.get().getMetadata();
            return predicate.apply(metadata.getVersion());
        }
    }

    /**
     * Gets the {@link StackTraceElement} at {@code depth} stack frames deep.
     * Zero means the method calling {@link #getStackTraceElementAt(int)}.
     * One means the caller method of the method calling {@link #getStackTraceElementAt(int)}.
     *
     * @param depth The number of steps to step up the stack. Must not be negative.
     */
    @Nullable
    public static StackTraceElement getStackTraceElementAt(int depth) {
        assert depth >= 0;

        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        return depth + 2 >= trace.length ? null : trace[depth + 2];
    }

    /**
     * Gets the entry point name of the given type from its {@link EntrypointName} annotation.
     *
     * @param type The type to check its entry point name
     * @return The value of the annotation of {@code null} if the annotation is not present
     */
    @Nullable
    public static String getEntrypointName(@NotNull Class<?> type) {
        EntrypointName name = type.getAnnotation(EntrypointName.class);
        return name == null ? null : name.value();
    }

    /**
     * Gets all entry points from {@code fabric.mod.json} with name specified in the {@link EntrypointName} annotation of the given type.
     *
     * @param type The type of the entry points
     * @param <T>  The type of the entry points
     * @see #getEntrypointName(Class)
     * @see #getEntrypointContainers(Class)
     * @see FabricLoader#getEntrypoints(String, Class)
     */
    @NotNull
    public static <T> List<@NotNull T> getEntrypoints(@NotNull Class<@NotNull T> type) {
        String name = getEntrypointName(type);
        if (name == null) {
            throw new EntrypointException("`%s` is not annotated with `%s`".formatted(type.getName(), EntrypointName.class.getName()));
        }

        return FabricLoader.getInstance().getEntrypoints(name, type);
    }

    /**
     * Gets all entry point containers from {@code fabric.mod.json} with name specified in the {@link EntrypointName} annotation of the given type.
     *
     * @param type The type of entry points
     * @param <T>  The type of entry points
     * @see #getEntrypointName(Class)
     * @see #getEntrypoints(Class)
     * @see FabricLoader#getEntrypoints(String, Class)
     */
    @NotNull
    public static <T> List<EntrypointContainer<@NotNull T>> getEntrypointContainers(@NotNull Class<@NotNull T> type) {
        String name = getEntrypointName(type);
        if (name == null) {
            throw new EntrypointException("`%s` is not annotated with `%s`".formatted(type.getName(), EntrypointName.class.getName()));
        }

        return FabricLoader.getInstance().getEntrypointContainers(name, type);
    }

    /**
     * Creates a {@link VersionPredicate} from the given {@link RequiresMinecraftVersion} annotation instance.
     *
     * @param annotationInstance The annotation to create a version predicate from
     */
    public static VersionPredicate toVersionPredicate(RequiresMinecraftVersion annotationInstance) {
        try {
            return VersionPredicate.parse(">=" + annotationInstance.minVersion() + " " + "<=" + annotationInstance.maxVersion());
        } catch (VersionParsingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Chooses the first supplied class, which has {@link RequiresMinecraftVersion} annotation,
     * and it matches currently running version of Minecraft.
     *
     * @param classes The classes to choose from
     * @param <T>     The type of the classes
     * @return The first matching class or {@code null}, if no match was found
     */
    @Nullable
    @SafeVarargs
    public static <T> Class<? extends T> chooseByRequiredMinecraftVersion(@NotNull Class<? extends T>... classes) {
        MinecraftVersion minecraftVersion = MinecraftVersion.current();
        if (minecraftVersion == null) throw new RuntimeException("Failed to detect current Minecraft version");
        int currentOrdinal = minecraftVersion.ordinal();

        for (Class<? extends T> cls : classes) {
            RequiresMinecraftVersion minecraftVersionRequirement = cls.getAnnotation(RequiresMinecraftVersion.class);
            if (minecraftVersionRequirement == null) continue;
            MinecraftVersion minVersion = minecraftVersionRequirement.minVersion(),
                    maxVersion = minecraftVersionRequirement.maxVersion();

            if (minVersion.ordinal() <= currentOrdinal && currentOrdinal <= maxVersion.ordinal()) {
                return cls;
            }
        }

        return null;
    }
}
