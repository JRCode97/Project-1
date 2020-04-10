package dev.rivera.entities;

public class Employee {
	private int eId;
	private String username;
	private String password;
	public Employee() {
		super();
	}
	public Employee(int eId, String username, String password) {
		super();
		this.eId = eId;
		this.username = username;
		this.password = password;
	}
	public int geteId() {
		return eId;
	}
	public void seteId(int eId) {
		this.eId = eId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Employee [eId=" + eId + ", username=" + username + ", password=" + password + "]";
	}
	
}
