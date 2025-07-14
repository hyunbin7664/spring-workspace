package com.example;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcTransactionManager;
/*
 * @Configuration
 *  - 이 클래스가 구성정보를 제공하는 클래스임을 나타낸다.
 *  - 이 클래스가 스프링 컨테이너의 빈으로 자동 등록되는 대상임을 나타낸다.
 *  - 이 클래스에 @Bean이 선언되어 있는 메소드가 반환하는 객체는
 *    스프링 컨테이너의 빈으로 자동 등록된다.
 * @ComponentScan
 *  - 지정된 패키지 및 그 하위 패키지에서 컴포넌트를 스캔해서
 *    스프링 컨테이너의 빈으로 자동등록 시킨다.
 *  - 컴포넌트는 @Component, @Service, @Repository, @Controller
 *    등의 어노테이션이 선언된 클래스다.
 * 
 * @PropertySource
 *  - 지정된 properties 설정 파일을 로딩하고, @Value와 ${key}로
 *    의존성 주입을 지원하는 PropertySourcesPlaceholderConfigurer를
 *    스프링 컨테이너의 빈으로 등록한다.
 */
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
 * @PropertySource
 *  - 지정된 properties 설정 파일을 로딩하고, @Value와 ${key}로
 *    의존성 주입을 지원하는 PropertySourcesPlaceholderConfigurer를
 *    스프링 컨테이너의 빈으로 등록한다.
 * 
 * @EnableTransactionManagement
 *  - 어노테이션(@Transactional) 기반 트랜잭션 AOP기능을 활성화하는 어노테이션
 */
@Configuration
@ComponentScan(basePackages = "com.example")
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class AppConfig {
	
	/*
	 * - 데이터베이스와 연결된 Connection 객체를 제공하는 객체를
	 *   스프링 컨테이너의 빈으로 등록시킨다.
	 * - 이 프로젝트에서는 BasicDataSource 객체가 스프링 컨테이너의 빈으로 등록된다.99
	 */
	@Bean
	public DataSource dataSource(
			@Value("${db.url}")		String url,
			@Value("${db.driver}") String driverClassName,
			@Value("${db.username}") String username,
			@Value("${db.password}") 
			String password) {
		
		/*
		 * BasicDataSource
		 *  - javax.sql.DataSource 인터페이스의 구현체다.
		 *  - 데이터베이스와 연결된 Connection 객체를 관리하고, 제공한다.
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
	 * - JDBC 기술을 이용해서 데이터베이스 액세스 작업을 수행할 때
	 *   사용되는 헬퍼 클래스다.
	 * - 커넥션 연결/해제, 파라미터 바인딩, SQL 전송과 실행, ResultSet 처리와
	 *   같은 반복적인 작업을 내부에서 전부 처리한다.
	 * - 매우 간결한 코드로 데이터베이스 액세스 작업을 수행할 수 있다.
	 * - 사용자의 데이터베이스와 연결하기 위해서 커넥션풀 객체에 의존한다.
	 */
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource ds) {
		return new JdbcTemplate(ds);
	}
	
	/*
	 * PlatformTransactionManager
	 *  - 이 인터페이스는 트랜잭션 처리와 관련된 기능을 추상화한 인터페이스다.
	 *  - 데이터베이스 액세스 기술에 맞게
	 *  	이 인터페이스를 구현한 다양한 트랜잭션매니저 구현체가 있다.
	 *  - 구현클래스
	 *  	- DataSourceTransactionManager
	 *  	- JdbcTransactionManager
	 *  	- HibernateTransactionManager
	 *  	- JpaTransactionManager
	 *  	...
	 *  - Spring Boot는 해당 프로젝트의 라이브러리 의존성을 참조해서
	 *  	적절한 트랜잭션매니저 구현객체를 스프링의 빈으로 등록한다.
	 *  
	 * JdbcTransactionManager
	 *  - JdbcTemplate를 이용한 데이터베이스 액세스 작업에 적절한
	 *  	트랜잭션 처리를 지원하는 트랜잭션매니저 구현체다.
	 *  - 트랜잭션 처리를 위해서 커넥션 객체가 필요하기 때문에
	 *  	커넥션관리를 담당하는 DataSource 구현객체를 주입한다.
	 * 
	 */
	@Bean
	public PlatformTransactionManager txManager(DataSource ds) {
		return new JdbcTransactionManager(ds);
	}
}
