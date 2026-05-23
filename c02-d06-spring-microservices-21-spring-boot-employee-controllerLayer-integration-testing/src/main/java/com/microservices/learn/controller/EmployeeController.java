package com.microservices.learn.controller;

import com.microservices.learn.bean.EmployeeBean;
import com.microservices.learn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/addEmployee")
    public ResponseEntity<EmployeeBean> addEmployee(@RequestBody EmployeeBean employeeBean){
        EmployeeBean employeeBean1 = employeeService.addEmployee(employeeBean);
        return new ResponseEntity<>(employeeBean1, HttpStatus.CREATED);
    }

    @GetMapping("/findEmployee/{empId}")
    public ResponseEntity<EmployeeBean> findEmployeeById(@PathVariable Integer empId){
        EmployeeBean employeeBean = employeeService.findEmployeeById(empId);
        if(employeeBean==null) return new ResponseEntity<>(employeeBean, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(employeeBean, HttpStatus.OK);
    }


    @GetMapping("/findAllEmployees")
    public ResponseEntity<List<EmployeeBean>> findAllEmployees(){
        List<EmployeeBean> employeeBeanList = employeeService.findAllEmployees();
        return new ResponseEntity<>(employeeBeanList, HttpStatus.OK);
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<EmployeeBean> updateEmployee(@RequestBody EmployeeBean employeeBean) {
        EmployeeBean employeeBean1 = employeeService.updateEmployee(employeeBean);
        if(employeeBean1==null) return new ResponseEntity<>(employeeBean1, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(employeeBean1, HttpStatus.OK);
    }

    @DeleteMapping("/deleteEmployee/{empId}")
    public ResponseEntity<EmployeeBean> deleteEmployee(@PathVariable Integer empId) {
        EmployeeBean employeeBean = employeeService.deleteEmployee(empId);
        if(employeeBean == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(employeeBean, HttpStatus.FOUND);
    }

}
