package com.microservices.learn.dao;

import com.microservices.learn.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeDBDao extends JpaRepository<EmployeeEntity, Integer> {

    @Query("select e from EmployeeEntity e where e.employeeName=?1")
    List<EmployeeEntity> findEmpListByName(String employeeName);

    @Modifying
    @Transactional
    @Query("delete from EmployeeEntity e where e.employeeName=?1")
    Integer deleteByName(String employeeName);
}
