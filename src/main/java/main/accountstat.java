package main;

public class accountstat extends account{
	private account a;
	private String fullname;
	public int getId_account() {
		return a.id_account;
	}
	public void setId_account(int id_account) {
		a.id_account = id_account;
	}
	public String getRole() {
		return a.role;
	}
	public void setRole(String role) {
		a.role = role;
	}
	public String getUsername() {
		return a.username;
	}
	public void setUsername(String username) {
		a.username = username;
	}
	public String getPassword() {
		return a.password;
	}
	public void setPassword(String password) {
		a.password = password;
	}
	public account getA() {
		return a;
	}
	public void setA(account a) {
		this.a = a;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
}
