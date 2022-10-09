package org.co;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.co.Drink.COFFEE;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CoffeeMachineTest {

    private static final BeverageQuantityChecker DRINK_IS_MISSING = drink -> true;
    private static final BeverageQuantityChecker NO_DRINK_IS_MISSING = drink -> false;

    private CoffeeMachine coffeeMachine;

    @Mock
    private DrinkReporter drinkReporter;
    @Mock
    private EmailNotifier emailNotifier;
    @Mock
    private DrinkMaker drinkMaker;


    @BeforeEach
    void setup() {
        coffeeMachine = new CoffeeMachine(
                drinkMaker,
                NO_DRINK_IS_MISSING,
                drinkReporter,
                emailNotifier
        );
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(
                drinkReporter,
                emailNotifier,
                drinkMaker
        );
    }

    @Test
    void order() {
        //Drink maker will make one orange juice
        Order juice = new Order(Drink.JUICE, 0, 1d, false);
        //Drink maker will make an extra hot coffee with no sugar
        Order hotCoffee = aCoffee();
        //Drink maker will make an extra hot chocolate with one sugar and a stick
        Order hotChocolate = new Order(Drink.CHOCOLATE,1, 2d, true );
        //The drink maker will make an extra hot tea with two sugar and a stick
        Order hotTea = new Order(Drink.TEA, 2, 1d, true);
        Order tea = new Order(Drink.TEA, 2, 1d, false);

        coffeeMachine.order(juice);
        verify(drinkReporter).addDrink(juice.getDrink());
        verify(drinkMaker).receive("O::");

        coffeeMachine.order(hotCoffee);
        verify(drinkReporter).addDrink(hotCoffee.getDrink());
        verify(drinkMaker).receive("Ch::");

        coffeeMachine.order(hotChocolate);
        verify(drinkReporter).addDrink(hotChocolate.getDrink());
        verify(drinkMaker).receive("Hh:1:0");

        coffeeMachine.order(hotTea);
        verify(drinkReporter).addDrink(hotTea.getDrink());
        verify(drinkMaker).receive("Th:2:0");

        coffeeMachine.order(tea);
        verify(drinkReporter, times(2)).addDrink(tea.getDrink());
        verify(drinkMaker).receive("T:2:0");
    }

    @Test
    void orderWhenProvidedAmountIsLowerThanPrice() {
        Order hotCoffee = new Order(COFFEE, 0, 0.1, true);

        coffeeMachine.order(hotCoffee);
        verify(drinkMaker).receive("M:{the amount is lower than the drink price you need: 0.5 }");
    }

    @Test
    void shouldPrintReport() {
        coffeeMachine.printReport();
        verify(drinkReporter).printReport();
    }

    @Test
    void shouldNotifyByMailWhenDrinkIsMissing() {
        coffeeMachine = new CoffeeMachine(drinkMaker, DRINK_IS_MISSING, drinkReporter, emailNotifier);
        coffeeMachine.order(aCoffee());
        verify(emailNotifier).notifyMissingDrink(COFFEE.name());
        verify(drinkMaker).receive("M:Sorry!! we have a shortage of COFFEE, an E-mail was sent to the company!");
    }

    private static Order aCoffee() {
        return new Order(COFFEE, 0, COFFEE.getPrice(), true);
    }

}
