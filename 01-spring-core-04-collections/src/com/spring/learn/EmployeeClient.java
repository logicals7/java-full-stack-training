package com.spring.learn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmployeeClient {

	public static void main(String[] args) {
		ApplicationContext context = new  ClassPathXmlApplicationContext("employee.xml");
		
		EmployeeBean emp1 = (EmployeeBean) context.getBean("emp1");		
		emp1.printList();
		emp1.printMap();
		emp1.printSet();
	}

}
