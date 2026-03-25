package com.spring.learn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmployeeClient {

	public static void main(String[] args) {
		ApplicationContext context = new  ClassPathXmlApplicationContext("employee.xml");
		
		EmployeeBean emp = (EmployeeBean) context.getBean("emp");
		System.out.println(emp);
		
	}

}
