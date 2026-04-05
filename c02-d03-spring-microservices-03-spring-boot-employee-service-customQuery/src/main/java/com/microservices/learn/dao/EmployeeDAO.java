package com.microservices.learn.dao;

import com.microservices.learn.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


//if you don't wanna expose all the methods of jpa/crud repo
//you can use @RepositoryDefinition
//takes two params: entity class & PK column
//benefit of using this is, now all methods will not be available
// but only methods you list here will be available
//@RepositoryDefinition(domainClass = EmployeeEntity.class, idClass = Integer.class)
public interface EmployeeDAO extends JpaRepository<EmployeeEntity, Integer> {

    //find all employees whose name is "Nands"

    //1st approach:
    public List<EmployeeEntity> findByEmployeeName(String employeeName);
}
