package com.example.rest.Controllers;


import com.example.rest.Entity.Employee3;
import com.example.rest.Repositories.EmployeeDatabase;
import com.example.rest.Entity.Employee;
import com.example.rest.Entity.Employee2;
import com.example.rest.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public List<Employee3> getEmployees(){
        logger.info("Somelog");

        return  employeeService.getEmployees();
    }
}
