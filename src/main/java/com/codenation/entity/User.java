package com.codenation.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

	private String name;
	private String email;
	private String password;
	private int accessLevel;

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public int getAcessLevel() {
		return accessLevel;
	}

	public User(String name, String email, String password, int accessLevel) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.accessLevel = accessLevel;
	}
}
