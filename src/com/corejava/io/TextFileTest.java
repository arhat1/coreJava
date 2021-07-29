package com.corejava.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Scanner;

public class TextFileTest {
    public static class Employee{
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

        public Employee(String name, double salary, int hireYear, int hireMonth, int hireDay){
            this.name = name;
            this.salary = salary;
            this.hireDate = LocalDate.of(hireYear, hireMonth, hireDay);
        }
    }

    public static Employee readEmployee(Scanner in){
        String line = in.nextLine();
        String[] tokens = line.split("\\|");
        String name= tokens[0];
        double salary = Double.parseDouble(tokens[1]);
        LocalDate hireDate = LocalDate.parse(tokens[2]);
        int year = hireDate.getYear();
        int month = hireDate.getMonthValue();
        int day = hireDate.getDayOfMonth();
        return new Employee(name, salary, year, month, day);
    }

    public static void writeEmployee(PrintWriter out, Employee e){
        out.println(e.getName() + "|" +   e.getSalary() + "|" + e.getHireDate());
    }

    public static void writeData(Employee[] employees, PrintWriter out) {
        out.println(employees.length);
        for(Employee e: employees){
            writeEmployee(out, e);
        }
    }

    public static Employee[] readData(Scanner in){
        int n = in.nextInt();
        in.nextLine();
        Employee[] employees = new Employee[n];
        for(int i=0; i < n; i++){
            employees[i] = readEmployee(in);
        }
        return employees;
    }

    public static void main(String[] args) throws IOException {
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("张三", 74000, 1987, 12, 13);
        staff[1] = new Employee("李四", 50000, 1989, 8, 1);
        staff[2] = new Employee("王麻子", 40000, 1990, 3, 15);
        try(PrintWriter out = new PrintWriter("employee.data", StandardCharsets.UTF_8)){
            writeData(staff, out);
        }


        try(Scanner in = new Scanner(new FileInputStream("employee.data"), StandardCharsets.UTF_8)){
            Employee[] newStaff = readData(in);
            for (Employee e: newStaff){
                System.out.println(e);
            }
        }
    }
}
