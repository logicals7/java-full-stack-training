package com.spring.learn;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

public class EmployeeClient {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
		
		//this is for understanding the flow of bean creation
		/*
		 * 
		 * Bean Lifecycle
			  - Life of the bean
			  - How it will be create, Work and destroy
			  - https://howtodoinjava.com/spring-core/spring-bean-life-cycle/

		 Output is:
		 	step-1. constructor...
			step-2. setter...
			step-3. @PostConstruct...
			step-4. @afterProperties...
			step-5. myInitMethod...
			EmployeeBean [empId=101]
			step-6. destroy method...
			step-7. myDestroyMethod...
			
		=> means, first constructor is invoked
					then setter is called
					then Object is initiated
					
		 */
		
		EmployeeBean emp1 = (EmployeeBean) context.getBean("emp1");
		System.out.println(emp1);
		context.close();
		
	}

}
