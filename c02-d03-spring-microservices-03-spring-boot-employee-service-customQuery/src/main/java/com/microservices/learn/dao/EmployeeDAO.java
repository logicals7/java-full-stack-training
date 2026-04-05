package com.microservices.learn.dao;

import com.microservices.learn.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
    //Where Query methods are defined in a particular naming style
    // so that hibernate can tokenize and break them into query
    public List<EmployeeEntity> findByEmployeeName(String employeeName);

    //2nd approach:
    //@Query Annotation with query: select k from EmployeeEntity where k.employeeName=?1
    @Query("select k from EmployeeEntity k where k.employeeName=?1")
    public List<EmployeeEntity> findByName(String employeeName);
}
