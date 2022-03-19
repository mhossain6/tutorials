package com.example.rest.Entity;

import org.springframework.stereotype.Component;

@Component
public class Employee3 {
    String name;
    Double salary;
    String Department;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public Employee3(String name, Double salary, String department) {
        this.name = name;
        this.salary = salary;
        Department = department;
    }
}
