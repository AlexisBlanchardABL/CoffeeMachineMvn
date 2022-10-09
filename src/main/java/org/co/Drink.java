package org.co;

public enum Drink {
	
	THE("T",0.4d),
	COFFEE("C",0.6d),
	CHOCOLATE("H",0.5d ),
	JUICE("O", 0.6d);

	private final String command;
	private final double price;

	Drink(String command, double price){
		this.command = command;
		this.price = price;
	}

	public double missingAmount(double amount) {
		return this.price - amount;
	}

	public String getCommand() {
		return command;
	}
}
