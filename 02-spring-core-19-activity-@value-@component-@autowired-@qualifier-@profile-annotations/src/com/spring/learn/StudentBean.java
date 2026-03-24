package com.spring.learn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class StudentBean {
	@Value("01")
	private Integer stuId;
	@Value("Muskan")
	private String stuName;
	@Value("AI")
	private String stuDept;
	
	@Autowired
	@Qualifier("add2")
	private Address address;

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
	
	@Override
	public String toString() {
		return "StudentBean [stuId=" + stuId + ", stuName=" + stuName + ", stuDept=" + stuDept + ", address=" + address
				+ "]";
	}
	
}