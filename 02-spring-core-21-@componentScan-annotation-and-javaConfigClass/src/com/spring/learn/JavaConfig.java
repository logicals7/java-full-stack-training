package com.spring.learn;import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
 * we have defined the classes as beans using @component & set the values using @value
 * we have injected the address bean into employee using @autowired
 * now if we run the code, we get error:  No bean named 'employeeBean' available
 * why?
 * we must tell spring, where to look for beans if not in config class.
 * solution: 
 * @ComponentScan
 * code should work fine.
 */

/*
 @ComponentScan (base-package="")   - Scanning the package   - Identify the required annotation from location

 */
@Configuration
@ComponentScan(basePackages="com.spring.learn")
public class JavaConfig {
	

}
