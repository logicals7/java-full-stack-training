package com.microservices.learn.dao;

import com.microservices.learn.entity.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//To use JPA reposistories we need to extend this interface for CRUD ops
//CrudRepository needs two params: entity class & PK type of the entity class
public interface EmployeeDAO extends CrudRepository<EmployeeEntity, Integer> {
    //define the method for custom query
    List<EmployeeEntity> findEmployeeBySalaryGreaterThan(Double salary);
}
