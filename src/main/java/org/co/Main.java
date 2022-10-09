package org.co;

public class Main {

    public static void main(String[] args) {
        Maker maker = new MakerImpl(
                drink -> false,
                new DrinkReporter(System.out::println),
                drink -> {}
        );
        System.out.println(maker.transformer(new Order(Drink.THE, 2, 1d, true)));
        System.out.println(maker.transformer(new Order(Drink.COFFEE, 1, 0.2d, true)));
        System.out.println(maker.transformer(new Order(Drink.COFFEE, 1, 0.8d, true)));
        System.out.println(maker.transformer(new Order(Drink.COFFEE, 1, 0.9d, true)));
        System.out.println(maker.transformer(new Order(Drink.CHOCOLATE, 3, 0.8d, true)));
        System.out.println(maker.transformer(new Order(Drink.THE, 0, 2d, true)));
        System.out.println(maker.transformer(new Order(Drink.JUICE, 0, 0.7, false)));
        System.out.println("-------------------Reports-----------------");
        maker.printReport();
    }

}
