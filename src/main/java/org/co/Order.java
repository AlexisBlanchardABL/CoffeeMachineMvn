package org.co;


public class Order {

	private Drink drink;
	private int sucre;
	private double amount;

	private boolean hot;
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

	public void setDrink(Drink drink) {
		this.drink = drink;
	}

	public int getSucre() {
		return sucre;
	}

	public void setSucre(int sucre) {
		this.sucre = sucre;
	}

	public boolean isHot() {
		return hot;
	}
}
