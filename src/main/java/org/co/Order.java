package org.co;


public class Order {

	private final Drink drink;
	private final int sugar;
	private final double amount;

	private final boolean hot;
	public Order(Drink drink, int sugar, double amount, boolean hot) {
		this.drink = drink;
		this.sugar = sugar;
		this.amount = amount;
		this.hot = hot;
	}

	public double getAmount() {
		return amount;
	}

	public Drink getDrink() {
		return drink;
	}

	public int getSugar() {
		return sugar;
	}

	public boolean isHot() {
		return hot;
	}

	double missingAmount() {
		return this.drink.missingAmount(amount);
	}

}
