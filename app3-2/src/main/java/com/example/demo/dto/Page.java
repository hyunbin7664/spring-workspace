package com.example.demo.dto;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page<T> {

	private List<T> items;
	private Map<String, Object> condition;
	private Pagination pagination;
}
