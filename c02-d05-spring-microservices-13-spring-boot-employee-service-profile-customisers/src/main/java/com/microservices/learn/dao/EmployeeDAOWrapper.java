package com.microservices.learn.dao;

import com.microservices.learn.bean.EmployeeBean;
import com.microservices.learn.entity.EmployeeEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.microservices.learn.dao.EmployeeProdDAOImpl.EmployeeDBDao;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDAOWrapper {

   @Autowired
   EmployeeDBDao employeeDBDao;

   public EmployeeDAOWrapper() {
       System.out.println("EmployeeDAOWrapper object is created...");
   }

   //add employee
    public EmployeeBean addEmployee(EmployeeBean employeeBean){
        //to save a method, we need it in entity form. so convert bean to entity.
        EmployeeEntity employeeEntity = convertBeanToEntity(employeeBean);
        //saves the entity to the table
        EmployeeEntity employeeEntity2 = employeeDBDao.save(employeeEntity);
        //converts the saved entity back to bean
        EmployeeBean employeeBean2 = convertEntityToBean(employeeEntity2);
        //returns bean
        return employeeBean2;
    }

    //find employee by id
    public EmployeeBean findEmployeeById(Integer id){
        EmployeeEntity employeeEntity = employeeDBDao.findById(id).orElse(null);
        if(employeeEntity == null) return null;
        EmployeeBean employeeBean = convertEntityToBean(employeeEntity);
        return employeeBean;
    }

    //find all employees
    public List<EmployeeBean> findAllEmployees(){
        List<EmployeeEntity> employeeEntityList = employeeDBDao.findAll();
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
        EmployeeEntity employeeEntity = employeeDBDao.findById(employeeBean.getEmployeeId()).get();
        if (employeeEntity == null) return null;

        employeeEntity.setEmployeeName(employeeBean.getEmployeeName());
        employeeEntity.setSalary(employeeBean.getSalary());
        employeeEntity.setDepartmentCode(employeeBean.getDepartmentCode());

        EmployeeEntity updatedEmployeeEntity = employeeDBDao.save(employeeEntity);
        return convertEntityToBean(updatedEmployeeEntity);
    }

    //deletes employee
    public EmployeeBean deleteEmployee(Integer id) {
        EmployeeEntity employeeEntity = employeeDBDao.findById(id).orElse(null);
        if (employeeEntity == null) return null;

        EmployeeBean employeeBean = convertEntityToBean(employeeEntity);
        employeeDBDao.delete(employeeEntity);
        return employeeBean;
    }

    //finds employee by name
    public List<EmployeeBean> findEmployeeByName(String name){
        List<EmployeeEntity> employeeEntityList = employeeDBDao.findEmpListByName(name);
        List<EmployeeBean> employeeBeanList = new ArrayList<>();
        for (EmployeeEntity ee : employeeEntityList){
            EmployeeBean eb = convertEntityToBean(ee);
            employeeBeanList.add(eb);
        }
        return employeeBeanList;
    }

    //delete employee By name
    public Integer deleteEmployesByName(String employeeName){
       Integer count = employeeDBDao.deleteByName(employeeName);
       return count;
    }



    private EmployeeBean convertEntityToBean(EmployeeEntity employeeEntity) {
        EmployeeBean employeeBean = new EmployeeBean();
        BeanUtils.copyProperties(employeeEntity, employeeBean);
        return employeeBean;
    }

    private EmployeeEntity convertBeanToEntity(EmployeeBean employeeBean) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employeeBean, employeeEntity, "employeeId");
        return employeeEntity;
    }

}
