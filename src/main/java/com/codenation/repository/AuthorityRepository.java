package com.codenation.repository;

import com.codenation.entity.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {
	@Override
	void delete(Authority authority);
	Optional<Authority> findByName(String name);
}
