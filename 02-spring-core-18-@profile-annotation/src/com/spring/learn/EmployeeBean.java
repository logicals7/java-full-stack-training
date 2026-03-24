package com.spring.learn;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/*
 * @Profile
  - Annotation we can use for environment specific 
  - We should use on the top of @Component annotation
  - on bean class we should use @Profile
  - then along with we should provide the value-> environment
  - so that will be available only for that profiles value not for others

	## if we dont mention the @profile("dev") => code was working fine.
		 * if we mention the @profile("dev") =>  org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'employeeBean' available
		 * why so?
		 	- because we didn't enable it.
		 	
	## How to enable the profiles?
	    Run as configuration-> environment-> new -> 
	    spring.profiles.active
	    value= dev
 */

@Profile("dev")
@Component
public class EmployeeBean {
	
	@Value("101")
	private Integer empId;
	
	@Value("KK")
	private String empName;
	@Value("Developer")
	private String role;
	
	@Value("5000000")
	private Double salary;
	
	
	public Integer getEmpId() {
		return empId;
	}
	
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	
	public String getEmpName() {
		return empName;
	}
	
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public Double getSalary() {
		return salary;
	}
	
	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "EmployeeBean [empId=" + empId + ", empName=" + empName + ", role=" + role + ", salary=" + salary + "]";
	}
	
	
}
