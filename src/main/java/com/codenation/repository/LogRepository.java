package com.codenation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.codenation.entity.Log;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LogRepository extends PagingAndSortingRepository<Log, Long> {

  Page<Log> findAllByStored(Boolean stored, Pageable pageable);

  Optional<Log> findById(Long id);

  Page<Log> findByOriginContainingIgnoreCase(String origin, Pageable pageable);

  Page<Log> findByDetailContainingIgnoreCase(String detail, Pageable pageable);

  Page<Log> findByLevelContainingIgnoreCase(String level, Pageable pageable);
  
  List<Log> findByEnvironment(String environment);

  List<Log> findByEnvironmentAndLevel(String environment, String level);
  }
