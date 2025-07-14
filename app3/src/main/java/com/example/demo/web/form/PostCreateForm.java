package com.example.demo.web.form;

/*
 * MultipartFile
 * 	- 첨부파일 업로드 기능을 추상화한 인터페이스다.
 * 	- 주의, 첨부파일이 업로드되지 않아도 MultipartFile객체는 생성된다.
 * 	- 주요 API
 * 		boolean isEmpty()
 * 			- 업로드된 첨부파일이 없으면 true를 반환한다.
 * 		String getOriginalFilename()
 * 			- 업로드된 첨부파일명을 반환한다.
 * 		String getContentType()
 * 			- 업로드된 첨부파일의 컨텐츠 타입을 반환한다.
 * 			- 예) image/png와 같은 MIME타입
 * 		long getSize()
 * 			- 업로드된 첨부파일의 사이즈를 바이트값으로 반환한다.
 * 		byte[] getBytes()
 * 			- 업로드된 첨부파일 데이터(바이너리 데이터)를 반환한다.
 * 		InputStream getInputStream()
 * 			- 업로드된 첨부파일과 연결된 스트림을 반환한다.
 * 		void transferTo(File dest)
 * 		void transferTo(Path dest)
 * 			- 업로드된 첨부파일을 dest로 보내서 저장시킨다.
 * 		
 * 		
 */
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateForm {

	private String title;
	private String content;
	private MultipartFile upfile;
}
