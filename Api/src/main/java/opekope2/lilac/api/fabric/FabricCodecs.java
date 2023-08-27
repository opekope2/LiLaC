package opekope2.lilac.api.fabric;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.fabricmc.loader.api.VersionParsingException;
import net.fabricmc.loader.api.metadata.version.VersionPredicate;

/**
 * Codecs for fabric objects.
 */
public class FabricCodecs {
    private FabricCodecs() {
    }

    /**
     * Codec for {@link VersionPredicate}.
     */
    public static final Codec<VersionPredicate> VERSION_PREDICATE = Codec.STRING.comapFlatMap(FabricCodecs::parseVersionPredicate, VersionPredicate::toString);

    private static DataResult<VersionPredicate> parseVersionPredicate(String predicate) {
        try {
            return DataResult.success(VersionPredicate.parse(predicate));
        } catch (VersionParsingException e) {
            return DataResult.error(() -> "Failed to parse `%s`".formatted(predicate));
        }
    }
}
