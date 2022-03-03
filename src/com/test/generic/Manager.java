package com.test.generic;

public class Manager extends Employee{
    private String duty;

    public Manager(String name, Double salary, String duty) {
        super(name, salary);
        this.duty = duty;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "name='" + super.getName() + '\'' +
                "，duty='" + duty + '\'' +
                '}';
    }
}
