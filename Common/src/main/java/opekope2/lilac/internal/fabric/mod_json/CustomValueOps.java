package opekope2.lilac.internal.fabric.mod_json;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import net.fabricmc.loader.api.metadata.CustomValue;
import opekope2.lilac.api.ILilacApi;
import opekope2.lilac.api.fabric.mod_json.ICustomValueFactory;
import opekope2.lilac.internal.dfu_compat.DataResultFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class CustomValueOps implements DynamicOps<CustomValue> {
    private static final ICustomValueFactory factory = ILilacApi.getImplementation().getCustomValueFactory();

    public static CustomValueOps INSTANCE = new CustomValueOps();

    private CustomValueOps() {
    }

    private <R> DataResult<R> createConversionError(CustomValue input, String targetType) {
        return DataResultFactory.createError(() -> "Can't convert %s to %s.".formatted(input.getType().name(), targetType));
    }

    @Override
    public CustomValue empty() {
        return factory.getNull();
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
                ? DataResultFactory.createSuccess(input.getAsNumber())
                : createConversionError(input, "Number");
    }

    @Override
    public CustomValue createNumeric(Number i) {
        return factory.createNumber(i);
    }

    @Override
    public DataResult<String> getStringValue(CustomValue input) {
        return input.getType() == CustomValue.CvType.STRING
                ? DataResultFactory.createSuccess(input.getAsString())
                : createConversionError(input, "String");
    }

    @Override
    public CustomValue createString(String value) {
        return factory.createString(value);
    }

    @Override
    public DataResult<Boolean> getBooleanValue(CustomValue input) {
        return input.getType() == CustomValue.CvType.BOOLEAN
                ? DataResultFactory.createSuccess(input.getAsBoolean())
                : createConversionError(input, "Boolean");
    }

    @Override
    public CustomValue createBoolean(boolean value) {
        return factory.createBoolean(value);
    }

    @Override
    public DataResult<CustomValue> mergeToList(CustomValue list, CustomValue value) {
        if (list.getType() != CustomValue.CvType.ARRAY && list != empty()) {
            return DataResultFactory.createError(() -> "Can't merge into %s (should be Array).".formatted(list.getType().name()));
        }

        List<CustomValue> l = new ArrayList<>();
        if (list != empty()) {
            list.getAsArray().forEach(l::add);
        }
        l.add(value);

        return DataResultFactory.createSuccess(factory.createArray(l.toArray(CustomValue[]::new)));
    }

    @Override
    public DataResult<CustomValue> mergeToMap(CustomValue map, CustomValue key, CustomValue value) {
        if (map.getType() != CustomValue.CvType.OBJECT && map != empty()) {
            return DataResult.error(() -> "Can't merge into %s (should be Object).".formatted(map.getType().name()));
        }
        if (key.getType() != CustomValue.CvType.STRING) {
            return DataResult.error(() -> "Key is %s (should be String).".formatted(key.getType().name()));
        }

        Map<String, CustomValue> m = new HashMap<>();
        if (map != empty()) {
            map.getAsObject().forEach(pair -> m.put(pair.getKey(), pair.getValue()));
        }
        m.put(key.getAsString(), value);

        return DataResultFactory.createSuccess(factory.createObject(m));
    }

    @Override
    public DataResult<Stream<Pair<CustomValue, CustomValue>>> getMapValues(CustomValue input) {
        // IntelliJ whacky formatting moment
        return input.getType() == CustomValue.CvType.OBJECT
                ? DataResultFactory.createSuccess(
                StreamSupport.stream(input.getAsObject().spliterator(), false)
                        .map(pair -> new Pair<>(createString(pair.getKey()), pair.getValue())))
                : createConversionError(input, "Map");
    }

    @Override
    public CustomValue createMap(Stream<Pair<CustomValue, CustomValue>> map) {
        return factory.createObject(map.collect(Collectors.toMap(pair -> pair.getFirst().getAsString(), Pair::getSecond)));
    }

    @Override
    public DataResult<Stream<CustomValue>> getStream(CustomValue input) {
        return (input.getType() == CustomValue.CvType.ARRAY)
                ? DataResultFactory.createSuccess(StreamSupport.stream(input.getAsArray().spliterator(), false))
                : DataResultFactory.createError(() -> "Can't convert %s to Stream (should be Array).".formatted(input.getType().name()));
    }

    @Override
    public CustomValue createList(Stream<CustomValue> input) {
        return factory.createArray(input.toArray(CustomValue[]::new));
    }

    @Override
    public CustomValue remove(CustomValue input, String key) {
        if (input.getType() == CustomValue.CvType.OBJECT) {
            Map<String, CustomValue> m = new HashMap<>();
            StreamSupport.stream(input.getAsObject().spliterator(), false)
                    .filter(pair -> !Objects.equals(pair.getKey(), key))
                    .forEach(pair -> m.put(pair.getKey(), pair.getValue()));
            return factory.createObject(m);
        }
        return input;
    }
}