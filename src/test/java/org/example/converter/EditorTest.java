package org.example.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EditorTest {

    @Test void testAddDigit1() {
        Editor editor = new Editor();
        String expect = "";
        String actual = editor.getNumber();
        Assertions.assertEquals(expect, actual);
    }

    @Test void testAddDigit2() {
        Editor editor = new Editor();
        editor.addDigit(0);
        editor.addDigit(0);
        editor.addDigit(0);
        String expect = "0";
        String actual = editor.getNumber();
        Assertions.assertEquals(expect, actual);
    }

    @Test void testAddDigit3() {
        Editor editor = new Editor();
        editor.addDigit(0);
        editor.addDelim();
        editor.addDigit(0);
        editor.addDigit(0);
        editor.addDigit(0);
        String expect = "0.000";
        String actual = editor.getNumber();
        Assertions.assertEquals(expect, actual);
    }

    @Test void testAddDigit4() {
        Editor editor = new Editor();
        editor.addDigit(15);
        editor.addDigit(12);
        editor.addDigit(1);
        editor.addDelim();
        editor.addDigit(1);
        editor.addDigit(9);
        String expect = "FC1.19";
        String actual = editor.getNumber();
        Assertions.assertEquals(expect, actual);
    }

    @Test void testAddDigit5() {
        Editor editor = new Editor();
        Assertions.assertThrows(
                IllegalArgumentException.class,
                ()-> editor.addDigit(17)
        );
    }

    @Test void testAddDigit6() {
        Editor editor = new Editor();
        Assertions.assertThrows(
                IllegalArgumentException.class,
                ()-> editor.addDigit(-1)
        );
    }

    @Test void testAcc1() {
        Editor editor = new Editor();
        editor.addDelim();
        editor.addDigit(1);
        editor.addDigit(2);
        editor.addDigit(3);
        int expect = 3;
        int actual = editor.acc();
        Assertions.assertEquals(expect, actual);
    }

    @Test void testAcc2() {
        Editor editor = new Editor();
        editor.addDigit(15);
        editor.addDigit(12);
        editor.addDigit(1);
        editor.addDelim();
        editor.addDigit(1);
        editor.addDigit(9);
        int expect = 2;
        int actual = editor.acc();
        Assertions.assertEquals(expect, actual);
    }

    @Test void testAcc3() {
        Editor editor = new Editor();
        editor.addDigit(5);
        int expect = 0;
        int actual = editor.acc();
        Assertions.assertEquals(expect, actual);
    }

    @Test void testAddZero() {
        Editor editor = new Editor();
        editor.addDigit(1);
        editor.addZero();
        String expect = "10";
        String actual = editor.getNumber();
        Assertions.assertEquals(expect, actual);
    }

    @Test void testAddDelim1() {
        Editor editor = new Editor();
        editor.addDelim();
        String expect = "0.";
        String actual = editor.getNumber();
        Assertions.assertEquals(expect, actual);
    }

    @Test void testAddDelim2() {
        Editor editor = new Editor();
        editor.addDelim();
        editor.addDelim();
        String expect = "0.";
        String actual = editor.getNumber();
        Assertions.assertEquals(expect, actual);
    }

    @Test void testAddDelim3() {
        Editor editor = new Editor();
        editor.addDigit(15);
        editor.addDigit(15);
        editor.addDigit(15);
        editor.addDelim();
        editor.addDelim();
        editor.addDelim();
        editor.addDigit(15);
        editor.addDigit(15);
        editor.addDigit(15);
        editor.addDelim();
        editor.addDelim();
        editor.addDelim();
        String expect = "FFF.FFF";
        String actual = editor.getNumber();
        Assertions.assertEquals(expect, actual);
    }

    @Test void testAddDelim4() {
        Editor editor = new Editor();
        editor.addDigit(0);
        editor.addDelim();
        editor.addDelim();
        editor.addDelim();
        editor.addDigit(0);
        editor.addDelim();
        editor.addDelim();
        editor.addDelim();
        String expect = "0.0";
        String actual = editor.getNumber();
        Assertions.assertEquals(expect, actual);
    }

    @Test void testBs1() {
        Editor editor = new Editor();
        editor.bs();
        editor.bs();
        String expect = "0";
        String actual = editor.getNumber();
        Assertions.assertEquals(expect, actual);
    }

    @Test void testBs2() {
        Editor editor = new Editor();
        editor.bs();
        editor.addDigit(1);
        editor.addDigit(2);
        editor.bs();
        String expect = "1";
        String actual = editor.getNumber();
        Assertions.assertEquals(expect, actual);
    }

    @Test void testBs3() {
        Editor editor = new Editor();
        editor.addDigit(3);
        editor.addDigit(3);
        editor.addDigit(3);
        editor.addDelim();
        editor.bs();
        String expect = "333";
        String actual = editor.getNumber();
        Assertions.assertEquals(expect, actual);
    }

    @Test void testBs4() {
        Editor editor = new Editor();
        editor.addDigit(3);
        editor.addDigit(3);
        editor.addDigit(3);
        editor.addDelim();
        editor.addDigit(3);
        editor.addDigit(3);
        editor.addDigit(3);
        editor.bs();
        editor.bs();
        String expect = "333.3";
        String actual = editor.getNumber();
        Assertions.assertEquals(expect, actual);
    }

    @Test void testClear() {
        Editor editor = new Editor();
        editor.addDigit(3);
        editor.addDigit(3);
        editor.addDigit(3);
        editor.addDelim();
        editor.addDigit(3);
        editor.addDigit(3);
        editor.addDigit(3);
        editor.clear();
        String expect = "";
        String actual = editor.getNumber();
        Assertions.assertEquals(expect, actual);
    }
}
