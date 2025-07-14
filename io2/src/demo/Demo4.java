package demo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo4 {

	public static void main(String[] args) throws IOException {
		System.out.println("백업본 기록하기.....");
		long beginTime = System.currentTimeMillis();
		
		// commons-io-2.19.0-bin.zip을 읽어서
		// backup.zip으로 백업본 기록하기
		
		// 1. 읽기/쓰기 스트림 생성
		String src = "commons-io-2.19.0-bin.zip";
		String dest = "backup.zip";
		FileInputStream fis = new FileInputStream(src);
		FileOutputStream fos = new FileOutputStream(dest);

//		BufferedInputStream in = new BufferedInputStream(fis);
//		BufferedOutputStream out = new BufferedOutputStream(fos);

		// 2. 읽어서 기록하기
		
//		int value = 0;
//		while ((value = in.read()) != -1) {
//			out.write(value);
//		}
		
		// 3. 닫기
		fis.close();
		fos.close();
		
		long endTime = System.currentTimeMillis();
		System.out.println("소요시간: " + (endTime - beginTime) + " 밀리초");
	}
}
