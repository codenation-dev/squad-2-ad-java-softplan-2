package com.codenation.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.codenation.entity.Log;

public interface LogRepository extends PagingAndSortingRepository<Log, Long> {

}
