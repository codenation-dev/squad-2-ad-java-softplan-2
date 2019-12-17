package com.codenation.service;

import com.codenation.entity.Log;
import com.codenation.enums.Environment;
import com.codenation.enums.Level;
import com.codenation.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LogService {

  @Autowired
  private LogRepository logRepository;

  public Boolean existsById(Long id){
    return logRepository.existsById(id);
  }


  public Integer events (Log log){
    return logRepository.countByStoredAndTitleAndDetailAndOriginAndLevelAndEnvironment(
            false,
            log.getTitle(),
            log.getDetail(),
            log.getOrigin(),
            log.getLevel(),
            log.getEnvironment()
    );
  }

  public Page<Log> findAll(Pageable pageable) {
    return logRepository.findAllByStored(false, pageable);
  }

  public Optional<Log> findById(Long id) {
    return logRepository.findById(id);
  }

  public Optional<Log> findByIdAndEnvironment(Long id, Environment environment) {
    return logRepository.findByStoredAndIdAndEnvironment(false, id, environment);
  }

  public void deleteById(Long aLong) {
    logRepository.deleteById(aLong);
  }

  public void save(Log log) {
      logRepository.save(log);
  }
  
  public Page<Log> findByEnvironment(Environment environment, Pageable pageable) {
		return  logRepository.findByStoredAndEnvironment(false, environment, pageable);

  }
  
	public Page<Log> findByEnvironmentAndLevel(Environment environment, Level level, Pageable pageable) {
		return logRepository.findByStoredAndEnvironmentAndLevel(false, environment, level, pageable);
  }
  public Page<Log> findByEnvironmentAndLevelOrderByLevel(Environment environment, Level level, Pageable pageable) {
    return logRepository.findByStoredAndEnvironmentAndLevelOrderByLevelAsc(false, environment, level, pageable);
  }
  public Page<Log> findByEnvironmentAndLevelOrderByEventOccurrences(Environment environment, Level level, Pageable pageable) {
    return logRepository.findByStoredAndEnvironmentAndLevelOrderByEventOccurrencesAsc(false, environment, level, pageable);
  }


  public Page<Log> findByEnvironmentAndDetail(Environment environment, String detail, Pageable pageable) {
    return logRepository.findByStoredAndEnvironmentAndDetailContainingIgnoreCase(false, environment, detail, pageable);
  }
  public Page<Log> findByEnvironmentAndDetailOrderByLevel(Environment environment, String detail, Pageable pageable) {
    return logRepository.findByStoredAndEnvironmentAndDetailContainingIgnoreCaseOrderByLevelAsc(false, environment, detail, pageable);
  }
  public Page<Log> findByEnvironmentAndDetailOrderByEventOccurrences(Environment environment, String detail, Pageable pageable) {
    return logRepository.findByStoredAndEnvironmentAndDetailContainingIgnoreCaseOrderByEventOccurrencesAsc(false, environment, detail, pageable);
  }

  public Page<Log> findByEnvironmentAndOrigin(Environment environment, String origin, Pageable pageable) {
    return logRepository.findByStoredAndEnvironmentAndOriginContaining(false, environment, origin, pageable);
  }
  public Page<Log> findByEnvironmentAndOriginOrderByLevel(Environment environment, String origin, Pageable pageable) {
    return logRepository.findByStoredAndEnvironmentAndOriginContainingOrderByLevelAsc(false, environment, origin, pageable);
  }
  public Page<Log> findByEnvironmentAndOriginOrderByEventOccurrences(Environment environment, String origin, Pageable pageable) {
    return logRepository.findByStoredAndEnvironmentAndOriginContainingOrderByEventOccurrencesAsc(false, environment, origin, pageable);
  }


  public Optional<Log> exists (String title, String detail, String origin, String environment, String level){
    return logRepository.findByTitleAndDetailAndOriginAndEnvironmentAndLevel(
            title,
            detail,
            origin,
            Environment.valueOf(environment.toUpperCase()),
            Level.valueOf(level.toUpperCase())
            );
  }


  
}
