package com.spring.learn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EmployeeClient {

	public static void main(String[] args) {
		//this one is for looking context in xml file
		//ApplicationContext context = new  ClassPathXmlApplicationContext("employee.xml");
		
		//for JavaConfig class we need to load context from
		ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
		
		EmployeeBean emp1 = (EmployeeBean) context.getBean("emp1");
		System.out.println(emp1);
		
		EmployeeBean emp2 = (EmployeeBean) context.getBean("createEmployee1");
		System.out.println(emp2);
		
	}

}
