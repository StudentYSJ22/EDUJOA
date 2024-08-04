<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="loginMember"
	value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}" />
	<c:set var="path" value="${pageContext.request.contextPath }" />
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<title>게시판 상세보기</title>

<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
	margin: 0;
	padding: 20px;
}

.post-container {
	background-color: white;
	border-radius: 8px;
	padding: 20px;
	margin: 0 auto;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	margin-top: 30px;
	height: 800px;
}

.post-header {
	border-bottom: 1px solid #e0e0e0;
	padding-bottom: 10px;
	margin-bottom: 20px;
}

.post-title {
	margin: 0 0 10px 0;
	font-size: 18px;
	color: #333;
}

.post-info {
	font-size: 12px;
	color: #666;
}

.post-info span {
	margin-right: 10px;
}

.post-content {
	min-height: 200px;
}

.post-footer {
	margin-top: 420px;
	text-align: right;
}

.btn-gotolist, .btn-delete, .btn-edit, .btn-save {
	padding: 5px 10px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 12px;
	margin-left: 10px;
}

.btn-gotolist {
	background-color: #4CAF50;
	color: white;
}

.btn-edit, .btn-save {
	background-color: #2196F3;
	color: white;
}

.btn-delete {
	background-color: #f44336;
	color: white;
}
.edit-content {
    width: 100%; /* 원하는 너비 설정 */
    height: 200px; /* 원하는 높이 설정 */
    box-sizing: border-box; /* 패딩을 포함한 너비와 높이를 설정 */
    padding: 10px; /* 원하는 패딩 설정 */
    font-size: 14px; /* 원하는 폰트 크기 설정 */
    line-height: 1.5; /* 원하는 줄 간격 설정 */
}
.edit-title{
	width: 80%;
	height: 40px;
	box-sizing: border-box; /* 패딩을 포함한 너비와 높이를 설정 */
    padding: 10px; /* 원하는 패딩 설정 */
    font-size: 14px; /* 원하는 폰트 크기 설정 */
    line-height: 1.5; /* 원하는 줄 간격 설정 */
}
</style>
</head>
<body>
	<div class="post-container" style="width: 1500px;">
		<div class="post-header">
			<h1 class="post-title">${board.boardTitle }</h1>
			<div class="post-info">
				<span class="author">작성자 ${board.employee.empName } </span><br>
				<span class="date">작성일 ${board.boardDate }</span> <span
					class="views">조회수 ${board.boardCount }</span>
			</div>
		</div>
		<div class="post-content"><pre>${board.boardContent }</pre></div>
		<div class="post-footer">
			<button class="btn-gotolist">목록으로</button>
			<button class="btn-edit">수정</button>
			<button class="btn-save" style="display: none;">저장</button>
			<button class="btn-delete">삭제</button>
		</div>
		<input type="hidden" id="contextPath" value="${path}">
		<input type="hidden" id="loginId" value="${loginMember.empId }">
	</div>
</body>
<script>
const MboardId = "";
const loginId = "${loginMember.empId}";
const boardId = "${board.boardId}";
const boardWriter = "${board.employee.empId}";
var contextPath = document.getElementById('contextPath').value;
</script>
<script src="${path }/resources/js/boardwrite.js"></script>
<div class="container-xxl flex-grow-1 container-p-y"></div>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />