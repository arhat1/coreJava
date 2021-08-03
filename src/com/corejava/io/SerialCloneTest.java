package com.corejava.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;


class SerialCloneable implements Cloneable, Serializable{
	public Object clone() throws CloneNotSupportedException{
		try {
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			try(ObjectOutputStream out = new ObjectOutputStream(bout)){
				out.writeObject(this);
			}
			try(InputStream bin = new ByteArrayInputStream(bout.toByteArray())){
				ObjectInputStream in = new ObjectInputStream(bin);
				return in.readObject();
			}
			
		}catch (IOException | ClassNotFoundException e) {
			CloneNotSupportedException e2 = new CloneNotSupportedException();
			e2.initCause(e);
			throw e2;
		}
	}	
}

class Employee2 extends SerialCloneable{
	private String name;
	private double salary;
	private LocalDate hireDate;
	public Employee2(String name, double salary, int year, int month, int day) {
		this.name = name;
		this.salary = salary;
		this.hireDate = LocalDate.of(year, month, day);
	}
	public String getName() {
		return name;
	}
	public double getSalary() {
		return salary;
	}
	public LocalDate getHireDate() {
		return hireDate;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + ", hireDate=" + hireDate + "]";
	}
	
	public void raiseSalary(double byPersent) {
		double raise = salary * byPersent / 100;
		salary += raise;
	}
		
}
public class SerialCloneTest {
	public static void main(String[] args) throws CloneNotSupportedException{
		Employee2 harry = new Employee2("Harry Hacker", 35000, 1989, 10, 1);
		Employee2 harry2 = (Employee2)harry.clone();
		harry.raiseSalary(10);
		System.out.println(harry);
		System.out.println(harry2);
		
	}

}
