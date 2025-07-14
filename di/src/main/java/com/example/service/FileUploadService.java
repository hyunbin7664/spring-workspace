package com.example.service;

public class FileUploadService {

	private String saveDirectory;
	private long maxUploadSize;
	
	public void setSaveDirectory(String saveDirectory) {
		this.saveDirectory = saveDirectory;
	}
	
	public void setMaxUploadSize(long maxUploadSize) {
		this.maxUploadSize = maxUploadSize;
	}
	
	public void 파일업로드(String filename, int size) {
		System.out.println("파일 업로드 실행됨");
		System.out.println("디렉토리: " + saveDirectory);
		System.out.println("최대 업로드 사이즈: " + maxUploadSize);
		System.out.println("파일명: " + filename);
		System.out.println("현재 파일 사이즈: " + size);
		
	}
}
