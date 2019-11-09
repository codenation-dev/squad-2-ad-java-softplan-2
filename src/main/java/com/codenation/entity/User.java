package com.codenation.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	Long id;
	String name;
	String email;
	String password;
	int acessLevel;
	

}
