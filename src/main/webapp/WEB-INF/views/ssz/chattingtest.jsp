<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--  <meta http-equiv="Refresh" content="10">-->
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var ="loginmember" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<c:set var="path" value="${pageContext.request.contextPath }"/>
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
    </style>
</head>
<body>
    <div class="messenger-container">
        <!-- 직원 목록 -->
        <div class="employee-list" id="attendContainer">
            <h5>직원 목록</h5>
            <c:forEach items="${employees}" var="employee">
                <div class="employee-item" data-emp-id="${employee.empId}" data-emp-name=" ${employee.empName}" data-emp-title= "${employee.empTitle}">
                   <a href="javascript:void(0);" style="color: black;">
                    <img src="${path}/resources/upload/IU.jpeg" style="width:30px; height:30px; border-Radius:100px;">${employee.empName}<br>  
	                   <c:choose>
		                   <c:when test="${employee.empTitle == 'J1'}"><sub>원장</sub></c:when>
		                   <c:when test="${employee.empTitle == 'J2'}"><sub>팀장</sub></c:when>
		                   <c:when test="${employee.empTitle == 'J3'}"><sub>매니저</sub></c:when>
		                   <c:otherwise>${employee.empTitle}</c:otherwise>
	                   </c:choose>
                   </a>

                </div>
            </c:forEach>
		</div>
		<div id="idstorage" style="display: none;"></div>
		<div id="namestorage" style="display: none;"></div>
           
        <!-- 채팅방 목록 -->
        <div class="chat-rooms" id="chatRooms">
            <h5>채팅방 목록</h5>
              <c:if test="${not empty chatrooms }">
	          	<c:forEach items="${chatrooms}" var="room">
	               <div class="chat-room-item" data-room-id="${room.roomId}" data-recevier-id="${room.receiverId }"
	               data-sender-id="${room.senderId }">
					<img src="${path}/resources/upload/IU.jpeg" style="width:30px; height:30px; border-Radius:100px;">
					${room.employee.empName}<br>
	               </div>
           		</c:forEach>
            </c:if>
        </div>

        <!-- 채팅 창 -->
        <div class="chat-window">
            <h5>채팅</h5>
            <div class="receiver-info" id="receiver-info"></div>
            <div class="chat-messages" id="chattingcontent">
                <!-- 메시지들이 여기에 동적으로 추가됩니다 -->
            </div>
            <div class="chat-input">
                <input type="text" id="msg" class="form-control" placeholder="메시지를 입력하세요">
                <input type="datetime-local" name="chatTime" id="chatTime" style="display:none;" >
                <button class="btn btn-primary" onclick="sendMessage();">전송</button>
            </div>
          <!--    <div class="chat-input">
                <input type="file" id="fileInput" class="form-control-file">
                <button class="btn btn-secondary" onclick="sendFile()">파일 전송</button>
            </div>-->
        </div>
    </div>
	<script>
		const loginMember=("${loginMember}");
		const path="${pageContext.request.contextPath}";
		const loginId="${loginMember.empId}";
		const loginName="${loginMember.empName}";
		const employees="${employees}";
		let chatTime = document.getElementById('chatTime').value;
		
		//console.log(employees);
		
		
	</script>

	<script src="${path }/resources/js/chatting.js"></script>
</body>
   <div class="container-xxl flex-grow-1 container-p-y">
   </div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>