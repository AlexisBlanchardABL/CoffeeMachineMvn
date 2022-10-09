package org.co;


public class MakerImpl implements Maker {

	private final BeverageQuantityChecker beverageQuantityChecker;
	private final DrinkReporter drinkReporter;
	private final EmailNotifier emailNotifier;

	public MakerImpl(
			BeverageQuantityChecker beverageQuantityChecker,
			DrinkReporter drinkReporter,
			EmailNotifier emailNotifier
	) {
		this.beverageQuantityChecker = beverageQuantityChecker;
		this.drinkReporter = drinkReporter;
		this.emailNotifier = emailNotifier;
	}

	@Override
	public String transformer(Order order) {
		if(isEmpty(order.getDrink())) {
			emailNotifier.notifyMissingDrink(order.getDrink().name());
			return "Sorry!! we have a shortage of " + order.getDrink().name() + ", an E-mail was sent to the company!";
		}
		if(order.missingAmount() > 0) {
			return "M:{the amount is lower than the drink price you need: " + order.missingAmount() +" }";
		}
		addDrinkToReport(order.getDrink());
		return new StringBuilder()
		.append(order.getDrink().getCommand())
		.append(order.isHot() ? "h": "")
		.append(":")
		.append(order.getSucre() > 0 ? order.getSucre() : "")
		.append(":")
		.append(order.getSucre() > 0 ? "0" : "").toString();
	}

	private void addDrinkToReport(Drink drink){
		drinkReporter.addDrink(drink);
	}

	public void printReport(){
		drinkReporter.printReport();
	}

	public boolean isEmpty(Drink drink) {
		return beverageQuantityChecker.isEmpty(drink.name());
	}

}
