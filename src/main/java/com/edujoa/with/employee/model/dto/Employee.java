package com.edujoa.with.employee.model.dto;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.edujoa.khj.main.controller.MyAuthority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//UserDetails 인터페이스 구현하여 사용자 세부 정보를 제공
//Enum 사용의 장점
//코드 가독성 증가, 오류 감소, 유지보수서 향상
public class Employee implements UserDetails{	
	//사용자 권한을 반환하는 메소드
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//GrantedAuthority 객체를 저장할 컬렉션을 생성
		Set<GrantedAuthority> auth = new HashSet<>();
		// 사용자 ID가 "admin"이라면 ADMIN 권한을 추가함.
		if(empTitle.equals("J1")) {
			auth.add(new SimpleGrantedAuthority(MyAuthority.ADMIN.name()));
		}else if(empTitle.equals("J2")) {
			auth.add(new SimpleGrantedAuthority(MyAuthority.TEAMLEADER.name()));
		}else if(empTitle.equals("J3")){
			auth.add(new SimpleGrantedAuthority(MyAuthority.MANAGER.name()));
		}
		return auth;
	}
	//사용자 이름을 반환하는 메소드
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.empId;
	}
	// 계정이 만료되지 않았는지 여부를 반환하는 메소드입니다.
	@Override
	public boolean isAccountNonExpired() {
		// 계정이 만료되지 않았다고 가정하고 true를 반환합니다.
		return true;
	}

	// 계정이 잠기지 않았는지 여부를 반환하는 메소드입니다.
	@Override
	public boolean isAccountNonLocked() {
		// 계정이 잠기지 않았다고 가정하고 true를 반환합니다.
		return true;
	}

	// 자격 증명이 만료되지 않았는지 여부를 반환하는 메소드입니다.
	@Override
	public boolean isCredentialsNonExpired() {
		// 자격 증명이 만료되지 않았다고 가정하고 true를 반환합니다.
		return true;
	}

	// 계정이 활성화되어 있는지 여부를 반환하는 메소드입니다.
	@Override
	public boolean isEnabled() {
		// 계정이 비활성화 상태라고 가정하고 false를 반환합니다.
		return false;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
	private String empId;
	private String empName;
	private String empPassword;
	private String empEmail;
	private String empTitle;
	private String empProfile;
	private Date empHireDate;
	private String empYn;
	private String empAddress;
	private int empRvacation;
	private int empTvacation;
	private int empSalary;
	private int empAnnualSal;
	private int status;
	private String empBank;
	private String empAccount;
	private String oriname;
	private Date empRetireDate;
	private List<Alarm> alarm;
	
}
