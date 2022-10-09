package org.co;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmountTest {

    @Test
    void instantiateAnAmount() {
        Amount amount = Amount.of(6d);
        assertInstanceOf(Amount.class, amount);
    }

    @Test
    void cannotInstantiateNegativeValue() {
        assertThrows(RuntimeException.class, () -> Amount.of(-1d));
    }

    @Test
    void addAmounts() {
        assertNotEquals(0.41d, 0.4d + 0.01d); // is actually equal to 0.41000000000000003

        Double actual = Amount.of(0.2d).add(Amount.of(0.2d)).toDouble();
        assertEquals(0.4d, actual);
    }


    @Test
    void subtractAmounts() {
        assertNotEquals(0.6d, 0.6d - 0.2d); // is actually equal to 0.39999999999999997

        Double actual = Amount.of(0.6d).subtract(Amount.of(0.2d)).toDouble();
        assertEquals(0.4d, actual);
    }

}
