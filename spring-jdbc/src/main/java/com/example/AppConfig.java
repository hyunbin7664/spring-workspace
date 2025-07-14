package com.example;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

/*
 * @Configuration
 * - 이 클래스가 구성정보를 제공하는 클래스임을 나타낸다.
 * - 이 클래스가 스프링 컨테이너의 빈으로 자동 등록되는 대상임을 나타낸다.
 * - 이 클래스에 @Bean이 선언되어 있는 메소드가 반환하는 객체는 스프링 컨테이너의 빈으로 자동 등록된다.
 * 
 * @ComponentScan
 * - 지정된 패키지 및 그 하위 패키지에서 컴포넌트를 스캔해서 스프링 컨테이너의 빈으로 자동등록 시킨다.
 * - 컴포넌트는 @Component, @Service, @Repository, @Controller 등의 어노테이션이 선언된 클래스다.
 * 
 * @PropertySource
 * - 지정된 properties 설정 파일을 로딩하고, @Values와 ${key}로 의존성 주입을 지원하는 
 *   PropertySourcePlaceholderConfigurer를 스프링 컨테이너의 빈으로 등록한다.
 *   
 */
@Configuration
@ComponentScan(basePackages = "com.example")
@PropertySource("classpath:db.properties")
public class AppConfig {
	/*
	 * - 데이터베이스와 연결된 Connectioin 객체를 제공하는 객체를 스프링 컨테이너의 빈으로 등록시킨다.
	 * - 이 프로젝트에서는 BasicDataSource를 사용하여 커넥션풀(Connection Pool)을 구성하고, 데이터베이스와의 연결을 효율적으로 관리한다.
	 */

	@Bean
	public DataSource dataSource(
			@Value("${db.url}") String url, 
			@Value("${db.driver}") String driverClassName, 
			@Value("${db.username}") String username, 
			@Value("${db.password}") String password ) {
		/*
		 * BasicDataSource
		 * - javax.sql.DataSource 인터페이스의 구현체다.
		 * - 데이터베이스와 연결된 Connection 객체를 관리하고, 제공한다.
		 */
		BasicDataSource ds = new BasicDataSource();
		ds.setUrl(url);
		ds.setDriverClassName(driverClassName);
		ds.setUsername(username);
		ds.setPassword(password);
		
		return ds;
	}
	
	/*
	 * JdbcTemplate
	 * - JDBC 기술을 이용해서 데이터베이스 액세스 작업을 수행할 때 사용되는 헬퍼 클래스다.
	 * - 커넥션 연결/해제, 파라미터 바인딩, SQL 전송과 실행, ResultSet 처리와 같은 반복적인 작업을 내부에서 전부 처리한다.
	 * - 매우 간결한 코드로 데이터베이스 엑세스 작업을 수행할 수 있다.
	 * - 사용자의 데이터베이스와 연결하기 위해서 커넥션풀 객체에 의존한다.
	 * 
	 */
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource ds) {
		return new JdbcTemplate(ds);
	}
}
