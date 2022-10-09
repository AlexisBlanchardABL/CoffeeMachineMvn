package org.co;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DrinkReporterTest {

    @Mock
    private Printer printer;

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(printer);
    }

    @Test
    void shouldPrintReport() {
        DrinkReporter drinkReporter = new DrinkReporter(printer);
        drinkReporter.addDrink(Drink.TEA);
        drinkReporter.addDrink(Drink.TEA);
        drinkReporter.addDrink(Drink.COFFEE);
        drinkReporter.addDrink(Drink.COFFEE);
        drinkReporter.addDrink(Drink.COFFEE);
        drinkReporter.addDrink(Drink.CHOCOLATE);
        drinkReporter.addDrink(Drink.JUICE);

        drinkReporter.printReport();
        verify(printer).print("Number of TEA sold: 2 for a total of : 0.8 euros");
        verify(printer).print("Number of COFFEE sold: 3 for a total of : 1.8 euros");
        verify(printer).print("Number of CHOCOLATE sold: 1 for a total of : 0.5 euros");
        verify(printer).print("Number of JUICE sold: 1 for a total of : 0.6 euros");
        verify(printer).print("Total Revenue: 3.7 euros");
    }

}
