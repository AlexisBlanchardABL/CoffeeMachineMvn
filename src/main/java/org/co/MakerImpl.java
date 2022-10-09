package org.co;

import java.util.HashMap;
import java.util.Map;

public class MakerImpl implements Maker, BeverageQuantityChecker, EmailNotifier {

	private final Map<String, Double> amountReport = new HashMap<>();
	private final Map<String, Integer> numberReport = new HashMap<>();

	@Override
	public String transformer(Order order) {
		if(isEmpty(order.getDrink().getType())) {
			notifyMissingDrink(order.getDrink().name());
			return "Sorry!! we have a shortage of " + order.getDrink().name() + ", an E-mail was sent to the company!";
		}
		if(order.missingAmount() > 0) {
			return "M:{the amount is lower than the drink price you need: " + order.missingAmount() +" }";
		}
		addDrinkToReport(order.getDrink(), order.getAmount());
		return new StringBuilder()
		.append(order.getDrink().getType())
		.append(order.isHot() ? "h": "")
		.append(":")
		.append(order.getSucre() > 0 ? order.getSucre() : "")
		.append(":")
		.append(order.getSucre() > 0 ? "0" : "").toString();
	}

	@Override
	public void addDrinkToReport(Drink drink,Double amount){
		if(!this.amountReport.containsKey(drink.getType())){
			this.amountReport.put(drink.getType(), amount);
			this.numberReport.put(drink.getType(), 1);
		}else{
			Double oldAmount = this.amountReport.get(drink.getType());
			Integer oldNumber = this.numberReport.get(drink.getType());
			this.amountReport.put(drink.getType(), oldAmount+ amount);
			this.numberReport.put(drink.getType(), oldNumber++);
		}
	}
	@Override
	public void printReport(Drink drink){
		System.out.println("Amount of " + drink.name()+ " sold is : " + (this.amountReport.get(drink.getType())!=null ? this.amountReport.get(drink.getType()): 0));
		System.out.println("Number of " + drink.name()+ " sold is : " + (this.numberReport.get(drink.getType())!=null ? this.numberReport.get(drink.getType()):0));
	}
	@Override
	public Map<String, Double> getAmountReport(){
		return this.amountReport;
	}

	public Map<String, Integer> getNumberReport(){
		return this.numberReport;
	}

	@Override
	public boolean isEmpty(String drink) {
		return false;
	}

	@Override
	public void notifyMissingDrink(String drink) {
		// send a message logic
	}


}
