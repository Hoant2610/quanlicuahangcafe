package main;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coffee")
public class coffee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_coffee")
	protected int id_coffee;
	@Column(name = "namecoffee")
	protected String namecoffee;
	@Column(name = "price")
	protected int price;
	@Column(name = "description")
	protected String describe;
	public int getId_coffee() {
		return id_coffee;
	}
	public void setId_coffee(int id_coffee) {
		this.id_coffee = id_coffee;
	}
	public String getNamecoffee() {
		return namecoffee;
	}
	public void setNamecoffee(String namecoffee) {
		this.namecoffee = namecoffee;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getId_coffeeString() {
		return ""+String.valueOf(this.id_coffee);
	}
}
