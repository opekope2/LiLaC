package opekope2.lilac.internal.fabric.mod_json;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import net.fabricmc.loader.api.metadata.CustomValue;
import opekope2.lilac.api.ILilacApi;
import opekope2.lilac.api.dfu.IDataResultFactory;
import opekope2.lilac.api.fabric.mod_json.ICustomValueFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class CustomValueOps implements DynamicOps<CustomValue> {
    private static final ICustomValueFactory CUSTOM_VALUE_FACTORY = ILilacApi.getImplementation().getCustomValueFactory();
    private static final IDataResultFactory DATA_RESULT_FACTORY = IDataResultFactory.getInstance();

    public static CustomValueOps INSTANCE = new CustomValueOps();

    private CustomValueOps() {
    }

    private <R> DataResult<R> createConversionError(CustomValue input, String targetType) {
        return DATA_RESULT_FACTORY.error(() -> "Can't convert `%s` to `%s`".formatted(input.getType().name(), targetType));
    }

    @Override
    public CustomValue empty() {
        return CUSTOM_VALUE_FACTORY.getNull();
    }

    @Override
    public <U> U convertTo(DynamicOps<U> outOps, CustomValue input) {
        return switch (input.getType()) {
            case OBJECT -> convertMap(outOps, input);
            case ARRAY -> convertList(outOps, input);
            case STRING -> outOps.createString(input.getAsString());
            case NUMBER -> outOps.createNumeric(input.getAsNumber());
            case BOOLEAN -> outOps.createBoolean(input.getAsBoolean());
            case NULL -> outOps.empty();
        };
    }

    @Override
    public DataResult<Number> getNumberValue(CustomValue input) {
        return input.getType() == CustomValue.CvType.NUMBER
                ? DATA_RESULT_FACTORY.success(input.getAsNumber())
                : createConversionError(input, "Number");
    }

    @Override
    public CustomValue createNumeric(Number i) {
        return CUSTOM_VALUE_FACTORY.createNumber(i);
    }

    @Override
    public DataResult<String> getStringValue(CustomValue input) {
        return input.getType() == CustomValue.CvType.STRING
                ? DATA_RESULT_FACTORY.success(input.getAsString())
                : createConversionError(input, "String");
    }

    @Override
    public CustomValue createString(String value) {
        return CUSTOM_VALUE_FACTORY.createString(value);
    }

    @Override
    public DataResult<Boolean> getBooleanValue(CustomValue input) {
        return input.getType() == CustomValue.CvType.BOOLEAN
                ? DATA_RESULT_FACTORY.success(input.getAsBoolean())
                : createConversionError(input, "Boolean");
    }

    @Override
    public CustomValue createBoolean(boolean value) {
        return CUSTOM_VALUE_FACTORY.createBoolean(value);
    }

    @Override
    public DataResult<CustomValue> mergeToList(CustomValue list, CustomValue value) {
        if (list.getType() != CustomValue.CvType.ARRAY && list != empty()) {
            return DATA_RESULT_FACTORY.error(() -> "Can't merge into `%s` (should be array)".formatted(list.getType().name()));
        }

        List<CustomValue> l = new ArrayList<>();
        if (list != empty()) {
            list.getAsArray().forEach(l::add);
        }
        l.add(value);

        return DATA_RESULT_FACTORY.success(CUSTOM_VALUE_FACTORY.createArray(l.toArray(CustomValue[]::new)));
    }

    @Override
    public DataResult<CustomValue> mergeToMap(CustomValue map, CustomValue key, CustomValue value) {
        if (map.getType() != CustomValue.CvType.OBJECT && map != empty()) {
            return DATA_RESULT_FACTORY.error(() -> "Can't merge into `%s` (should be object)".formatted(map.getType().name()));
        }
        if (key.getType() != CustomValue.CvType.STRING) {
            return DATA_RESULT_FACTORY.error(() -> "Key is `%s` (should be string)".formatted(key.getType().name()));
        }

        Map<String, CustomValue> m = new HashMap<>();
        if (map != empty()) {
            map.getAsObject().forEach(pair -> m.put(pair.getKey(), pair.getValue()));
        }
        m.put(key.getAsString(), value);

        return DATA_RESULT_FACTORY.success(CUSTOM_VALUE_FACTORY.createObject(m));
    }

    @Override
    public DataResult<Stream<Pair<CustomValue, CustomValue>>> getMapValues(CustomValue input) {
        // IntelliJ whacky formatting moment
        return input.getType() == CustomValue.CvType.OBJECT
                ? DATA_RESULT_FACTORY.success(
                StreamSupport.stream(input.getAsObject().spliterator(), false)
                        .map(pair -> new Pair<>(createString(pair.getKey()), pair.getValue())))
                : createConversionError(input, "Map");
    }

    @Override
    public CustomValue createMap(Stream<Pair<CustomValue, CustomValue>> map) {
        return CUSTOM_VALUE_FACTORY.createObject(map.collect(Collectors.toMap(pair -> pair.getFirst().getAsString(), Pair::getSecond)));
    }

    @Override
    public DataResult<Stream<CustomValue>> getStream(CustomValue input) {
        return (input.getType() == CustomValue.CvType.ARRAY)
                ? DATA_RESULT_FACTORY.success(StreamSupport.stream(input.getAsArray().spliterator(), false))
                : DATA_RESULT_FACTORY.error(() -> "Can't convert `%s` to Stream (should be array)".formatted(input.getType().name()));
    }

    @Override
    public CustomValue createList(Stream<CustomValue> input) {
        return CUSTOM_VALUE_FACTORY.createArray(input.toArray(CustomValue[]::new));
    }

    @Override
    public CustomValue remove(CustomValue input, String key) {
        if (input.getType() == CustomValue.CvType.OBJECT) {
            Map<String, CustomValue> m = new HashMap<>();
            StreamSupport.stream(input.getAsObject().spliterator(), false)
                    .filter(pair -> !Objects.equals(pair.getKey(), key))
                    .forEach(pair -> m.put(pair.getKey(), pair.getValue()));
            return CUSTOM_VALUE_FACTORY.createObject(m);
        }
        return input;
    }
}
