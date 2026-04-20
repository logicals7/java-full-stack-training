package com.microservices.learn.dao;

import com.microservices.learn.bean.EmployeeBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Component
@Profile("test_profile")
public class EmployeeTestDAOImpl implements EmployeeDAO {

    private static Logger logger = LoggerFactory.getLogger("EmployeeTestDAOImpl");

    static {
        logger.info("*****************************************");
        logger.info("EmployeeTestDAOImpl Class loaded: [Stub not hitting Database, this is for test_profile]");
        logger.info("*****************************************");
    }

    @Override
    public Optional<EmployeeBean> getEmployeeDetailByEmployeeId(int employeeId) {
        EmployeeBean employee = new EmployeeBean();
        employee.setEmployeeName("DummyEmployeeDBDown");
        employee.setSalary(0.0);
        employee.setDepartmentCode("0");
        employee.setEmployeeId(0);
        return Optional.ofNullable(employee);
    }

    @Override
    public Collection<EmployeeBean> getAllEmployee() {
        EmployeeBean employee = new EmployeeBean();
        employee.setEmployeeName("DummyEmployeeDBDown");
        employee.setSalary(0.0);
        employee.setDepartmentCode("0");
        employee.setEmployeeId(0);
        return Arrays.asList(employee);
    }

    @Override
    public Integer addEmployee(EmployeeBean employee) {
        EmployeeBean employee2 = new EmployeeBean();
        employee2.setEmployeeName("DummyEmployeeDBDown");
        employee2.setSalary(0.0);
        employee2.setDepartmentCode("0");
        employee2.setEmployeeId(0);
        return 1001;
    }

    @Override
    public Optional<EmployeeBean> updateEmployee(EmployeeBean employeeBean) {
        EmployeeBean employee2 = new EmployeeBean();
        employee2.setEmployeeName("DummyEmployeeDBDown");
        employee2.setSalary(0.0);
        employee2.setDepartmentCode("0");
        employee2.setEmployeeId(0);
        return Optional.ofNullable(employee2);
    }

    @Override
    public Optional<EmployeeBean> deleteEmployee(int employeeId) {
        EmployeeBean employee2 = new EmployeeBean();
        employee2.setEmployeeName("DummyEmployeeDBDown");
        employee2.setSalary(0.0);
        employee2.setDepartmentCode("0");
        employee2.setEmployeeId(0);
        return Optional.ofNullable(employee2);
    }
}
