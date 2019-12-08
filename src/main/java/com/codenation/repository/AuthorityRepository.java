package com.codenation.repository;

import com.codenation.entity.Authority;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {
	@Override
	void delete(Authority authority);

	Authority findByName(String name);
}
