package com.example.rest.service;

import com.example.rest.Entity.Employee;
import com.example.rest.Entity.Employee2;
import com.example.rest.Entity.Employee3;
import com.example.rest.Repositories.EmployeeDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDatabase employeeDatabase;

    ArrayList<Employee2> employees2 = new ArrayList<>();

    public List<Employee3> getEmployees(){

        employees2.add(new Employee2("John", "Accountant"));
        employees2.add(new Employee2("Henry", "Finance"));

        ArrayList<Employee> employees = employeeDatabase.getEmployees();
        Map<String,Employee3> employee3Map = new HashMap<>();
        employees.forEach(employee -> {
            employee3Map.put(employee.getName(), new Employee3(employee.getName(), employee.getSalary(), null));
        });

        for (Employee2 employee2 : employees2) {
            Employee3 emp3 = employee3Map.get(employee2.getName());
            if(null != emp3) {
                emp3.setDepartment(employee2.getDepartment());
            }
        }

        return employee3Map.values().stream().collect(Collectors.toList());

    }
    private List<Employee> mergeSorted(List<Employee> e1, List<Employee> e2) {
        List<Employee> e3 = new ArrayList<>();


        int index1 = e1.size();
        int index2 = e2.size();

        int i=0;
        int j = 0;

        while(i < index1 || j < index2){
            if (i < index1){
                if(j < index2){
                    Employee emp1 = e1.get(i);
                    Employee emp2 = e2.get(j);

                    if (emp1.compareTo(emp2) < 0) {
                        e3.add(emp1);
                        ++i;
                    } else {
                        e3.add(emp2);
                        ++j;
                    }

                } else {
                    e3.add( e1.get(i));
                    ++i;
                }

            } else {
                e3.add( e2.get(j));
                ++j;
            }
        }
 return e3;
    }
}
