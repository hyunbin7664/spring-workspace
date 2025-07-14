package com.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FileDownloadService {

	/*
	 * @Value
	 * 	- 값(기본자료형타입의 값, 문자열)을 의존성 주입하는 어노테이션이다.
	 * 	- ${key} : properties 파일에 설정된 값을 찾아서 필드에 주입한다.
	 */
	@Value("${app.file.save-directory}")
	private String saveDirectory;
	
	@Value("${app.file.max-upload-size}")
	private long maxUploadSize;
	
	public void 파일다운로드(String filename) {
		System.out.println("파일 다운로드 실행됨");
		System.out.println("디렉토리: " + saveDirectory);
		System.out.println("최대 업로드 사이즈: " + maxUploadSize);
		System.out.println("파일명: " + filename);
	}
}
