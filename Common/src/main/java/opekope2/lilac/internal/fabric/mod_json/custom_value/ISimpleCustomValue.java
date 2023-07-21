package opekope2.lilac.internal.fabric.mod_json.custom_value;

import net.fabricmc.loader.api.metadata.CustomValue;

public interface ISimpleCustomValue extends CustomValue {
    @Override
    default CvObject getAsObject() {
        throw new ClassCastException("Can't convert %s to Object.".formatted(getType().name()));
    }

    @Override
    default CvArray getAsArray() {
        throw new ClassCastException("Can't convert %s to Array.".formatted(getType().name()));
    }

    @Override
    default String getAsString() {
        throw new ClassCastException("Can't convert %s to String.".formatted(getType().name()));
    }

    @Override
    default Number getAsNumber() {
        throw new ClassCastException("Can't convert %s to Number.".formatted(getType().name()));
    }

    @Override
    default boolean getAsBoolean() {
        throw new ClassCastException("Can't convert %s to Boolean.".formatted(getType().name()));
    }
}
