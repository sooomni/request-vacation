package com.example.requestvacation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.requestvacation.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity 
@Configuration 
public class SecurityConfig extends WebSecurityConfigurerAdapter{

   @Autowired
   private UserService userService;
   
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
               .antMatchers("/","/users/**","/v3/api-docs/**","/swagger*/**").permitAll()
            .and()
            	.csrf().disable()
                .cors().disable()
            ;                  
    }
    //권한 인증 받기    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth
          .userDetailsService(userService)
          .passwordEncoder(passwordEncoder());
    }
    //패스워드 암호화
    @Bean
   public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
    }
  }
    