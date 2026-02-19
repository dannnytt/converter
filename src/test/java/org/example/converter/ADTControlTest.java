package org.example.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ADTControlTest {

    private ADTControl converter;

    @BeforeEach
    void setUp() {
        converter = new ADTControl();
    }

    @Test void testConstructorDefaultValues() {
        Assertions.assertEquals(10, converter.getPin());
        Assertions.assertEquals(16, converter.getPout());
        Assertions.assertEquals(ADTControl.State.EDIT, converter.getState());
    }

    @Test void testGettersAndSetters() {
        converter.setPin(5);
        converter.setPout(13);
        Assertions.assertEquals(5, converter.getPin());
        Assertions.assertEquals(13, converter.getPout());
    }
}
