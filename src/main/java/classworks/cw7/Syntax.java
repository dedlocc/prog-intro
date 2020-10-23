package classworks.cw7;

public final class Syntax {
    public static void main(String[] args) {
        // Line comment \u000a System.out.println("Hello");
        System.out.println(Character.isJavaIdentifierStart('$'));
        System.out.println(Character.isJavaIdentifierPart('0'));
        привет();
        variables();
        operators();
    }

    private static void operators() {
        // Postfix   ++ --
        int i = 0;
        System.out.println(i++);
        System.out.println(i--);
//        System.out.println((i++)++);

        // Unary
        // Prefix ++, --; +, -, ~
        System.out.println(i);      // 0
        System.out.println(++i);    // 1
        System.out.println(--i);    // 0
        System.out.println(+i);     // 0
        System.out.println(-i);     // 0
        System.out.println(~i);     // -1
        System.out.println(-++i);   // -1

        // Multiplicative
        System.out.println(10 * 20);
        System.out.println(20 / 3);     // 6
        System.out.println(-20 / 3);    // -6
        System.out.println(20 / -3);    // -6
        System.out.println(-20 / -3);   // 6
        System.out.println(20 % 3);     // 2
        System.out.println(20 % -3);    // 2
        System.out.println(-20 % 3);    // -2
        System.out.println(-20 % -3);   // -2

        // a / b * b + a % b == a
        // a % b == a - (int) (a / b) * b;

        System.out.println(Math.PI / Math.E);
        System.out.println(Math.PI % Math.E);

        // Additive
        System.out.println(10 + 20);
        System.out.println(10 - 20);
        i = 0;
        System.out.println(i++ + ++i);

        // Shift
        System.out.println(Integer.toString(0b101100 >> 2, 2));
        System.out.println(Integer.toString(0b101100 >>> 2, 2));
        System.out.println(Integer.toString(0b101100 << 2, 2));
        System.out.println(Integer.toString(-50 >> 2, 2));
        System.out.println(Integer.toString(-50 >>> 2, 2));
    }

    public static void types() {
        primitives();
        referenced();

    }

    public static void referenced() {
//        Void[] hellos = new Void[0];
//        String[] strings = {"hello", "world"};
//        Object[] objects = strings;
//        Object integer = new Integer(10);
//        String string = (String) new Integer(10);
//        strings = (String[]) objects;
//
//        final Object string = objects[1];
//        objects[1] = Integer.MAX_VALUE;
//        // Reifiable
//        Number[] numbers = new Integer[10];
//        System.out.println(string);
//        Object obj = new int[10];
////        System.arraycopy();
//        System.out.println(new int[10].hashCode());
    }

    public strictfp static void primitives() {
        // Integral
        byte[] bytes = new byte[100];
        //  Name    Bytes   Wrapper
        //  byte    1       Byte
        //  short   2       Short
        //  int     4       Integer
        //  long    8       Long
        //  char    2       Character

//        int min = Integer.MAX_VALUE;
//        Integer i1 = Integer.valueOf(10);
//        Integer i2 = new Integer(10);
//        int i3 = Integer.parseInt("10");
//        int i4 = Integer.valueOf("10");
//        long z = i1.longValue();
//        System.out.println(i1 == i2);

//        int a = 1_2_3;
//        int q = 0x1234abcd;
//        int p = 0b1000_1100_1110_1111;
//        System.out.println(q + " " + p);
//        long l = 12345678909L;

        // Floating point           Wrapper
        // float    32  binary32    Float
        // double   64  binary64    Double

//        float f = 1.1f;
//        double d = 0x1.1p10;
//        System.out.println(Double.MAX_VALUE + " " + Double.MIN_VALUE);
//        System.out.println(Double.POSITIVE_INFINITY + Double.NEGATIVE_INFINITY);
//        System.out.println(Double.NaN);

//        byte b1 = 1;
//        byte b2 = 2;
//        byte b3 = b1 + b2;

//        int i = 1234567890;
//        float j = i;
//        System.out.println(i + " " + j);

//        float sum = 0;
//        for (float i = 0; i < Integer.MAX_VALUE; ++i) {
//            sum += i + i + i;
//        }
//        System.out.println(sum);

        // Boolean
//        boolean b1 = true;
//        boolean b2 = false;
//        Boolean B1 = Boolean.TRUE;
//        Boolean B2 = Boolean.FALSE;
//        System.out.println(b1 + b2);
//        System.out.println(b1 + 10);
    }

    private static void variables() {
//        final int a;
//
//        try {
//            a = 10;
//        } catch (Throwable e) {
//            a = 20;
//        }

//        if (new Random().nextBoolean()) {
//            a = 10;
//        } else {
//            a = 20;
//        }
    }

    private static void привет() {
        System.out.println("Hello");
    }
}
