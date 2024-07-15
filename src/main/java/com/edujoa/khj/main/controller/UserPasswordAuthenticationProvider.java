package com.edujoa.khj.main.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.edujoa.chs.employee.model.dao.ChsEmployeeDao;
import com.edujoa.with.employee.model.dto.Employee;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
//AuthencicationProvider 인터페이스를 구현하면 사용자 인증 로직을 정의할 수 있음.
public class UserPasswordAuthenticationProvider implements AuthenticationProvider{
	//데이터 조회를 위한 매퍼 객체
	private final ChsEmployeeDao dao;
	private final SqlSession session;
	//빈으로 등록해서 하는 걸로 바꿔봐야함
	//비밀번호 암호화를 위한 객체, 입력한 Password와 데이터베이스에 저장된 암호화된 비밀번호를 비교
	private final BCryptPasswordEncoder pwencoder;
	//인증
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		//사용자가 입력한 ID
		String empId = authentication.getName();
		//사용자가 입력한 Password
		String password = (String)authentication.getCredentials();
		log.debug(password);
		Employee loginMember = dao.selectOneEmployee(session, empId);
		log.debug("{}",loginMember);
		//matches 첫 번째 매개변수는 원본값, 두번 째는 암호화된 값
		if(loginMember != null && pwencoder.matches(password, loginMember.getEmpPassword())){
		//만약 인증이 성공되면 UsernamePasswordAuthenticationToken 객체를 생성하여 
		// 사용자 정보를포함한 인증 토큰을 반환함.
			return new UsernamePasswordAuthenticationToken(loginMember, loginMember.getEmpPassword()
								,loginMember.getAuthorities());
		}else {
			throw new BadCredentialsException("인증 실패");
		}
		
	}
	//AuthenticationProvider가 특정 인증 클래스를 지원하는지 여부를 나타내느 메소드
	@Override
	public boolean supports(Class<?> authentication) {
		//autheticate 메소드에서 사용됨
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
	
}