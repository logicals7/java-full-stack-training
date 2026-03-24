package com.spring.learn;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//if @Autowired(required=false) & we dont mention this class as bean/component => we will get null values for address injection
//if @Autowired(required=true) or @Autowired & we dont mention this class as bean/component => we will get bean not found exception
//if @Autowired(required=false) & we mention this class as bean/component => we will get values for address injection
@Component("add2")
public class AddressBean {
	
	@Value("AECS")
	private String area;
	@Value("BLR")
	private String city;
	
	public String getArea() {
		return area;
	}
	
	public void setArea(String area) {
		this.area = area;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "AddressBean [area=" + area + ", city=" + city + "]";
	}
	
}
