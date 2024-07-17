<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<link rel="stylesheet" href="${path }/resources/css/chs/personnel/insert_emp_personnel.css">
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="chs-custom">
		<jsp:include page="/WEB-INF/views/chs/personnel/left_personnel.jsp"/>
	    <div class="container" style="width: 50%; height:95%;">
	        <h2 class="title">학생 수정</h2>
	        <form method="post" action="${path }/tutor/updatestudentend" id="personnel-card-form" class="form-container">
	        	<input type="hidden" name="stdId" value="${student.stdId }">
	            <div class="form-group">
	                <label for="stdName">이름</label>
	                <input type="text" id="stdName" name="stdName" value="${student.stdName }" required>
	            </div>
	            <div class="form-group">
	                <label for="stdSchool">학교</label>
	                <input type="text" id="stdSchool" name="stdSchool" value="${student.stdSchool }">
	            </div>
	            <div class="form-group">
	                <label for="stdPhone">전화번호</label>
	                <input type="text" id="stdPhone" name="stdPhone" value="${student.stdPhone }">
	            </div>
	            <div class="form-group">
	                <label for="stdParentPhone">보호자 전화번호</label>
	                <input type="text" id="stdParentPhone" name="stdParentPhone" value="${student.stdParentPhone }">
	            </div>
	            <div class="form-group">
	                <label for="stdPayment">결재 금액</label>
	                <input type="number" id="stdPayment" name="stdPayment" value="${student.stdPayment }">
	            </div>
	            <div class="form-group">
	                <label for="myClass">수강할 반</label>
	                <select id="myClass" name="myClass" >
	                	<option>선택안함</option>
	                	<c:forEach var="c" items="${classRoom}">
					        <option value="${c.classId}" data-open="${c.classOpen}" data-close="${c.classClose}">
					            ${c.classId} / open : ${c.classOpen} / close : ${c.classClose}
					        </option>
					    </c:forEach>
	                </select>
	            </div>
	            <div class="btn-container">
	            	<input type="submit" value="수정하기">
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