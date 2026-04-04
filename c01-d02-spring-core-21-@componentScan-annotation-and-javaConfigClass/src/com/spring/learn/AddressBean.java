package com.spring.learn;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
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
