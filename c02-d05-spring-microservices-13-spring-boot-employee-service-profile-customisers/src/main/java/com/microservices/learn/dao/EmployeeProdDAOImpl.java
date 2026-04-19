package com.microservices.learn.dao;

import com.microservices.learn.bean.EmployeeBean;
import com.microservices.learn.entity.EmployeeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Profile("prod_profile")
public class EmployeeProdDAOImpl implements EmployeeDAO {

    private static Logger logger = LoggerFactory.getLogger(EmployeeProdDAOImpl.class);

    static {
        logger.info("*****************************************");
        logger.info("EmployeeProdDAOImpl class loaded: [Hitting DB to Perform CRUD Operations]");
        logger.info("*****************************************");
    }

    @Autowired
    private EmployeeDBDao employeeDBDao;

    @Override
    public Collection<EmployeeBean> getAllEmployee() {
        Collection<EmployeeEntity> employeeEntities = employeeDBDao.findAll();
        List<EmployeeBean> employeeBeans = new ArrayList<>();
        for (EmployeeEntity employeeEntity : employeeEntities) {
            EmployeeBean employeeBean = new EmployeeBean();
            BeanUtils.copyProperties(employeeEntity, employeeBean);
            employeeBeans.add(employeeBean);
        }
        return employeeBeans;
    }

    @Override
    public Optional<EmployeeBean> getEmployeeDetailByEmployeeId(int employeeId) {
        Optional<EmployeeBean> employeeBeanResult = null;
        employeeBeanResult = employeeDBDao.findById(employeeId).map(employeeEntity -> {
            EmployeeBean employeeBean = new EmployeeBean();
            BeanUtils.copyProperties(employeeEntity, employeeBean);
            return employeeBean;
        });
        return employeeBeanResult;
    }

    @Override
    public Integer addEmployee(EmployeeBean employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);
        EmployeeEntity emp = employeeDBDao.save(employeeEntity);
        return emp.getEmployeeId();
    }

    @Override
    public Optional<EmployeeBean> deleteEmployee(int employeeId) {
        Optional<EmployeeBean> employeeBeanResult = null;
        employeeBeanResult = employeeDBDao.findById(employeeId).map(employeeEntity -> {
            employeeDBDao.delete(employeeEntity);
            EmployeeBean employeeBean = new EmployeeBean();
            BeanUtils.copyProperties(employeeEntity, employeeBean);
            return employeeBean;
        });
        return employeeBeanResult;
    }

    @Override
    public Optional<EmployeeBean> updateEmployee(EmployeeBean employeeBean) {
        Optional<EmployeeBean> employeeBeanResult = null;
        employeeBeanResult = employeeDBDao.findById(employeeBean.getEmployeeId()).map(employeeEntity -> {
            BeanUtils.copyProperties(employeeBean, employeeEntity);
            employeeDBDao.save(employeeEntity);
            return employeeBean;
        });
        return employeeBeanResult;
    }

    @Repository
    public interface EmployeeDBDao extends JpaRepository<EmployeeEntity, Integer> {

        @Query("select e from EmployeeEntity e where e.employeeName=?1")
        List<EmployeeEntity> findEmpListByName(String employeeName);

        @Modifying
        @Transactional
        @Query("delete from EmployeeEntity e where e.employeeName=?1")
        Integer deleteByName(String employeeName);
    }
}
