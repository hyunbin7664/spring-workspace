package com.example.service;

import java.util.List;

import com.example.vo.Job;

/*
 * 직종 관련 업무로직 메소드를 정의한다.
 */
public interface JobService {
	
	/**
	 * 지정된 직종아이디에 해당하는 직종 정보를 조회해서 제공하는 업무로직을 수행한다.
	 * @param id 직종 아이디
	 * @return 직종 정보, 존재하지 않으면 null이 반환된다.
	 */
	Job getJob(String id);
	
	/**
	 * 신규 직종 등록하기 업무로직을 수행한다.
	 * @param job 신규 직종 정보
	 */
	void createNewJob(Job job);
	
	/**
	 * 모든 직종 정보를 조회해서 제공하는 업무로직을 수행한다.
	 * @return 모든 직종정보 목록
	 */
	List<Job> getJobs();

}
