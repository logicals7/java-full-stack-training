package com.microservices.learn.controller;

import com.microservices.learn.bean.EmployeeBean;
import com.microservices.learn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/addEmployee")
    public ResponseEntity<String> addEmployee(@Valid @RequestBody EmployeeBean employeeBean, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity<>(errors.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
        }
        //Of course, the below line is not being used now.
        // Because addEmployee is returning EmployeeBean.
        // Otherwise, we would have change the return type of EmployeeDAOWrapper, EmployeeServiceImpl.
        // But for now its okay.
        EmployeeBean employeeBean1 = employeeService.addEmployee(employeeBean);
        return new ResponseEntity<>("Employee Record Inserted!!", HttpStatus.CREATED);
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
