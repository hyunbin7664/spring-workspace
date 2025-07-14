package com.example;

import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.repository.OracleSampleRepository;
import com.example.repository.SampleRepository;
import com.example.service.SampleService;

@Configuration
public class App5Config {

	@Bean
	public Date date() {
		return new Date();
	}
	
	@Bean
	public SampleRepository sampleRepository() {
		SampleRepository x = new OracleSampleRepository();
		System.out.println("생성한 객체: " + x);
		
		return x;
	}
	
	@Bean
	public SampleService sampleService(SampleRepository repo) {
		System.out.println("전달받은 객체: " + repo);
		SampleService service = new SampleService();
		service.setSampleRepository(repo);
		
		return service;
	}
}
