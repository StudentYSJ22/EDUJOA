<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <!-- 진진  -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="loginMember" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}" />

<jsp:include page="/WEB-INF/views/common/header.jsp" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ysj/attendance.css">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" />

<div class="container mt-5">
  <div class="summary-container">
    <h2>휴가 Summary</h2>
    <div class="summary-grid">
      <div class="summary-item active" onclick=>
        <div class="icon">&#x2708;</div>
        <div class="text">전체 휴가 <br><span id="">0건</span></div>
      </div>
      <div class="summary-item" onclick=>
        <div class="icon purple">&#x2708;</div>
        <div class="text">사용 휴가<br><span id="">0건</span></div>
      </div>
      <div class="summary-item" onclick=>
        <div class="icon grey">&#x2708;</div>
        <div class="text">잔여 휴가<br><span id="">0건</span></div>
      </div>
  
    </div>
  </div>
</div>



<div class="container mt-4">
  <div class="summary-container">
    <h2>출퇴근 Summary</h2>
    <div class="summary-grid">
      <div class="summary-item active" onclick="filterAttendance('')">
        <div class="icon">&#128188;</div>
        <div class="text">전체<br><span id="totalCount">0건</span></div>
      </div>
      <div class="summary-item" onclick="filterAttendance('출근')">
        <div class="icon purple">&#128188;</div>
        <div class="text">출근<br><span id="onTimeCount">0건</span></div>
      </div>
      <div class="summary-item" onclick="filterAttendance('지각')">
        <div class="icon grey">&#128188;</div>
        <div class="text">지각<br><span id="lateCount">0건</span></div>
      </div>
      <div class="summary-item" onclick="filterAttendance('결근')">
        <div class="icon blue">&#128188;</div>
        <div class="text">결근<br><span id="absentCount">0건</span></div>
      </div>
      <div class="summary-item" onclick="filterAttendance('미처리')">
        <div class="icon blue">&#128188;</div>
        <div class="text">미처리<br><span id="unprocessedCount">0건</span></div>
      </div>
    </div>
  </div>

  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <h2>근태 기록</h2>
        <div class="card">
          <div class="table-responsive text-nowrap">
            <table class="table table-hover">
              <thead>
                <tr>
                  <th>일자</th>
                  <th>출근시간</th>
                  <th>퇴근시간</th>
                  <th>상태</th>
                </tr>
              </thead>
              <tbody id="attendance-list">
                <c:forEach var="record" items="${records}">
                  <tr>
                    <td>${record.atnIn}</td>
                    <td>${record.atnOut}</td>
                    <td>${record.atnStatus}</td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
          <div id="page-bar" style="height:60px;">
            <c:out value="${pagebar}" escapeXml="false"/>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script>
    var path = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/resources/js/ysj/attendance.js"></script>
