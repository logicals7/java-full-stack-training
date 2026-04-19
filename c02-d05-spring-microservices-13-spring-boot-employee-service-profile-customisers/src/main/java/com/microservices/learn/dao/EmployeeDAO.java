package com.microservices.learn.dao;

import com.microservices.learn.bean.EmployeeBean;

import java.util.Collection;
import java.util.Optional;

public interface EmployeeDAO {

    Collection<EmployeeBean> getAllEmployee();

    Optional<EmployeeBean> getEmployeeDetailByEmployeeId(int employeeId);

    Integer addEmployee(EmployeeBean employee);

    Optional<EmployeeBean> deleteEmployee(int employeeId);

    Optional<EmployeeBean> updateEmployee(EmployeeBean employeeBean);

}
