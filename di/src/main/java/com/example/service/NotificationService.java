package com.example.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	private Map<String, MessageService> messageServices;
	
	@Autowired
	public void setMessageServices(Map<String , MessageService> messageServices) {
		System.out.println("NotificationService의 Setter 실행됨");
		System.out.println("NotificationService의 Setter에 주입된 객체: " + messageServices);
		this.messageServices = messageServices;
	}
	
	/*
	 * type은 메세지 전송 종류를 나타낸다.
	 * 	"sms", "kakao" 중 하나다.
	 */
	public void sendMessage(String type, String message) {
		System.out.println("type: " + type);
		MessageService service = messageServices.get(type);
		service.connect();
		service.send(message);
	}
}