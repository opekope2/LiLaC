package opekope2.lilac.annotation;

import opekope2.lilac.api.Util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies the entry point name in {@code fabric.mod.json} to be searched with {@link Util#getEntrypointName(Class)}.
 *
 * @see Util#getEntrypointName(Class)
 * @see Util#getEntrypoints(Class)
 * @see Util#getEntrypointContainers(Class)
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EntrypointName {
    /**
     * Gets the name of the entry point to look for in {@code fabric.mod.json}.
     */
    String value();
}
