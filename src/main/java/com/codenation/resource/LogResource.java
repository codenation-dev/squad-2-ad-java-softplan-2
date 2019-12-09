package com.codenation.resource;

import com.codenation.entity.Log;
import com.codenation.service.LogService;
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
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/log")
public class LogResource {

  @Autowired
  private LogService logService;

  @GetMapping("/{id}")
  public Optional<Log> findById(@PathVariable Long id){
    return logService.findById(id);
  }

  @GetMapping()
  public Page<Log> findAll(@RequestParam(required = false) String search,
                           @RequestParam(required = false) String field,
                           Pageable pageable)
  {

    Page<Log> result = logService.findAll(pageable);

    if (!StringUtils.isEmpty(search) && !StringUtils.isEmpty(field)){
      switch (field.toLowerCase()){
        case "level":
          result = logService.findByLevel(search, pageable);
          break;
        case "detail":
          result =  logService.findByDetail(search, pageable);
          break;
        case "origin":
          result =  logService.findByOrigin(search, pageable);
          break;
        default:
          result =  logService.findAll(pageable);
          break;
      }
    }
    return result;
  }


  @PostMapping
  public ResponseEntity<HttpEntity> create(@RequestBody @Valid List<Log> logs, HttpServletRequest req){
    Map<String, String> headers = new WebUtils().getHeadersInfo(req);

    String jwt = headers.getOrDefault("authorization".toLowerCase(), "NO TOKEN");

    if(!jwt.equals("NO TOKEN")) {jwt = jwt.substring(7);}

    Map<String, Object> jwtMap = new JWTParser().parseToken(jwt);

    String email = (String) jwtMap.getOrDefault("user_name", "NO EMAIL SET");
    String token = (String) jwtMap.getOrDefault("jti", "NO TOKEN SET");

    List<Log> result = new ArrayList<>();
    for(Log log: logs) {
      log.setCreatedAt(new Date());
      log.setOrigin(req.getRemoteAddr());
      log.setGeneratedBy(email);
      log.setToken(token);
      result.add(log);
    }

    logService.save(result);
    return ResponseEntity.ok().build();
  }

  @PatchMapping("/store/{ids}")
  public ResponseEntity<HttpEntity> patchAll(@PathVariable List<Long> ids){
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
