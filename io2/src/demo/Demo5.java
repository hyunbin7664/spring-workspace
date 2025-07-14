package demo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Demo5 {

	public static void main(String[] args) throws IOException {
		
		Role role = new Role();
		role.setName("ROLE_USER");
		role.setEnable(true);
		
		User user = new User();
		user.setNo(100);
		user.setUsername("hong");
		user.setPassword("zxcv1234");
		user.setName("홍길동");
		user.setEmail("hong@example.com");
		user.setRole(role);
		
		// 객체를 내보내서 파일에 기록하기
		FileOutputStream fos = new FileOutputStream("user.sav");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		// 2. 객체 내보내기
		oos.writeObject(user);
		
		// 3. 닫기
		oos.close();
	}
}
