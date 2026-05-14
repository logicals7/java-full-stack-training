package com.microservices.learn.controller;

import java.util.Collection;
import java.util.Optional;

import com.microservices.learn.bean.EmployeeBean;
import com.microservices.learn.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @RequestMapping(
            value = "emp/controller/getDetails",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Collection<EmployeeBean>> getEmployeeDetails() {
        Collection<EmployeeBean> listEmployee = employeeService.getEmployeeDetails();
        return new ResponseEntity<Collection<EmployeeBean>>(listEmployee, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/emp/controller/getDetailsById/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<EmployeeBean> getEmployeeDetailByEmployeeId(@PathVariable("id") int myId) {
        Optional<EmployeeBean> employeeBean = employeeService.getEmployeeDetailsByEmployeeId(myId);
        return employeeBean
                .map(redeemedOpt -> new ResponseEntity<EmployeeBean>(redeemedOpt, HttpStatus.OK))
                .orElse(new ResponseEntity<EmployeeBean>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(
            value = "/emp/controller/addEmp",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_HTML_VALUE
    )
    public ResponseEntity<String> addEmployee(@RequestBody EmployeeBean employee) {
        int id = employeeService.addEmployee(employee).getEmployeeId();
        return new ResponseEntity<String>("Employee added successfully with id:" + id, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/emp/controller/updateEmp",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<EmployeeBean> updateEmployee(@RequestBody EmployeeBean employeeBean) {
        Optional<EmployeeBean> employeeBeanResult = employeeService.updateEmployee(employeeBean);
        return employeeBeanResult
                .map(redeemedOpt -> new ResponseEntity<EmployeeBean>(redeemedOpt, HttpStatus.OK))
                .orElse(new ResponseEntity<EmployeeBean>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @RequestMapping(
            value = "/emp/controller/deleteEmp/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<EmployeeBean> deleteEmployee(@PathVariable("id") int myId) {
        Optional<EmployeeBean> employeeBean = employeeService.deleteEmployee(myId);
        return employeeBean
                .map(redeemedOpt -> new ResponseEntity<EmployeeBean>(redeemedOpt, HttpStatus.OK))
                .orElse(new ResponseEntity<EmployeeBean>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @RequestMapping(
            value = "/emp/controller/clearCache",
            method = RequestMethod.PUT,
            produces = MediaType.TEXT_HTML_VALUE
    )
    public ResponseEntity<String> evictAll() {
        employeeService.evictAll();
        return new ResponseEntity<String>("Cache is cleared....:)", HttpStatus.OK);
    }
}