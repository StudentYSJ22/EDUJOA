<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="loginMember" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}" />

<jsp:include page="/WEB-INF/views/common/header.jsp" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ysj/attendance.css">

<!-- 근태 메뉴 박스  -->
<div class="summary-container">
    <h2>출퇴근 Summary</h2>
    <div class="summary-grid">
        <div class="summary-item active">
            <div class="icon">&#128188;</div>
            <div class="text">전체</div>
        </div>
        <div class="summary-item">
            <div class="icon purple">&#128188;</div>
            <div class="text">출근<br><span>17건</span></div>
        </div>
        <div class="summary-item">
            <div class="icon grey">&#128188;</div>
            <div class="text">지각<br><span>6건</span></div>
        </div>
        <div class="summary-item">
            <div class="icon blue">&#128188;</div>
            <div class="text">결근<br><span>20건</span></div>
        </div>
        <div class="summary-item">
            <div class="icon blue">&#128188;</div>
            <div class="text">미처리<br><span>2</span></div>
        </div>
       
    </div>
</div>

<!-- 근태기록  -->
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h2>근태 기록</h2>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>일자</th>
                        <th>출근시간</th>
                        <th>퇴근시간</th>
                        <th>상태</th>
                    </tr>
                </thead>
                <tbody id="attendance-list">
                </tbody>
            </table>
            <div id="page-bar" style="height:60px;">
                ${pagebar}
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    var path = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/resources/js/ysj/attendance.js"></script>
