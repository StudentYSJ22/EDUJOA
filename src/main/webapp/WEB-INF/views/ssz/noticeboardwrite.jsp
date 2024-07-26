<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="loginMember"
	value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}" />
<c:set var="path" value="${pageContext.request.contextPath}" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<title>공지사항 작성</title>

<style>
:root {
	--primary-color: #4CAF50;
	--secondary-color: #45a049;
	--background-color: #f1f8e9;
	--text-color: #333;
}

body {
	font-family: 'Noto Sans KR', sans-serif;
	background-color: var(--background-color);
	color: var(--text-color);
	line-height: 1.6;
	margin: 0;
	padding: 20px;
}

.container {
	max-width: 800px;
	margin: 30px auto;
	background-color: white;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	padding: 20px;
}

h3 {
	color: var(--primary-color);
	text-align: center;
	margin-bottom: 30px;
}

.form-group {
	margin-bottom: 20px;
}

label {
	display: block;
	margin-bottom: 5px;
	font-weight: bold;
}

input[type="text"], textarea {
	width: 100%;
	padding: 10px;
	border: 1px solid #ddd;
	border-radius: 4px;
	font-size: 16px;
}

textarea {
	height: 200px;
	resize: vertical;
}

.btn {
	background-color: var(--primary-color);
	color: white;
	padding: 10px 15px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.btn:hover {
	background-color: var(--secondary-color);
}

.btn-group {
	margin-left:580px;
	justify-content: flex-end;
	margin-top: 20px;
}

.btn {
	margin-left: 10px;
	margin-right: 10px;
}

.btn-cancel {
	background-color: #f44336;
}

.btn-cancel:hover {
	background-color: #d32f2f;
}
</style>

<div class="container">
	<h3>공지사항 작성</h3>
	<form id="noticeForm" action="${path}/noticeboard/writeNotice" method="post">
		<div class="form-group">
			<label for="title">게시글 제목</label> 
			<input type="text" id="title" name="boardTitle" placeholder="제목을 입력하세요" required>
			<input type="text" id="username" name="empId" value="${loginMember.empId }" style="display: none;">
			<input type="date" id="date" name="boardDate" value="" style="display: none;">
			<input type="number" id="count" name="boardCount" value="0" style="display: none;">
		</div>
		<div class="form-group">
			<label for="content">게시글 내용</label>
			<textarea id="content" name="boardContent" placeholder="내용을 입력하세요" required></textarea>
		</div>
		<div class="btn-group">
			<button type="submit" class="btn">작성완료</button>
			<button type="button" class="btn btn-cancel" onclick="history.back()">취소</button>
		</div>
	</form>
	<input type="hidden" id="contextPath" value="${path}">
</div>

<script>
	const loginId=$("#username").val();
	const today = new Date().toISOString().split('T')[0];
    document.getElementById("date").value = today;
    var contextPath = document.getElementById('contextPath').value;
</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />