package com.spring.learn;

public class EmployeeBean {
	
	private Integer empId;
	private String empName;
	private String role;
	private Double salary;
	
	//This constructor is invoked automatically during bean creation and prints employee
	public EmployeeBean() {
		System.out.println("Employee");
	}
	

	public EmployeeBean(Integer empId, String empName, String role, Double salary) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.role = role;
		this.salary = salary;
	}




	@Override
	public String toString() {
		return "EmployeeBean [empId=" + empId + ", empName=" + empName + ", role=" + role + ", salary=" + salary + "]";
	}
	
	
}
