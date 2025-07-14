package com.example.service;

import org.springframework.stereotype.Service;

@Service("sms")
public class SmsMessageService implements MessageService{

	@Override
	public void connect() {
		System.out.println("SMS 메세지 전송 서버에 연결합니다.");
		
	}
	
	@Override
	public void send(String message) {
		System.out.println("SMS로 메세지[" + message + "]를 발송합니다.");
		
	}
}
