package main;

public class coffeestat extends coffee{
	private coffee c;
	private long totalrevenue;
	private int totalturn;
	private int quantity;
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalturn() {
		return totalturn;
	}

	public void setTotalturn(int totalturn) {
		this.totalturn = totalturn;
	}

	public long getTotalrevenue() {
		return totalrevenue;
	}

	public void setTotalrevenue(long totalrevenue) {
		this.totalrevenue = totalrevenue;
	}

	public coffee getC() {
		return c;
	}

	public void setC(coffee c) {
		this.c = c;
	}
	public int getId_coffee() {
		return c.id_coffee;
	}
	public void setId_coffee(int id_coffee) {
		c.id_coffee = id_coffee;
	}
	public String getNamecoffee() {
		return c.namecoffee;
	}
	public void setNamecoffee(String namecoffee) {
		c.namecoffee = namecoffee;
	}
	public int getPrice() {
		return c.price;
	}
	public void setPrice(int price) {
		c.price = price;
	}
	public String getDescribe() {
		return c.describe;
	}
	public void setDescribe(String describe) {
		c.describe = describe;
	}
}
