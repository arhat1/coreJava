package com.test.stream;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Employee {
    private Integer empId;
    private Integer deptId;
    private String name;
    private LocalDate hireDate;
    private Double salary;

    public Employee(Integer empId, Integer deptId, String name, LocalDate hireDate, Double salary) {
        this.empId = empId;
        this.deptId = deptId;
        this.name = name;
        this.hireDate = hireDate;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", deptId=" + deptId +
                ", name='" + name + '\'' +
                ", hireDate =" + hireDate +
                ", salary=" + salary +
                '}';
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public static Employee[] testDate(){
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, 100, "张三", LocalDate.of(1999,1,1),9000.0));
        employees.add(new Employee(2, 100, "李四", LocalDate.of(1999,1,2),10000.0));
        employees.add(new Employee(3, 100, "王麻子", LocalDate.of(2001,12,13),8000.0));
        employees.add(new Employee(4, 200, "Allen", LocalDate.of(1998,5,2),12000.0));
        employees.add(new Employee(5, 200, "Jim", LocalDate.of(2021,3,5),5000.0));
        employees.add(new Employee(6, 300, "Frank", LocalDate.of(1995,4,12),18000.0));
        return employees.toArray(new Employee[employees.size()]);
    }
}
