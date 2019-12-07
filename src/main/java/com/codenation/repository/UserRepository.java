package com.codenation.repository;

import com.codenation.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

  List<User> findAll();

  Optional<User> findById(Long id);

  @Query(value = "SELECT * FROM user WHERE name = ?1", nativeQuery = true)
  User findByName(String s);

  User findByEmail(String email);
}
