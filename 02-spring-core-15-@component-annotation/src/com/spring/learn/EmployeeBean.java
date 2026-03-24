package com.spring.learn;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component("emp1")
public class EmployeeBean {
	
	@Value("101")
	private Integer empId;
	
	@Value("${name}")
	private String empName;
	@Value("${role}")
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
