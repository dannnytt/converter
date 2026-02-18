package org.example.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HistoryTest {

    @Test void testAddRecord1() {
        History history = new History();
        history.addRecord(12, 4, "23.42", "52.42");

        History.Record expect = new History.Record(12, 4, "23.42", "52.42");
        History.Record actual = history.get(0);

        Assertions.assertEquals(expect, actual);
    }

    @Test void testAddRecord2() {
        History history = new History();
        history.addRecord(3, 7, "11.11", "11.11");

        History.Record expect = new History.Record(3, 7, "11.11", "11.11");
        History.Record actual = history.get(0);

        Assertions.assertEquals(expect, actual);
    }

    @Test void testOverride1() {
        History history = new History();
        history.addRecord(12, 4, "23.42", "52.42");
        history.addRecord(12, 4, "23.42", "52.42");
        history.addRecord(12, 4, "11", "11");

        History.Record expectedValue = new History.Record(12, 4, "11", "11");
        History.Record actualValue = history.get(2);

        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test void testOverride2() {
        History history = new History();
        history.addRecord(12, 4, "23.42", "52.42");
        history.addRecord(12, 4, "23.42", "52.42");
        history.addRecord(12, 4, "11", "11");

        History.Record toOverride = new History.Record(1, 1, "1", "1");
        history.set(1, toOverride);

        History.Record expectedValue = new History.Record(1, 1, "1", "1");
        History.Record actualValue = history.get(1);

        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test void testOverride3() {
        History history = new History();
        history.addRecord(3, 7, "11.11", "11.11");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> history.get(-1));
    }

    @Test void testOverride4() {
        History history = new History();
        history.addRecord(3, 7, "11.11", "11.11");

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> history.get(1));
    }

    @Test void testOverride5() {
        History history = new History();
        History.Record value = new History.Record(12, 4, "11", "11");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> history.set(0, value));
    }
}
