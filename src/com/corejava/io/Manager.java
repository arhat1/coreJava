package com.corejava.io;

public class Manager extends Employee {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Employee secretary;
	public Manager(String name, int salary, int hireYear, int hireMonth, int hireDay) {
		super(name, salary, hireYear, hireMonth, hireDay);
	}
	public Employee getSecretary() {
		return secretary;		
	}
	public void setSecretary(Employee secretary) {
		this.secretary = secretary;
	}

}
