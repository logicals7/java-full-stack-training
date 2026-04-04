package com.microservices.learn.dao;

import com.microservices.learn.bean.EmployeeBean;
import com.microservices.learn.entity.EmployeeEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDAOWrapper {

   @Autowired
   EmployeeDAO employeeDAO;

   //add employee
    public EmployeeBean addEmployee(EmployeeBean employeeBean){
        //to save a method, we need it in entity form. so convert bean to entity.
        EmployeeEntity employeeEntity = convertBeanToEntity(employeeBean);
        //saves the entity to the table
        EmployeeEntity employeeEntity2 = employeeDAO.save(employeeEntity);
        //converts the saved entity back to bean
        EmployeeBean employeeBean2 = convertEntityToBean(employeeEntity2);
        //returns bean
        return employeeBean2;
    }

    //find employee by id
    public EmployeeBean findEmployeeById(Integer id){
        EmployeeEntity employeeEntity = employeeDAO.findById(id).orElse(null);
        if(employeeEntity == null) return null;
        EmployeeBean employeeBean = convertEntityToBean(employeeEntity);
        return employeeBean;
    }

    //find all employees
    public List<EmployeeBean> findAllEmployees(){
        List<EmployeeEntity> employeeEntityList = employeeDAO.findAll();
        if(employeeEntityList.isEmpty()) return null;

        List<EmployeeBean> employeeBeanList = new ArrayList<>();
        for(EmployeeEntity ee : employeeEntityList){
            EmployeeBean eb = convertEntityToBean(ee);
            employeeBeanList.add(eb);
        }
        return employeeBeanList;
    }

    //updates employee
    public EmployeeBean updateEmployee(EmployeeBean employeeBean) {
        EmployeeEntity employeeEntity = employeeDAO.findById(employeeBean.getEmployeeId()).get();
        if (employeeEntity == null) return null;

        employeeEntity.setEmployeeName(employeeBean.getEmployeeName());
        employeeEntity.setSalary(employeeBean.getSalary());
        employeeEntity.setDepartmentCode(employeeBean.getDepartmentCode());

        EmployeeEntity updatedEmployeeEntity = employeeDAO.save(employeeEntity);
        return convertEntityToBean(updatedEmployeeEntity);
    }

    public EmployeeBean deleteEmployee(Integer id) {
        EmployeeEntity employeeEntity = employeeDAO.findById(id).orElse(null);
        if (employeeEntity == null) return null;

        EmployeeBean employeeBean = convertEntityToBean(employeeEntity);
        employeeDAO.delete(employeeEntity);
        return employeeBean;
    }


    private EmployeeBean convertEntityToBean(EmployeeEntity employeeEntity) {
        EmployeeBean employeeBean = new EmployeeBean();
        BeanUtils.copyProperties(employeeEntity, employeeBean);
        return employeeBean;
    }

    private EmployeeEntity convertBeanToEntity(EmployeeBean employeeBean) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employeeBean, employeeEntity);
        return employeeEntity;
    }

}
