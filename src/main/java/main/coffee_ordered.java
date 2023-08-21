package main;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coffee_ordered")
public class coffee_ordered {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_coffee_ordered")
	private int id_coffee_ordered;
	@Column(name="quantity")
	private int quantity;
	@Column(name="id_coffee")
	private int id_coffee;
	@Column(name="id_order")
	private int id_order;
	@Column(name="date")
	private Date date;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getId_coffee_ordered() {
		return id_coffee_ordered;
	}
	public void setId_coffee_ordered(int id_coffee_ordered) {
		this.id_coffee_ordered = id_coffee_ordered;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getId_coffee() {
		return id_coffee;
	}
	public void setId_coffee(int id_coffee) {
		this.id_coffee = id_coffee;
	}
	public int getId_order() {
		return id_order;
	}
	public void setId_order(int id_order) {
		this.id_order = id_order;
	}
	
}
