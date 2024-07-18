<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<link rel="stylesheet" href="${path}/resources/css/ysj/salary.css">

<div class="container-xxl flex-grow-1 container-p-y">
	   <div class="container">
	  <div class="header">
	    <h2>급여조회</h2>
	    <!-- 사용자 프로필 정보 -->
	    <table>
	      <tr>
	        <td rowspan="5"><img src="${path}/resources/upload/${employee.empProfile}" alt="Profile Picture"></td>
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
	    </table>
	  </div>
	
	  <div class="salary-details">
	    <h3>급여상세</h3>
	    <table>
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
	    </table>
	  </div>
	</div>
        
        <div class="salary-slip">
            <h3>급여명세서</h3>
            <table>
                <tr>
                    <th>급(상)여월</th>
                    <th>급(상)여지급일</th>
                    <th>지급합계</th>
                    <th>공제합계</th>
                    <th>공제 후 지급액</th>
                </tr>
              
            </table>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
