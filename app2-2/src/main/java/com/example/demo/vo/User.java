package com.example.demo.vo;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
	
	private List<String> roleNames;

	@Builder
	public User(int userNo, String username, String password, String name, String email, String deleted,
			Date createdDate, Date updatedDate, List<String> roleNames) {
		super();
		this.userNo = userNo;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.deleted = deleted;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.roleNames = roleNames;
	}
	
	
}
