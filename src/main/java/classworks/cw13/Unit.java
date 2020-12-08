package classworks.cw13;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

public enum Unit {
    METER(1),
    KILOMETER(1e3),
    MILLIMETER(1e-3),
    INCH(254e-4),
    FEET(254e-4 * 12);

    private final double meters;

    Unit(final double meters) {
        this.meters = meters;
    }

    public double getMeters() {
        return meters;
    }

    public static void main(final String[] args) {
        final var russian = new EnumMap<>(Map.of(
            METER, "м",
            KILOMETER, "км",
            MILLIMETER, "мм",
            INCH, "'",
            FEET, "фт"
        ));

        final var units = EnumSet.allOf(Unit.class);

        for (final var unit : units) {
            System.out.println("1 " + russian.get(unit) + " = " + unit.getMeters() + " " + russian.get(METER));
        }
    }
}
