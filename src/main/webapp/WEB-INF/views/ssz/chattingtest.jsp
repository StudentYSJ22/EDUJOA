<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--  <meta http-equiv="Refresh" content="10">-->
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="loginmember" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}" />
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!--<c:set var="loginMember" value="${sessionScope.loginMember }"/>-->
<!-- 모든 내용은 밑에있는 div안에만 설정해야함. -->
<title>사내 메신저</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<style>
body {
	font-family: Arial, sans-serif;
}

.messenger-container {
	height: 100vh;
	display: flex;
	margin-top:30px;
}

.employee-list, .chat-rooms, .chat-window {
	padding: 20px;
	border-right: 1px solid #dee2e6;
	overflow-y: auto;
}

.employee-list {
	width: 20%;
	background-color: #e6f9e6;
}

.chat-rooms {
	width: 30%;
	background-color: #ccffcc;
}

.chat-window {
	width: 50%;
	background-color: #b3ffb3;
}

.employee-item, .chat-room-item {
	cursor: pointer;
	padding: 10px;
	margin-bottom: 5px;
	border-radius: 5px;
}

.employee-item:hover, .chat-room-item:hover {
	background-color: #aaffaa;
}

.chat-messages {
	height: 70vh;
	overflow-y: auto;
	border: 1px solid #dee2e6;
	border-radius: 5px;
	padding: 10px;
	margin-bottom: 10px;
}

.chat-input {
	display: flex;
}

.chat-input input {
	flex-grow: 1;
	margin-right: 10px;
}

.modal {
	display: none;
	position: fixed;
	z-index: 1000;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.4);
}

.modal-content {
	background-color: #fefefe;
	margin: 15% auto;
	padding: 20px;
	border: 1px solid #888;
	width: 350px;
	height: 500px;
	position: relative;
}

#employeeInfo {
	margin-bottom: 20px;
}

#chatButton, #closeModal {
	position: absolute;
	bottom: 20px;
	padding: 10px;
	cursor: pointer;
}

#chatButton {
	left: 20px;
}

#closeModal {
	right: 20px;
}
.message-container {
    display: flex;
    margin-bottom: 10px;
}

.message-container.sent {
    justify-content: flex-end;
}

.message-container.received {
    justify-content: flex-start;
}

.message {
    max-width: 60%;
    padding: 10px;
    border-radius: 10px;
    background-color: #DCF8C6; /* 기본 색상을 발신 메시지 색상으로 설정 */
}

.message.sent .message {
    background-color: #DCF8C6; /* 발신 메시지 색상 */
}

.message.received .message {
    background-color: #FFFFFF; /* 수신 메시지 색상 */
}

.message-content {
    margin-bottom: 5px;
}

.message-time {
    font-size: 0.8em;
    color: #888;
}
}
</style>
</head>
<body>
	<div class="messenger-container">
		<!-- 직원 목록 -->
		<div class="employee-list" id="attendContainer">
			<h5>직원 목록</h5>
			<c:forEach items="${employees}" var="employee">
				<div class="employee-item" data-emp-id="${employee.empId}"
					data-emp-name=" ${employee.empName}"
					data-emp-title="${employee.empTitle}"
					data-emp-email="${employee.empEmail}">
					<a href="javascript:void(0);" style="color: black;"> <img
						src="${path}/resources/upload/IU.jpeg"
						style="width: 30px; height: 30px; border-Radius: 100px;">${employee.empName}<br>
						<c:choose>
							<c:when test="${employee.empTitle == 'J1'}">
								<sub>원장</sub>
							</c:when>
							<c:when test="${employee.empTitle == 'J2'}">
								<sub>팀장</sub>
							</c:when>
							<c:when test="${employee.empTitle == 'J3'}">
								<sub>매니저</sub>
							</c:when>
							<c:otherwise>${employee.empTitle}</c:otherwise>
						</c:choose>
					</a>

				</div>
			</c:forEach>
		</div>
		<div id="namestorage" style="display: none;"></div>
		<div id="titlestorage" style="display: none;"></div>
		<div id="profilestorage" style="display: none;"></div>		

		<!-- 채팅방 목록 -->
		<div class="chat-rooms" id="chatRooms">
		<h5>채팅방 목록</h5>
			<div id="chatRoomList">
				<!-- 여기에 채팅방 목록이 동적으로 추가될 것입니다 -->
			</div>
		</div>

		<!-- 채팅 창 -->
		<div class="chat-window">
			<h5>채팅</h5>
			<div class="receiver-name" id="receiver-name"></div>
			<div class="receiver-id" id="receiver-id"></div>
			<div id="room-id" style="display:none;"></div>
			<div class="chat-messages" id="chattingcontent">
				<!-- 메시지들이 여기에 동적으로 추가됩니다 -->
			</div>
			<div class="chat-input">
				<input type="text" id="msg" class="form-control" placeholder="메시지를 입력하세요"> 
					<input type="datetime-local" name="chatTime" id="chatTime" style="display: none;">
				<button class="btn btn-primary" onclick="sendMessage();" style="width:70px;">전송</button>
			</div>
			<!--    <div class="chat-input">
                <input type="file" id="fileInput" class="form-control-file">
                <button class="btn btn-secondary" onclick="sendFile()">파일 전송</button>
            </div>-->
		</div>
	</div>
	<!-- 모달 div -->
	<div id="employeeModal" class="modal">
		<div class="modal-content">
			<h3>직원 정보</h3>
			<div id="employeeInfo"></div>
			<button id="chatButton">1:1 대화하기</button>
			<button id="closeModal">닫기</button>
		</div>
	</div>
	<script>
		const loginMember = "${loginmember}";
		const path = "${pageContext.request.contextPath}";
		const loginId = "${loginmember.empId}";
		const loginName = "${loginmember.empName}";
		const employees = "${employees}";
		let chatTime = document.getElementById('chatTime').value;

		//console.log(employees);
		
		
	</script>

	<script src="${path }/resources/js/chatting.js"></script>
</body>
<div class="container-xxl flex-grow-1 container-p-y"></div>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />