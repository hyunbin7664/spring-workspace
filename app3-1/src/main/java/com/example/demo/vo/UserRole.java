package com.example.demo.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("UserRole")
public class UserRole {

	private int userNo;
	private String roleName;
}
