package org.co;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class DrinkReporter {

    private final Map<Drink, Double> drinkRevenue = new HashMap<>();
    private final Map<Drink, Integer> drinkCount = new HashMap<>();
    private final Printer printer;

    public DrinkReporter(Printer printer) {
        this.printer = printer;
    }

    public void addDrink(Drink drink) {
        if (this.drinkRevenue.containsKey(drink)) {
            Double previousRevenue = this.drinkRevenue.get(drink);
            Integer previousCount = this.drinkCount.get(drink);
            this.drinkRevenue.put(drink, add(previousRevenue, drink.getPrice()));
            this.drinkCount.put(drink, previousCount + 1);
        } else {
            this.drinkRevenue.put(drink, drink.getPrice());
            this.drinkCount.put(drink, 1);
        }
    }

    public void printReport() {
        String currency = "euros";
        for (Drink drink : Drink.values()) {
            Integer drinkCount = this.drinkCount.getOrDefault(drink, 0);
            Double drinkRevenue = this.drinkRevenue.getOrDefault(drink, 0d);
            printer.print(
                    "Number of " + drink.name() + " sold: " + drinkCount + " for a total of : " + drinkRevenue + " " + currency
            );
        }
        double totalRevenue = drinkRevenue.values().stream().mapToDouble(f -> f).sum();
        printer.print(
                "Total Revenue: " + totalRevenue + " " + currency
        );
    }

    private static Double add(double previousAmount, double drinkPrice) {
        return BigDecimal.valueOf(previousAmount)
                .add(BigDecimal.valueOf(drinkPrice)).doubleValue();
    }

}
