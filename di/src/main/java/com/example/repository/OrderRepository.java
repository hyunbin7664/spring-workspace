package com.example.repository;

/**
 * 주문 정보를 CRUD하는 기능을 추상화한 인터페이스다.
 * 이 인터페이스를 구현하는 구현 클래스에서는 실제 저장소에 맞게 추상화된 기능을 구현한다.
 */
public interface OrderRepository {

	void insertOrder(String name, String product, int price);
}
