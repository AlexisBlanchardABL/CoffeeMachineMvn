package org.co;


public class Order {

	private final Drink drink;
	private final int sugar;
	private final Amount amount;
	private final boolean hot;

	public Order(Drink drink, int sugar, double amount, boolean hot) {
		this.drink = drink;
		this.sugar = sugar;
		this.amount = Amount.of(amount);
		this.hot = hot;
	}

	public Drink getDrink() {
		return drink;
	}

	double missingAmount() {
		return this.drink.missingAmount(amount);
	}

	String instruction() {
		return drink.getCommand() +
				(hot ? "h" : "") +
				":" +
				(sugar > 0 ? sugar : "") +
				":" +
				(sugar > 0 ? "0" : "");
	}

}
