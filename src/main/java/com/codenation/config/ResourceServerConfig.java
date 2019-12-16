package com.codenation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.antMatcher("/**")
            .authorizeRequests()
            .antMatchers("/h2-console/**").permitAll()
            .antMatchers("/swagger-ui.html").permitAll()
            .antMatchers("/oauth/token").permitAll()
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .antMatchers(HttpMethod.POST,"/api/v1/user/**").hasAnyAuthority("CREATE_USER")
            .antMatchers(HttpMethod.PATCH,"/api/v1/user/**").hasAnyAuthority("ALTER_USER")
            .antMatchers(HttpMethod.POST,"/api/v1/log/**").hasAnyAuthority("WRITE")
            .antMatchers(HttpMethod.GET,"/api/v1/log/**").hasAnyAuthority("READ")
            .antMatchers(HttpMethod.PATCH,"/api/v1/log/**").hasAnyAuthority("STORE")
            .antMatchers("/api/v1/log/dev/**").hasAnyAuthority("DEVELOP")
            .antMatchers("/api/v1/log/stage/**").hasAnyAuthority("STAGE")
            .antMatchers("/api/v1/log/production/**").hasAnyAuthority("PRODUCTION")
            .anyRequest().authenticated();
    //http.cors().and().csrf().disable();
    //    http.headers().frameOptions().disable();
    http.csrf().disable().headers().frameOptions().sameOrigin().and().anonymous().and().httpBasic().disable();
  }
}
