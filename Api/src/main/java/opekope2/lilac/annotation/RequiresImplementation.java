package opekope2.lilac.annotation;

import opekope2.lilac.api.ILilacApi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that before using this function, the presence of the LiLaC API should be checked using {@link ILilacApi#isAvailable()}.
 *
 * @see ILilacApi#getImplementation()
 * @see ILilacApi#isAvailable()
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface RequiresImplementation {
}
