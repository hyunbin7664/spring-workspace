package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.repository.OrderRepository;
import com.example.repository.UserRepository;

/**
 * 주문관련 업무로직을 수행하는 클래스다.
 * UserRepository 구현 객체, OrderRepository 구현객체가 필요하다.
 */
@Service
public class OrderService {


	private UserRepository userRepository;
	private OrderRepository orderRepository;
	
	@Autowired
	public OrderService(@Qualifier("oracleUserRepo") UserRepository userRepository, OrderRepository orderRepository) {
		System.out.println("OrderService 생성자 실행됨");
		System.out.println("OrderService 생성자가 주입받은 객체: " + userRepository);
		System.out.println("OrderService 생성자가 주입받은 객체: " + orderRepository);
		this.userRepository = userRepository;
		this.orderRepository = orderRepository;
	}
	
	public void 주문하기(String name, String product, int price) {
		orderRepository.insertOrder(name, product, price);
		userRepository.updatePoint(name, 1000);
	}
}
