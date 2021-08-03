package com.corejava.io;

import java.io.Serializable;
import java.time.LocalDate;

public class Employee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final static int NAME_SIZE = 40;
	final static int RECORD_SIZE = 100;
    private String name;
    private double salary;
    private LocalDate hireDate;

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDate +
                '}';
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public Employee() {
		super();
	}

	public Employee(String name, double salary, int hireYear, int hireMonth, int hireDay){
        this.name = name;
        this.salary = salary;
        this.hireDate = LocalDate.of(hireYear, hireMonth, hireDay);
    }
	
	public void raiseSalary(int value) {
		this.salary = this.salary * (1 + value/100.0);
		System.out.println(this.salary);
	}
}
