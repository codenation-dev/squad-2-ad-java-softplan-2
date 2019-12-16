package com.codenation.repository;

import com.codenation.enums.Environment;
import com.codenation.enums.Level;
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

  Optional<Log> findByStoredAndIdAndEnvironment(Boolean stored, Long id, Environment environment);

  Optional<Log> findById(Long id);
  
  Page<Log> findByStoredAndEnvironmentIgnoreCase(Boolean stored, Environment environment, Pageable pageable);

  Page<Log> findByStoredAndEnvironmentAndLevelIgnoreCase(Boolean stored, Environment environment, String level, Pageable pageable);

  Page<Log> findByStoredAndEnvironmentAndOriginContainingOrLevelContainingIgnoreCaseOrDetailContainingIgnoreCase(Boolean stored, String origin, String level, String detail, Environment environment, Pageable pageable);

  Integer countByStoredAndTitleAndDetailAndOriginAndLevelAndEnvironment(Boolean stored, String title, String detail, String origin, Level level, Environment environment);

}
