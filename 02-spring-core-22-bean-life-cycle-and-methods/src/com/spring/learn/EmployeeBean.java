package com.spring.learn;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class EmployeeBean implements InitializingBean, DisposableBean {
	
	private Integer empId;
	
	EmployeeBean(){
		System.out.println("step-1. constructor...");
	}
	
	public Integer getEmpId() {
		return empId;
	}
	
	public void setEmpId(Integer empId) {
		System.out.println("step-2. setter...");
		this.empId = empId;
	}
	
	//lets create one more method
	//this will be executed at number 3.
	//postConstruct is not avail after java 1.8 jre, curr using java21 jre. giving error.
	@PostConstruct
	public void postConstruct() {
		System.out.println("step-3. @postConstruct...");
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("step-4. @afterProperties...");		
	}
	
	//to invoke this we must define it in xml.
	public void myInitMethod() {
		System.out.println("step-5. myInitMethod...");		
	}
	
	//to invoke context must be closed in Main class.
	@Override
	public void destroy() throws Exception {
		System.out.println("step-6. destroy method...");		
	}

	//to invoke this we must define it in xml & context must be closed in Main class.
	public void myDestroyMethod() {
		System.out.println("step-7. myDestroyMethod...");		
	}
	
	@Override
	public String toString() {
		return "EmployeeBean [empId=" + empId + "]";
	}
	
}
