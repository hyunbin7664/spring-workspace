package com.example.service;

public class KakaoMessageService implements MessageService{

	@Override
	public void connect() {
		System.out.println("카카오톡 메세지 전송 서버에 연결합니다.");
		
	}
	
	@Override
	public void send(String message) {
		System.out.println("카카오톡으로 메세지[" + message + "]를 발송합니다.");
		
	}
}
