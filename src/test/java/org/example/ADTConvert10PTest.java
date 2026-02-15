package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ADTConvert10PTest {

    @Test void testConvertToBase1() {
        double number = 123.123;
        int base = 12;
        int precision = 3;
        String expect = "A3.158";
        String actual = ADTConvert10P.convertToBase(number, base, precision);
        Assertions.assertEquals(expect, actual);
    }

    @Test void testConvertToBase2() {
        double number = -123.123;
        int base = 12;
        int precision = 3;
        String expect = "-A3.158";
        String actual = ADTConvert10P.convertToBase(number, base, precision);
        Assertions.assertEquals(expect, actual);
    }

    @Test void testConvertToBase3() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> ADTConvert10P.convertToBase(123.123, 17, 3)
        );
    }

    @Test void testConvertToBase4() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> ADTConvert10P.convertToBase(123.123, 12, 11)
        );
    }

    @Test void testDigitToChar1() {
       Assertions.assertEquals('5', ADTConvert10P.digitToChar(5));
   }

    @Test void testDigitToChar2() {
        Assertions.assertEquals('D', ADTConvert10P.digitToChar(13));
    }

    @Test void testDigitToChar3() {
       Assertions.assertThrows(
               IllegalArgumentException.class,
               () -> ADTConvert10P.digitToChar(-1)
       );
    }

    @Test void testDigitToChar4() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> ADTConvert10P.digitToChar(17)
        );
    }

    @Test void testIntegerToBaseString1() {
       Assertions.assertEquals("11C", ADTConvert10P.integerToBaseString(252, 15));
    }

    @Test void testIntegerToBaseString2() {
        Assertions.assertEquals("-11C", ADTConvert10P.integerToBaseString(-252, 15));
    }

    @Test void testIntegerToBaseString3() {
       Assertions.assertThrows(
               IllegalArgumentException.class,
               () -> ADTConvert10P.integerToBaseString(25, -1)
       );
    }

    @Test void testIntegerToBaseString4() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> ADTConvert10P.integerToBaseString(25, 17)
        );
    }

    @Test void testIntegerToBaseString5() {
       Assertions.assertEquals("0", ADTConvert10P.integerToBaseString(0, 16));
    }

    @Test void testIntegerToBaseString6() {
        Assertions.assertEquals("5", ADTConvert10P.integerToBaseString(5, 10));
    }

    @Test void testDoubleToBaseString1() {
        double number = 0.123;
        int base = 12;
        int precision = 3;
        String expect = "158";
        String actual = ADTConvert10P.doubleToBaseString(number, base, precision);
        Assertions.assertEquals(expect, actual);
    }

    @Test void testDoubleToBaseString2() {
        double number = 0.417;
        int base = 9;
        int precision = 5;
        String expect = "36688";
        String actual = ADTConvert10P.doubleToBaseString(number, base, precision);
        Assertions.assertEquals(expect, actual);
    }

    @Test void testDoubleToBaseString3() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> ADTConvert10P.doubleToBaseString(0.417, 17, 3)
        );
    }

    @Test void testDoubleToBaseString4() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> ADTConvert10P.doubleToBaseString(0.417, 16, 11)
        );
    }
}
