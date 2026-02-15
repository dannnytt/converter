package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ADTConvertP10Test {

    @Test void testDVal1() {
        String num = "123.321";
        int base = 4;
        double expect = 27.890625;
        double actual = ADTConvertP10.dval(num, base);
        Assertions.assertEquals(expect, actual, 0.00001);
    }

    @Test void testDVal2() {
        String num = "37.53";
        int base = 8;
        double expect = 31.671875;
        double actual = ADTConvertP10.dval(num, base);
        Assertions.assertEquals(expect, actual, 0.00001);
    }

    @Test void testDVal3() {
        String num = "A8F.9C9";
        int base = 16;
        double expect = 2703.611572265625;
        double actual = ADTConvertP10.dval(num, base);
        Assertions.assertEquals(expect, actual, 0.00001);
    }

    @Test void testDVal4() {
        String num = "0.23A5";
        int base = 13;
        double expect = 0.17632435839081264662;
        double actual = ADTConvertP10.dval(num, base);
        Assertions.assertEquals(expect, actual, 0.00001);
    }

    @Test void testDVal5() {
        String num = "9876";
        int base = 11;
        double expect = 13030;
        double actual = ADTConvertP10.dval(num, base);
        Assertions.assertEquals(expect, actual, 0.00001);
    }

    @Test void testDVal6() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> ADTConvertP10.dval("9876", -1)
        );
    }

    @Test void testDVal7() {
        Assertions.assertThrows(
                NumberFormatException.class,
                () -> ADTConvertP10.dval(".F", 16)
        );
    }

    @Test void testDVal8() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> ADTConvertP10.dval("FFF", 2)
        );
    }

    @Test void testCharToNum1() {
        int expect = 10;
        int actual = ADTConvertP10.charToNum('A');
        Assertions.assertEquals(expect, actual);
    }

    @Test void testCharToNum2() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> ADTConvertP10.charToNum('G'));
    }
}
