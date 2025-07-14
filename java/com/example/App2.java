package com.example;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.service.EmployeeService;
import com.example.vo.Employee;

public class App2 {
	
	public static void main(String[] args) {
		ApplicationContext context
			= new AnnotationConfigApplicationContext(AppConfig.class);
		
		EmployeeService service = context.getBean(EmployeeService.class);
		
		Employee emp = new Employee();
		emp.setFirstName("성계");
		emp.setLastName("이");
		emp.setEmail("lee1234");
		emp.setJobId("IT_PROG");
		emp.setHireDate(new Date());
		emp.setSalary(6000);
		emp.setDepartmentId(60);
		
		service.createEmployee(emp);
	}

}
