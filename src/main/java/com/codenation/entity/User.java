package com.codenation.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@NotNull
	@Column(unique = true)
	private String email;

	@NotNull
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

	public int getAccessLevel() {
		return accessLevel;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public User (){}

	public User(String name, String email, String password, int accessLevel) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.accessLevel = accessLevel;
	}
}
