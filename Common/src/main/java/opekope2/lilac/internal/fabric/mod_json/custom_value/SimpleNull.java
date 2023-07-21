package opekope2.lilac.internal.fabric.mod_json.custom_value;

public record SimpleNull() implements ISimpleCustomValue {
    @Override
    public CvType getType() {
        return CvType.NULL;
    }
}
