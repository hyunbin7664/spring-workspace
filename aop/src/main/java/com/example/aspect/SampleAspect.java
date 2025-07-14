package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class SampleAspect {
	
	@Pointcut("within(com.example.service.*)")
	private void myPointcut() {}
	
	/*
	 * @AfterReturning
	 * - 대상 메소드가 정상 완료된 후 실행되는 공통기능을 정의한다.
	 * - 대상 메소드의 반환값에 접근할 수 있다.(조회만 가능하다.)
	 * - returning는 대상 메소드의 반환값을 전달받는 매개변수 이름이다.
	 */
	@AfterReturning(pointcut = "myPointcut()", returning = "value")
	public void printReturnValue(JoinPoint joinPoint, Object value) {
		System.out.println("대상 메소드의 반환값을 확인한다.");
		String methodName = joinPoint.getSignature().getName();
		System.out.println("[" + methodName + "] 반환값: " + value);
		
	}

	/*
	 * @Around Advice
	 * - 반환타입이 Object다.
	 * - 매개변수로 ProceedingJoinPoint를 전달받는다.
	 * - throws 키워드로 Throwable를 선언한다.
	 */
	@Around("within(com.example.service.*)")
	public Object runningTimeCheck(ProceedingJoinPoint joinPoint) 
		throws Throwable {
		
			// 대상 메소드 실행 전 수행할 코드를 작성한다. - @Before
			System.out.println("프로그램 실행 시간 체크를 시작한다.");
			String methodName = joinPoint.getSignature().getName();
			
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			
			// 대상 메소드를 실행한다.
			// 대상 메소드 실행 후 반환값을 returnValue로 받는다.
			Object returnValue = joinPoint.proceed();
			
			// 대상 메소드 실행 후 수행할 코드를 작성한다. - @AfterReturning
			stopWatch.stop();
			long timeMillis = stopWatch.getTotalTimeMillis();
			System.out.println("[" + methodName + "] 실행시간: " + timeMillis + " 밀리초");
			
			System.out.println("프로그램 실행 시간 체크를 종료한다.");
			
			// 반환값을 반환한다.
			return returnValue;
	}
}
