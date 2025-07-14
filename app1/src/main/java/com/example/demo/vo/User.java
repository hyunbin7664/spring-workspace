package com.example.demo.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Alias("User")
public class User {

	private int userNo;
	private String username;
	private String password;
	private String name;
	private String email;
	private String deleted;
	private Date createdDate;
	private Date updatedDate;
}
