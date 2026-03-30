package com.microservices.learn.controller;

import com.microservices.learn.bean.EmployeeBean;
import com.microservices.learn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/hello")
    public String hello() {
        return "Working!";
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<String> addEmployee(@RequestBody EmployeeBean employeeBean) {
        Integer empId = employeeService.addEmployee(employeeBean);
        return new ResponseEntity<>("Employee added with id: " + empId, HttpStatus.CREATED);
    }
}