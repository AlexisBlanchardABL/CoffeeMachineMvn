package org.co;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DrinkReporterTest {

    @Mock
    private Printer printer;

    @Test
    void shouldPrintReport() {
        DrinkReporter drinkReporter = new DrinkReporter(printer);
        drinkReporter.addDrink(Drink.THE);
        drinkReporter.addDrink(Drink.THE);
        drinkReporter.addDrink(Drink.COFFEE);
        drinkReporter.addDrink(Drink.COFFEE);
        drinkReporter.addDrink(Drink.COFFEE);
        drinkReporter.addDrink(Drink.CHOCOLATE);
        drinkReporter.addDrink(Drink.JUICE);

        drinkReporter.printReport();
        verify(printer).print("Amount of THE sold is : 0.8");
        verify(printer).print("Amount of COFFEE sold is : 1.8");
        verify(printer).print("Amount of CHOCOLATE sold is : 0.5");
        verify(printer).print("Amount of JUICE sold is : 0.6");
    }

}
