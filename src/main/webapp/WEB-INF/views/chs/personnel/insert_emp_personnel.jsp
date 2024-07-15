<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<link rel="stylesheet" href="${path }/resources/css/chs/personnel/insert_emp_personnel.css">
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="chs-custom">
		<jsp:include page="/WEB-INF/views/chs/personnel/left_personnel.jsp"/>
	    <div class="container" style="width: 50%; height:95%;">
	        <h2 class="title">인사 카드 등록</h2>
	        <form method="post" action="${path }/employee/insertemployee" id="personnel-card-form" class="form-container" enctype="multipart/form-data">
	            <div class="form-group">
	                <label for="empName">이름</label>
	                <input type="text" id="empName" name="empName" required>
	            </div>
	            <div class="form-group">
	                <label for="empProfile">사진</label>
	                <input type="file" id="empProfile" name="empProfile" accept=".jpg, .jpeg, .png">
	            </div>
	            <div class="form-group">
	                <label for="empTitle">직책</label>
	                <select id="empTitle" name="empTitle">
	                    <option value="J2">팀장</option>
	                    <option value="J3">매니저</option>
	                </select>
	            </div>
	            <div class="form-group">
	                <label for="empDate">입사일자</label>
	                <input type="date" id="empHireDate" name="empHireDate">
	            </div>
	            <div class="form-group">
	                <label for="empEmail">이메일 주소</label>
	                <input type="email" id="empEmail" name="empEmail">
	            </div>
	            <div class="form-group">
	                <label for="empAddress">주소</label>
	                <input type="text" id="empAddress" name="empAddress">
	            </div>
	            <div class="btn-container">
	            	<input type="submit" value="등록하기">
	            	<button>취소하기</button>
	            </div>
	        </form>
	    </div>
	 </div>
    <script>
        function resetForm() {
            document.getElementById('personnel-card-form').reset();
        }
    </script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>