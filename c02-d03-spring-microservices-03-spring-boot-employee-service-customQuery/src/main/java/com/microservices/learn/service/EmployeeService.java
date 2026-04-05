package com.microservices.learn.service;

import com.microservices.learn.bean.EmployeeBean;

import java.util.List;

public interface EmployeeService {
    public EmployeeBean addEmployee(EmployeeBean employeeBean);
    public EmployeeBean findEmployeeById(Integer id);
    public List<EmployeeBean> findEmployeeByName(String name);
    public List<EmployeeBean> findAllEmployees();
    public EmployeeBean updateEmployee(EmployeeBean employeeBean);
    public EmployeeBean deleteEmployee(Integer id);
}
