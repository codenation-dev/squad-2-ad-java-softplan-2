package com.codenation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User implements Serializable {

	private static final long serialVersionUID = 5447349252217756923L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@NotNull
	@Column(unique = true)
	private String email;

	@NotNull
	private String password;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
					name = "users_roles",
					joinColumns = @JoinColumn(
									name = "user_id", referencedColumnName = "id"),
					inverseJoinColumns = @JoinColumn(
									name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;

	public User(String name, String email, String password, Collection<Role> roles) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public List<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList();
		roles.forEach(role -> authorities.addAll(role.getAuthorities()));
		return authorities;
	}


}
