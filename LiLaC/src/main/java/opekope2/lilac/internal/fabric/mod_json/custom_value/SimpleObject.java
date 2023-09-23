package opekope2.lilac.internal.fabric.mod_json.custom_value;

import net.fabricmc.loader.api.metadata.CustomValue;
import net.fabricmc.loader.api.metadata.CustomValue.CvObject;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Map;

public record SimpleObject(Map<String, CustomValue> obj) implements ISimpleCustomValue, CvObject {
    @Override
    public CvType getType() {
        return CvType.OBJECT;
    }

    @Override
    public CvObject getAsObject() {
        return this;
    }

    @Override
    public int size() {
        return obj.size();
    }

    @Override
    public boolean containsKey(String key) {
        return obj.containsKey(key);
    }

    @Override
    public CustomValue get(String key) {
        return obj.get(key);
    }

    @Override
    @NotNull
    public Iterator<Map.Entry<String, CustomValue>> iterator() {
        return obj.entrySet().iterator();
    }
}
