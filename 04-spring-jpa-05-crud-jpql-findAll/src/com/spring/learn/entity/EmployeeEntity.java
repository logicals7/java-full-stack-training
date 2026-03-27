package com.spring.learn.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //mandatory - creates table inside DB. default table name will be class name.
@Table(name="Employee1") //optional - to change the table name inside DB.
public class EmployeeEntity {
	
	@Id //mandatory - primary key
	//@Column(name="EmployeeId") //optional - change column name inside table
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer empId;
	String empName;
	String role;
	Double salary;
	
	
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
		return "EmployeeEntity{" +
				"empId=" + empId +
				", empName='" + empName + '\'' +
				", role='" + role + '\'' +
				", salary=" + salary +
				'}';
	}
}
