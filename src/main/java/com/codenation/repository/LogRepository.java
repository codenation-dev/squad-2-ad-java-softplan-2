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

  Optional<Log> findByStoredAndIdAndEnvironment(Boolean stored, Long id, String environment);

  Page<Log> findByStoredAndOriginContainingIgnoreCase(Boolean stored, String origin, Pageable pageable);

  Page<Log> findByStoredAndDetailContainingIgnoreCase(Boolean stored, String detail, Pageable pageable);

  Page<Log> findByStoredAndLevelContainingIgnoreCase(Boolean stored, String level, Pageable pageable);
  
  Page<Log> findByStoredAndEnvironmentIgnoreCase(Boolean stored, String environment, Pageable pageable);

  Page<Log> findByStoredAndEnvironmentAndLevelIgnoreCase(Boolean stored, String environment, String level, Pageable pageable);

  Page<Log> findByStoredAndEnvironmentAndOriginOrLevelIgnoreCase(Boolean stored, String origin, String level, String environment, Pageable pageable);
  
  Page<Log> findByStoredAndEnvironmentOrderByLevelAsc(Boolean stored, String environment,String level, Pageable pageable);
  
  Page<Log> findByStoredAndEnvironmentOrLevelIgnoreCaseOrDetailOrOrigin(Boolean stored, String environment,String level,String detail,String origin, Pageable pageable);
   
  Page<Log> findByStoredAndEnvironmentOrLevelIgnoreCaseOrDetailOrOriginOrderByLevelAsc(Boolean stored, String environment,String level,String detail,String origin, Pageable pageable);
  
  Page<Log> findByStoredAndEnvironmentOrLevelIgnoreCaseOrDetailOrOriginOrderByEventsAsc(Boolean stored, String environment,String level,String detail,String origin, Pageable pageable);
}
