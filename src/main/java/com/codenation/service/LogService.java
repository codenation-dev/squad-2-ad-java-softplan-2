package com.codenation.service;

import com.codenation.entity.Log;
import com.codenation.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class LogService {

  @Autowired
  private LogRepository logRepository;

  public Page<Log> findAll(Pageable pageable) {
    Page<Log> result = logRepository.findAllByStored(false, pageable);

    for(Log log: result.getContent()){
      log.setFreq(Collections.frequency(result.getContent(), log));
    }
    return result;
  }

  public Optional<Log> findById(Long id) {
    return logRepository.findById(id);
  }

  public long count() {
    return logRepository.count();
  }


  public void deleteById(Long aLong) {
    logRepository.deleteById(aLong);
  }

  public Page<Log> findByOrigin(String origin, Pageable pageable) {
    Page<Log> result = logRepository.findByOriginContainingIgnoreCase(origin, pageable);

    for(Log log: result.getContent()){
      log.setFreq(Collections.frequency(result.getContent(), log));
    }
    return result;
  }

  public Page<Log> findByDetail(String detail, Pageable pageable) {
    Page<Log> result = logRepository.findByDetailContainingIgnoreCase(detail, pageable);

    for(Log log: result.getContent()){
      log.setFreq(Collections.frequency(result.getContent(), log));
    }
    return result;
  }

  public Page<Log> findByLevel(String level, Pageable pageable) {
    Page<Log> result = logRepository.findByLevelContainingIgnoreCase(level, pageable);

    for(Log log: result.getContent()){
      log.setFreq(Collections.frequency(result.getContent(), log));
    }

    return result;
  }

  public void save(List<Log> objects) {
    for(Log log: objects){
      logRepository.save(log);
    }
  }
  
  public Page<Log> findByEnv(String environment, Pageable pageable) {
		return logRepository.findByEnv(environment, pageable);
	}
  
	public Page<Log> findByEnvAndLevel(String environment, String level,  Pageable pageable) {
		return logRepository.findByEnvAndLevel(environment, level, pageable);
	}

  
}
