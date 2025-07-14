package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * @@SpringBootApplication
 * - spring boot 애플리케이션의 핵심 어노테이션이다.
 * - 자동구성, 컴포넌트 스캔, 빈등록을 지원한다.
 * 
 * @MapperScan
 * - 지정된 패키지에 정의된 Mapper 인터페이스를 스캔해서 구현체(Maooer 인스턴스)를 생성하고, 
 *   스프링 컨테이너의 빈으로 자동으로 등록시킨다.
 * - MapperScannerConfigurer를 스프링 컨테이너의 빈으로 등록시켜서 위의 작업을 수행하게 한다.
 */
@SpringBootApplication
@MapperScan("com.example.demo.mapper")
public class App1Application {

	public static void main(String[] args) {
		SpringApplication.run(App1Application.class, args);
	}

}
