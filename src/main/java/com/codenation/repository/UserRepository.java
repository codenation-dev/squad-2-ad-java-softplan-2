package com.codenation.repository;

import com.codenation.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>{

  @Query(value = "SELECT * FROM user WHERE name = ?1", nativeQuery = true)
  User findByName(String s);
}
