package com.example.demo.config;

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
		
		// 생성된 ModelMapper객체에 설정을 추가하기 위해 Configuration 획득
		mapper.getConfiguration()
			// 매핑 전략을 설정한다. 엄격하게 매칭 : 프로퍼티 이름이 완전히 일치할 때만 매핑한다.
			.setMatchingStrategy(MatchingStrategies.STRICT)
			// null값은 skip한다.(null 필드는 덮어쓰지 않음)
			.setSkipNullEnabled(true);
		
		return mapper;
	}
}

/*
 * ModelMapper : 객체 간 데이터를 복사해주는 도구
 * 		예를 들어, UserEntity → UserDTO로 바꾸거나 반대로 바꿀 때 사용
 * 		필드명이 같으면 자동으로 매핑해줘서 수동으로 하나씩 복사 안 해도 돼서 편함
 * 
 * @Configuration
 * public class AppConfig {}
 * 	: 이 클래스는 설정용 클래스라는 의미
 * 		-> 스프링이 시작할 때 여기서 설정한 것들을 등록해줌
 * 
 * @Bean
 * public ModelMapper modelMapper() {}
 * 	: ModelMapper를 스프링 빈(Bean) 으로 등록하는 것
 * 		-> 다른 클래스에서 @Autowired로 이 객체를 쓸 수 있게 해줌!
 * 
 * * @Bean : Spring이 관리해주는 객체(Bean)를 직접 등록하는 방법
 * 		-> 외부 라이브러리 같은 내가 직접 만든 게 아닌 클래스를 Bean으로 등록하고 싶을 때 사용
 */
