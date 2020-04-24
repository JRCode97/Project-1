package dev.rivera.entities;

public class WorkForce {
	private String name;
	private int Budget;
	public WorkForce(String name, int budget) {
		super();
		this.name = name;
		Budget = budget;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBudget() {
		return Budget;
	}
	public void setBudget(int budget) {
		Budget = budget;
	}
	@Override
	public String toString() {
		return "WorkForce [name=" + name + ", Budget=" + Budget + "]";
	}
	
}
