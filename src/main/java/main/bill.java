package main;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bill")
public class bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_bill")
	private int id_bill;
	@Column(name = "id_order")
	private int id_order;
	public int getId_bill() {
		return id_bill;
	}
	public void setId_bill(int id_bill) {
		this.id_bill = id_bill;
	}
	public int getId_order() {
		return id_order;
	}
	public void setId_order(int id_order) {
		this.id_order = id_order;
	}
	
}
