<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="loginMember" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}" />

<jsp:include page="/WEB-INF/views/common/header.jsp" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ysj/salary.css">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" />

<div class="container mt-4">
  <!-- 사용자 프로필 정보 -->
  <div class="card mb-4">
    <div class="card-header">
      <h3>프로필 정보</h3>
    </div>
    <div class="card-body">
      <div class="row">
        <div class="col-md-3 text-center">
          <img src="${pageContext.request.contextPath}/resources/upload/${employee.empProfile}" alt="Profile Picture" class="img-thumbnail">
        </div>
        <div class="col-md-9">
          <table class="table table-borderless">
            <tbody>
              <tr>
                <th>이름</th>
                <td>${employee.empName}</td>
                <th>직책</th>
                <td>${employee.empTitle}</td>
              </tr>
              <tr>
                <th>이메일</th>
                <td>${employee.empEmail}</td>
                <th>주소</th>
                <td>${employee.empAddress}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>

  <!-- 급여 상세 정보 -->
  <div class="card mb-4">
    <div class="card-header">
      <h3>급여 상세</h3>
    </div>
    <div class="card-body">
      <table class="table table-bordered">
        <tbody>
          <tr>
            <th>급여유형</th>
            <td>연봉</td>
            <th>연봉</th>
            <td><fmt:formatNumber value="${employee.empAnnualSal}" type="currency" currencySymbol="₩" /></td>
          </tr>
          <tr>
            <th>월급</th>
            <td colspan="3"><fmt:formatNumber value="${employee.empSalary}" type="currency" currencySymbol="₩" /></td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>

  <!-- 급여 명세서 -->
  <div class="card mb-4">
    <div class="card-header">
      <h3>급여 명세서</h3>
      <button id="printSalaryDetails" class="btn btn-primary float-right">인쇄</button>
    </div>
    <div class="card-body" id="salaryDetailsSection">
      <table class="table table-hover">
        <thead>
          <tr>
            <th>급여월</th>
            <th>급여일</th>
            <th>지급합계</th>
            <th>공제합계</th>
            <th>공제 후 지급액</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="detail" items="${salaryDetails}">
            <tr>
              <td>${detail.month}</td>
              <td>20일</td>
              <td><fmt:formatNumber value="${detail.salary}" type="currency" currencySymbol="₩" /></td>
              <td><fmt:formatNumber value="${detail.deduction}" type="currency" currencySymbol="₩" /></td>
              <td><fmt:formatNumber value="${detail.netSalary}" type="currency" currencySymbol="₩" /></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>

<script>
// 인쇄하기 기능 
$(document).ready(function() {
    $('#printSalaryDetails').click(function() {
        var printContents = document.getElementById('salaryDetailsSection').innerHTML;
        var originalContents = document.body.innerHTML;

        document.body.innerHTML = '<html><head><title>급여 명세서</title></head><body>' + printContents + '</body>';

        window.print();

        document.body.innerHTML = originalContents;
        location.reload();
    });
});
</script>
