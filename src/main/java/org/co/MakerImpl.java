package org.co;

import java.util.HashMap;
import java.util.Map;

public class MakerImpl implements Maker, EmailNotifier {

	private final Map<Drink, Double> amountReport = new HashMap<>();
	private final Map<Drink, Integer> numberReport = new HashMap<>();
	private final BeverageQuantityChecker beverageQuantityChecker;

	public MakerImpl(BeverageQuantityChecker beverageQuantityChecker) {
		this.beverageQuantityChecker = beverageQuantityChecker;
	}

	@Override
	public String transformer(Order order) {
		if(isEmpty(order.getDrink())) {
			notifyMissingDrink(order.getDrink().name());
			return "Sorry!! we have a shortage of " + order.getDrink().name() + ", an E-mail was sent to the company!";
		}
		if(order.missingAmount() > 0) {
			return "M:{the amount is lower than the drink price you need: " + order.missingAmount() +" }";
		}
		addDrinkToReport(order.getDrink(), order.getAmount());
		return new StringBuilder()
		.append(order.getDrink().getCommand())
		.append(order.isHot() ? "h": "")
		.append(":")
		.append(order.getSucre() > 0 ? order.getSucre() : "")
		.append(":")
		.append(order.getSucre() > 0 ? "0" : "").toString();
	}

	@Override
	public void addDrinkToReport(Drink drink,Double amount){
		if(!this.amountReport.containsKey(drink)){
			this.amountReport.put(drink, amount);
			this.numberReport.put(drink, 1);
		}else{
			Double oldAmount = this.amountReport.get(drink);
			Integer oldNumber = this.numberReport.get(drink);
			this.amountReport.put(drink, oldAmount+ amount);
			this.numberReport.put(drink, oldNumber++);
		}
	}
	@Override
	public void printReport(Drink drink){
		System.out.println("Amount of " + drink.name()+ " sold is : " + (this.amountReport.get(drink)!=null ? this.amountReport.get(drink): 0));
		System.out.println("Number of " + drink.name()+ " sold is : " + (this.numberReport.get(drink)!=null ? this.numberReport.get(drink):0));
	}
	@Override
	public Map<Drink, Double> getAmountReport(){
		return this.amountReport;
	}

	public Map<Drink, Integer> getNumberReport(){
		return this.numberReport;
	}

	public boolean isEmpty(Drink drink) {
		return beverageQuantityChecker.isEmpty(drink.name());
	}

	@Override
	public void notifyMissingDrink(String drink) {
		// send a message logic
	}


}
