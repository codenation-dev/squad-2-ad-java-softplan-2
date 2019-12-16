package com.codenation.service;

import com.codenation.entity.Log;
import com.codenation.enums.Environment;
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
    Page<Log> result = logRepository.findAllByStored(false, pageable);

    List<Log> tmp = new ArrayList<>();

    result.forEach(log -> {
      if (!tmp.contains(log)){

        Integer events = events(log);
        log.setEvents(events);
        tmp.add(log);

      } else{
        tmp.stream()
                .filter(item -> item.equals(log))
                .findFirst()
                .ifPresent(item -> {
                  log.setEvents(item.getEvents());
                });
      }
    });

    return result;
  }

  public Optional<Log> findById(Long id) {
    return logRepository.findById(id);
  }

  public Optional<Log> findByIdAndEnvironment(Long id, Environment environment) {
    Optional<Log> result = logRepository.findByStoredAndIdAndEnvironment(false, id, environment);

    result.ifPresent(item -> {
      item.setEvents(events(item));
    });

    return result;
  }

  public void deleteById(Long aLong) {
    logRepository.deleteById(aLong);
  }

  public void save(List<Log> objects) {
    for(Log log: objects){
      logRepository.save(log);
    }
  }
  
  public Page<Log> findByEnvironment(Environment environment, Pageable pageable) {
		Page<Log> result =  logRepository.findByStoredAndEnvironmentIgnoreCase(false, environment, pageable);

    List<Log> tmp = new ArrayList<>();

    result.getContent().forEach(log -> {
      if (!tmp.contains(log)){

        Integer events = events(log);
        log.setEvents(events);
        tmp.add(log);

      } else{
        tmp.stream()
                .filter(item -> item.equals(log))
                .findFirst()
                .ifPresent(item -> {
                  log.setEvents(item.getEvents());
                });
      }
    });

    return result;
	}
  
	public Page<Log> findByEnvironmentAndLevel(Environment environment, String level, Pageable pageable) {
		Page<Log> result = logRepository.findByStoredAndEnvironmentAndLevelIgnoreCase(false, environment, level, pageable);

    List<Log> tmp = new ArrayList<>();

    result.getContent().forEach(log -> {
      if (!tmp.contains(log)){

        Integer events = events(log);
        log.setEvents(events);
        tmp.add(log);

      } else{
        tmp.stream()
                .filter(item -> item.equals(log))
                .findFirst()
                .ifPresent(item -> {
                  log.setEvents(item.getEvents());
                });
      }
    });

    return result;
	}

  public Page<Log> findByOriginOrLevelOrDetail(String origin, String level, String detail, Environment environment, Pageable pageable) {
    Page<Log> result = logRepository.findByStoredAndEnvironmentAndOriginContainingOrLevelContainingIgnoreCaseOrDetailContainingIgnoreCase(false, origin, level, detail, environment, pageable);

    List<Log> tmp = new ArrayList<>();

    result.getContent().forEach(log -> {
      if (!tmp.contains(log)){

        Integer events = events(log);
        log.setEvents(events);
        tmp.add(log);

      } else{
        tmp.stream()
                .filter(item -> item.equals(log))
                .findFirst()
                .ifPresent(item -> {
                  log.setEvents(item.getEvents());
                });
      }
    });

    return result;
  }

public Page<Log> findByEnvironmentAndLevelOrDetailOrOrigin(String environment, String level, String detail,
		String origin, Pageable pageable) {
	return logRepository.findByStoredAndEnvironmentOrLevelIgnoreCaseOrDetailOrOrigin(false, environment, level, detail, origin, pageable);
	
}

public Page<Log> findByEnvironmentAndLevelOrDetailOrOriginOrderBy(String environment, String level, String detail,
		String origin, String orderBy, Pageable pageable) {
	return orderBy == "level" ? logRepository.findByStoredAndEnvironmentOrLevelIgnoreCaseOrDetailOrOriginOrderByLevelAsc(false, environment, level, detail, origin, pageable):
		logRepository.findByStoredAndEnvironmentOrLevelIgnoreCaseOrDetailOrOriginOrderByEventsAsc(false, environment, level, detail, origin, pageable);
	
}

  
}
