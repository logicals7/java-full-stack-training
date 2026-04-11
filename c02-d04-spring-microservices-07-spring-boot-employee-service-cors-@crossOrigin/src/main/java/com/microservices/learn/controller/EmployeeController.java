package com.microservices.learn.controller;

import com.microservices.learn.bean.EmployeeBean;
import com.microservices.learn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//CrossOrigin allows us identify another app running on a diff port on the same machine.
//you can mention the url as well in the params of annotation.
@CrossOrigin
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
    public ResponseEntity<EmployeeBean> findEmployee(@PathVariable Integer empId){
        return new ResponseEntity<>(employeeService.findEmployeeById(empId), HttpStatus.OK);
    }

    @GetMapping("/findEmployeeByName/{name}")
    public ResponseEntity<List<EmployeeBean>> findEmployeeByName(@PathVariable String name) {
        List<EmployeeBean> employeeBeanList = employeeService.findEmployeeByName(name);
        if (employeeBeanList == null || employeeBeanList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeBeanList, HttpStatus.OK);
    }

    @GetMapping("/findAllEmployees")
    public ResponseEntity<List<EmployeeBean>> findAllEmployees(){
        return new ResponseEntity<>(employeeService.findAllEmployees(), HttpStatus.OK);
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<EmployeeBean> updateEmployee(@RequestBody EmployeeBean employeeBean) {
        return new ResponseEntity<>(employeeService.updateEmployee(employeeBean), HttpStatus.OK);
    }

    @DeleteMapping("/deleteEmployee/{empId}")
    public ResponseEntity<EmployeeBean> deleteEmployee(@PathVariable Integer empId) {
        return new ResponseEntity<>(employeeService.deleteEmployee(empId), HttpStatus.OK);
    }

    @DeleteMapping("/deleteEmployeeByName/{employeeName}")
    public ResponseEntity<String> deleteEmployeeByName(@PathVariable String employeeName){
        Integer count = employeeService.deleteEmployesByName(employeeName);
        if (count==0) return new ResponseEntity<>("No Employee record Found!!", HttpStatus.NOT_FOUND);
        return new ResponseEntity<String>(count + " Employee record(s) deleted!!", HttpStatus.OK);
    }

}
