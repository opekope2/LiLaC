package opekope2.lilac.internal.fabric.mod_json.custom_value;

public record SimpleBoolean(boolean value) implements ISimpleCustomValue {
    @Override
    public CvType getType() {
        return CvType.BOOLEAN;
    }

    @Override
    public boolean getAsBoolean() {
        return value;
    }
}
