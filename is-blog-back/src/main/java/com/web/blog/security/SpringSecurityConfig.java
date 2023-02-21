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

import com.web.blog.security.common.JwtAccessDeniedHandler;
import com.web.blog.security.common.JwtAuthenticationEntryPoint;
import com.web.blog.security.common.JwtSecurityConfig;
import com.web.blog.security.common.TokenProvider;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity  //Spring Security 설정 활성화
@RequiredArgsConstructor
public class SpringSecurityConfig {
	
	private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAtuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	
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
				/**401, 403 Exception 핸들링 */
                .exceptionHandling()
                .authenticationEntryPoint(jwtAtuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                /**세션 사용하지 않음*/
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                /** HttpServletRequest를 사용하는 요청들에 대한 접근 제한 설정*/
                .and()
                .authorizeRequests()
                .antMatchers("/authenticate","/isblog/back/test").permitAll()
                /**JwtSecurityConfig 적용 */
                .and()
                .apply(new JwtSecurityConfig(tokenProvider))
                .and().build();
	}
	
	
}
