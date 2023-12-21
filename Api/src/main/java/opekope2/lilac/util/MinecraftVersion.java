package opekope2.lilac.util;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.SemanticVersion;
import org.jetbrains.annotations.NotNull;

/**
 * Minecraft versions supported by LiLaC.
 */
public enum MinecraftVersion {
    MINECRAFT_1_18(1, 18, 0),
    MINECRAFT_1_18_1(1, 18, 1),
    MINECRAFT_1_18_2(1, 18, 2),
    MINECRAFT_1_19(1, 19, 0),
    MINECRAFT_1_19_1(1, 19, 1),
    MINECRAFT_1_19_2(1, 19, 2),
    MINECRAFT_1_19_3(1, 19, 3),
    MINECRAFT_1_19_4(1, 19, 4),
    MINECRAFT_1_20(1, 20, 0),
    MINECRAFT_1_20_1(1, 20, 1),
    MINECRAFT_1_20_2(1, 20, 2),
    MINECRAFT_1_20_3(1, 20, 3),
    MINECRAFT_1_20_4(1, 20, 4)
    // Don't forget to update RequiresMinecraftVersion.maxVersion() default value
    ;

    /**
     * First component of the version (X.y.z).
     */
    public final int major;
    /**
     * Second component of the version (x.Y.z).
     */
    public final int minor;
    /**
     * Third component of the version (x.y.Z).
     */
    public final int patch;

    MinecraftVersion(int major, int minor, int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    @Override
    public String toString() {
        return major + "." + minor + "." + patch;
    }

    /**
     * Detects and returns the {@link MinecraftVersion} corresponding to the currently running Minecraft version.
     */
    @NotNull
    public static MinecraftVersion current() {
        ModContainer minecraftContainer = FabricLoader.getInstance().getModContainer("minecraft").orElseThrow();
        // FIXME assumes fabric-loader internal behavior
        SemanticVersion minecraftVersion = (SemanticVersion) minecraftContainer.getMetadata().getVersion();

        int major = minecraftVersion.getVersionComponent(0),
                minor = minecraftVersion.getVersionComponent(1),
                patch = minecraftVersion.getVersionComponent(2);
        // TODO snapshots

        for (MinecraftVersion version : MinecraftVersion.values()) {
            if (version.major == major && version.minor == minor && version.patch == patch) {
                return version;
            }
        }

        throw new RuntimeException("Failed to detect current Minecraft version");
    }
}
