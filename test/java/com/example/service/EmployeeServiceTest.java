package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.example.AppConfig;
import com.example.dao.EmployeeDao;
import com.example.vo.Employee;

/*
 * @ExtendWith(SpringExtension.class)
 *  - @ExtendWith는 JUnit5로 단위 테스트 실행 시 같이 실행될 추가작업이
 *    구현된 클래스를 지정한다.
 *  - SpringExtension.class는 스프링 기반 애플리케이션에 대한 테스트 수행에
 *    필요한 부가적인 작업이 정의되어 있다.
 * 		- 스프링 컨테이너를 생성하고, 캐싱한다.
 * 		- @Autowired를 감지하고, 필요한 의존성 주입한다.
 * 		  (테스트 실행에 필요한 객체를 제공받을 수 있다.)
 * 		- @Transactional를 감지하고, 데이터베이스 액세스 작업을 롤백시킨다.
 * 
 * @ContextConfiguration(classes = {AppConfig.class})
 *  - 테스트 수행에 필요한 빈(객체) 구성정보를 지정한다.
 *  - XML 파일 혹은 Java 클래스를 지정한다.
 *  - @ExtendWith(SpringExtension.class)과 연계되어
 *    스프링 컨테이너가 생성되고, 빈 생성/조립된다.
 * 
 * @Transactional
 *  - 테스트가 완료되면 데이터베이스에 대한 액세스 작업 결과를 전부 롤백해서
 *    데이터베이스를 테스트 실행 전 상태로 되돌린다.
 *  - 데이터베이스가 연계된 테스트 작업을 반복수행할 수 있게 한다.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
@Transactional
public class EmployeeServiceTest {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeDao employeeDao;
	
	@Disabled
	@Test
	@DisplayName("의존성 주입받은 EmployeeService는 null이 아니다.")
	public void testConfig() {
		assertNotNull(employeeService);
	}
	
	@Test
	@DisplayName("새 직원정보 저장하기")
	public void testCreateEmployee() {
		// 준비 - 객체 준비, 입력값 준비
		Employee emp = new Employee();
		emp.setFirstName("이유");
		emp.setLastName("아");
		emp.setEmail("iu");
		emp.setHireDate(new Date());
		emp.setJobId("MK_REP");
		emp.setDepartmentId(20);
		emp.setSalary(5000);
		
		// 실행
		employeeService.createEmployee(emp);
		
		// 검증 - 모든 직원정보를 조회하고, 그 중에서 "아이유"가 있는지 체크한다.
		int count = 0;
		List<Employee> employees = employeeDao.getAllEmployees();
		for (Employee e : employees) {
			if (e.getEmail().equals("iu")) {
				count++;
			}
		}
		assertEquals(1, count);
	}
	
	@Test
	@DisplayName("이메일이 중복되면 오류 출력")
	public void testCreateEmployee2() {
		// 준비 - 객체 준비, 입력값 준비
		Employee emp = new Employee();
		emp.setFirstName("이유");
		emp.setLastName("아");
		emp.setEmail("iu");
		emp.setHireDate(new Date());
		emp.setJobId("MK_REP");
		emp.setDepartmentId(20);
		emp.setSalary(5000);
		
		// 실행
		employeeService.createEmployee(emp);
		
		// 검증
		assertThrows(RuntimeException.class, () -> {
			employeeService.createEmployee(emp);
		});
	}
	
	

}
