package com.codenation.resource;

import com.codenation.entity.Log;
import com.codenation.service.LogService;
import com.codenation.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("api/v1/log")
public class LogResource {

  @Autowired
  private LogService logService;

  @GetMapping("/{id}")
  public Optional<Log> findById(@PathVariable Long id){
    return logService.findById(id);
  }

  @GetMapping("/logs/{environment}")
	public Page<Log> 
	findByEnvironment(@PathVariable String environment, @RequestParam(required = false) String level) {
		if(level != null) {
			return logService.findByEnvironmentAndLevel(environment,level);
		}
		return logService.findByEnvironment(environment);
	}
  


  @PostMapping
  public ResponseEntity<HttpEntity> create(@RequestBody @Valid List<Log> logs, HttpServletRequest req){
    Map<String, String> headers = new WebUtils().getHeadersInfo(req);

    String token = headers.getOrDefault("authorization".toLowerCase(), "NO TOKEN");

    if(!token.equals("NO TOKEN")) {token = token.substring(7);}

    List<Log> result = new ArrayList<>();
    for(Log log: logs) {
      result.add(new Log(log.getTitle(), log.getLevel(), log.getDetail(), new Date(), req.getRemoteAddr(), token, log.getEnv()));
    }

    logService.save(result);
    return ResponseEntity.ok().build();
  }

  @PatchMapping("/store/{ids}")
  public ResponseEntity<HttpEntity> patchAll(@PathVariable List<Long> ids){ //store?ids=1,2,3,4
    List<Log> result = new ArrayList<>();

    for(Long id: ids){
      Optional<Log> tmp = logService.findById(id);
      tmp.ifPresent(log -> {log.setStored(!log.getStored()); result.add(log);
      });
    }
      logService.save(result);
    return !result.isEmpty() ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpEntity> delete(@PathVariable("id") Long id){
    logService.deleteById(id);
    return ResponseEntity.ok().build();
  }
}
