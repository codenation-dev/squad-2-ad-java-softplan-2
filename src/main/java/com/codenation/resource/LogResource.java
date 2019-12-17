package com.codenation.resource;

import com.codenation.dto.LogDTO;
import com.codenation.entity.Log;
import com.codenation.enums.Environment;
import com.codenation.enums.Level;
import com.codenation.exceptions.EmptyRequestException;
import com.codenation.exceptions.EnvironmentNotFoundException;
import com.codenation.exceptions.LevelNotFoundException;
import com.codenation.exceptions.LogNotFoundException;
import com.codenation.service.LogService;
import com.codenation.service.UserService;
import com.codenation.utils.JWTParser;
import com.codenation.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("api/v1/log")
public class LogResource {

  @Autowired
  private LogService logService;

  @Autowired
  private UserService userService;

  @GetMapping
  public Page<Log> findAll(Pageable pageable) {
    return logService.findAll(pageable);
  }

  @GetMapping("/{environment}")
	public Page<Log> findByEnvironment(@PathVariable String environment,
                                     @RequestParam(required = false) String level,
                                     Pageable pageable) {
		if(level != null) {
			return logService.findByEnvironmentAndLevel(Enum.valueOf(Environment.class, environment.toUpperCase()), level, pageable);
		}
		return logService.findByEnvironment(Enum.valueOf(Environment.class, environment.toUpperCase()), pageable);
	}

  @GetMapping("/{environment}/{id}")
  public Log findById(
          @PathVariable String environment,
          @PathVariable Long id) throws LogNotFoundException {
    Optional<Log> result = logService.findByIdAndEnvironment(id, Enum.valueOf(Environment.class, environment.toUpperCase()));

    return result.orElseThrow(LogNotFoundException::new);
  }

  @GetMapping("/{environment}/search")
  public Page<Log> searchBy(
          @PathVariable String environment,
          @RequestParam(required = false) String level,
          @RequestParam(required = false) String detail,
          @RequestParam(required = false) String origin,
          @RequestParam(required = false) String orderBy,
          Pageable pageable) {

    if(StringUtils.isEmpty(level) && StringUtils.isEmpty(detail) && StringUtils.isEmpty(origin)){
      return logService.findByEnvironment(Enum.valueOf(Environment.class,environment.toUpperCase()), pageable);
    }else if (!StringUtils.isEmpty(level)){
      if(StringUtils.isEmpty(orderBy)){
        switch(orderBy){
          case "level": return null;
        }
      }
    }
  }


  @PostMapping
  public ResponseEntity<HttpEntity> create(@RequestBody LogDTO logDTO, HttpServletRequest req) throws EmptyRequestException, EnvironmentNotFoundException, LevelNotFoundException {

    if(!logDTO.isValid()) throw new EmptyRequestException();

    Map<String, String> headers = new WebUtils().getHeadersInfo(req);

    String jwt = headers.getOrDefault("authorization", "NO TOKEN");
    if(!jwt.equals("NO TOKEN")) {jwt = jwt.substring(7);}
    Map<String, Object> jwtMap = new JWTParser().parseToken(jwt);
    String emailHeader = (String)jwtMap.getOrDefault("user_name", "NO EMAIL SET");

    AtomicReference<String> email = new AtomicReference<>(emailHeader);

    userService.findByEmail(email.get()).ifPresent(
            user ->
              email.set(user.getName())
            );

    String token = (String) jwtMap.getOrDefault("jti", "NO TOKEN SET");

    Environment environment = Environment.DEVELOPMENT;
    try {
      if (!StringUtils.isEmpty(logDTO.getEnvironment()))
        environment = Environment.valueOf(logDTO.getEnvironment().toUpperCase());
      else
        throw new EnvironmentNotFoundException();
    }catch(IllegalArgumentException e){
      throw new EnvironmentNotFoundException();
    }

    Level level = Level.DEBUG;
    try {
      if (!StringUtils.isEmpty(logDTO.getLevel()))
        level = Level.valueOf(logDTO.getLevel().toUpperCase());
      else
        throw new LevelNotFoundException();
    }catch(IllegalArgumentException e){
      throw new LevelNotFoundException();
    }

    Log log = new Log(
            logDTO.getTitle(),
            level,
            logDTO.getDetail(),
            new Date(),
            req.getRemoteAddr(),
            token,
            email.get(),
            environment,
            false);

    System.out.println(logService.exists(log.getTitle(), log.getDetail(), log.getOrigin(), logDTO.getEnvironment(), logDTO.getLevel()).isPresent());
    Log logResult = logService.exists(log.getTitle(), log.getDetail(), log.getOrigin(), logDTO.getEnvironment(), logDTO.getLevel()).orElse(log);

    logResult.addEvent(email.get(), new Date());
    logService.save(logResult);

    return ResponseEntity.status(201).build();
  }

  @PatchMapping("/store/{ids}")
  public ResponseEntity<HttpEntity> patchAll(@PathVariable List<Long> ids) throws EmptyRequestException {
    AtomicReference<Boolean> flag = new AtomicReference<>(false);

    for(Long id: ids){
      Optional<Log> tmp = logService.findById(id);
      tmp.ifPresent(log -> {
        log.setStored(!log.getStored());
        logService.save(log);
        flag.set(true);
      });
    }

    if (flag.get().equals(Boolean.FALSE)) throw new EmptyRequestException();

    return ResponseEntity.status(201).build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpEntity> delete(@PathVariable("id") Long id) throws LogNotFoundException {
      if (logService.existsById(id).equals(true)){
        logService.deleteById(id);
        return ResponseEntity.status(201).build();
      }
      throw new LogNotFoundException();
  }
}
