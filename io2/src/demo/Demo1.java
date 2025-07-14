package demo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class Demo1 {

	public static void main(String[] args) throws IOException {
		String path = "https://img3.daumcdn.net/thumb/R658x0.q70/?fname=https://t1.daumcdn.net/news/202506/15/JTBC/20250615194151668zwog.jpg";
		URL url = URI.create(path).toURL();
		
		// 파이프 생성 및 획득
		InputStream in = url.openStream();
		OutputStream out = new FileOutputStream("sample.jpg");
		System.out.println(in);
		
		IOUtils.copy(in, out);
	}
}
