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
            this.numberReport.put(drink, oldNumber++);
        } else {
            this.amountReport.put(drink, drink.getPrice());
            this.numberReport.put(drink, 1);
        }
    }

    public void printReport() {
        for (Drink drink : Drink.values()) {
            double amountReport2 = this.amountReport.get(drink) != null ? this.amountReport.get(drink) : 0;
            printer.print("Amount of " + drink.name() + " sold is : " + amountReport2);
            double amountReport1 = this.numberReport.get(drink) != null ? this.numberReport.get(drink) : 0;
            printer.print("Number of " + drink.name() + " sold is : " + amountReport1);
        }
    }

    private static Double add(double previousAmount, double drinkPrice) {
        return BigDecimal.valueOf(previousAmount)
                .add(BigDecimal.valueOf(drinkPrice)).doubleValue();
    }

}
