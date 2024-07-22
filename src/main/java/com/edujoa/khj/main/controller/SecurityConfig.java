package com.edujoa.khj.main.controller;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.cors.CorsUtils;

import com.edujoa.with.employee.model.dto.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

// HttpSecurity는 XML 설정 대신 Java 코드로 보안 설정을 가능하게 함
@Configuration
//Lombok에서 제공하는 것. // 필드에 final을 선언하면 의존성 주입을 알아서 해줌.
@RequiredArgsConstructor
//스프링 시큐리티를 활성화함
@EnableWebSecurity
public class SecurityConfig {
	// 커스텀 인증 공급자를 주입받음
	private final UserPasswordAuthenticationProvider provider;
	
	// SecurityFilterChain 타입의 Bean을 반환하는 메서드
	@Bean
	SecurityFilterChain authenticationFilter(HttpSecurity http) throws Exception{
		// HttpSecurity 객체를 사용하여 보안 설정 시작
		return http
				// CSRF 보호 비활성화
				.csrf(csrf -> csrf.disable())
				// HTTP 요청에 대한 권한 설정
				.authorizeHttpRequests(request ->
				// requestMatchers는 경로에 따라 권한을 설정할 수 있음
				request
				// 다른 도메인에서 접근할 때 Security가 PreFlightRequest를 허용하도록 설정
				.requestMatchers(req -> CorsUtils.isPreFlightRequest(req)).permitAll()
				// 로그인 페이지에 대한 접근을 허용
				.requestMatchers("/login.do","/member/loginpage").permitAll()
				// 루트와 특정 경로에 대한 접근을 허용
				.requestMatchers("/resources/**","/WEB-INF/**").permitAll()
				.requestMatchers("/employee/**","/approval/**","/chatting/**","/noticeboard/**","/webmail/**","/schedule/**").hasAnyAuthority(MyAuthority.ADMIN.name(), MyAuthority.TEAMLEADER.name(), MyAuthority.MANAGER.name())
				// 위에서 설정한 경로 이외의 모든 요청은 인증을 요구
				.anyRequest().authenticated())
				// 커스텀 인증 공급자를 설정
				.authenticationProvider(provider) // 커스텀 인증 공급자를 설정
				// 로그인에 대한 설정
				.formLogin(form -> form
						// 로그인 인증 처리를 위한 URL
						.loginProcessingUrl("/member/loginpage")
						// 사용자 정의 로그인 페이지 설정
						.loginPage("/login.do").permitAll()
						.defaultSuccessUrl("/home")
						)
				.build(); // 설정을 바탕으로 SecurityFilterChain을 빌드하여 반환
	}	
	
	
	//핸들러를 적용해서 세션에 로그인한 사원 담기
	@Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                HttpSession session = request.getSession();
                Employee employee = (Employee) authentication.getPrincipal();
                session.setAttribute("loginMember", employee);
                super.handle(request, response, authentication);
            }
        };
    }
	//Jsp에서 // 문자가 있으면 막힘.
	@Bean
	public HttpFirewall looseHttpFirewall() {
		StrictHttpFirewall firewall = new StrictHttpFirewall();
		firewall.setAllowSemicolon(true);
		firewall.setAllowUrlEncodedDoubleSlash(true); // `//` 문자를 허용하도록 설정
		return firewall;
	}
}	
