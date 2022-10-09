package org.co;

public class Main {

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine(
                System.out::println,
                drink -> false,
                new DrinkReporter(System.out::println),
                drink -> {}
        );
        coffeeMachine.order(new Order(Drink.TEA, 2, 1d, true));
        coffeeMachine.order(new Order(Drink.COFFEE, 1, 0.2d, true));
        coffeeMachine.order(new Order(Drink.COFFEE, 1, 0.8d, true));
        coffeeMachine.order(new Order(Drink.COFFEE, 1, 0.9d, true));
        coffeeMachine.order(new Order(Drink.CHOCOLATE, 3, 0.8d, true));
        coffeeMachine.order(new Order(Drink.TEA, 0, 2d, true));
        coffeeMachine.order(new Order(Drink.JUICE, 0, 0.7, false));
        System.out.println("-------------------Reports-----------------");
        coffeeMachine.printReport();
    }

}
