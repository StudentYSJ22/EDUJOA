<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<link rel="stylesheet" href="${path }/resources/css/chs/personnel/insert_emp_personnel.css">
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="chs-custom">
		<jsp:include page="/WEB-INF/views/chs/personnel/left_personnel.jsp"/>
	    <div class="container" style="width: 50%; height:95%;">
	        <h2 class="title">학생 등록</h2>
	        <form method="post" action="${path }/tutor/insertstudentend" id="personnel-card-form" class="form-container">
	            <div class="form-group">
	                <label for="stdName">이름</label>
	                <input type="text" id="stdName" name="stdName" required>
	            </div>
	            <div class="form-group">
	                <label for="stdSchool">학교</label>
	                <input type="text" id="stdSchool" name="stdSchool">
	            </div>
	            <div class="form-group">
	                <label for="stdPhone">전화번호</label>
	                <input type="text" id="stdPhone" name="stdPhone">
	            </div>
	            <div class="form-group">
	                <label for="stdParentPhone">보호자 전화번호</label>
	                <input type="text" id="stdParentPhone" name="stdParentPhone">
	            </div>
	            <div class="form-group">
	                <label for="stdPayment">결재 금액</label>
	                <input type="number" id="stdPayment" name="stdPayment">
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