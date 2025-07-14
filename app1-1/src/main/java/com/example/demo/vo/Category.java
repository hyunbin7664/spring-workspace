package com.example.demo.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("Category")
public class Category {

	int catNo;
	String name;
}
