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
        verify(printer).print("Number of THE sold is : 2");
        verify(printer).print("Number of COFFEE sold is : 3");
        verify(printer).print("Number of CHOCOLATE sold is : 1");
        verify(printer).print("Number of JUICE sold is : 1");
    }

}
