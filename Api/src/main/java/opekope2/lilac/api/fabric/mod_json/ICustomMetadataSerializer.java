package opekope2.lilac.api.fabric.mod_json;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.fabricmc.loader.api.metadata.CustomValue;
import org.jetbrains.annotations.NotNull;

/**
 * An interface defining a serializer and deserializer for a {@link CustomValue}.
 */
public interface ICustomMetadataSerializer {
    /**
     * Serializes the given object with the given codec.
     *
     * @param codec The codec to serialize with
     * @param value The object to serialize
     * @param <T>   The type the codec serializes
     * @return The serialized result
     * @see <a href="https://fabric.mineblock11.dev/misc/codecs">Codec docuemntation</a>
     */
    @NotNull <T> DataResult<CustomValue> serialize(@NotNull Codec<T> codec, @NotNull T value);

    /**
     * Deserializes the given custom value with the given codec.
     *
     * @param codec       The codec to deserialize with
     * @param customValue The custom value to deserialize
     * @param <T>         The type the codec deserializes
     * @return The deserialized object
     * @see <a href="https://fabric.mineblock11.dev/misc/codecs">Codec docuemntation</a>
     */
    @NotNull <T> T deserialize(@NotNull Codec<T> codec, @NotNull CustomValue customValue);
}
