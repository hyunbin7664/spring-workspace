package com.example.demo.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("User")
public class User {

	private int no;
	private String username;
	private String nickname;
	private String email;
	private String tel;
}
