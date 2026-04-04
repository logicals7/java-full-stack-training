package com.spring.learn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmployeeClient {

	public static void main(String[] args) {
		ApplicationContext context = new  ClassPathXmlApplicationContext("employee.xml");
		
		System.out.println("****** Lazy Bean is only created when it is called...******");
		EmployeeBean emp1 = (EmployeeBean) context.getBean("emp1");
		System.out.println(emp1);
		
		EmployeeBean emp2 = (EmployeeBean) context.getBean("emp1");
		System.out.println(emp2);
		
		
		EmployeeBean emp3 = context.getBean("emp2", EmployeeBean.class);
		System.out.println(emp3);
		
	}

}
