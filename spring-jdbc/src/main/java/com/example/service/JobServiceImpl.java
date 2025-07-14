package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.JobDao;
import com.example.vo.Job;

@Service
public class JobServiceImpl implements JobService {
	
	@Autowired
	private JobDao jobDao;
	
	@Override
	public void createNewJob(Job job) {
		Job savedJob = jobDao.getJobById(job.getId());
		if (savedJob != null) {
			throw new RuntimeException("동일한 아이디의 직종이 이미 존재합니다.");
		}
		jobDao.insertJob(job);
		
	}
	@Override
	public List<Job> getJobs() {
		
		return jobDao.getAllJobs();
	}

}
