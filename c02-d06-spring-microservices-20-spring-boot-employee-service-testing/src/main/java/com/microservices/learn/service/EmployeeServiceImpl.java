package com.microservices.learn.service;

import com.microservices.learn.bean.EmployeeBean;
import com.microservices.learn.dao.EmployeeDAOWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDAOWrapper employeeDAOWrapper;

    @Override
    public EmployeeBean addEmployee(EmployeeBean employeeBean) {
        return employeeDAOWrapper.addEmployee(employeeBean);
    }

    @Override
    public EmployeeBean findEmployeeById(Integer id) {
        return employeeDAOWrapper.findEmployeeById(id);
    }

    @Override
    public List<EmployeeBean> findAllEmployees() {
        return employeeDAOWrapper.findAllEmployees();
    }

    @Override
    public EmployeeBean updateEmployee(EmployeeBean employeeBean) {
        return employeeDAOWrapper.updateEmployee(employeeBean);
    }

    @Override
    public EmployeeBean deleteEmployee(Integer id) {
        return employeeDAOWrapper.deleteEmployee(id);
    }

}
