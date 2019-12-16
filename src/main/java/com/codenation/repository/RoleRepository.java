package com.codenation.repository;

import com.codenation.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	@Override
	void delete(Role role);

	Optional<Role> findByNameIgnoreCase(String name);
}
