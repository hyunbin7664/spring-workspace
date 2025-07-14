package com.example;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.service.JobService;
import com.example.vo.Job;

public class App {

	public static void main(String[] args) {
		ApplicationContext context
		= new AnnotationConfigApplicationContext(AppConfig.class);
		
		JobService service
		= context.getBean(JobService.class);
		
		List<Job> jobs
		= service.getJobs();
		
		for (Job job : jobs) {
			System.out.println(job.getId() + ", " + job.getTitle());
		}
		
	}
}
