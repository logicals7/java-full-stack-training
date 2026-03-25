package com.spring.learn;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
