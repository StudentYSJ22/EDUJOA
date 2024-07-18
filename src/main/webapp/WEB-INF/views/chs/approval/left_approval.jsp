 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var ="loginMember" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}"/>
<link rel="stylesheet" href="${path }/resources/css/chs/approval/left_approval.css">
		<div class="left-container">
			<div class='btn-container'>
				<button onclick="location.assign('${path }/approval/insert');">기안서 작성</button>
			</div>
			<div class="request-container">
				<div>
					<p>결재 대기</p>
					<span>0</span>
				</div>
				<div>
					<p>결재 요청</p>
					<span>0</span>
				</div>
			</div>
			<div class="flagging-container">
				<p>기안함</p>
				<a href="${path }/approval/flagginging.do?empId=${loginMember.empId}">진행중</a>
				<a href="${path }/approval/flaggingback.do?empId=${loginMember.empId}">반려</a>
				<a href="${path }/approval/flaggingapproval.do?empId=${loginMember.empId}">승인</a>
				<a href="${path }/approval/temporarystorage.do?empId=${loginMember.empId}">임시 저장</a>
			</div>
			<div class="approval-container">
				<p>결재함</p>
				<a href="#">미결</a>
				<a href="#">종결</a>
				<a href="#">열람/공람</a>
			</div>
		</div>
<script>
    // JSTL 변수를 JavaScript 변수에 할당
    const path = '${path}';
    const empId = '${loginMember.empId}';
</script>
		