package com.bottlehs.springboot.backend.springboot_backend.api.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private Logger log = LoggerFactory.getLogger(this.getClass());   

    private final JwtTokenProvider jwtTokenProvider;
    
    public WebSecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/api/user/log").hasRole("USER")
                .antMatchers("/api/user/likehumananswer").hasRole("USER")
                .antMatchers("/api/user/interest").hasRole("USER")
                .antMatchers("/api/user/createcard").hasRole("USER")
                .antMatchers("/api/user/bookmark").hasRole("USER")
                .antMatchers("/api/qna").hasRole("USER")
                .antMatchers("/api/faq").hasRole("USER")
                .antMatchers("/api/me").hasRole("USER")
                .antMatchers("/api/password").hasRole("USER")
                .antMatchers("/api/user").hasRole("USER")                                                                                                                                
                .anyRequest()
                .permitAll()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }
}