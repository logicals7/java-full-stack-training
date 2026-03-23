package com.spring.learn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmployeeClient {

	public static void main(String[] args) {
		ApplicationContext context = new  ClassPathXmlApplicationContext("employee.xml");
		
		EmployeeBean emp1 = (EmployeeBean) context.getBean("emp1");
		System.out.println(emp1);
		
		EmployeeBean emp2 = context.getBean("emp2", EmployeeBean.class);
		System.out.println(emp2);
		
		//Gives error as no bean named emp exists in xml
		EmployeeBean emp = context.getBean("emp", EmployeeBean.class);
		System.out.println(emp1);
	}

}
