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
            .antMatchers(HttpMethod.OPTIONS).permitAll()
            .antMatchers(HttpMethod.POST,"/user/**").hasAnyAuthority("CREATE_USER")
            .antMatchers(HttpMethod.PATCH,"/user/**").hasAnyAuthority("ALTER_USER")
            .antMatchers(HttpMethod.POST,"/log/**").hasAnyAuthority("WRITE")
            .antMatchers(HttpMethod.GET,"/log/**").hasAnyAuthority("READ")
            .antMatchers(HttpMethod.PATCH,"/log/**").hasAnyAuthority("STORE")
            .antMatchers("/log/dev/**").hasAnyAuthority("DEVELOP")
            .antMatchers("/log/stage/**").hasAnyAuthority("STAGE")
            .antMatchers("/log/production/**").hasAnyAuthority("PRODUCTION")
            .anyRequest().authenticated();
    http.cors().and().csrf().disable();
    http.headers().frameOptions().disable();
  }
}