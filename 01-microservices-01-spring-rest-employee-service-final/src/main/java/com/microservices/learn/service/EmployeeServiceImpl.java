package com.microservices.learn.service;

import com.microservices.learn.bean.EmployeeBean;
import com.microservices.learn.dao.EmployeeDAOWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDAOWrapper employeeDAOWrapper;

    @Override
    public Integer addEmployee(EmployeeBean employeeBean) {
        return employeeDAOWrapper.addEmployee(employeeBean);
    }
}
