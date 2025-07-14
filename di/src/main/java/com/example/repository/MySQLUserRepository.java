package com.example.repository;

import org.springframework.stereotype.Repository;

@Repository("mySQLUserRepo")
public class MySQLUserRepository implements UserRepository{
	
	@Override
	public void createUser(String name) {
		System.out.println("MySQL 저장소에 [" + name + "]를 저장한다.");
		
	}
	
	@Override
	public void deleteUser(String name) {
		System.out.println("MySQL 저장소에 [" + name + "]를 삭제한다.");
		
	}
	
	@Override
		public void updatePoint(String name, int point) {
		System.out.println("MySQL 저장소에 [" + name + "]의 포인트[" + point + "]를 적립한다.");
			
	}

}
