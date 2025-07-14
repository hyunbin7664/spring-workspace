package com.example.dao;

import java.util.List;

import com.example.vo.Employee;

public interface EmployeeDao {
	
	/**
	 * 이메일로 직원정보를 조회해서 반환한다.
	 * @param email 이메일
	 * @return 직원정보
	 */
	Employee getEmployeeByEmail(String email);
	
	/**
	 * 모든 직원정보를 조회해서 반환한다.
	 * @return 직원목록
	 */
	List<Employee> getAllEmployees();
	
	/**
	 * 신규 직원정보를 등록한다.
	 * @param emp 직원정보
	 */
	void insertEmployee(Employee emp);
	
	/**
	 * 해당 부서의 소속 직원수를 업데이트 한다.
	 * @param deptId 부서아이디
	 * @param cnt 직원수
	 */
	void updateEmployeeCnt(int deptId, int cnt);
	
	/**
	 * 지정된 부서아이디의 부서에 소속된 직원수를 조회해서 반환한다.
	 * @param deptId 부서 아이디
	 * @return 소속된 직원수
	 */
	int getEmployeeCntByDeptId(int deptId);

}
