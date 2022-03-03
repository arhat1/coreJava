package com.test.stream;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.lang.System.out;
public class EmployeeTest {
    public static void main(String[] args) {
        Employee[] employees = Employee.testDate();
        printEmployees(employees);
        out.println("---------------------分组后取deptId为200的----------------------------------");
        Map<Integer, List<Employee>> dept = Arrays.stream(employees).collect(Collectors.groupingBy(Employee::getDeptId));
        dept.get(200).forEach(e->out.println(e));
        out.println("---------------------分组后取deptId不为300的----------------------------------");
        Map<Boolean, List<Employee>> dept2 = Arrays.stream(employees).collect(
                Collectors.partitioningBy(d->d.getDeptId() == 300));
        dept2.get(false).forEach(e->out.println(e));

        out.println("---------------------分组后计算每个部门人数----------------------------------");
        Map<Integer, Long> dept_employees = Arrays.stream(employees).collect(
                Collectors.groupingBy(Employee::getDeptId, Collectors.counting()));
        out.println(dept_employees);

        out.println("---------------------分组后计算每个部门总薪水----------------------------------");
        Map<Integer, Double> dept_salarys = Arrays.stream(employees).collect(
                Collectors.groupingBy(Employee::getDeptId, Collectors.summingDouble(Employee::getSalary))
        );
        out.println(dept_salarys);

        out.println("---------------------分组后计算每个部门平均薪水----------------------------------");
        Map<Integer, Double> dept_ave = Arrays.stream(employees).collect(
                Collectors.groupingBy(Employee::getDeptId, Collectors.averagingDouble(Employee::getSalary))
        );
        out.println(dept_ave);


    }



    public static void printEmployees(Employee[] employees){
        Arrays.stream(employees).forEach(e->out.printf("%s|%s|%s|%s|%f\n", e.getEmpId(),e.getDeptId(), e.getName(), e.getHireDate(),e.getSalary()));
    }
}
