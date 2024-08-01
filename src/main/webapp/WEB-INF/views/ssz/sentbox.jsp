<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="loginMember"
	value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}" />
<c:set var="path" value="${pageContext.request.contextPath }" />
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


<!-- 모든 내용은 밑에있는 div안에만 설정해야함. -->

<title>이메일 레이아웃</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f9f9f9;
	margin: 0;
	padding: 0;
}

.container {
	display: flex;
	width: 100%;
	height: 100vh; /* 전체 화면을 채우도록 설정 */
}

.menubar {
	width: 240px;
	background-color: #15d471;
	color: #ecf0f1;
	padding: 20px;
	box-sizing: border-box;
	border: 1px solid black;
}

.logo {
	text-align: center;
	margin-bottom: 20px;
}

.compose-btn {
	padding: 10px 20px;
	border: none;
	border-radius: 4px;
	background-color: #3498db;
	color: #ffffff;
	cursor: pointer;
}

.compose-btn:hover {
	background-color: #2980b9;
}

.menulist {
	list-style: none;
	padding: 0;
	margin: 0;
}

.menulist li {
	margin: 15px 0;
}

.menulist a {
	color: #ecf0f1;
	text-decoration: none;
	display: block;
	padding: 10px;
	border-radius: 4px;
}

.menulist a:hover {
	background-color: #2e856e;
}

.content {
	flex-grow: 1;
	padding: 20px;
	box-sizing: border-box;
	overflow-y: auto;
	background-color: #ffffff;
	border: 1px solid black;
}

.toolbar {
	display: flex;
	justify-content: flex-start;
	margin-bottom: 20px;
}

.toolbar button {
	padding: 10px 20px;
	border: none;
	border-radius: 4px;
	background-color: #15d471;
	color: #ffffff;
	cursor: pointer;
	margin-right: 10px;
}

.toolbar button:hover {
	background-color: #2e856e;
}

.email-list {
	display: flex;
	flex-direction: column;
}

.email-item {
	display: flex;
	align-items: center;
	padding: 10px;
	border-bottom: 1px solid #ddd;
}

.email-item:hover {
	background-color: #f5f5f5;
}

.email-checkbox {
	margin-right: 10px;
}

.sender, .subject, .date {
	flex: 1;
}

.subject {
	flex: 2;
}

#refresh {
	align-self: flex-end;
}
</style>

<body>
	<div class="container" style="margin-top: 30px;">
		<!-- 사이드바 -->
		<aside class="menubar">
			<div class="logo">
				<button class="compose-btn">메일 쓰기</button>
			</div>
			<ul class="menulist">
				<li><a href="/mailbox" id="inbox">받은메일함</a></li>
				<li><a href="#">보낸메일함</a></li>
				<li><a href="#">스팸메일함</a></li>
				<li><a href="/mailbox/tempbox">임시저장함</a></li>
				<li><a href="#">즐겨찾기</a></li>
				<li><a href="/mailbox/deletebox" id="trash">삭제메일함</a></li>
			</ul>
		</aside>
		<main class="content">
			<!-- 메일리스트 위 버튼 -->
			<div class="toolbar">
				<button id="delete">삭제</button>
				<button>스팸 신고</button>
				<button id="refresh">새로고침</button>
			</div>
			<!-- 메일 리스트 -->
			<div class="email-list">
				<!-- 메일 아이템 -->
				<div class="email-item">
					<input type="checkbox" id="select-all" class="email-checkbox">
					<span class="sender">받는 주소</span> 
					<span class="subject">메일 제목</span> 
					<span class="date">날짜</span>
				</div>
				<!-- 추가 메일 아이템 -->
				<c:forEach items="${SentMail}" var="email">
					<div class="email-item" data-email-id="${email.mailId}">
						<input type="checkbox" class="email-checkbox"> 
						<span class="sender">${email.sendto}</span> 
						<span class="subject">${email.mailTitle}</span> 
						<span class="date">${email.mailDate}</span>
					</div>
				</c:forEach>
				<input type="hidden" id="contextPath" value="${path}">
			</div>
		</main>
	</div>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<!--  <script src="${path }/resources/js/mail.js"></script>-->
</body>
<script>
	const empId = "${loginMember.empId}";
	console.log(empId);
	var contextPath = "${path}";
	console.log(contextPath);
</script>
<!-- background-color: #28a745;  -->
<div class="container-xxl flex-grow-1 container-p-y"></div>
<!--  style="border-top: 3px solid black; margin-top: 20px;">
	<div class="footer" style="width: 100%; height: 150px;">
		<div style="display:flex;">
			<img src="${path }/resources/upload/edulogo.png" style="height: 80px; margin-top: 15px; margin-left: 30px;"> 
				<div style="color: black;">
					<span style="color: black;"> 상호명: (주)에듀조아 대표자: 유선정 서울특별시 금천구 가산2로 77 </span><br>
					<strong>사업자등록번호:</strong><span>&nbsp;17-760-11776</span><strong>&nbsp;&nbsp;&nbsp;통신판매번호:</strong><span>&nbsp;17-76011776</span>
					<span>학원설립-운영등록번호: 제10242호 에듀조아학원&nbsp;<button style="width:100px; height:30px;">&nbsp;정보조회▶</button>&nbsp;신고기관명 : 서울특별시 금천교육지원청</span>
					<p>학습지원센터 : 1599-1010  개인정보보호책임자 : 정보보안(별정)실 최헌수 (edujoaOfficial@gmail.com)</p>
					<p>copyrightⓒ2014 EduJoa.co.,Ltd. All rights reserved.</p>
				</div>
		</div>
	</div>
</div>-->
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
