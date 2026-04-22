package com.microservices.learn.service;

import com.microservices.learn.bean.EmployeeBean;
import com.microservices.learn.dao.EmployeeDAO;
import com.microservices.learn.entity.EmployeeEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
//Step 4
public class EmployeeServiceImpl {

    @Autowired
    EmployeeDAO employeeDAO;

    /*
        - step 4 to annotate the method with cache instructions
        - name element defines name of the cache used key defines the index value for the cache object
        - This annotation makes sure that the return value from this method is stored in the cache empCacheSpace with employeeId as the PK and return values as the value
        - Note: employeeId is retrieved from the parameter
     */

    @Cacheable(value = "empCacheSpace", key = "#employeeId", unless = "#result==null")
    public Optional<EmployeeBean> getEmployeeDetailsByEmployeeId(int employeeId){ //1001 1009
        System.out.println("I am inside the getEmployeeDetailsByEmployeeId() method");
        Optional<EmployeeBean> employeeBeanResult = employeeDAO.findById(employeeId)
                .map(employeeEntity -> {
                    EmployeeBean employeeBean = new EmployeeBean();
                    BeanUtils.copyProperties(employeeEntity, employeeBean);
                    return employeeBean;
        });
        return employeeBeanResult;
    }

    //Return value is saved in cache with random key
    @Cacheable(value = "empCacheSpace")
    public Collection<EmployeeBean> getEmployeeDetails(){
        System.out.println("I am inside the getEmployeeDetails() method");
        Collection<EmployeeEntity> employeeEntities = employeeDAO.findAll();
        List<EmployeeBean> employeeBeans = new ArrayList<>();
        for(EmployeeEntity employeeEntity : employeeEntities){
            EmployeeBean employeeBean = new EmployeeBean();
            BeanUtils.copyProperties(employeeEntity, employeeBean);
            employeeBeans.add(employeeBean);
        }
        return employeeBeans;
    }


    //2nd to discuss
    //@cachePut is used to put items in cache
    //or update the items already present in cache.
    //This annotation makes sure that the return value from this method is stored
    //in the cache empCacheSpace with the employeeId as the key
    //and return values as Employee Object returned by the method after the DB update

    //Note: key is derived from the method's return value
    //if return type of the method would have been int then it would have format: result
    @CachePut(value = "empCacheSpace", key = "#result.employeeId")
    public EmployeeBean addEmployee(EmployeeBean employeeBean){
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employeeBean, employeeEntity);
        EmployeeEntity emp = employeeDAO.save(employeeEntity);
        BeanUtils.copyProperties(emp, employeeBean);
        return employeeBean;
    }

    // 3rd to discuss
    // @CachePut is used to put the items in the cache
    // or update the items already in the cache.
    // This annotation makes sure that the return value from
    // this method is stored in the cache employeeCache with the employeeId as the key
    // and return values as the Employee object returned by the method after the DB update
    // Note: key is derived from the method parameter received
    @CachePut(value = "empCacheSpace", key = "#employeeBean.employeeId", unless = "#result==null")
    public Optional<EmployeeBean> updateEmployee(EmployeeBean employeeBean) {
        Optional<EmployeeBean> employeeBeanResult =
                employeeDAO.findById(employeeBean.getEmployeeId()).map(employeeEntity -> {
                    BeanUtils.copyProperties(employeeBean, employeeEntity);
                    employeeDAO.save(employeeEntity);
                    return employeeBean;
                });
        return employeeBeanResult;
    }

    // 4th one
    // This annotation is used to delete the values from the cache.
    // key defines index/key value for the cached object.
    // when delete method is successfully completed then element is removed from the cache also
    // after removing the same from DB.
    @CacheEvict(value = "empCacheSpace", key = "#employeeId")
    public Optional<EmployeeBean> deleteEmployee(int employeeId) {
        Optional<EmployeeBean> employeeBeanResult =
                employeeDAO.findById(employeeId).map(employeeEntity -> {
                    employeeDAO.delete(employeeEntity);
                    EmployeeBean employeeBean = new EmployeeBean();
                    BeanUtils.copyProperties(employeeEntity, employeeBean);
                    return employeeBean;
                });
        return employeeBeanResult;
    }

    //5th one
    //Many apps need a method to clear the whole cache
    //purge all the items in the cache
    @CacheEvict(value = "empCacheSpace", allEntries = true)
    public void evictAll(){}

}
