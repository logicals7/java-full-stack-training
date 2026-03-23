package com.spring.learn;

public class EmployeeBean {
	
	private Integer empId;
	private String empName;
	private String role;
	private Double salary;
	private AddressBean address;
	
	public AddressBean getAddress() {
		return address;
	}

	public void setAddress(AddressBean address) {
		this.address = address;
	}

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
		return "EmployeeBean [empId=" + empId + ", empName=" + empName + ", role=" + role + ", salary=" + salary
				+ ", address=" + address + "]";
	}
	
}
