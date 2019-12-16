package com.codenation.entity;

import com.codenation.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name="application_user")
public class User implements Serializable {

	private static final long serialVersionUID = 8327398596591441079L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	private List<Role> roles;

	public User(String name, String email, String password, List<Role> roles) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public User (UserDTO userDTO){
		this.name = userDTO.getName();
		this.email = userDTO.getEmail();
		this.password = userDTO.getPassword();
		this.roles = userDTO.getRoles();
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

	public void setRoles (List<Role> roles){
		this.roles = new ArrayList<>(roles);
	}


}
