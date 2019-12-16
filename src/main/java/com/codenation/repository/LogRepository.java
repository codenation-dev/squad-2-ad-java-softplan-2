package com.codenation.repository;

import com.codenation.entity.Log;
import com.codenation.enums.Environment;
import com.codenation.enums.Level;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogRepository extends PagingAndSortingRepository<Log, Long> {

  Page<Log> findAllByStored(Boolean stored, Pageable pageable);

  Optional<Log> findByStoredAndIdAndEnvironment(Boolean stored, Long id, Environment environment);

  Optional<Log> findById(Long id);
  
  Page<Log> findByStoredAndEnvironment(Boolean stored, Environment environment, Pageable pageable);

  Page<Log> findByStoredAndEnvironmentAndLevel(Boolean stored, Environment environment, String level, Pageable pageable);
  
  Page<Log> findByStoredAndEnvironmentOrLevelOrDetailContainingIgnoreCaseOrOriginContaining(Boolean stored, Environment environment, String level, String detail, String origin, Pageable pageable);

  Page<Log> findByStoredAndEnvironmentOrLevelIgnoreCaseOrDetailContainingIgnoreCaseOrOriginOrderByLevelAsc(Boolean stored, Environment environment, String level, String detail, String origin, Pageable pageable);

  Page<Log> findByStoredAndEnvironmentOrLevelOrDetailContainingIgnoreCaseOrOriginContainingOrderByEventsAsc(Boolean stored, Environment environment, String level, String detail, String origin, Pageable pageable);
  
  Integer countByStoredAndTitleAndDetailAndOriginAndLevelAndEnvironment(Boolean stored, String title, String detail, String origin, Level level, Environment environment);

}
