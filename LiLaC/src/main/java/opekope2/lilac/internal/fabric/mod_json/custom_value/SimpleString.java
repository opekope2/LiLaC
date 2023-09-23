package opekope2.lilac.internal.fabric.mod_json.custom_value;

public record SimpleString(String value) implements ISimpleCustomValue {
    @Override
    public CvType getType() {
        return CvType.STRING;
    }

    @Override
    public String getAsString() {
        return value;
    }
}
