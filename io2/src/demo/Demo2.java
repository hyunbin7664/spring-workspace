package demo;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Demo2 {

	public static void main(String[] args) throws IOException {
		
		// 값을 파일에 기록하기
		// 파이프 생성하기
		FileOutputStream out = new FileOutputStream("demo2.txt");
		FileWriter out2 = new FileWriter("demo3.txt");
		
		// 쓰기
		out.write('A');
		out2.write("A");
		
		// 닫기
		out.close();
		out2.close();
		
	}
}
