package opekope2.lilac.api.fabric.mod_json;

import net.fabricmc.loader.api.metadata.CustomValue;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * A factory interface for creating {@link CustomValue}s.
 */
public interface ICustomValueFactory {
    /**
     * Gets the representation of {@link CustomValue.CvType#NULL}.
     */
    @NotNull CustomValue getNull();

    /**
     * Creates a custom value of type {@link CustomValue.CvType#BOOLEAN}.
     */
    @NotNull CustomValue createBoolean(boolean value);

    /**
     * Creates a custom value of type {@link CustomValue.CvType#NUMBER}.
     */
    @NotNull CustomValue createNumber(@NotNull Number value);

    /**
     * Creates a custom value of type {@link CustomValue.CvType#STRING}.
     */
    @NotNull CustomValue createString(@NotNull String value);

    /**
     * Creates a custom value of type {@link CustomValue.CvType#ARRAY}.
     *
     * @param values The elements to put in the created array
     */
    @NotNull CustomValue createArray(@NotNull CustomValue[] values);

    /**
     * Creates a custom value of type {@link CustomValue.CvType#OBJECT}.
     *
     * @param obj The member names of the object and their values
     */
    @NotNull CustomValue createObject(@NotNull Map<String, CustomValue> obj);
}
