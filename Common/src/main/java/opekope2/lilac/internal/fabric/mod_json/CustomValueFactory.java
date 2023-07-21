package opekope2.lilac.internal.fabric.mod_json;

import net.fabricmc.loader.api.metadata.CustomValue;
import opekope2.lilac.api.fabric.mod_json.ICustomValueFactory;
import opekope2.lilac.internal.fabric.mod_json.custom_value.*;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public final class CustomValueFactory implements ICustomValueFactory {
    private static final CustomValue NULL = new SimpleNull();
    private static final CustomValue TRUE = new SimpleBoolean(true);
    private static final CustomValue FALSE = new SimpleBoolean(false);

    @Override
    @NotNull
    public CustomValue getNull() {
        return NULL;
    }

    @Override
    @NotNull
    public CustomValue createBoolean(boolean value) {
        return value ? TRUE : FALSE;
    }

    @Override
    @NotNull
    public CustomValue createNumber(@NotNull Number value) {
        return new SimpleNumber(value);
    }

    @Override
    @NotNull
    public CustomValue createString(@NotNull String value) {
        return new SimpleString(value);
    }

    @Override
    @NotNull
    public CustomValue createArray(CustomValue[] values) {
        return new SimpleArray(values);
    }

    @Override
    @NotNull
    public CustomValue createObject(@NotNull Map<String, CustomValue> obj) {
        return new SimpleObject(obj);
    }
}
