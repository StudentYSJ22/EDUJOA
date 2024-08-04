<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="loginMember"
	value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}" />
<c:set var="path" value="${pageContext.request.contextPath }" />
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<title>새 메일 작성</title>
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
	height: 100vh;
	margin-top: 30px;
}

.menubar {
	width: 240px;
	background-color: #15d471;
	color: #ecf0f1;
	padding: 20px;
	box-sizing: border-box;
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
	margin-left: 500px;
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

.email-compose {
	flex-grow: 1;
	padding: 20px;
	box-sizing: border-box;
	background-color: #ffffff;
}

.email-form {
	display: flex;
	flex-direction: column;
}

.email-form input, .email-form textarea {
	margin-bottom: 15px;
	padding: 10px;
	border: 1px solid #ddd;
	border-radius: 4px;
}

.email-form textarea {
	height: 300px;
	resize: vertical;
}

.email-form button {
	padding: 10px 20px;
	border: none;
	border-radius: 4px;
	background-color: #15d471;
	color: #ffffff;
	cursor: pointer;
	align-self: flex-start;
}

.email-form button:hover {
	background-color: #2e856e;
}

.field {
	margin-bottom: 15px;
}

.field label {
	display: block;
	margin-bottom: 5px;
}
</style>
</head>
<body>
	<div class="container">
		<!-- 사이드바 -->
		<aside class="menubar">
			<div class="logo">
				<h3>메일 메뉴</h3>
			</div>
			<ul class="menulist">
				<li><a href="${path }/mailbox" id="inbox">받은메일함</a></li>
				<li><a href="${path }/mailbox/sentbox">보낸메일함</a></li>
				<li><a href="#">스팸메일함</a></li>
				<li><a href="${path }/mailbox/tempbox">임시저장함</a></li>
				<li><a href="#">즐겨찾기</a></li>
				<li><a href="${path }/mailbox/deletebox" id="trash">삭제메일함</a></li>
			</ul>
		</aside>
		<div class="email-compose">
			<form class="email-form" id="emailForm">
				<!-- 제목 -->
				<div class="field">
					<label for="mailTitle">제목: </label> 
					<input type="text" name="mailTitle" id="mailTitle" placeholder="제목" required
						value="${email.mailTitle }" style="width: 80%; box-sizing: border-box;">
				</div>
				<!-- 수신자 -->
				<div class="field">
					<label for="mailReceiver">수신자: </label> 
					<input type="email" name="sendto" id="sendto" placeholder="받는 사람" required
						value="${email.sendto }" style="width: 80%; box-sizing: border-box;">
				</div>
				<!-- 참조 -->
				<div class="field">
					<label for="mailCc">참조: </label> 
					<input type="text" name="ccto" id="ccto" placeholder="받는 사람"
						value="${email.ccto }" style="width: 80%; box-sizing: border-box;">
				</div>
				<!-- 숨은참조 -->
				<div class="field">
					<label for="mailBcc">숨은 참조: </label> 
					<input type="text" name="Bccto" id="Bccto" placeholder="받는 사람"
						 style="width: 80%; box-sizing: border-box;">
				</div>
				<!-- 내용 -->
				<div class="field">
					<textarea name="message" id="message" placeholder="내용을 입력하세요" required style="width: 100%; box-sizing: border-box;">
						${email.mailContent }
					</textarea>
				</div>
				<!-- 안보이는 시간 보내는 용도 -->
				<div class="field">
					<input type="text" name="mailDate" id="mailDate" style="display: none;">
				</div>
				<!-- 보내기버튼 -->
				
				<button type="button" id="tempsubmit" style="align-self: flex-end;">임시저장</button>
				<button type="submit" id="button" style="align-self: flex-end;">보내기</button>
				
				<!-- 이름 -->
				<div class="field">
					<input type="text" name="empName" id="empName"
						value="${loginMember.empName }" style="display: none;">
				</div>
				<!-- 발신자 이메일 -->
				<div class="field">
					<input type="text" name="empEmail" id="empEmail"
						value="${loginMember.empEmail }" style="display: none;">
				</div>
				<!-- 직급 -->
				<div class="field">
					
					<input type="text" name="empTitle" id="empTitle"
						value="<c:choose>
                        <c:when test="${loginmember.empTitle == 'J1'}">원장</c:when>
                        <c:when test="${loginmember.empTitle == 'J2'}">팀장</c:when>
                        <c:when test="${loginmember.empTitle == 'J3'}">매니저</c:when>
                    </c:choose>" style="display: none;">
						<input type="text" name="senderEmail" id="senderEmail" value="${loginMember.empEmail }" style="display: none;"/>
				</div>
			</form>
		</div>
	</div>
</body>

<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/@emailjs/browser@4/dist/email.min.js"></script>

<script src="${path }/resources/js/mailsend.js"></script>
<script>
var contextPath = "${path}";
function removeBrackets(value) {
	return value.replace(/[\[\]]/g, '');
}

$(document).ready(function() {
	let sendto = $('#sendto').val();
	let ccto = $('#ccto').val();

	$('#sendto').val(removeBrackets(sendto));
	$('#ccto').val(removeBrackets(ccto));
});
	const empName="${loginMember.empName}";
	const empTitle="${loginMember.empTitle}";
</script>

<div class="container-xxl flex-grow-1 container-p-y">
	<!--  style="border-top: 3px solid black; margin-top: 20px;">
	<div class="footer" style="width: 100%; height: 150px;">
		<div style="display: flex;">
			<img src="${path }/resources/upload/edulogo.png"
				style="height: 80px; margin-top: 15px; margin-left: 30px;">
			<div style="color: black;">
				<span style="color: black;"> 상호명: (주)에듀조아 대표자: 유선정 서울특별시 금천구
					가산2로 77 </span><br> <strong>사업자등록번호:</strong><span>&nbsp;17-760-11776</span><strong>&nbsp;&nbsp;&nbsp;통신판매번호:</strong><span>&nbsp;17-76011776</span>
				<span>학원설립-운영등록번호: 제10242호 에듀조아학원&nbsp;
					<button style="width: 100px; height: 30px;">&nbsp;정보조회▶</button>&nbsp;신고기관명
					: 서울특별시 금천교육지원청
				</span>
				<p>학습지원센터 : 1599-1010 개인정보보호책임자 : 정보보안(별정)실 최헌수
					(edujoaOfficial@gmail.com)</p>
				<p>copyrightⓒ2014 EduJoa.co.,Ltd. All rights reserved.</p>
			</div>
		</div>
	</div>-->
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />