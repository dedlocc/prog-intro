package classworks.cw13;

public class Length {
    private final double value;
    private final Unit unit;

    public Length(final double value, final Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public Unit getUnit() {
        return unit;
    }

    Length convertTo(final Unit unit) {
        return new Length(value * this.unit.getMeters() / unit.getMeters(), unit);
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }

    public static void main(final String[] args) {
        System.out.println(new Length(.1, Unit.METER).convertTo(Unit.INCH));
    }

    public static String getEnglishName(final Unit unit) {
        return switch (unit) {
            case METER -> "m";
            case KILOMETER -> "km";
            case MILLIMETER -> "mm";
            case INCH -> "'";
            case FEET -> "feet";
            default -> {
                //if (unit.name().equals("hello")) {
                //    return "zzz";
                //}
                // More code

                yield "unknown unit";
            }
        };
    }
}
