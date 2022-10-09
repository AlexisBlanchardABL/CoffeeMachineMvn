package org.co;

import java.util.Map;

public interface Maker {

	String transformer(Order order);
	void addDrinkToReport(Drink drink,Double amount);
	void printReport(Drink drink);

	Map<String, Double>  getAmountReport();

}
