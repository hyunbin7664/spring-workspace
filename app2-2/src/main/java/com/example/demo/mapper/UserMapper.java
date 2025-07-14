package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.vo.User;

@Mapper
public interface UserMapper {

	void insertUser(User user);
	void insertUserRole(@Param("userNo") int userNo, @Param("roleName") String roleName);
	
	User getUserByUsername(String username);
	User getUserByUsernameWithRoleNames(String username);
	User getUserByEmail(String email);
	User getUserByUserNo(int userNo);
}
