package com.web.blog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity  //Spring Security 설정 활성화
@RequiredArgsConstructor
public class SpringSecurityConfig {
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // base 64 인코딩용
    }
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring()
                    .antMatchers("/favicon.ico"); // 파비콘은 검사 막기
    }
	
	@Bean
	public SecurityFilterChain securityFileterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.httpBasic().disable()
				.csrf().disable()
				.cors().and()
				.headers().frameOptions().disable().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() // 로그인 후 토큰 미발급시에도 접근 허용 막기
				.authorizeRequests()
				.antMatchers("/vi/member/login").permitAll() // permitAll : 무조건 접근을 허용
				.antMatchers("/vi/**").authenticated() // authenticated : 인증된 사용자의 접근을 허용
				.anyRequest().authenticated().and() // 파라미터
				.build();
	}
	
	
}
