<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>사내 메신저</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .messenger-container {
            height: 100vh;
            display: flex;
        }
        .employee-list, .chat-rooms, .chat-window {
            padding: 20px;
            border-right: 1px solid #dee2e6;
            overflow-y: auto;
            margin-top:50px;
            
        }
        .employee-list {
            width: 15%;
            margin-left:100px;
        }
        .chat-rooms {
            width: 20%;
        }
        .chat-window {
            width: 40%;
        }
        .employee-item, .chat-room-item {
            cursor: pointer;
            padding: 10px;
            margin-bottom: 5px;
            border-radius: 5px;
        }
        .employee-item:hover, .chat-room-item:hover {
            background-color: #f8f9fa;
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
        <div class="employee-list">
            <h4>직원 목록</h4>
            <c:forEach items="${employees}" var="employee">
                <div class="employee-item" data-emp-id="${employee.empId}">
                    <strong>${employee.empName}</strong> - ${employee.empTitle}
                </div>
            </c:forEach>
        </div>

        <!-- 채팅방 목록 -->
        <div class="chat-rooms">
            <h4>채팅방 목록</h4>
            <c:forEach items="${chatRooms}" var="room">
                <div class="chat-room-item" data-room-id="${room.roomId}">
                    ${room.roomName}
                </div>
            </c:forEach>
        </div>

        <!-- 채팅 창 -->
        <div class="chat-window">
            <h4>1:1 채팅</h4>
            <div class="chat-messages" id="chatMessages">
                <!-- 메시지들이 여기에 동적으로 추가됩니다 -->
            </div>
            <div class="chat-input">
                <input type="text" id="messageInput" class="form-control" placeholder="메시지를 입력하세요...">
                <button class="btn btn-primary" onclick="sendMessage()">전송</button>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script>
        let stompClient = null;
        let selectedRoom = null;

        function connect() {
            let socket = new SockJS('/chat');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function(frame) {
                console.log('Connected: ' + frame);
                // 연결 후 로직
            });
        }

        function sendMessage() {
            let messageContent = $("#messageInput").val().trim();
            if(messageContent && selectedRoom) {
                let chatMessage = {
                    roomId: selectedRoom,
                    empName: '${currentEmployee.empName}',  // 현재 로그인한 직원 이름
                    chatContent: messageContent,
                    messageType: 'TALK'
                };
                stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
                $("#messageInput").val('');
            }
        }

        $(document).ready(function() {
            connect();

            $(".employee-item").click(function() {
                let empId = $(this).data('emp-id');
                // 1:1 채팅방 생성 또는 기존 채팅방 열기 로직
            });

            $(".chat-room-item").click(function() {
                selectedRoom = $(this).data('room-id');
                // 채팅방 열기 로직
            });
        });
    </script>
</body>
   <div class="container-xxl flex-grow-1 container-p-y">
   </div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>