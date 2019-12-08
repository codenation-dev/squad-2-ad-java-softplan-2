package com.codenation.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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

	@ManyToMany
	@JoinTable(name = "users_roles", //
					joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), //
					inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private List<Role> roles;

	public User(String name, String email, String password, int accessLevel) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.accessLevel = accessLevel;
	}

	public User (){}


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

	public List<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList();
		roles.forEach(role -> {
			authorities.add(role);
			role.getAuthorities().forEach(authorities::add);
		});
		return authorities;
	}
}
