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
	public Job getJob(String id) {
		// 모든 직종 정보를 조회한 다음
		// 전달받은 직종 아이디와 일치하는 직종정보를 반환한다.
		List<Job> jobs = jobDao.getAllJobs();
		for (Job job : jobs) {
			if (job.getId().equals(id)) {
				return job;
			}
		}
		return null;
	}

	@Override
	public void createNewJob(Job job) {
		Job savedJob = this.getJob(job.getId());
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
