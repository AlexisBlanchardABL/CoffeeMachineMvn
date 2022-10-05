package org.co;

import java.util.HashMap;
import java.util.Map;

public class MakerImpl implements Maker, BeverageQuantityChecker, EmailNotifier {

	private Map<String, Double> amountReport = new HashMap<>();
	private Map<String, Integer> numberReport = new HashMap<>();

	@Override
	public String transformer(Order order) {
		if(isEmpty(order.getDrink().type)){
			notifyMissingDrink(order.getDrink().name());
			return "Sorry!! we have a shortage of " + order.getDrink().name() + ", an E-mail was sent to the company!";
		}
		if(order.getAmount() < order.getDrink().price) {
			return "M:{the amount is lower than the drink price you need: " + ( order.getDrink().price - order.getAmount()) +" }";
		}
		StringBuilder res = new StringBuilder();
		res.append(order.getDrink().type)
				.append(order.isHot() ? "h": "")
				.append(":")
				.append(order.getSucre() > 0 ? order.getSucre() : "")
				.append(":")
				.append(order.getSucre() > 0 ? "0" : "");
		addDrinkToReport(order.getDrink(), order.getAmount());
		return res.toString();
	}
	@Override
	public void addDrinkToReport(Drink drink,Double amount){
		if(!this.amountReport.containsKey(drink.type)){
			this.amountReport.put(drink.type, amount);
			this.numberReport.put(drink.type, 1);
		}else{
			Double oldAmount = this.amountReport.get(drink.type);
			Integer oldNumber = this.numberReport.get(drink.type);
			this.amountReport.put(drink.type, oldAmount+ amount);
			this.numberReport.put(drink.type, oldNumber++);
		}
	}
	@Override
	public void printReport(Drink drink){
		System.out.println("Amount of " + drink.name()+ " sold is : " + (this.amountReport.get(drink.type)!=null ? this.amountReport.get(drink.type): 0));
		System.out.println("Number of " + drink.name()+ " sold is : " + (this.numberReport.get(drink.type)!=null ? this.numberReport.get(drink.type):0));
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
		return true;
	}

	@Override
	public void notifyMissingDrink(String drink) {
		// send a message logic
	}


}
