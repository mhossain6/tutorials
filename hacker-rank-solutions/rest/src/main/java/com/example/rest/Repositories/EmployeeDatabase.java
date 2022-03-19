package com.example.rest.Repositories;

import com.example.rest.Entity.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class EmployeeDatabase {

    ArrayList<Employee> employees = new ArrayList<>();

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public void initDatabase() {
        employees.add(new Employee("John", 10000.0));
        employees.add(new Employee("Henry", 20000.0));
    }
}
