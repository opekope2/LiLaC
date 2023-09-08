package opekope2.lilac.internal.fabric.mod_json.custom_value;

import net.fabricmc.loader.api.metadata.CustomValue;
import net.fabricmc.loader.api.metadata.CustomValue.CvArray;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Iterator;

public record SimpleArray(CustomValue[] values) implements ISimpleCustomValue, CvArray {
    @Override
    public CvType getType() {
        return CvType.ARRAY;
    }

    @Override
    public CvArray getAsArray() {
        return this;
    }

    @Override
    public int size() {
        return values.length;
    }

    @Override
    public CustomValue get(int index) {
        return values[index];
    }

    @Override
    @NotNull
    public Iterator<CustomValue> iterator() {
        return Arrays.stream(values).iterator();
    }
}
