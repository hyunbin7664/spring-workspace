package com.example.demo.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("Todo")
public class Todo {

	int todoNo;
	int userNo;
	int catNo;
	String title;
	String content;
	Date dueDate;
	Date completeDate;
	String status;
	Date createdDate;
	Date updatedDate;
	
	User user;
	Category category;
}
