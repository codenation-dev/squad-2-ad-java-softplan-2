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

  Optional<Log> findByStoredAndIdAndEnv(Boolean stored, Long id, String env);

  Page<Log> findByStoredAndOriginContainingIgnoreCase(Boolean stored, String origin, Pageable pageable);

  Page<Log> findByStoredAndDetailContainingIgnoreCase(Boolean stored, String detail, Pageable pageable);

  Page<Log> findByStoredAndLevelContainingIgnoreCase(Boolean stored, String level, Pageable pageable);
  
  Page<Log> findByStoredAndEnvIgnoreCase(Boolean stored, String environment, Pageable pageable);

  Page<Log> findByStoredAndEnvAndLevelIgnoreCase(Boolean stored, String environment, String level, Pageable pageable);

  Page<Log> findByStoredAndEnvAndOriginOrLevelIgnoreCase(Boolean stored, String origin, String level, String environment, Pageable pageable);

}
