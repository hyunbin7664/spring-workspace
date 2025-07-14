package com.example.service;

import com.example.repository.SampleRepository;

public class SampleService {

	 private SampleRepository sampleRepository;
	 
	 public void setSampleRepository(SampleRepository sampleRepository) {
		 this.sampleRepository = sampleRepository;
	 }
	 
	 public void 전체삭제() {
		 sampleRepository.deleteAll();
	 }
}
