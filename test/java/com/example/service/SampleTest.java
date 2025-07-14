package com.example.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SampleTest {
	
	@BeforeEach
	public void init() {
		System.out.println("@BeforeEach 실행됨");
	}
	
	@AfterEach
	public void destroy() {
		System.out.println("@AfterEach 실행됨");
	}
	
	@Test
	public void testSomething1() {
		System.out.println("testSomething1 테스트 실행됨");
	}
	
	@Test
	public void testSomething2() {
		System.out.println("testSomething2 테스트 실행됨");
	}

}
