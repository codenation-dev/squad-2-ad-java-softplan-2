package com.codenation.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.codenation.entity.User;

public interface UserRepoitory extends PagingAndSortingRepository<User, Long>{

}
