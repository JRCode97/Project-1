package dev.rivera.entities;

public class Employee {
	private int eId;
	private String name;
	private String username;
	private String password;
	private String workForce;
	public Employee() {
		super();
	}
	public Employee(int eId, String username, String password,String name,String workForce) {
		super();
		this.eId = eId;
		this.username = username;
		this.password = password;
		this.name = name;
		this.workForce = workForce;
	}
	public String getWorkForce() {
		return workForce;
	}
	public void setWorkForce(String workForce) {
		this.workForce = workForce;
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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Employee [eId=" + eId + ", name=" + name + ", username=" + username + ", password=" + password
				+ ", workForce=" + workForce + "]";
	}
	
	
}
