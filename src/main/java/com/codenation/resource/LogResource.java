package com.codenation.resource;

import com.codenation.entity.Log;
import com.codenation.exceptions.EmptyRequestException;
import com.codenation.service.LogService;
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

  @GetMapping
  public Page<Log> findAll(Pageable pageable) {
    return logService.findAll(pageable);
  }

  @GetMapping("/{environment}")
	public Page<Log> findByEnvironment(@PathVariable String environment, @RequestParam(required = false) String level, Pageable pageable) {
		if(level != null) {
			return logService.findByEnvironmentAndLevel(environment,level, pageable);
		}
		return logService.findByEnvironment(environment, pageable);
	}

  @GetMapping("/{environment}/{id}")
  public Optional<Log> findById(
          @PathVariable String environment,
          @PathVariable Long id) {
    return logService.findByIdAndEnvironment(id, environment);
  }

  @GetMapping("/{environment}/search")
  public Page<Log> searchByOriginOrLevel(
          @PathVariable String environment,
          @RequestParam(required = false) String origin,
          @RequestParam(required = false) String level,
          Pageable pageable) {

    return logService.findByOriginOrLevel(origin, level, environment, pageable);
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

    String email = (String) jwtMap.getOrDefault("user_name", "NO EMAIL SET"); //TODO name para requisição
    String token = (String) jwtMap.getOrDefault("jti", "NO TOKEN SET");

    logs.forEach(log -> {
      log.setGeneratedBy(email);
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

    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpEntity> delete(@PathVariable("id") Long id){
    logService.deleteById(id);
    return ResponseEntity.status(201).build();
  }
}
