package com.codenation.repository;

import com.codenation.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
	@Override
	void delete(Role role);

	Role findByName(String name);
}
