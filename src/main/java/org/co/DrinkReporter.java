package org.co;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class DrinkReporter {

    private final Map<Drink, Double> amountReport = new HashMap<>();
    private final Map<Drink, Integer> numberReport = new HashMap<>();
    private final Printer printer;

    public DrinkReporter(Printer printer) {
        this.printer = printer;
    }

    public void addDrink(Drink drink) {
        if (this.amountReport.containsKey(drink)) {
            Double oldAmount = this.amountReport.get(drink);
            Integer oldNumber = this.numberReport.get(drink);
            this.amountReport.put(drink, add(oldAmount, drink.getPrice()));
            this.numberReport.put(drink, oldNumber + 1);
        } else {
            this.amountReport.put(drink, drink.getPrice());
            this.numberReport.put(drink, 1);
        }
    }

    public void printReport() {
        for (Drink drink : Drink.values()) {
            printer.print("Amount of " + drink.name() + " sold is : " + this.amountReport.getOrDefault(drink, 0d));
            printer.print("Number of " + drink.name() + " sold is : " + this.numberReport.getOrDefault(drink, 0));
        }
    }

    private static Double add(double previousAmount, double drinkPrice) {
        return BigDecimal.valueOf(previousAmount)
                .add(BigDecimal.valueOf(drinkPrice)).doubleValue();
    }

}
