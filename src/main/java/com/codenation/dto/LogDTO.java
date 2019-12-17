package com.codenation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogDTO {

    private String title;

    private String level;

    private String detail;

    private String origin;

    private String environment;

  public boolean isValid(){
    return !StringUtils.isEmpty(this.detail)
            && !StringUtils.isEmpty(this.title)
            && !StringUtils.isEmpty(this.level)
            && !StringUtils.isEmpty(this.environment);
  }

}
