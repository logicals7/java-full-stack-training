package com.spring.learn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentClient {

	public static void main(String[] args) {
		ApplicationContext context = new  ClassPathXmlApplicationContext("student.xml");
		
		System.out.println("#####Autowiring Address bean byName...####");
		StudentBean stu1 = (StudentBean) context.getBean("stu1");
		System.out.println(stu1);
		
		System.out.println("#####Autowiring Address bean byType...####");
		StudentBean stu2 = (StudentBean) context.getBean("stu2");
		System.out.println(stu2);
		
		System.out.println("#####Autowiring Address bean constructor...####");
		StudentBean stu3 = (StudentBean) context.getBean("stu3");
		System.out.println(stu3);
		
	}

}
