package opekope2.lilac.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies the entry point name in {@code fabric.mod.json} to be searched with TODO.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EntrypointName {
    /**
     * Gets the name of the entry point to look for in {@code fabric.mod.json}.
     */
    String value();
}
