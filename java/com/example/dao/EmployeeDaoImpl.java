package com.example.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.vo.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
	@Autowired
	private JdbcTemplate template;
	
	@Override
	public Employee getEmployeeByEmail(String email) {
		String sql = """
			select employee_id			as id,
				   first_name			as firstName,
				   last_name			as lastName,
				   email,
				   job_id				as jobId
			from employees
		""";
		
		List<Employee> employees = template.query(sql, new BeanPropertyRowMapper<>(Employee.class));
		
		if (employees.isEmpty()) {
			return null;
		} else {
			return employees.get(0);
		}
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		String sql = """
			select employee_id		as id,
				   first_name		as firstName,
				   last_name		as lastName,
				   email,
				   job_id			as jobId
			from employees
		""";
		return template.query(sql, new BeanPropertyRowMapper<>(Employee.class));
	}
	
	@Override
	public void insertEmployee(Employee emp) {
		String sql = """
				insert into employees
				(employee_id, first_name, last_name, email,
				 hire_date, job_id, salary, department_id)
				values
				(employees_seq.nextval, ?, ?, ?, ?, ?, ?, ?)
			""";
		
		template.update(sql,
						emp.getFirstName(),
						emp.getLastName(),
						emp.getEmail(),
						emp.getHireDate(),
						emp.getJobId(),
						emp.getSalary(),
						emp.getDepartmentId());
	}
	
	@Override
	public void updateEmployeeCnt(int deptId, int cnt) {
		String sql = """
			update departments
			set
				employee_cnt = ?
			where
				department_id = ?
		""";
		
		template.update(sql, cnt, deptId);
	}

	@Override
	public int getEmployeeCntByDeptId(int deptId) {
		String sql = """
			select nvl(count(*), 0)
			from employees
			where department_id = ?
		""";
		return template.queryForObject(sql, Integer.class, deptId);
	}
	
}
