package com.microservices.learn.dao;

import com.microservices.learn.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//To use JPA reposistories we need to extend this interface for CRUD ops
//CrudRepository needs two params: entity class & PK type of the entity class

//@Transaction should be used to support DML ops for custom queries
//inside the annotation value of transaction manager is passed which we defined in cst_jpa_spring_config.xml
@Transactional(value = "txManager")
public interface EmployeeDAO extends CrudRepository<EmployeeEntity, Integer> {
    //define the method for custom query

    //Approach-1: Jpql Query Methods - support the query to find employee details by salary
    //The method name is converted into this query: works by splitting the keywords
    //select k from EmployeeEntity k where k.salary > ?1
    //List<EmployeeEntity> findEmployeeBySalaryGreaterThan(Double salary);

    //Approach-2: @Query Annotation
    //If you want to define own custom name for the method, then use @Query.
    //We define our JPQL query as a parameter to @Query annotation.
    //@Query("select k from EmployeeEntity k where k.salary > ?1")
    //List<EmployeeEntity> findEmployeeBySalaryGreaterThan(Double salary);


    //Problem with above two approaches is that - we are hard coding the queries into code, which is vulnerable.

    //Approach-3: Named Query
    //we can give any name, but the name should be DAOInterface.methodName
    //So DAOInterface.methodName will be the key, the value of the query we will store in a file
    @Query(name="EmployeeDAO.findEmployeeBySalaryGreaterThan")
    List<EmployeeEntity> findEmployeeBySalaryGreaterThan(Double salary);

    //Approach-4: Custom Query
    // update employee set k.salary=?1 where k.empName = ?2;
    //return type of the method will be Integer - to show how many rows are affected in DB
    //Also we need to add the @Modifying annotation for custom modification queries
    @Modifying
    @Query(name="EmployeeDAO.updateEmployeeSalaryByName")
    //@Query("update EmployeeEntity k set k.salary=?1 where k.empName=?2")
    public Integer updateEmployeeSalaryByName(Double salary, String empName);


}
