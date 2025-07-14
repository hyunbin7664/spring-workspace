package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.EmployeeDao;
import com.example.vo.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	@Transactional
	public void createEmployee(Employee emp) {
		
		Employee savedEmp
			= employeeDao.getEmployeeByEmail(emp.getEmail());
		if (savedEmp != null) {
			throw new RuntimeException("이메일 중복");
		}
		
		// 신규 직원정보를 EMPLOYEES 테이블에 저장시킨다.
		employeeDao.insertEmployee(emp);
		
		// 해당 부서에 소속된 직원수를 조회한다.
		int deptId = emp.getDepartmentId();
		int cnt = employeeDao.getEmployeeCntByDeptId(deptId);
		
		// 해당 부서의 직원수를 업데이트한다.
		employeeDao.updateEmployeeCnt(deptId, cnt);
	}

}
