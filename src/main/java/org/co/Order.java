package org.co;


public class Order {

	private final Drink drink;
	private final int sucre;
	private final double amount;

	private final boolean hot;
	public Order(Drink drink, int sucre, double amount, boolean hot) {
		this.drink = drink;
		this.sucre = sucre;
		this.amount = amount;
		this.hot = hot;
	}

	public double getAmount() {
		return amount;
	}

	public Drink getDrink() {
		return drink;
	}

	public int getSucre() {
		return sucre;
	}

	public boolean isHot() {
		return hot;
	}

	boolean isAmountLowerThanPrice() {
		return this.drink.isPriceHigherThan(amount);
	}

}
