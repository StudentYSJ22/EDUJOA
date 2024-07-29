<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<link rel="stylesheet" href="${path }/resources/css/chs/personnel/insert_emp_personnel.css">
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="chs-custom">
		<jsp:include page="/WEB-INF/views/chs/personnel/left_personnel.jsp"/>
	    <div class="container" style="width: 50%; height:95%;">
	        <h2 class="title">직원 수정</h2>
	        <form method="post" action="${path }/employee/updateemployeeend" id="personnel-card-form" class="form-container" enctype="multipart/form-data">
	        	<input type="hidden" name="empId" value="${employee.empId }">
	            <div class="form-group">
	                <label for="empName">이름</label>
	                <input type="text" id="empName" value="${employee.empName }" name="empName" required>
	            </div>
	            <div class="form-group">
	                <label for="empProfile">사진</label>
	                <input type="file" id="empProfile"  name="empProfile" accept=".jpg, .jpeg, .png">
	                <img src="${path }/resources/upload/chs/employee/${employee.empProfile}" 
	                		style='position:relative; right:120px; bottom:65px' alt="현재 프로필 사진" width="100" height="100">
	            </div>
	            <div class="form-group">
	                <label for="empTitle">직책</label>
	                <select id="empTitle" name="empTitle">
	                    <option value="J2" ${employee.empTitle == 'J2' ? "selected" : "" }>팀장</option>
	                    <option value="J3" ${employee.empTitle == 'J3' ? "selected" : "" }>매니저</option>
	                </select>
	            </div>
	            <div class="form-group">
	                <label for="empDate">입사일자</label>
	                <input type="date" value="${employee.empHireDate }" id="empHireDate" name="empHireDate" required>
	            </div>
	            <div class="form-group">
	                <label for="empEmail">이메일 주소</label>
	                <input type="email" value="${employee.empEmail }" id="empEmail" name="empEmail" required>
	            </div>
	            <div class="form-group">
	                <label for="empAddress">주소</label>
	                <input type="text" value="${employee.empAddress }" id="empAddress" name="empAddress" required>
	            </div>
	            <div class="btn-container">
	            	<input type="submit" value="수정하기">
	            	<button>취소하기</button>
	            </div>
	        </form>
	    </div>
	 </div>
	 <div class='msg' style="display:none">${msg }</div>
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