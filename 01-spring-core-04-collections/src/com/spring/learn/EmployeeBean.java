package com.spring.learn;

import java.util.*;

public class EmployeeBean {
	
	private List empList;
	private Set empSet;
	private Map empMap;

	public List getEmpList() {
		return empList;
	}

	public void setEmpList(List empList) {
		this.empList = empList;
	}

	public Set getEmpSet() {
		return empSet;
	}

	public void setEmpSet(Set empSet) {
		this.empSet = empSet;
	}

	public Map getEmpMap() {
		return empMap;
	}

	public void setEmpMap(Map empMap) {
		this.empMap = empMap;
	}
	
	public void printList() {
		//method-1 of printing
		//empList.forEach(System.out::println);
		
		//method-2 of printing
		for(Object obj : empList) {
			System.out.println(obj);
		}
	}
	
	public void printSet() {
		empSet.forEach(System.out::println);
	}
	
//	public void printMap() {
//	    empMap.forEach((key, value) -> 
//	        System.out.println("Key: " + key + ", Value: " + value)
//	    );
//	}
	
	
	public void printMap() {
		// Method 1 (recommended - entrySet)
	    for (Object entryObj : empMap.entrySet()) {
	        Map.Entry entry = (Map.Entry) entryObj;
	        System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
	    }
	}
	
	
}
