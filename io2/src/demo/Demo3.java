package demo;

import java.io.FileInputStream;
import java.io.IOException;

public class Demo3 {

	public static void main(String[] args) throws IOException {
		
		// 파일의 내용 읽어오기
		
		// 1. 스트림 생성
		FileInputStream in = new FileInputStream("sample.txt");
		
		// 2. 읽기
//		int value = in.read(); 	System.out.println(value);
//		value = in.read();		System.out.println(value);
//		value = in.read();		System.out.println(value);
//		value = in.read();		System.out.println(value);
//		value = in.read();		System.out.println(value);
//		value = in.read();		System.out.println(value);
//		value = in.read();		System.out.println(value);
		
		int value = 0;
		while ((value = in.read()) != -1) {
			System.err.println((char) value);
		}
		
		// 3. 닫기
		in.close();
	}
}
