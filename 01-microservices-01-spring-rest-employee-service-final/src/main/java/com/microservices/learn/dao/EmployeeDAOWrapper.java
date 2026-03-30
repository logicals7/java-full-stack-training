package com.microservices.learn.dao;

import com.microservices.learn.bean.EmployeeBean;
import com.microservices.learn.entity.EmployeeEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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

    public EmployeeBean findEmployeeById(Integer id){
        EmployeeEntity employeeEntity = employeeDAO.findById(id).orElse(null);
        if(employeeEntity == null) return null;
        EmployeeBean employeeBean = convertEntityToBean(employeeEntity);
        return employeeBean;
    }

    private EmployeeBean convertEntityToBean(EmployeeEntity employeeEntity) {
        EmployeeBean employeeBean = new EmployeeBean();
        BeanUtils.copyProperties(employeeEntity, employeeBean);
        return employeeBean;
    }

    public List<EmployeeBean> findAllEmployees(){
        List<EmployeeEntity> listEmployeeEntities = (List<EmployeeEntity>) employeeDAO.findAll();
        List<EmployeeBean> employeeBeanList = new ArrayList<>();
        for(EmployeeEntity employeeEntity : listEmployeeEntities){
            EmployeeBean employeeBean = convertEntityToBean(employeeEntity);
            employeeBeanList.add(employeeBean);
        }
        return employeeBeanList;
    }

    public EmployeeBean updateEmployee(EmployeeBean employeeBean){
        EmployeeEntity employeeEntity = employeeDAO.findById(employeeBean.getEmpId()).get();
        employeeEntity.setEmpName(employeeBean.getEmpName());
        employeeEntity.setSalary(employeeBean.getSalary());
        employeeEntity.setDeptCode(employeeBean.getDeptCode());
        EmployeeEntity employeeEntity1 = employeeDAO.save(employeeEntity);
        return convertEntityToBean(employeeEntity1);
    }

    public EmployeeBean deleteEmployee(Integer id){
        EmployeeEntity employeeEntity = employeeDAO.findById(id).orElse(null);
        if (employeeEntity == null) return null;
        employeeDAO.deleteById(id);
        return convertEntityToBean(employeeEntity);
    }
}
 