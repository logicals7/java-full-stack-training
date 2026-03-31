package com.microservices.learn.controller;

import com.microservices.learn.bean.EmployeeBean;
import com.microservices.learn.entity.EmployeeEntity;
import com.microservices.learn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/hello")
    public String hello() {
        return "Working!";
    }

    @GetMapping("/findEmployeeById/{empId}")
    public ResponseEntity<EmployeeBean> findEmployeeById(@PathVariable("empId") Integer empId){
        EmployeeBean employeeBean = employeeService.findEmployeeById(empId);
        if (employeeBean == null) return new ResponseEntity<EmployeeBean>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<EmployeeBean>(employeeBean, HttpStatus.OK);
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<String> addEmployee(@RequestBody EmployeeBean employeeBean) {
        Integer empId = employeeService.addEmployee(employeeBean);
        return new ResponseEntity<>("Employee added with id: " + empId, HttpStatus.CREATED);
    }

    @GetMapping("/findAllEmployees")
    public ResponseEntity<List<EmployeeBean>> findAllEmployees(){
        List<EmployeeBean> employeeBeanList = employeeService.findAllEmployees();
        return new ResponseEntity<List<EmployeeBean>>(employeeBeanList, HttpStatus.OK);
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<EmployeeBean> updateEmployee(@RequestBody EmployeeBean employeeBean){
        EmployeeBean employeeBean1 = employeeService.updateEmployee(employeeBean);
        return new ResponseEntity<EmployeeBean>(employeeBean1, HttpStatus.FOUND);
    }

    @DeleteMapping("/deleteEmployee/{empId}")
    public ResponseEntity<EmployeeBean> deleteEmployee(@PathVariable("empId") Integer empid){
        EmployeeBean employeeBean = employeeService.deleteEmployee(empid);
        if (employeeBean == null) return new ResponseEntity<EmployeeBean>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<EmployeeBean>(employeeBean, HttpStatus.FOUND);
    }

    @GetMapping("/findEmployeeBySalaryGreaterThan/{salary}")
    public ResponseEntity<List<EmployeeBean>> findBySalary(@PathVariable("salary") Double salary){
        List<EmployeeBean> employeeBeanList = employeeService.findEmployeeBySalaryGreaterThan(salary);
        return new ResponseEntity<List<EmployeeBean>>(employeeBeanList, HttpStatus.OK);
    }
}