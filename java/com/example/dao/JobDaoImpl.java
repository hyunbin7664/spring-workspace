package com.example.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.vo.Job;

@Repository
public class JobDaoImpl implements JobDao {
	
	@Autowired
	private JdbcTemplate template;
	
	@Override
		public void insertJob(Job job) {
			String sql = """
					insert into jobs
					(job_id, job_title, min_salary, max_salary)
					values
					(?, ?, ?, ?)
					""";
			template.update(sql, job.getId(), 
									job.getTitle(), 
									job.getMinSalary(), 
									job.getMaxSalary());
			
		}
	@Override
		public List<Job> getAllJobs() {
			String sql = """
					select job_id as id
						, job_title as title
						, min_salary as minSalary
						, max_salary as maxSalary
					from jobs
					order by job_id asc
					""";
			return template.query(sql, new BeanPropertyRowMapper<>(Job.class));
		}
	@Override
		public Job getJobById(String id) {
			String sql = """
					select job_id as id
						, job_title as title
						, min_salary as minSalary
						, max_salary as maxSalary
					from jobs
					where job_id = ?
					""";
			return template.queryForObject(sql, new BeanPropertyRowMapper<>(Job.class), id);
		}
	@Override
		public void deleteJobById(String id) {
			String sql = """
						delete from jobs
						where job_id = ?
					""";
			template.update(sql, id);
			
		}
}
