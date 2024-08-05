function getKSTDate(date = new Date()) {
    if (typeof date === 'string') {
        date = new Date(date);
    }
    
    if (!(date instanceof Date) || isNaN(date)) {
        date = new Date();
    }

    
    return date;
}

// WebSocket 서버 설정
let server;
let isConnected = false;
const reconnectInterval = 5000; // 5초마다 재연결 시도

const titleMap = {
	'J1': '원장',
	'J2': '팀장',
	'J3': '매니저'
};

function getKoreanTitle(empTitle) {
	return titleMap[empTitle] || empTitle;
}

function connectWebSocket() {
	//server = new WebSocket("ws://14.36.141.71:10079/GDJ79_EDUJOA_final/chattest");
	server = new WebSocket("ws://localhost:9090/chattest");

	server.onopen = () => {
		console.log("서버 연결됨");
		isConnected = true;
		const msg = new Message("open", "", loginId, "", "", new Date());
		console.log(msg);
		server.send(msg.convert());
	};

	server.onmessage = response => {
		console.log("메시지 수신:", response.data);
		const receiveMsg = Message.deconvert(response.data);
		switch (receiveMsg.chatType) {
			case "send":
				handleIncomingMessage(receiveMsg);
				break;
			case "newRoom":
				handleNewChatRoom(receiveMsg);
				break;
			default:
				console.log("알 수 없는 메시지 타입:", receiveMsg.chatType);
		}
	};

	server.onclose = event => {
		console.log("서버 연결 종료", event);
		isConnected = false;
		setTimeout(connectWebSocket, reconnectInterval);
	};

	server.onerror = error => {
		console.error("WebSocket 오류 발생:", error);
		isConnected = false;
	};
}
function handleNewChatRoom(message) {
	if (!isChatRoomExist(message.roomId)) {
		addChatRoomToList(message.roomId, message.sender, message.senderName, message.senderTitle, message.senderProfile);
		console.log("새로운 채팅방이 생성되었습니다:", message.roomId);
		getMyChatRooms(); // 채팅방 목록 갱신
	}
}

const $chatRoomList = $('#chatList');
const $chats = $('#chats');
const $chattingcontent = $('#chattingcontent');
const $roomId = $('#room-id');
const $receiverName = $('#receiver-name');
const $receiverId = $('#receiver-id');


$(document).ready(function() {
	connectWebSocket();
	getMyChatRooms();
	console.log("getMyChatRoom() 실행했음!");

	// 직원 클릭 이벤트
	$(document).on('click', '.employee-item, .receiver-name', function() {
		//receiver-name 클릭했을 때 필요한 정보 다 담아두고 거기서 뽑아와야함

		const empName = $(this).data('emp-name');
		const empTitle = $(this).data('emp-title');
		const empId = $(this).data('emp-id');
		const empProfile = $(this).find('img').attr('src');
		const empEmail = $(this).data('emp-email');
		const koreanTitle = getKoreanTitle(empTitle);

		const infoHtml = `
			<img src="${empProfile}" alt="${empName}" style="width:100px; height:100px; border-radius:50%;">
            <p><strong>이름:</strong> ${empName}</p>
            <p><strong>직급:</strong> ${koreanTitle}</p>
            <p><strong>직원 ID:</strong> ${empId}</p>
            <p><strong>이메일:</strong> ${empEmail}</p>
            
            <input id="targetId" type="hidden" value="${empId}"/>
            <input id="targetName" type="hidden" value="${empName}"/>
            <input id="targetTitle" type="hidden" value="${koreanTitle}"/>
            <input id="targetProfile" type="hidden" value="${empProfile}"/>
        `;

		$('#employeeInfo').html(infoHtml);
		$('#employeeModal').show();

	});

	// 모달 닫기 버튼 이벤트
	$('#closeModal').on('click', function() {
		$('#employeeModal').hide();
	});

	// 1:1 대화하기 버튼 이벤트
	$('#chatButton').on('click', function() {
		const targetId = $('#targetId').val();
		const targetName = $('#targetName').val();
		const targetTitle = $('#targetTitle').val();
		const targetProfile = $('#targetProfile').val();

		$('#receiver-id').text(targetId);
		$('#receiver-name').text(`${targetName} ${targetTitle}`);
		//여기에 사진 넣어줘야함
		$("#receiver-profile").attr("src", targetProfile).show();

		const empProfile = $('#employeeInfo').find('img').attr('src');
		createChatRoom(targetId, targetName, targetTitle, empProfile);
		$('#employeeModal').hide();
	});

	// 모달 외부 클릭 시 닫기
	$(window).on('click', function(event) {
		if (event.target == $('#employeeModal')[0]) {
			$('#employeeModal').hide();
		}
	});
});

function createChatRoom(targetId, empName, empTitle, empProfile) {
	$.ajax({
		type: "POST",
		url: `${path}`+"/chatting/createChatRoom",
		contentType: "application/json",
		data: JSON.stringify({
			sender: loginId,
			receiver: targetId
		}),
		dataType: "json",
		success: function(response) {
			clearChatWindow();
			console.log("Raw response:", response);

			let roomId = response.roomId || (Array.isArray(response) && response.length > 0 ? response[0].roomId : "");

			if (roomId > 0) {
				//1:1대화하기 버튼 누른 직후에 채팅방목록에 방금 만든 채팅방번호가 없다면
				if (!isChatRoomExist(roomId)) {
					//그 채팅방을 추가하고
					addChatRoomToList(roomId, targetId, empName, empTitle, empProfile);
					console.log("채팅방 목록에 추가 완료.");

					//대화하기 버튼 눌렀을 때 return받은 채팅방 번호가 채팅방목록에 있다면,
				} else {
					displayChatHistory(response.content, targetId);
				}

				selectChatRoom(roomId, targetId);
				$roomId.text(roomId);
				$receiverId.text(targetId);
				$receiverName.text(`${empName} ${getKoreanTitle(empTitle)}`);
				// 프로필 이미지 설정 및 표시
				$("#receiver-profile").attr("src", empProfile).show();
			} else {
				console.error("유효하지 않은 roomId:", roomId);
				$("#chattingcontent").html("채팅방 생성 중 오류가 발생했습니다.");
			}

			getMyChatRooms();
		},
		error: handleAjaxError
	});
}
// 새로운 함수: 채팅방 선택
function selectChatRoom(roomId, empId) {
	$roomId.text(roomId);
	$receiverId.text(empId);

	// 채팅방 아이템에서 프로필 이미지 URL 가져오기
	const chatRoomItem = $(`.chat-room-item[data-room-id="${roomId}"]`);
	const empProfile = chatRoomItem.data('room-emp-profile');

	// 프로필 이미지 설정 및 표시
	$("#receiver-profile").attr("src", empProfile).show();

	getMyChatRecords(roomId, empId);
}

function isChatRoomExist(roomId) {
	return $chatRoomList.find(`[data-room-id="${roomId}"]`).length > 0;
}

function addChatRoomToList(roomId, empId, empName, empTitle, empProfile) {

	const position = getKoreanTitle(empTitle);
	const newChatRoomHtml = `
        <div class="chat chat-room-item" 
             data-room-id="${roomId}" 
             data-room-emp-id="${empId}"
             data-room-emp-name="${empName}"
             data-room-emp-title="${empTitle}"
             data-room-emp-profile="${empProfile}">
            <img src="${empProfile}" style="width: 30px; height: 30px; border-radius: 100%;">
            <div class="details">
            <div class="name">${escapeHTML(empName)}</div> 
            <div class="message">${position}</div>
            </div>
        </div>
    `;

	$chats.prepend(newChatRoomHtml);
	attachChatRoomClickEvent();
}

function escapeHTML(str) {
	const entityMap = {
		'&': '&amp;',
		'<': '&lt;',
		'>': '&gt;',
		'"': '&quot;',
		"'": '&#39;',
		'/': '&#x2F;',
		'`': '&#x60;',
		'=': '&#x3D;'
	};
	return String(str).replace(/[&<>"'`=\/]/g, s => entityMap[s]);
}

function getMyChatRooms() {
	$.ajax({
		type: "GET",
		url: `${path}`+"/chatting/getMyChatRooms",
		//메소드 실행되면 empName, empProfile, empTitle, roomId, empId나옴
		data: { empId: loginId },
		dataType: "json",
		success: function(chatrooms) {
			let html = '';
			if (chatrooms && chatrooms.length > 0) {
				html = chatrooms.map(room => `
                    <div class="chat-room-item chat" 
                         data-room-id="${room.roomId}" 
                         data-room-emp-id="${room.empId}"
                         data-room-emp-name="${room.empName}"
                         data-room-emp-title="${room.empTitle}"
                         data-room-emp-profile="${room.empProfile}">
                        <img src="${path}/resources/upload/${room.empProfile}" 
                             style="width: 30px; height: 30px; border-radius: 100%;">
                        ${escapeHTML(room.empName)} ${getKoreanTitle(room.empTitle)}<br>
                    </div>
                `).join('');
			} else {
				html = '<p>채팅방이 없습니다.</p>';
			}
			$chats.html(html);
			attachChatRoomClickEvent();
		},
		error: handleAjaxError
	});
}

function attachChatRoomClickEvent() {
	$('.chat-room-item').on('click', function() {
		const roomId = $(this).data('room-id');
		const empId = $(this).data('room-emp-id');
		const empName = $(this).data('room-emp-name');
		const empTitle = $(this).data('room-emp-title');
		const empProfile = $(this).data('room-emp-profile');
		$roomId.text(roomId);
		$receiverId.text(empId);
		$receiverName.text(empName + " " + getKoreanTitle(empTitle));

		// 새 메시지 배지 제거
		$(this).find('.new-message-badge').remove();

		clearChatWindow();
		getMyChatRecords(roomId, empId);
	});
}

function getMyChatRecords(roomId, empId) {
	$.ajax({
		type: "POST",
		url: `${path}`+"/chatting/getMyChatRecords",
		//ROOM_ID, EMP_ID, CONTENT, CHAT_TIME
		contentType: "application/json",
		data: JSON.stringify({
			roomId: roomId,
			receiver: empId
		}),
		dataType: "json",
		success: function(chatHistory) {
			console.log("chatHistory");
			console.log(chatHistory);
			if (chatHistory.length === 0) {
				$("#chattingcontent").html("대화내용이 없습니다. 메시지를 입력하세요.");
				if ($chattingcontent.length > 0) {
					clearChatWindow();
				}
			} else {
				displayChatHistory(chatHistory, chatHistory.empId);
			}
			$chattingcontent.scrollTop($chattingcontent[0].scrollHeight);
		}
	});
}

// 채팅 기록을 표시하는 함수
function displayChatHistory(content, empId) {
    $chattingcontent.empty();

    if (content && content.length > 0) {
        let lastDate = null;
        content.forEach(function(cont) {
            const messageDate = new Date(cont.chatTime);

            if (!lastDate || !isSameDay(lastDate, messageDate)) {
                const dateString = formatDate(messageDate);
                $chattingcontent.append(`<div class="date-divider" data-date="${messageDate.toISOString()}">---------${dateString}--------</div>`);
                lastDate = messageDate;
            }

            createMessageHtml(cont);
        });
    } else {
        $chattingcontent.append('<p>대화 내역이 없습니다. 메세지를 입력하세요.</p>');
    }

    $chattingcontent.scrollTop($chattingcontent[0].scrollHeight);
}
//////////////////////////////////////////////////////////////////////////////////////////////////////
// 메시지 HTML을 생성하는 함수
function createMessageHtml(message) {
    if (!message || !message.content) {
        console.error("Invalid message object:", message);
        return;
    }

    const sender = message.sender || message.empId;
    const messageClass = sender === loginId ? 'sent' : 'received';
    const content = message.content;
    const messageDate = new Date(message.chatTime); // KST로 변환된 시간
    const formattedTime = formatDateTime(messageDate);
    const msg = `
        <div class="message ${messageClass}">
            <div class="message-container">
                <div class="message-content content">${escapeHTML(content)}</div>
                <div class="message-time">${formattedTime}</div>
            </div>
        </div>
    `;

    $chattingcontent.find('p:contains("대화내용이 없습니다")').remove();
    $chattingcontent.append(msg);
}

// 타임스탬프를 KST로 변환하여 포맷팅하는 함수
function formatDateTime(date) {
    return date.toLocaleTimeString('ko-KR', { hour: '2-digit', minute: '2-digit' });
}

// 날짜를 포맷팅하는 함수
function formatDate(date) {
    const days = ['일', '월', '화', '수', '목', '금', '토'];
    return `${date.getFullYear()}년 ${date.getMonth() + 1}월 ${date.getDate()}일 ${days[date.getDay()]}요일`;
}

// 두 날짜가 같은 날인지 확인하는 함수
function isSameDay(date1, date2) {
    return date1.getFullYear() === date2.getFullYear() &&
           date1.getMonth() === date2.getMonth() &&
           date1.getDate() === date2.getDate();
}

// 수신 메시지를 처리하는 함수
function handleIncomingMessage(message) {
    console.log("Handling incoming message:", message);
    const currentRoomId = $roomId.text();

    if (message.chatType === "send") {
        // 수신한 메시지의 타임스탬프를 KST로 변환
        const messageDate = new Date(message.chatTime); // UTC 시간
        message.chatTime = messageDate; 
        
        if (message.roomId === currentRoomId) {
            appendMessageWithDateCheck(message);
        } else {
            addNewMessageBadge(message.roomId);
        }

        // 새로운 채팅방 생성 확인 및 추가
        if (!isChatRoomExist(message.roomId)) {
            addChatRoomToList(message.roomId, message.sender, message.senderName, message.senderTitle, message.senderProfile);
            getMyChatRooms(); // 채팅방 목록 갱신
        }
    }
}

// 메시지를 추가하는 함수 (날짜 구분선 추가 여부 체크)
function appendMessageWithDateCheck(message) {
    if (!message || !message.chatTime) {
        console.error("Invalid message object:", message);
        return;
    }

    const newMessageDate = new Date(message.chatTime);

    if (shouldAddDateDivider(newMessageDate)) {
        const dateString = formatDate(newMessageDate);
        $chattingcontent.append(`<div class="date-divider" data-date="${newMessageDate.toISOString()}">---------${dateString}--------</div>`);
    }

    

    createMessageHtml(message);
    $chattingcontent.scrollTop($chattingcontent[0].scrollHeight);
}

// 날짜 구분선 추가 여부를 결정하는 함수
function shouldAddDateDivider(newMessageDate) {
    const lastDivider = $chattingcontent.find('.date-divider').last();
    if (lastDivider.length === 0) {
        return true;
    }

    const lastDate = new Date(lastDivider.data('date'));
    return !isSameDay(lastDate, newMessageDate);
}

function addNewMessageBadge(roomId) {
	const chatRoomItem = $(`.chat-room-item[data-room-id="${roomId}"]`);
	let badge = chatRoomItem.find('.new-message-badge');
	if (badge.length === 0) {
		chatRoomItem.append('<span class="new-message-badge">1</span>');
	} else {
		let count = parseInt(badge.text()) + 1;
		badge.text(count);
	}
	// 채팅방 목록의 맨 위로 이동
	chatRoomItem.prependTo(chatRoomItem.parent());
}

const sendMessage = () => {
    if (!isConnected) {
        alert("서버와의 연결이 끊어졌습니다. 페이지를 새로고침해 주세요.");
        return;
    }

    const inputData = $("#msg").val().trim();
    const receiverId = $("#receiver-id").text();
    const selectedRoomId = $roomId.text();
    const chatTime = getKSTDate().toISOString(); // KST 시간으로 변환하여 ISO 문자열로 변환

    if (inputData.length > 0 && selectedRoomId) {
        const msgObj = {
            chatType: "send",
            roomId: selectedRoomId,
            sender: loginId,
            receiverId: receiverId,
            content: inputData,
            chatTime: chatTime, // KST 시간 문자열
        };
        try {
            server.send(JSON.stringify(msgObj));
            console.log("메시지 전송:", msgObj);
            appendMessageWithDateCheck(msgObj);
            $("#msg").val("");
        } catch (error) {
            console.error("메시지 전송 중 오류 발생:", error);
            alert("메시지 전송에 실패했습니다. 다시 시도해 주세요.");
        }
    } else {
        console.error("메시지를 보낼 수 없습니다. 입력 데이터나 채팅방 ID가 없습니다.");
    }
};

class Message {
	constructor(chatType = "", roomId = "", sender = "", receiverId = "", chatContent = "", chatTime = "") {
		this.chatType = chatType;
		this.roomId = roomId;
		this.sender = sender;
		this.receiverId = receiverId;
		this.chatContent = chatContent;
		this.chatTime = chatTime;
	}

	convert() {
		return JSON.stringify(this);
	}
	static deconvert(data) {
		return JSON.parse(data);
	}
}

function clearChatWindow() {
	$chattingcontent.empty();
}

function handleAjaxError(xhr, status, error) {
	console.error("Ajax 요청 실패:", status, error);
	alert("요청 처리 중 오류가 발생했습니다. 다시 시도해 주세요");
}
document.addEventListener('DOMContentLoaded', function() {
	const btnEmployeeList = document.getElementById('btnEmployeeList');
	const btnChatList = document.getElementById('btnChatList');
	const employeeList = document.getElementById('employeeList');
	const chatList = document.getElementById('chatList');

	btnEmployeeList.addEventListener('click', function() {
		employeeList.style.display = 'block';
		chatList.style.display = 'none';
	});

	btnChatList.addEventListener('click', function() {
		employeeList.style.display = 'none';
		chatList.style.display = 'block';
	});
});