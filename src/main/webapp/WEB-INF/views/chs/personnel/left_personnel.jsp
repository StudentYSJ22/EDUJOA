<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var ="loginMember" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}"/>
<link rel="stylesheet" href="${path }/resources/css/chs/personnel/left_personnel.css">
<div class="left-container">
	<p class="tag-top">직원</p>
	<p class="tag-bottom" onclick="insertEmployee('${loginMember.empTitle}')">직원 등록</p>
	<p class="tag-bottom" onclick="location.assign(`${path}/employee/selectall?empYn=0`);">재직자</p>
	<p class="tag-bottom" onclick="location.assign(`${path}/employee/selectall?empYn=1`);">퇴직자</p>
	<p class="tag-top">강사</p>
	<p class="tag-bottom">강사 등록</p>
	<p class="tag-bottom">재직자</p>
	<p class="tag-bottom">퇴직자</p>
	<p class="tag-top">학생</p>
	<p class="tag-bottom">학생 등록</p>
	<p class="tag-bottom">학생 조회</p>
	<p class="tag-top">강의</p>
	<p class="tag-bottom">강의 조회</p>
	<p class="tag-bottom">강의 등록</p>
</div>
<script>
    // JSTL 변수를 JpvpScript 변수에 할당
    const path = '${path}';
    const empId = '${loginMember.empId}';
    
    const insertEmployee = function(empTitle){
    	if(empTitle =="J1"){
    		location.assign(path+"/employee/insert");
    	}else{
    		alert("권한이 부족합니다.");
    	}
    }
    
</script>
