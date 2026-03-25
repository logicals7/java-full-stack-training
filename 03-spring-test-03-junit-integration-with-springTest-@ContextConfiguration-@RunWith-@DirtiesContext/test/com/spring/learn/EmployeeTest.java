package com.spring.learn;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/*
3 Dependencies will be needed for Spring Test:
	- Javax-inject-1
	- Jsr250-api-1.0
	- Spring-test-5.3.22
	Add them to your project.


Spring Test:
	- After adding Spring Test dependency, we can now load the file using @ContextConfiguration(location=“employee.xml”) annotation, to tell the location of the config file which is xml.
	- Along with this, we will use @RunWith(SpringJUnit4ClassRunner.class) to integrate our application with the SpringJunitClassRunner. 

Next thing you need to do is inject the been using @Autowired.
*/


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/employee.xml")
public class EmployeeTest {
	
	//we don't need to load the context now. @ContextConfiguration will load it automatically/
	//ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
	//EmployeeBean bean = (EmployeeBean) context.getBean("emp");
	
	@Autowired
	EmployeeBean bean;

	@Test
	public void testEmpId() {
		assertTrue(bean.getEmpId() == 101);
	}
	
	@Test
	public void testEmpName() {
		assertEquals("Mark", bean.getEmpName() );
	}
	
	//Suppose we want to reload the context again before any method of after any method is run, 
	//we will use @DirtiesContext annotation.
	@DirtiesContext(methodMode=MethodMode.BEFORE_METHOD)
	@Test
	public void testEmpRole() {
		assertEquals("Developer", bean.getRole() );
	}
	
	@DirtiesContext(methodMode=MethodMode.AFTER_METHOD)
	@Test
	public void testEmpSalary() {
		assertTrue(45000 == bean.getSalary());
	}
	
	
	

}
