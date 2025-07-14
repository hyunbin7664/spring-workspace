package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.service.FileUploadService;

public class App6 {

	public static void main(String[] args) {
		
		ApplicationContext context
		= new ClassPathXmlApplicationContext("ctx6.xml");
		
		FileUploadService service 
		= context.getBean(FileUploadService.class);
		
		service.파일업로드("이력서.hwp", 245000);
	}
}
