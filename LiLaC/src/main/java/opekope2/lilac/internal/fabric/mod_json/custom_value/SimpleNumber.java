package opekope2.lilac.internal.fabric.mod_json.custom_value;

public record SimpleNumber(Number value) implements ISimpleCustomValue {
    @Override
    public CvType getType() {
        return CvType.NUMBER;
    }

    @Override
    public Number getAsNumber() {
        return value;
    }
}
