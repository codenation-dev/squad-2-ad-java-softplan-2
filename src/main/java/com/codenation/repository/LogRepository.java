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

  Integer countByStoredAndTitleAndDetailAndOriginAndLevelAndEnvironment(Boolean stored, String title, String detail, String origin, Level level, Environment environment);

  Optional<Log> findByStoredAndIdAndEnvironment(Boolean stored, Long id, Environment environment);

  Optional<Log> findById(Long id);
  
  Page<Log> findByStoredAndEnvironment(Boolean stored, Environment environment, Pageable pageable);

  Page<Log> findByStoredAndEnvironmentAndLevel(Boolean stored, Environment environment, Level level, Pageable pageable);
  Page<Log> findByStoredAndEnvironmentAndLevelOrderByLevelAsc(Boolean stored, Environment environment, Level level, Pageable pageable);
  Page<Log> findByStoredAndEnvironmentAndLevelOrderByEventOccurrencesAsc(Boolean stored, Environment environment, Level level, Pageable pageable);


  Page<Log> findByStoredAndEnvironmentAndDetailContainingIgnoreCase(Boolean stored, Environment environment, String detail, Pageable pageable);
  Page<Log> findByStoredAndEnvironmentAndDetailContainingIgnoreCaseOrderByLevelAsc(Boolean stored, Environment environment, String detail, Pageable pageable);
  Page<Log> findByStoredAndEnvironmentAndDetailContainingIgnoreCaseOrderByEventOccurrencesAsc(Boolean stored, Environment environment, String detail, Pageable pageable);


  Page<Log> findByStoredAndEnvironmentAndOriginContaining(Boolean stored, Environment environment, String origin, Pageable pageable);
  Page<Log> findByStoredAndEnvironmentAndOriginContainingOrderByLevelAsc(Boolean stored, Environment environment, String origin, Pageable pageable);
  Page<Log> findByStoredAndEnvironmentAndOriginContainingOrderByEventOccurrencesAsc(Boolean stored, Environment environment, String origin, Pageable pageable);


  Optional<Log> findByTitleAndDetailAndOriginAndEnvironmentAndLevel(String title, String detail, String origin, Environment environment, Level level);
}
