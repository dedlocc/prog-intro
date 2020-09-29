package classworks.cw4;

import classworks.cw4.circle.ICircle;
import classworks.cw4.circle.MCircle;
import classworks.cw4.vector.IVector;
import classworks.cw4.vector.MVector;

public final class Main {
    public static void main(String[] args) {
        {
            var center = IVector.fromCartesian(1, 1);
            var c = new ICircle(center, 10);
            var c2 = c.shift(IVector.fromCartesian(1, 0));

            System.out.println(c);
            System.out.println(c2);
        }

        {
            var center = new MVector(1, 1);
            var c = new MCircle(center, 10);
            center.add(new MVector(1, 1));
            c.shift(new MVector(1, 0));
            c.getCenter().add(new MVector(1, 1));

            System.out.println(c);
        }
    }
}
