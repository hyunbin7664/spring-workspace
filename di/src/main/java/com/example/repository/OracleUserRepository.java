package com.example.repository;

import org.springframework.stereotype.Repository;

/**
 * UserRepository 인터페이스를 구현해서
 * 오라클 데이터베이스에 사용자 정보를 CRUD
 */
@Repository("oracleUserRepo")
public class OracleUserRepository implements UserRepository{

	@Override
	public void createUser(String name) {
		System.out.println("오라클 저장소에 [" + name + "]를 저장한다.");
		
	}
	
	@Override
	public void deleteUser(String name) {
		System.out.println("오라클 저장소에 [" + name + "]를 삭제한다.");
		
	}
	
	@Override
		public void updatePoint(String name, int point) {
		System.out.println("오라클 저장소에 [" + name + "]의 포인트[" + point + "]를 적립한다.");
			
	}
}
