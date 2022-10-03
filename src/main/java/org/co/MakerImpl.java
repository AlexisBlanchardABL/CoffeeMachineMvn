package org.co;

public class MakerImpl implements Maker {

	@Override
	public String transformer(Order order) {
		
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
		return res.toString();
	}

}
