package com.spring.learn;

import static org.junit.Assert.*;

import org.junit.Test;

public class EmployeeTest {

	@Test
	public void test() {
		//checks if expected & actual values are equal.
		//the expected value was 101, and the actual value will be 45+56=101. So the test will pass.
		assertEquals(101, 45+56);
	}
	
	
	@Test
	public void test1() {
		//checks if expected & actual values are not equal.
		//the expected value was 101, and the actual value will be 45+56=101. So the test will pass.
		assertNotEquals(101, 45+57);
	}

}
