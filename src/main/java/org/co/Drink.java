package org.co;

public enum Drink {
	
	THE("T",0.4d),
	COFFEE("C",0.6d),
	CHOCOLATE("H",0.5d ),
	JUICE("O", 0.6d);

	private final String type;
	private final double price;

	Drink(String type,double price){
		this.type = type;
		this.price = price;
	}

	public boolean isPriceHigherThan(double amount) {
		return this.price > amount;
	}

	public double getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}

}
