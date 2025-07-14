package com.example;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/*
 * @EnableAspectJAutoProxy
 * - 이 어노테이션의 Spring AOP 기능을 활성화하는 어노테이션이다.
 * - @Aspect가 부착된 클래스의 AOP 설정정보를 분석해서 공통기능 적용대상 객체에 대한 Proxy객체를 생성하는
 * 	 AutoProxyCreator 객체를 스프링의 빈으로 등록한다.
 */
@Configuration
@ComponentScan(basePackages = "com.example")
@EnableAspectJAutoProxy
public class App1Config {

}
