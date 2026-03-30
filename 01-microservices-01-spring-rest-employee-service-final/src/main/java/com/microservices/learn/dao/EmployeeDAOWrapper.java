package com.microservices.learn.dao;

import com.microservices.learn.bean.EmployeeBean;
import com.microservices.learn.entity.EmployeeEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//In this class, we will be creating the proxy object of the EmployeeDAO interface
@Repository
public class EmployeeDAOWrapper {

    @Autowired
    EmployeeDAO employeeDAO;

    public Integer addEmployee(EmployeeBean employeeBean){
        EmployeeEntity employeeEntity = convertBeanToEntity(employeeBean);
        EmployeeEntity employeeEntity2 = employeeDAO.save(employeeEntity);
        return employeeEntity2.getEmpId();
    }

    private EmployeeEntity convertBeanToEntity(EmployeeBean employeeBean) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employeeBean, employeeEntity);
        return employeeEntity;
    }
}
 