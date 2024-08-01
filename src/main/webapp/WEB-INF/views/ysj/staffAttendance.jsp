<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/common/header.jsp" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ysj/attendance.css">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" />

<div class="container mt-5">
    <h2> 직원 근태 현황</h2>
    <div class="summary-container">
        <h2>오늘의 직원 출퇴근 Summary</h2>
        <div class="summary-grid">
            <div class="summary-item active">
                <div class="icon">&#128188;</div>
                <div class="text">전체<br><span id="totalCount">0명</span></div>
            </div>
            <div class="summary-item">
                <div class="icon purple">&#128188;</div>
                <div class="text">출근<br><span id="onTimeCount">0명</span></div>
            </div>
            <div class="summary-item">
                <div class="icon grey">&#128188;</div>
                <div class="text">지각<br><span id="lateCount">0명</span></div>
            </div>
            <div class="summary-item">
                <div class="icon blue">&#128188;</div>
                <div class="text">결근<br><span id="absentCount">0명</span></div>
            </div>
        </div>
    </div>
  
    <table id="attendanceTable" class="table table-striped mt-3">
        <thead>
            <tr>
                <th>사원 ID</th>
                <th>사원 이름</th>
                <th>날짜</th>
                <th>출근 시간</th>
                <th>퇴근 시간</th>
                <th>상태</th>
            </tr>
        </thead>
        <tbody>
            <!-- 데이터가 동적으로 추가될 자리 -->
        </tbody>
    </table>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script>
    var path = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/resources/js/ysj/staffAttendance.js"></script>
