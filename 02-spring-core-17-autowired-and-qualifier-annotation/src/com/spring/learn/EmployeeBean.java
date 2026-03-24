package com.spring.learn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmployeeBean {
	
	@Value("000")
	private Integer empId;
	@Value("KK")
	private String empName;
	@Value("Writer")
	private String role;
	@Value("628961986396")
	private Double salary;
	
	/*
	 * @Autowired
		  - will be use for automatically injecting the reference classes
		  - by default will take class type
		  - We will apply the annotation on the reference varaiable
		  - That respective class should be manage the spring bean
		  - on that class we must write @Component annotation to provide the inject values
		
		  - Default value for Autowired annotation is true=> (required=true)
		    - Its implementation class must have Component annotation
		    - if we did not have then we will get exception- NoSuchBeanDefinitionException
		  - We can customized the default Autowired annotation value to false also.
		    - we can use required=false
		    - Means if reference class marked with annotation @Component then value will be injected
		    - if we did not marked annotation @Component then will not get any exception will get null values.

	 */
	
	//till now we are not autowiring or injecting the AddressBean
	//if we run the code without writing autowire, we will get nothing in address while printing emp1
	//np need for getter & setters
	
	//@Autowired(required=true)
	//@Autowired
	@Autowired(required=false)
	private AddressBean address;


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
