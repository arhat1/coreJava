package com.corejava.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;


public class RandomAccessTest {

	public static void main(String[] args) throws IOException{
		final String fileName = "Employee2.data";
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Jim", 74000, 1987, 12, 13);
        staff[1] = new Employee("Allen", 50000, 1989, 8, 1);
        staff[2] = new Employee("Lucy", 40000, 1990, 3, 15);
        
        try(DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName))){
        	for(Employee e: staff) {
        		writeData(out, e);
        	}
        }
        
        try (RandomAccessFile in = new RandomAccessFile(fileName, "r")){
        	System.out.println(in.length());
        	int n = (int)(in.length() / Employee.RECORD_SIZE);
        	Employee[] newStaff = new Employee[n];
        	for (int i = n - 1; i >= 0; i--) {
        		newStaff[i] = new Employee();
        		in.seek(i * Employee.RECORD_SIZE);
        		newStaff[i] = readData(in); 
        	}
        	
        	for (Employee e: newStaff) {
        		System.out.println(e);
        	}
        }
	}
	
	public static void writeFixedString(String s, int size, DataOutput out) throws IOException {
		for(int i = 0; i < size; i++) {
			char ch = 0;
			if (i < s.length()) {
				ch = s.charAt(i);
			}
			out.writeChar(ch);
		}
	}
	
	public static String readFixedString(int size,  DataInput in) throws IOException{
		StringBuilder builder = new StringBuilder(size);
		int i = 0;
		boolean more = true;
		while (more && i < size) {
			char ch = in.readChar();
			i++;
			if (ch == 0) {
				more = false;
			}else {
				builder.append(ch);
			}
		}
		in.skipBytes(2 * (size -i));
		return builder.toString();
	}
	
	public static void writeData(DataOutput out, Employee e) throws IOException{
		writeFixedString(e.getName(), Employee.NAME_SIZE, out);
		out.writeDouble(e.getSalary());
		LocalDate hireDate = e.getHireDate();
		out.writeInt(hireDate.getYear());
		out.writeInt(hireDate.getMonthValue());
		out.writeInt(hireDate.getDayOfMonth());
	}
	
	public static Employee readData(DataInput in) throws IOException{
		String name = readFixedString(Employee.NAME_SIZE, in);
		double salary = in.readDouble();
		int year = in.readInt();
		int month = in.readInt();
		int day = in.readInt();
		System.out.printf("%s  %f  %d %d %d\n", name, salary, year, month-1, day);
		return new Employee(name, salary, year, month - 1, day);
		
	}


}
