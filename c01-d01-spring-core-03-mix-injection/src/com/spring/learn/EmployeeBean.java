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
	
	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public EmployeeBean(String empName, String role, Double salary) {
		super();
		this.empName = empName;
		this.role = role;
		this.salary = salary; //salary will come at index 2 starting from 0.
	}

	@Override
	public String toString() {
		return "EmployeeBean [empId=" + empId + ", empName=" + empName + ", role=" + role + ", salary=" + salary + "]";
	}
	
	
}
