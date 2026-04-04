package com.spring.learn;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*
 * @Configuration   - Java base configuration   - root configuration  @Bean   - Will be use on method level   - to create bean inside configuration   - By default if we did not write bean value then method name will be the value

 */

@Configuration
public class JavaConfig {
	
	@Bean("emp1")
	public EmployeeBean createEmployee() {
		EmployeeBean bean = new EmployeeBean();
		bean.setEmpId(1);
		bean.setEmpName("KK");
		bean.setRole("dev");
		bean.setSalary(450000000.00);
		//no addressBean is injected, so address will have null values
		return bean;
	}
	
	//if we dont define the bean value here => then, by default method name will be the bean value.
	@Bean
	public EmployeeBean createEmployee1() {
		EmployeeBean bean = new EmployeeBean();
		bean.setEmpId(2);
		bean.setEmpName("KKK");
		bean.setRole("dev");
		bean.setSalary(1050000000.00);
		
		//injecting the addressBean
		AddressBean add = createAdd();
		bean.setAddress(add);
		
		return bean;
	}
	
	@Bean
	public AddressBean createAdd() {
		AddressBean bean = new AddressBean();
		bean.setArea("ABC");
		bean.setCity("DEF");
		return bean;
	}
	

}
