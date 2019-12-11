package com.codenation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.codenation.entity.Log;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LogRepository extends PagingAndSortingRepository<Log, Long> {

  Page<Log> findAllByStored(Boolean stored, Pageable pageable);

  Optional<Log> findById(Long id);

  Page<Log> findByOriginContainingIgnoreCase(String origin, Pageable pageable);

  Page<Log> findByDetailContainingIgnoreCase(String detail, Pageable pageable);

  Page<Log> findByLevelContainingIgnoreCase(String level, Pageable pageable);
  
  Page<Log> findByEnvIgnoreCase(String environment, Pageable pageable);

  Page<Log> findByEnvAndLevelIgnoreCase(String environment, String level, Pageable pageable);

}
