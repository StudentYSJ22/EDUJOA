<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var ="loginMember" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}"/>
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
            <h5>직원 목록</h5>
            <c:forEach items="${employees}" var="employee">
                <div class="employee-item" data-emp-id="${employee.empId}">
                    <a href="javascript:void(0);" onclick="openChatRoom('${employee.empName}','${employee.empId}');" style="color: black;">${employee.empName} - ${employee.empTitle}</a>
                </div>
            </c:forEach>
        </div>

        <!-- 채팅방 목록 -->
        <div class="chat-rooms" id="chatRooms">
            <h5>채팅방 목록</h5>
            <c:forEach items="${chatrooms}" var="room">
                <div class="chat-room-item" id="chat-room-item">
                <!--여기는 로그인한 empId랑 roomId 일치하는 거 있는지 체크 후 없으면 생성 있으면 불러오기  -->
                    ${room}
                </div>
            </c:forEach>
        </div>

        <!-- 채팅 창 -->
        <div class="chat-window">
            <h5>채팅</h5>
            <div class="chat-messages" id="chatMessages">
                <!-- 메시지들이 여기에 동적으로 추가됩니다 -->
            </div>
            <div class="chat-input">
                <input type="text" id="messageInput" class="form-control" placeholder="메시지를 입력하세요...">
                <button class="btn btn-primary" onclick="sendMessage()">전송</button>
            </div>
            <div class="chat-input">
                <input type="file" id="fileInput" class="form-control-file">
                <button class="btn btn-secondary" onclick="sendFile()">파일 전송</button>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script>
        let stompClient = null;
        let selectedRoom = null;
        let empName = null;
        let empId = null;

        function connect() {
            let socket = new SockJS('/chat');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function(frame) {
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/public', function(messageOutput) {
                    showMessage(JSON.parse(messageOutput.body));
                });
            });
        }

        function sendMessage() {
            let messageContent = $("#messageInput").val().trim();
            if(messageContent && selectedRoom) {
                let chatMessage = {
                    empName: '${loginMember.empName}',  // 현재 로그인한 직원 이름
                    chatContent: messageContent,
                    messageType: 'TALK',
                    roomId: selectedRoom
                };
                stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
                $("#messageInput").val('');
            }
        }

        function sendFile() {
            let file = $("#fileInput")[0].files[0];
            if (file && selectedRoom) {
                let formData = new FormData();
                formData.append("file", file);

                $.ajax({
                    url: "/uploadFile",
                    type: "POST",
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function(fileUrl) {
                        let chatMessage = {
                            empName: '${loginMember.empName}',
                            chatContent: messageContent,
                            messageType: 'TALK',
                            roomId: selectedRoom
                        };
                        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
                    },
                    error: function() {
                        alert("파일 업로드 실패");
                    }
                });
            }
        }

        function showMessage(message) {
            if (message.messageType === 'TALK' || message.messageType === 'FILE') {
                let content = message.messageType === 'TALK' ? message.chatContent : `<a href="${message.chatContent}" target="_blank">파일 다운로드</a>`;
                $("#chatMessages").append(`<div><strong>${message.empName}:</strong> ${content}</div>`);
            }
        }
        function openChatRoom(empName, empId) {
        	// 기존 채팅방 목록에 동일한 아이디의 채팅방이 있는지 확인
            if ($(`.chat-room-item[data-room-id='${room.empId}']`).length === 0) {
            // 채팅방 목록에 추가
            let roomElement = `<div class="chat-room-item" data-room-id="${room.empId}">${room.empName}</div>`;
            $("#chatRooms").append(roomElement);
            }
        }

        $(document).ready(function() {
            connect();

            $(".employee-item").click(function() {
                let empId = $(this).data("emp-id");
                selectedRoom = empId; // 단일 사용자와 채팅하는 경우
                $("#chatMessages").empty(); // 새로운 대화를 위해 메시지 초기화
                openChatRoom($(this).text(), empId);
            });

            $(".chat-room-item").click(function() {
                let roomId = $(this).data("room-id");
                selectedRoom = roomId; // 그룹 채팅방 선택
                $("#chatMessages").empty(); // 새로운 대화를 위해 메시지 초기화
            });
        });
    </script>
</body>
</html>