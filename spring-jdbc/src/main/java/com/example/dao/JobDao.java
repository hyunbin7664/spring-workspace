package com.example.dao;

import java.util.List;

import com.example.vo.Job;

/*
 * 직종 정보 관련 CRUD 작업을 정의한다.
 */
public interface JobDao {

	/**
	 * 신규 직종 정보를 추가한다.
	 * @param job 신규 직종 정보
	 */
	void insertJob(Job job);
	
	/**
	 * 모든 직종 정보를 조회해서 반환한다
	 * @return 직종 정보 목록
	 */
	List<Job> getAllJobs();
	
	/**
	 * 지정된 직종 아이디에 해당하는 직종 정보를 조회해서 반환한다.
	 * @param id 직종아이디
	 * @return 직종 정보
	 */
	Job getJobById(String id);
	
	/**
	 * 지정된 직종 아이디에 해당하는 직종 정보를 삭제한다.
	 * @param id 직종 아이디
	 */
	void deleteJobById(String id);
}
