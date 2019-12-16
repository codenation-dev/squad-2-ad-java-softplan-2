package com.codenation.resource;

import com.codenation.entity.Log;
import com.codenation.enums.Environment;
import com.codenation.exceptions.EmptyRequestException;
import com.codenation.exceptions.LogNotFoundException;
import com.codenation.service.LogService;
import com.codenation.service.UserService;
import com.codenation.utils.JWTParser;
import com.codenation.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
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
			return logService.findByEnvironmentAndLevel(Enum.valueOf(Environment.class, environment.toUpperCase()),level, pageable);
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

	  return orderBy == null ?
            logService.findByEnvironmentAndLevelOrDetailOrOrigin(environment, level, detail, origin, pageable) :
		        logService.findByEnvironmentAndLevelOrDetailOrOriginOrderBy(environment, level, detail, origin, orderBy, pageable);


  }


  @PostMapping
  public ResponseEntity<HttpEntity> create(@RequestBody List<Log> logs, HttpServletRequest req) throws EmptyRequestException {

    AtomicReference<Boolean> flag = new AtomicReference<>(false);
    logs.forEach(log -> {
      if(!log.isValid()) flag.set(true);
    });

    if(flag.get()) throw new EmptyRequestException();

    Map<String, String> headers = new WebUtils().getHeadersInfo(req);

    String jwt = headers.getOrDefault("authorization".toLowerCase(), "NO TOKEN");

    if(!jwt.equals("NO TOKEN")) {jwt = jwt.substring(7);}

    Map<String, Object> jwtMap = new JWTParser().parseToken(jwt);
    String emailHeader = (String)jwtMap.getOrDefault("user_name", "NO EMAIL SET");

    AtomicReference<String> email = new AtomicReference<>(emailHeader);

    userService.findByEmail(email.get()).ifPresent(
            user -> {
              email.set(user.getName());
            });

    String token = (String) jwtMap.getOrDefault("jti", "NO TOKEN SET");

    logs.forEach(log -> {
      log.setGeneratedBy(email.get());
      log.setToken(token);
      log.setCreatedAt(new Date());
      log.setOrigin(req.getRemoteAddr());
    });

    logService.save(logs);

    return ResponseEntity.ok().build();
  }

  @PatchMapping("/store/{ids}")
  public ResponseEntity<HttpEntity> patchAll(@PathVariable List<Long> ids) throws EmptyRequestException {
    List<Log> result = new ArrayList<>();

    for(Long id: ids){
      Optional<Log> tmp = logService.findById(id);
      tmp.ifPresent(log -> {log.setStored(!log.getStored()); result.add(log);
      });
    }
      logService.save(result);

    if (result.isEmpty()) throw new EmptyRequestException();

    return ResponseEntity.status(201).build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpEntity> delete(@PathVariable("id") Long id) throws LogNotFoundException {
      if (logService.existsById(id)){
        logService.deleteById(id);
        return ResponseEntity.status(201).build();
      }
      throw new LogNotFoundException();
  }
}
