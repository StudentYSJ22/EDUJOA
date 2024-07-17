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
	<p class="tag-bottom" onclick="insertTutor('${loginMember.empTitle}')">강사 등록</p>
	<p class="tag-bottom" onclick="location.assign(`${path}/tutor/selectall?ttYn=0&empId=${loginMember.empId }&`);">재직자</p>
	<p class="tag-bottom" onclick="location.assign(`${path}/tutor/selectall?ttYn=1`);">퇴직자</p>
	<p class="tag-top">학생</p>
	<p class="tag-bottom" onclick="insertStudent('${loginMember.empTitle}')">학생 등록</p>
	<p class="tag-bottom" onclick="location.assign(`${path}/tutor/selectallstudent`);">학생 조회</p>
	<p class="tag-top">강의</p>
	<p class="tag-bottom" onclick="insertClass('${loginMember.empTitle}')">강의 등록</p>
	<p class="tag-bottom">강의 조회</p>
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
    const insertTutor = function(empTitle){
    	if(empTitle =="J1" || empTitle == "J3"){
    		location.assign(path+"/tutor/insert");
    	}else{
    		alert("권한이 부족합니다.");
    	}
    }
    const insertClass = function(empTitle){
    	if(empTitle =="J1" || empTitle == "J3"){
    		location.assign(path+"/tutor/insertclass");
    	}else{
    		alert("권한이 부족합니다.");
    	}
    }
    const insertStudent = function(empTitle){
    	if(empTitle =="J1" || empTitle == "J3"){
    		location.assign(`${path}/tutor/insertstudent`);
    	}else{
    		alert("권한이 부족합니다.");
    	}
    }
</script>
