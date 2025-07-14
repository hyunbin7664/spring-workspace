package com.example.service;

import com.example.vo.Employee;

public interface EmployeeService {
	
	/**
	 * 신규 직원을 등록하는 업모로직을 수행한다.
	 * @param emp 신규 회원 정보
	 */
	void createEmployee(Employee emp);

}
