package com.example.demo.dto;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/*
 * 정보의 목록을 표현하는 DTO 클래스다.
 * <p>
 * items: 표현할 데이터
 * condition: 페이징 정보
 */
@Getter
@Setter
public class ListDto<T> {

	private List<T> items;
	private Map<String, Object> condition;
	private Pagination pagination;
}
