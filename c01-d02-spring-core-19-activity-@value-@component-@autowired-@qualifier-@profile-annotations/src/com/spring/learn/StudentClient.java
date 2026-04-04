package com.spring.learn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentClient {

	public static void main(String[] args) {
		ApplicationContext context = new  ClassPathXmlApplicationContext("student.xml");
		
		StudentBean stu1 = (StudentBean) context.getBean("studentBean");
		System.out.println(stu1);
		
	}

}
