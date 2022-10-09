package org.co;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.co.Drink.COFFEE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MakerImplTest {

    private static final BeverageQuantityChecker DRINK_IS_MISSING = drink -> true;
    private static final BeverageQuantityChecker NO_DRINK_IS_MISSING = drink -> false;

    private Maker maker;

    @Mock
    private DrinkReporter drinkReporter;
    @Mock
    private EmailNotifier emailNotifier;


    @BeforeEach
    void setup() {
        maker = new MakerImpl(NO_DRINK_IS_MISSING, drinkReporter, emailNotifier);
    }

    @Test
    void transformer() {
        //Drink maker will make one orange juice
        Order juice = new Order(Drink.JUICE, 0, 1d, false);
        //Drink maker will make an extra hot coffee with no sugar
        Order hotCoffee = aCoffee();
        //Drink maker will make an extra hot chocolate with one sugar and a stick
        Order hotChocolate = new Order(Drink.CHOCOLATE,1, 2d, true );
        //The drink maker will make an extra hot tea with two sugar and a stick
        Order hotThea = new Order(Drink.THE, 2, 1d, true);
        Order Thea = new Order(Drink.THE, 2, 1d, false);


        assertEquals(maker.transformer(juice), "O::");
        assertEquals(maker.transformer(hotCoffee), "Ch::");
        assertEquals(maker.transformer(hotChocolate), "Hh:1:0");
        assertEquals(maker.transformer(hotThea), "Th:2:0");
        assertEquals(maker.transformer(Thea), "T:2:0");
    }

    @Test
    void shouldPrintReport() {
        maker.printReport();
        verify(drinkReporter).printReport();
    }

    @Test
    void shouldNotifyByMailWhenDrinkIsMissing() {
        maker = new MakerImpl(DRINK_IS_MISSING, drinkReporter, emailNotifier);
        String result = maker.transformer(aCoffee());
        verify(emailNotifier).notifyMissingDrink(COFFEE.name());
        assertEquals("Sorry!! we have a shortage of COFFEE, an E-mail was sent to the company!", result);
    }

    private static Order aCoffee() {
        return new Order(COFFEE, 0, COFFEE.getPrice(), true);
    }

}
