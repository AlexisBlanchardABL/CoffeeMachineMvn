package org.co;

import java.math.BigDecimal;


public class Amount {

    private final Double value;

    public static Amount of(Double value) {
        if (value < 0) throw new IllegalArgumentException();
        return new Amount(value);
    }

    private Amount(Double value) {
        this.value = value;
    }

    public Amount add(Amount other) {
        return new Amount(
                BigDecimal.valueOf(value)
                .add(BigDecimal.valueOf(other.value))
                .doubleValue()
        );
    }


    public Double toDouble() {
        return this.value;
    }

    public Amount subtract(Amount other) {
        return new Amount(
                BigDecimal.valueOf(value)
                        .subtract(BigDecimal.valueOf(other.value))
                        .doubleValue()
        );
    }

}
