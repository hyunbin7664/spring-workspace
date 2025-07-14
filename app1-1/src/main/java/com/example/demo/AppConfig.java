package com.example.demo;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	public ModelMapper modelMapper() {
		// ModelMapper 객체를 생성한다.
		ModelMapper mapper = new ModelMapper();
		// 생성된 ModelMapper 객체에 설정을 추가하기 위해 Configuration 획득
		mapper.getConfiguration()
				// 매핑 전략을 설정한다. (STRICT : 프로퍼티 이름이 일치할 때만 매핑한다.)
				.setMatchingStrategy(MatchingStrategies.STRICT)
				// null값은 skip한다.
				.setSkipNullEnabled(true);
		
		return mapper;
	}
}
/*
 * >> @SpringBootApplication <<
 * 
 * > @EnableAutoConfiguration <
 * spring boot의 자동 구성으로 객체가 스프링 컨테이너에 등록된다.
 * - 라이브러리 의존성, application.properties 설정정보를 참조
 * 
 * > @ComponentScan <
 * 컴포넌트 스캔으로 객체가 스프링 컨테이너에 등록된다.
 * - 애플리케이션 부트스트랩 클래스가 위치한 패키지 및 그 하위 패키지에서 
 *   @Component, @Service, @Controller, @RestController, @Repository
 * 
 * > @SpringBootConfiguration <
 * @Configuration이 선언된 클래서(구성 정보를 제공하는 클래스)에서
 * - @Bean 어노테이션이 부착된 메소드가 반환하는 객체를 스프링 컨테이너로 등록시킨다.
 */