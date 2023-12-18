package opekope2.lilac.annotation;

import opekope2.lilac.util.MinecraftVersion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates the Minecraft versions the annotated type or method is available on.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresMinecraftVersion {
    /**
     * Gets the minimum supported Minecraft version (inclusive).
     */
    MinecraftVersion minVersion();

    /**
     * Gets the maximum supported Minecraft version (inclusive).
     */
    MinecraftVersion maxVersion() default MinecraftVersion.MINECRAFT_1_20_4;
}
