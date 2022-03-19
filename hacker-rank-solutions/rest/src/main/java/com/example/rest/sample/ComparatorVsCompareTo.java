package com.example.rest.sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ComparatorVsCompareTo {


    // Sort by name (Comparator), age (Comparable)
    public static void main(String[] args) {

        Date d = new Date(System.nanoTime());

        Employee e1 = new Employee("John", 10, d);

        e1.setName("");

        Employee e2 = new Employee("Doe", 12, d);
        Employee e3 = new Employee("Henry", 7, d);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(e1);
        employeeList.add(e2);
        employeeList.add(e3);

        for (Employee employee : employeeList) {
            System.out.println("Employee " + employee.toString());
        }

        Collections.sort(employeeList, ComparatorVsCompareTo::Comparator);

        for (Employee employee : employeeList) {
            System.out.println("Employee " + employee.toString());
        }

        Collector<Employee, ?, Integer> sumOfAge = Collectors.summingInt(Employee::getAge);

        employeeList.stream().collect(sumOfAge);


    }

    public static int Comparator(Employee e1, Employee e2) {

        return e1.getName().compareTo(e2.getName());
    }

    static final class Employee implements Comparable<Employee> {
        String name;
        Integer age;
        Date dob;

        public Employee(String name, Integer age, Date dob) {
            this.name = name;
            this.age = age;
            this.dob = dob;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Date getDob() {
            return dob;
        }

        public void setDob(Date dob) {
            this.dob = dob;
        }

        @Override
        public int compareTo(Employee o) {
            return this.age - o.age;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", dob=" + dob +
                    '}';
        }
    }


}



