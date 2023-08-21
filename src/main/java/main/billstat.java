package main;

import java.util.Date;

public class billstat extends bill{
	
	private bill b;
	private String nameclient;
	private String date;
	private int totalturn;
	private long totalrevenue;
	private int price;
	private int id_bill;
	private String telephone;
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getId_bill() {
		return id_bill;
	}
	public void setId_bill(int id_bill) {
		this.id_bill = id_bill;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public bill getB() {
		return b;
	}
	public void setB(bill b) {
		this.b = b;
	}
	public String getNameclient() {
		return nameclient;
	}
	public void setNameclient(String nameclient) {
		this.nameclient = nameclient;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	
}
