package com.microservices.learn.controller;

import com.microservices.learn.bean.EmployeeBean;
import com.microservices.learn.dao.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emp/controller")
public class EmployeeController {
    @Autowired
    EmployeeDAO employeeDAO;

    @RequestMapping(value = "/getDetails", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<EmployeeBean>> getEmployeeDetails(){
        Collection<EmployeeBean> listEmployee = employeeDAO.getAllEmployee();
        return new ResponseEntity<>(listEmployee, HttpStatus.OK);
    }

    @RequestMapping(value = "/getDetailsById/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeBean> getEmployeeDetailsById(@PathVariable("id") int myId){
        Optional<EmployeeBean> employeeBean = employeeDAO.getEmployeeDetailByEmployeeId(myId);
        return employeeBean.map(redeemedOpt-> new ResponseEntity<>(redeemedOpt, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/addEmp", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> addEmployee(@RequestBody EmployeeBean employee){
        int id = employeeDAO.addEmployee(employee);
        return new ResponseEntity<>("Employee added successfully with id: " + id, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/updateEmp", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeBean> updateEmployee(@RequestBody EmployeeBean employee){
        Optional<EmployeeBean> employeeBeanResult = employeeDAO.updateEmployee(employee);
        return employeeBeanResult.map(redeemedOpt-> new ResponseEntity<>(redeemedOpt, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @RequestMapping(value = "/deleteEmp", method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeBean> deleteEmployee(@PathVariable("id") int myID){
        Optional<EmployeeBean> employeeBean = employeeDAO.deleteEmployee(myID);
        return employeeBean.map(redeemedOpt-> new ResponseEntity<>(redeemedOpt, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

}
