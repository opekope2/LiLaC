package opekope2.lilac.internal.fabric.mod_json;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.fabricmc.loader.api.metadata.CustomValue;
import opekope2.lilac.api.fabric.mod_json.ICustomMetadataSerializer;
import org.jetbrains.annotations.NotNull;

public class CustomMetadataSerializer implements ICustomMetadataSerializer {
    @Override
    @NotNull
    public <T> DataResult<CustomValue> serialize(@NotNull Codec<T> codec, @NotNull T value) {
        return codec.encodeStart(CustomValueOps.INSTANCE, value);
    }

    @Override
    @NotNull
    public <T> T deserialize(@NotNull Codec<T> codec, @NotNull CustomValue customValue) {
        return codec.parse(CustomValueOps.INSTANCE, customValue).getOrThrow(false, m -> {
        });
    }
}
