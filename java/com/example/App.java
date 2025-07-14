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
		
		JobService service = context.getBean(JobService.class);
		
		Job job = new Job();
		job.setId("IT");
		job.setTitle("프로그램 개발자");
		job.setMinSalary(7000);
		job.setMaxSalary(14000);
		
		service.createNewJob(job);
		
//		List<Job> jobs = service.getJobs();
//		for (Job job: jobs) {
//			System.out.println(job.getId() + ", " + job.getTitle());
//		}
	}

}
