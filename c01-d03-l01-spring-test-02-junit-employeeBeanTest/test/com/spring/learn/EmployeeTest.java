package com.spring.learn;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/*
Unit Testing:
	- Done using JUnit framework in Java
	- We will be using JUnit 4 with Spring version 4
	- Junit will be provided by default in Eclipse
	- Right click src folder > New > Source Folder > name it as test > create new Junit class using option given by Eclipse.
	- What will be difference between normal Java class & Junit class?
	- In Junit class, there should be at least one method with @Test annotation.
	- A normal Java class can’t be run until and unless it has main() method. It can only be compiled. But a Junit class can be run by right click > run unit test.


Problem with Junit:
	- Till now, we ain’t doing any transactions.
	- Our normal Junit doesn’t support any transactions like commit or rollback.
	- When we write test cases, we should not write the actual data. It should be taken the rollback operation.
	- So we will use Spring Test module instead of Unit.
	- What will Spring Test module do? - It will initially just load the file.

*/

public class EmployeeTest {
	
	/*
	 * If we load the ApplicationContext for each test, it will be heavy on memory.
	 * we are using the same bean & loading it again & again.
	 * better we put it out of tests & place it under class instead of test methods.
	 */
	
	//First we need to load the App Context & get the bean, because it will be holding the information of the bean
	ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
	EmployeeBean bean = (EmployeeBean) context.getBean("emp");

	@Test
	public void testEmpId() {
		//First we need to load the App Context & get the bean, because it will be holding the information of the bean
		//ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
		//EmployeeBean bean = (EmployeeBean) context.getBean("emp");
		
		//lets test the value of empId
		assertTrue(bean.getEmpId() == 101);
	}
	
	@Test
	public void testEmpName() {
		//ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
		//EmployeeBean bean = (EmployeeBean) context.getBean("emp");
		assertEquals("Mark", bean.getEmpName() );
	}
	
	@Test
	public void testEmpRole() {
		//ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
		//EmployeeBean bean = (EmployeeBean) context.getBean("emp");
		assertEquals("Developer", bean.getRole() );
	}
	
	@Test
	public void testEmpSalary() {
		//ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
		//EmployeeBean bean = (EmployeeBean) context.getBean("emp");
		assertTrue(450000 == bean.getSalary());
	}
	
	
	

}
