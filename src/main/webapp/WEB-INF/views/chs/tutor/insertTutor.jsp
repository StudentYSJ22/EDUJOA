<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<link rel="stylesheet" href="${path }/resources/css/chs/personnel/insert_emp_personnel.css">
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="chs-custom">
		<jsp:include page="/WEB-INF/views/chs/personnel/left_personnel.jsp"/>
	    <div class="container" style="width: 50%; height:95%;">
	        <h2 class="title">강사 등록</h2>
	        <form method="post" action="${path }/tutor/inserttutor" id="personnel-card-form" class="form-container" enctype="multipart/form-data">
	            <div class="form-group">
	                <label for="ttName">이름</label>
	                <input type="text" id="ttName" name="ttName" required>
	            </div>
	            <div class="form-group">
	                <label for="ttProfile">사진</label>
	                <input type="file" id="ttProfile" name="ttProfile" accept=".jpg, .jpeg, .png">
	            </div>
	            <div class="form-group">
	                <label for="ttDate">입사일자</label>
	                <input type="date" id="ttHireDate" name="ttHireDate">
	            </div>
	            <div class="form-group">
	                <label for="ttEmail">이메일 주소</label>
	                <input type="email" id="ttEmail" name="ttEmail">
	            </div>
	            <div class="form-group">
	                <label for="ttPhone">전화번호</label>
	                <input type="text" id="ttPhone" name="ttPhone">
	            </div>
	            <div class="form-group">
	                <label for="subId">과목</label>
	                <select id="subId" name="subId">
	                	<c:forEach var="s" items="${subjects }">
	                		<option value="${s.subId }">${s.subName }</option>
	                	</c:forEach>
	                </select>
	            </div>
	            <div class="form-group">
	                <label for="empId">담당 매니저</label>
	                <select id="empId" name="empId">
	                ${employees }
	                	<c:forEach var="e" items="${employees }">
	                		<option value="${e.empId }">${e.empName }</option>
	                	</c:forEach>
	                </select>
	            </div>
	            <div class="btn-container">
	            	<input type="submit" value="등록하기">
	            	<button>취소하기</button>
	            </div>
	        </form>
	    </div>
	 </div>
	 <div class='msg'>${msg }</div>
    <script>
    window.onload = function() {
    	if($('.msg').text() != ''){
    		alert($('.msg').text());
    	}
    }
        function resetForm() {
            document.getElementById('personnel-card-form').reset();
        }
    </script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>