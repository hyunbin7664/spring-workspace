package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages = "com.example")
/*
 * @@PropertySource
 * 	- 이 어노테이션은 config.properties 파일을 로딩하게 한다.
 */
@PropertySource("classpath:config.properties")
public class App7Config {

	/*
	 * static이 붙으면, 
	 * 스프링 컨테이너가 이 개게를 다른 객체들보다 먼저 스프링 컨테이너의 빈으로 등록시킨다.
	 * PropertySourcesPlaceholderConfigurer
	 * - properties 파일의 설정값을 ${key}로 빈에게 주입한다.
	 * - xml에서는 
	 * 	<property name="" value="${key}" />
	 * - 어노테이션으로는 
	 * 	@Value("${key}")
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfig() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
