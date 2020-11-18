package classworks.cw10;

/*
 * Single responsibility principle
 * Open-closed principle
 * Liskov substitution principle
 * Interface segregation principle
 * Dependency inversion principle
 */

import java.awt.*;

public final class Main {
    public static void main(final String[] args) {
        //final var square = new MutableSquare(10, 10, 100);
        //square.setWidth(200);

        final var point = new Point(10, 20);
        final var bluePoint = new ColorPoint(10, 20, Color.BLUE);
        final var redPoint = new ColorPoint(10, 20, Color.RED);

        System.out.println(bluePoint.equals(point));
        System.out.println(point.equals(redPoint));
        System.out.println(bluePoint.equals(redPoint));
    }
}
