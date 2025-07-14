package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.service.FileDownloadService;

public class App7 {

	public static void main(String[] args) {
		ApplicationContext context
		= new AnnotationConfigApplicationContext(App7Config.class);
		
		FileDownloadService service
		= context.getBean(FileDownloadService.class);
		
		service.파일다운로드("이력서.hwp");
	}
}
