package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sample_users")
@Getter
@Setter
public class User {

	@Id
	@Column(name = "user_no")
	private Integer no;
	
	@Column(name = "user_name")
	private String name;
	
	@Column(name = "user_email")
	private String email;
}
