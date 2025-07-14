package com.example.repository;

import org.springframework.stereotype.Repository;

@Repository("oracleOrderRepo")
public class OracleOrderRepository implements OrderRepository {

	@Override
	public void insertOrder(String name, String product, int price) {
		System.out.println("오라클 저장소에 [" + name + " , " + product + " , " + price + "]를 저장한다.");
		
	}
}
