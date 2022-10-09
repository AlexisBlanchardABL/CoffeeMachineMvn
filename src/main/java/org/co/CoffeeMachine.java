package org.co;


public class CoffeeMachine {

	private final DrinkMaker drinkMaker;
	private final BeverageQuantityChecker beverageQuantityChecker;
	private final DrinkReporter drinkReporter;
	private final EmailNotifier emailNotifier;

	public CoffeeMachine(
			DrinkMaker drinkMaker,
			BeverageQuantityChecker beverageQuantityChecker,
			DrinkReporter drinkReporter,
			EmailNotifier emailNotifier

	) {
		this.drinkMaker = drinkMaker;
		this.beverageQuantityChecker = beverageQuantityChecker;
		this.drinkReporter = drinkReporter;
		this.emailNotifier = emailNotifier;
	}

	public void order(Order order) {
		Drink drink = order.getDrink();
		if(isEmpty(drink)) {
			emailNotifier.notifyMissingDrink(order.getDrink().name());
			drinkMaker.receive("M:Sorry!! we have a shortage of " + order.getDrink().name() + ", an E-mail was sent to the company!");
			return;
		}
		if(order.missingAmount() > 0) {
			drinkMaker.receive("M:{the amount is lower than the drink price you need: " + order.missingAmount() +" }");
			return;
		}
		addDrinkToReport(drink);
		drinkMaker.receive(
				order.instruction()
		);
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
