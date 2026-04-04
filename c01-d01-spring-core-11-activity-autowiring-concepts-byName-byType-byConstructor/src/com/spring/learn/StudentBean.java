package com.spring.learn;


public class StudentBean {
	
	private Integer stuId;
	private String stuName;
	private String stuDept;
	private Address address;
	
	
	//autowiring bean in constructor
	//default constructor is imp to mention for constructor injection
	public StudentBean() {}
	public StudentBean(Address address) {
		this.address = address;
	}
	

	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuDept() {
		return stuDept;
	}
	public void setStuDept(String stuDept) {
		this.stuDept = stuDept;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "StudentBean [stuId=" + stuId + ", stuName=" + stuName + ", stuDept=" + stuDept + ", address=" + address
				+ "]";
	}
	
}