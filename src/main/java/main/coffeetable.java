package main;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coffeetable")
public class coffeetable {
	@Id
	@Column(name="id_table")
	private int id_table;
	@Column(name="numberofseats")
	private int numberofseats;
	@Column(name="stt")
	private String stt;
	public int getId_table() {
		return id_table;
	}
	public void setId_table(int id_table) {
		this.id_table = id_table;
	}
	public int getNumberofseats() {
		return numberofseats;
	}
	public void setNumberofseats(int numberofseats) {
		this.numberofseats = numberofseats;
	}
	public String getStt() {
		return stt;
	}
	public void setStt(String stt) {
		this.stt = stt;
	}
	
}	
