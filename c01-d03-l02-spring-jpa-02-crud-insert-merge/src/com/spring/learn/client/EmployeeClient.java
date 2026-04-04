package com.spring.learn.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.spring.learn.entity.EmployeeEntity;

public class EmployeeClient {
	
	//first we need to provide the connectivity & to provide the connectivity we use:
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA");
	//to perform operations
	static EntityManager em = emf.createEntityManager();
	
	//before running this code, you must create db in your system. hibernate can create tables not dbs.
	//export PATH="/usr/local/mysql/bin:$PATH"
	//run: mysql -u root -p
	//mysql> create database JPA;
	//mysql> show databases;
	
	public static void main(String[] args) {
		//insertEmployee();
		mergeOperation();
	}
	
	//inserts a new employee in table
	public static void insertEmployee() {
		EmployeeEntity entity = new EmployeeEntity();
		//while using auto_increment strategy for @GeneratedValue, we should not set the ID.
		//entity.setEmpId(0001);
		entity.setEmpName("Brass");
		entity.setRole("Player");
		entity.setSalary(0.0);
		//jpa doesn't commit the transaction. so if you dont write this line, it will not add data in table
		em.getTransaction().begin();
		//if we want to insert data we must use persist
		em.persist(entity);
		//we need to commit the transaction as well.
		em.getTransaction().commit();
		System.out.println("Employee Added...");
		em.close();     
		emf.close(); 
	}
	
	/*
	 Persist()
    - insert operation
    - in persist() - we not suppose to set id because it is auto increment
    - if we so then we will get detached entity pass... 
  merge()
    - insert or update
    - when we to merge() then we can set the id 
    - If id is new then it will do insert operation with next increment value. It will not follow the value which we provided.
    - If id is existing one, then will do update.
	 */
	public static void mergeOperation() {
		EmployeeEntity entity= new EmployeeEntity();
		//entity.setEmpId(101); //it will be inserted
		entity.setEmpId(2); //it will be updated as the id 2 is already there in db in my case
		entity.setEmpName("Mark");
		entity.setRole("DevOps Developer");
		entity.setSalary(53000.0);
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
		System.out.println("Employee Addedd!!");
		}
}
