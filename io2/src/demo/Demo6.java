package demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

public class Demo6 {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		// 직렬화된 객체정보를 파일에서 읽어와서 		객체로 복원하기
		//				  FileInputStream   ObjectInputStream
		
		// 1. 스트림 생성 및 연결
		FileInputStream fis = new FileInputStream("user.sav");
		ObjectInput ois = new ObjectInputStream(fis);
		
		// 2. 객체 복원
		User user = (User) ois.readObject();
		System.out.println("복원된 객체 정보");
		System.out.println("번호: " + user.getNo());
		System.out.println("아이디: " + user.getUsername());
		System.out.println("비밀번호: " + user.getPassword());
		System.out.println("이름: " + user.getName());
		System.out.println("이메일: " + user.getEmail());
		System.out.println("직급: " + user.getRole().getName());
		
		// 3. 닫기
		ois.close();
	}
}
