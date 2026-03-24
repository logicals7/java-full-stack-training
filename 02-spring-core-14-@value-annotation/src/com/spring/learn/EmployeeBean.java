package com.spring.learn;

import org.springframework.beans.factory.annotation.Value;

public class EmployeeBean {
	
	//setting the value by variable but will be overridden by setter @Value
	@Value("101")
	private Integer empId;
	
	//setting the values from properties file
	@Value("${name}")
	private String empName;
	@Value("${role}")
	private String role;
	
	//setting the value by variable
	@Value("5000000")
	private Double salary;
	
	
	public Integer getEmpId() {
		return empId;
	}
	
	//setter value will have higher priority over fields
	@Value("102") 
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
	
	//arithmetic operations are allowed
	@Value("#{5000000+200000}")
	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "EmployeeBean [empId=" + empId + ", empName=" + empName + ", role=" + role + ", salary=" + salary + "]";
	}
	
	
}
