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
    //This JPQL query is similar to SQL Query, But it is not DB specific.
    //It is based on entity class and its fields.
    //It is independent of DB & portable across different DBs.
    @Query("select k from EmployeeEntity k where k.employeeName=?1")
    public List<EmployeeEntity> findByName(String employeeName);

    //3rd approach: Named Queries
    /*
        So the 3rd-approach consists of using the Named-Queries.
        In DAO class, specify the name of the Query & associate query from somewhere else.
        Write the ref for named query in DAO class as:
        Syntax: @Query(name=“EmployeeDAO.findByName”)
        Put the actual named Query in resources/META-INF/jpa-named-queries.properties file.
        Also change the DAOwrapper method to findEmpListByName from findByName
     */
    @Query(name = "EmployeeDAO.findEmpListByName")
    public List<EmployeeEntity> findEmpListByName(String employeeName);


}
