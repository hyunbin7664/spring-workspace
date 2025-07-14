package com.example.aspect;


import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/*
 * @Aspect
 * 	- 이 어노테이션은 해당 객체가 공통기능이 모듈화된 것임을 나타낸다.
 * 	- 스프링 컨테이너는 이 객체의 AOP 설정정보를 분석해서 대상 객체에 공통기능이 결합괸 Proxy 객체를 생성한다.
 * 	
 */
@Aspect
@Component
public class LoggingAspect {

	// When: @Before
	// Where: within(com.example.service.*)
	@Before("within(com.example.service.*)")
	public void logBefore(JoinPoint joinPoint) {
		// What: 공통기능 코드블록
		// 대상 객체 정보를 획득한다.
		Object target = joinPoint.getTarget();
		// 대상 메소드 정보를 획득한다.
		Signature signature = joinPoint.getSignature();
		// 매개변수 정보를 획득한다.
		Object[] args = joinPoint.getArgs();
		
		String className = target.getClass().getName();
		String methodName = signature.getName();
		System.out.println(className + " " + methodName + "실행됨");
		System.out.println("매개변수 값: " + Arrays.toString(args));
		
	}
	
	@After("within(com.example.service.*)")
	public void logAfter(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		String methodName = signature.getName();
		System.out.println(methodName + " 종료됨");
	}
}
