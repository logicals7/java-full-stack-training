package com.microservices.learn.dao;

import com.microservices.learn.bean.Employee;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public class EmployeeDAO {

    //map o mimick db ops
    static public Map<Integer, Employee> mapOfEmployees = new LinkedHashMap<>();
    //initial count we are putting as 10004
    static int count = 10004;

    static{
        mapOfEmployees.put(10001, new Employee("Jack", 10001, 12345.6, 1001));
        mapOfEmployees.put(10002, new Employee("Justin", 10002, 12355.6, 1002));
        mapOfEmployees.put(10003, new Employee("Eric", 10002, 12365.6, 1003));
    }

    public Collection<Employee> getAllEmployee(){ return mapOfEmployees.values(); }
    public Employee getEmployeeDetailsById(int id){ return mapOfEmployees.get(id); }

    public Integer addEmployee(Employee employee){
        count++;
        employee.setEmployeeId(count);
        mapOfEmployees.put(count, employee);
        return count;
    }

    public Employee updateEmployee(Employee employee){
        mapOfEmployees.put(employee.getEmployeeId(), employee);
        return employee;
    }

    public Employee removeEmployee(int id){
        Employee emp = mapOfEmployees.remove(id);
        return emp;
    }

}
