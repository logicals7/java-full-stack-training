package com.spring.learn;


public class AddressBean {
	private String area;
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
