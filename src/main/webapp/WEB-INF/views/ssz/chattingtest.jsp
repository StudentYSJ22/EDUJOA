<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="loginmember"
	value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}" />
<c:set var="path" value="${pageContext.request.contextPath }" />
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!--<c:set var="loginMember" value="${sessionScope.loginMember }"/>-->
<!-- 모든 내용은 밑에있는 div안에만 설정해야함. -->
<title>Chat Interface</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f9f9f9;
	margin: 0;
	padding: 0;
}

.container {
	display: flex;
	height: 100vh;
}

.sidebar {
	width: 400px;
	background-color: #fff;
	box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
	display: flex;
	flex-direction: column;
	overflow: hidden;
}

.sidebar .search {
	padding: 20px;
	border-bottom: 1px solid #eee;
}

.sidebar .search input {
	width: 100%;
	padding: 10px;
	border: 1px solid #ddd;
	border-radius: 5px;
}
.sidebar .chats, .sidebar .contacts {
	flex: 1;
	overflow-y: auto;
}

.sidebar .chats .chat, .sidebar .contacts .contact {
	padding: 15px 20px;
	border-bottom: 1px solid #eee;
	display: flex;
	align-items: center;
	cursor: pointer;
}

.sidebar .chats .chat.active, .sidebar .contacts .contact:hover {
	background-color: #f5f5f5;
}

.sidebar .chats .chat img, .sidebar .contacts .contact img {
	width: 40px;
	height: 40px;
	border-radius: 50%;
	margin-right: 15px;
}

.sidebar .chats .chat .details, .sidebar .contacts .contact .details {
	flex: 1;
}

.sidebar .chats .chat .details .name, .sidebar .contacts .contact .details .name
	{
	font-weight: bold;
	margin-bottom: 5px;
}

.sidebar .chats .chat .details .message, .sidebar .contacts .contact .details .status
	{
	color: #888;
	font-size: 14px;
}

.main {
	flex: 1;
	display: flex;
	flex-direction: column;
	background-color: #fff;
}

.main .header {
	padding: 20px;
	border-bottom: 1px solid #eee;
	display: flex;
	align-items: center;
}

.main .header img {
	width: 40px;
	height: 40px;
	border-radius: 50%;
	margin-right: 15px;
}

.main .header .details {
	flex: 1;
}

.main .header .details .name {
	font-weight: bold;
}

.main .chat-area {
	flex: 1;
	padding: 20px;
	overflow-y: auto;
	background-color: #f9f9f9;
}
.main .chat-area .message {
    display: flex;
    margin-bottom: 20px;
    flex-direction: column;
}

.main .chat-area .message.sent {
    align-items: flex-end;
}

.main .chat-area .message.received {
    align-items: flex-start;
}

.main .chat-area .message .message-container {
    max-width: 60%;
    display: flex;
    flex-direction: column;
}

.main .chat-area .message .message-content {
    padding: 15px;
    border-radius: 15px;
    position: relative;
}

.main .chat-area .message.sent .message-content {
    background-color: #4f92ff;
    color: #fff;
    border-bottom-right-radius: 0;
}

.main .chat-area .message.received .message-content {
    background-color: #fff;
    border: 1px solid #ddd;
    border-bottom-left-radius: 0;
}

.main .chat-area .message .message-time {
    font-size: 12px;
    color: #888;
    margin-top: 5px;
}

.main .chat-area .message.sent .message-time {
    text-align: right;
}

.main .chat-area .message.received .message-time {
    text-align: left;
}
.main .chat-input {
	padding: 20px;
	border-top: 1px solid #eee;
	display: flex;
	align-items: center;
}

.main .chat-input input {
	flex: 1;
	padding: 10px;
	border: 1px solid #ddd;
	border-radius: 5px;
	margin-right: 10px;
}

.main .chat-input button {
	background-color: #4f92ff;
	color: #fff;
	border: none;
	padding: 10px 20px;
	border-radius: 5px;
	cursor: pointer;
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
.chat-room-item {
    position: relative;
}

.new-message-badge {
    position: absolute;
    top: 5px;
    right: 5px;
    background-color: red;
    color: white;
    border-radius: 50%;
    padding: 2px 6px;
    font-size: 12px;
    min-width: 20px;
    text-align: center;
}
#employeeList{
	flex:1;
	overflow-y: auto; 
}
#chatList{
	flex:1;
	overflow-y: auto; 
}
.sidebar .contacts{
	height: 100%;
}
</style>
</head>
<body>
	<!-- 한덩어리 -->
	<div class="container" style="margin-top: 30px;">
		<!--왼쪽-->
		<div class="sidebar">
			<div class="search">
				<div class="chat">
					<img src="${path }/resources/upload/${loginmember.empProfile}"
						alt="User" style="border-radius: 100%; width: 40px; height: 40px;">
					${loginmember.empName} 
					<c:choose>
                        <c:when test="${loginmember.empTitle == 'J1'}">원장</c:when>
                        <c:when test="${loginmember.empTitle == 'J2'}">팀장</c:when>
                        <c:when test="${loginmember.empTitle == 'J3'}">매니저</c:when>
                    </c:choose>
					<div class="details"></div>
				</div>
			</div>
			<div class="button-group"
				style="display: flex; justify-content: space-around; padding: 10px 0;">
				<button id="btnEmployeeList" class="btn btn-primary btn-sm">직원목록</button>
				<button id="btnChatList" class="btn btn-primary btn-sm">채팅목록</button>
			</div>
			<div id="employeeList" >
				<div class="contacts">
					<c:forEach items="${employees}" var="employee">
						<a href="javascript:void(0);" style="color: black;">
							<div class="contact employee-item"
								data-emp-id="${employee.empId}"
								data-emp-name="${employee.empName}"
								data-emp-title="${employee.empTitle}"
								data-emp-email="${employee.empEmail}">
								<img src="${path }/resources/upload/${employee.empProfile}"
									alt="${employee.empName}"
									style="width: 50px; height: 50px; border-radius: 50%;">
								${employee.empName }
								<c:choose>
									<c:when test="${employee.empTitle == 'J1'}">
										<span>&nbsp;원장</span>
									</c:when>
									<c:when test="${employee.empTitle == 'J2'}">
										<span>&nbsp;팀장</span>
									</c:when>
									<c:when test="${employee.empTitle == 'J3'}">
										<span>&nbsp;매니저</span>
									</c:when>
									<c:otherwise>${employee.empTitle}</c:otherwise>
								</c:choose>
							</div>
						</a>
					</c:forEach>
				</div>
			</div>
			<!-- class에 active 추가하면 채팅하고 잇는 직원 회색칠됨 -->
			<!-- 채팅방 목록 -->
			<div id="chatList" style="display: none;">
				<div class="chats" id="chats">
					<!-- ajax로 채팅방 목록 추가하는 영역 -->
				</div>
			</div>
		</div>
		<!--왼쪽끝-->
		<!-- 채팅부분 -->
		<div class="main" style="box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);">
			<div class="header search">
				<img id="receiver-profile" src="" alt="" style="display: none;">
				<div class="details">
					<div class="name receiver-name" id="receiver-name"></div>
					<div class="receiver-id" id="receiver-id" style="display:none;"></div>
					<div id="room-id" style="display:none;"></div>
					<div class="status"></div>
				</div>
			</div>
			<div class="chat-area chat-messages" id="chattingcontent">
			<!-- 메세지 동적으로 추가인데 밑에 div형식 맞춰서 js에서 추가해야됨 -->

			</div>
			<div class="chat-input">
				<input type="text" id="msg" class="form-control"
					placeholder="메시지를 입력하세요"> <input type="datetime-local"
					name="chatTime" id="chatTime" style="display: none;">
				<button onclick="sendMessage();">전송</button>
			</div>
		</div>
		<!-- 채팅부분 끝 -->
	</div>
	<!-- 한덩어리 끝 -->
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