package opekope2.lilac.api.fabric.mod_json;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.Bootstrap;
import net.minecraft.SharedConstants;
import opekope2.lilac.api.ILilacApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class MetadataDeserializeTests {
    private static ICustomMetadataSerializer serializer;
    private static ModMetadata metadata;

    @BeforeAll
    public static void init() {
        SharedConstants.createGameVersion();
        Bootstrap.initialize();

        assert ILilacApi.getImplementation().isAvailable();

        serializer = ILilacApi.getImplementation().getCustomMetadataSerializer();
        var container = FabricLoader.getInstance().getModContainer("lilac-test");
        assert container.isPresent();
        metadata = container.get().getMetadata();
        assert metadata != null;
    }

    @Test
    void testFull() {
        TestMetadata1 expected = new TestMetadata1(
                101,
                Optional.of(102),
                "Aa",
                Optional.of("Bb"),
                true,
                Optional.of(false),
                new TestMetadata2(List.of(111, 112), List.of("Xx", "Yy"), Optional.of(List.of(true, false))),
                Optional.of(new TestMetadata2(List.of(121, 122), List.of("AB", "BA"), Optional.of(List.of(false, true))))
        );

        var actual = serializer.deserialize(TestMetadata1.CODEC, metadata.getCustomValue("lilac-test-full"));

        assertEquals(expected, actual);
    }

    @Test
    void testMinimal() {
        TestMetadata1 expected = new TestMetadata1(
                201,
                Optional.empty(),
                "Xy",
                Optional.empty(),
                false,
                Optional.empty(),
                new TestMetadata2(List.of(211), List.of("Yx"), Optional.of(List.of(true))),
                Optional.empty()
        );

        var actual = serializer.deserialize(TestMetadata1.CODEC, metadata.getCustomValue("lilac-test-minimal"));

        assertEquals(expected, actual);
    }

    @Test
    void testMissing() {
        assertThrows(
                RuntimeException.class,
                () -> serializer.deserialize(TestMetadata1.CODEC, metadata.getCustomValue("lilac-test-missing"))
        );
    }
}

record TestMetadata1(int number1, Optional<Integer> number2, String text1, Optional<String> text2, boolean bool1,
                     Optional<Boolean> bool2, TestMetadata2 obj1, Optional<TestMetadata2> obj2) {
    public static final Codec<TestMetadata1> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("number1").forGetter(TestMetadata1::number1),
                    Codec.INT.optionalFieldOf("number2").forGetter(TestMetadata1::number2),
                    Codec.STRING.fieldOf("text1").forGetter(TestMetadata1::text1),
                    Codec.STRING.optionalFieldOf("text2").forGetter(TestMetadata1::text2),
                    Codec.BOOL.fieldOf("bool1").forGetter(TestMetadata1::bool1),
                    Codec.BOOL.optionalFieldOf("bool2").forGetter(TestMetadata1::bool2),
                    TestMetadata2.CODEC.fieldOf("obj1").forGetter(TestMetadata1::obj1),
                    TestMetadata2.CODEC.optionalFieldOf("obj2").forGetter(TestMetadata1::obj2)
            ).apply(instance, TestMetadata1::new));
}

record TestMetadata2(List<Integer> numbers, List<String> texts, Optional<List<Boolean>> bools) {
    public static final Codec<TestMetadata2> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.listOf().fieldOf("numbers").forGetter(TestMetadata2::numbers),
                    Codec.STRING.listOf().fieldOf("texts").forGetter(TestMetadata2::texts),
                    Codec.BOOL.listOf().optionalFieldOf("bools").forGetter(TestMetadata2::bools)
            ).apply(instance, TestMetadata2::new));
}
