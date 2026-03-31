package com.microservices.learn.service;

import com.microservices.learn.bean.EmployeeBean;

import java.util.List;

public interface EmployeeService {
    public Integer addEmployee(EmployeeBean employeeBean);
    public EmployeeBean findEmployeeById(Integer id);
    public List<EmployeeBean> findAllEmployees();
    public EmployeeBean updateEmployee(EmployeeBean employeeBean);
    public EmployeeBean deleteEmployee(Integer id);
    public List<EmployeeBean> findEmployeeBySalaryGreaterThan(Double salary);
}
