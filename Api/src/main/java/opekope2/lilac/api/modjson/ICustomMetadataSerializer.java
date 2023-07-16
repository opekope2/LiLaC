package opekope2.lilac.api.modjson;

import com.mojang.serialization.Codec;
import net.fabricmc.loader.api.metadata.CustomValue;
import org.jetbrains.annotations.NotNull;

/**
 * An interface defining a (de)serializer for a {@link CustomValue}.
 */
public interface ICustomMetadataSerializer {
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
