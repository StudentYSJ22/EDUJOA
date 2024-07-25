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
    server = new WebSocket("ws://localhost:9090/chattest");

    server.onopen = () => {
        console.log("서버 연결됨");
        isConnected = true;
        const msg = new Message("open", "", loginId, "", "", new Date().toISOString());
        server.send(msg.convert());
    };

    server.onmessage = response => {
        const receiveMsg = Message.deconvert(response.data); // 서버로부터 받은 데이터 JSON 방식으로 변환
        switch (receiveMsg.chatType) {
            case "send":
                handleIncomingMessage(receiveMsg);
                break;
            // 추가 이벤트 처리 필요 시 여기에 추가
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

const $chatRoomList = $('#chatRoomList');
const $chattingcontent = $('#chattingcontent');
const $roomId = $('#room-id');
const $receiverName = $('#receiver-name');
const $receiverId = $('#receiver-id');


$(document).ready(function() {
    connectWebSocket();
    getMyChatRooms();
	
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
            <p><strong>이름:</strong> ${empName}</p>
            <p><strong>직급:</strong> ${koreanTitle}</p>
            <p><strong>직원 ID:</strong> ${empId}</p>
            <p><strong>이메일:</strong> ${empEmail}</p>
            <img src="${empProfile}" alt="${empName}" style="width:100px; height:100px; border-radius:50%;">
            <input id="targetId" type="hidden" value="${empId}"/>
            <input id="targetName" type="hidden" value="${empName}"/>
            <input id="targetTitle" type="hidden" value="${koreanTitle}"/>
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
        
        $('#receiver-id').text(targetId);
        $('#receiver-name').text(`${targetName} (${targetTitle})`);

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
        url: "/chatting/createChatRoom",
        contentType: "application/json",
        data: JSON.stringify({
            sender: loginId,
            receiver: targetId
        }),
        dataType: "json",
        success: function(response) {
            clearChatWindow();
            console.log("Raw response:", response);
            let responseArray = Array.isArray(response) ? response : [response];

            if (responseArray.length > 0) {
                const roomId = responseArray[0].roomId;

                if (!isChatRoomExist(roomId)) {
                    addChatRoomToList(roomId, targetId, empName, empTitle, empProfile);
                }
                responseArray.forEach(createMessageHtml);
                getMyChatRooms();
            } else {
				$("#chattingcontent").html("대화내용이 없습니다. 메시지를 입력하세요.");
            }
        },
        error: handleAjaxError
    });
}

function isChatRoomExist(roomId) {
    return $chatRoomList.find(`[data-room-id="${roomId}"]`).length > 0;
}

function addChatRoomToList(roomId, empId, empName, empTitle, empProfile) {
    const position = getKoreanTitle(empTitle);
    const newChatRoomHtml = `
        <div class="chat-room-item" 
             data-room-id="${roomId}" 
             data-room-emp-id="${empId}">
            <img src="${path}/resources/upload/${empProfile}" 
                 style="width: 30px; height: 30px; border-radius: 100%;">
            ${escapeHTML(empName)} ${position}<br>
        </div>
    `;

    $chatRoomList.prepend(newChatRoomHtml);
    $chatRoomList.find('p:contains("채팅방이 없습니다.")').remove();
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
    return String(str).replace(/[&<>"'`=\/]/g, function (s) {
        return entityMap[s];
    });
}

function getMyChatRooms() {
    $.ajax({
        type: "GET",
        url: "/chatting/getMyChatRooms",
        data: { empId: loginId },
        dataType: "json",
        success: function(chatrooms) {
            let html = '';
            if (chatrooms && chatrooms.length > 0) {
                html = chatrooms.map(room => `
                    <div class="chat-room-item" 
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
            $chatRoomList.html(html);
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
        $receiverName.text(empName+" "+getKoreanTitle(empTitle));
        
        // 새 메시지 배지 제거
        $(this).removeClass('new-message');
        $(this).find('.new-message-badge').remove();
        
        getMyChatRecords(roomId, empId);
    });
}

function getMyChatRecords(roomId, empId) {
    $.ajax({
        type: "POST",
        url: "/chatting/getMyChatRecords",
        contentType: "application/json",
        data: JSON.stringify({
            roomId: roomId,
            receiver: empId
        }),
        dataType: "json",
        success: function(chatHistory) {
            clearChatWindow();
            if (chatHistory === null || chatHistory.length === 0) {
                $chattingcontent.append('<p>대화 내역이 없습니다. 메세지를 입력하세요.</p>');
            } else {
                displayChatHistory(chatHistory, empId);
            }
            $chattingcontent.scrollTop($chattingcontent[0].scrollHeight);
        },
        error: handleAjaxError
    });
}

// 채팅 기록을 표시하는 함수
function displayChatHistory(content, empId) {
    $chattingcontent.empty();

    if (content && content.length > 0) {
        let lastDate = null;
        content.forEach(function(cont) {
            const messageDate = new Date(cont.chatTime);
            const kstDate = new Date(messageDate.getTime() + (9 * 60 * 60 * 1000)); // KST로 변환

            if (!lastDate || !isSameDay(lastDate, kstDate)) {
                const dateString = formatDate(kstDate);
                $chattingcontent.append(`<div class="date-divider" data-date="${kstDate.toISOString()}">---------${dateString}--------</div>`);
                lastDate = kstDate;
            }
            createMessageHtml(cont);
        });
    } else {
        $chattingcontent.append('<p>대화 내역이 없습니다. 메세지를 입력하세요.</p>');
    }

    $chattingcontent.scrollTop($chattingcontent[0].scrollHeight);
}

// 메시지 HTML을 생성하는 함수
function createMessageHtml(message) {
    const messageClass = message.empId === loginId ? 'sent' : 'received';
    const messageAlignment = message.empId === loginId ? 'right' : 'left';
    const msg = `
        <div class="message-container ${messageClass}" style="text-align: ${messageAlignment};">
            <div class="message">
                <div class="message-content">${escapeHTML(message.content)}</div>
                <div class="message-time" data-time="${message.chatTime}">${formatDateTime(message.chatTime)}</div>
            </div>
        </div>
    `;
    $chattingcontent.append(msg);
}

// 타임스탬프를 KST로 변환하여 포맷팅하는 함수
function formatDateTime(timestamp) {
    const date = new Date(timestamp);
    const koreaTime = new Date(date.getTime() + (9 * 60 * 60 * 1000)); // UTC to KST (UTC+9)
    return koreaTime.toLocaleTimeString('ko-KR', { hour: '2-digit', minute: '2-digit' });
}

// 날짜를 포맷팅하는 함수
function formatDate(date) {
    const days = ['일', '월', '화', '수', '목', '금', '토'];
    return `${date.getMonth() + 1}월 ${date.getDate()}일 ${days[date.getDay()]}요일`;
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
        if (message.roomId === currentRoomId) {
            appendMessageWithDateCheck(message);
        } else {
            updateChatRoomList(message.roomId);
        }
    }
}

// 메시지를 추가하는 함수 (날짜 구분선 추가 여부 체크)
function appendMessageWithDateCheck(message) {
    const newMessageDate = new Date(message.chatTime);
    const kstDate = new Date(newMessageDate.getTime() + (9 * 60 * 60 * 1000)); // KST로 변환

    const lastDivider = $chattingcontent.find('.date-divider').last();
    const lastDate = lastDivider.length > 0 ? new Date(lastDivider.data('date')) : null;

    if (!lastDate || !isSameDay(lastDate, kstDate)) {
        const dateString = formatDate(kstDate);
        $chattingcontent.append(`<div class="date-divider" data-date="${kstDate.toISOString()}">---------${dateString}--------</div>`);
    }

    createMessageHtml({
        empId: message.sender,
        content: message.chatContent,
        chatTime: message.chatTime
    });
    $chattingcontent.scrollTop($chattingcontent[0].scrollHeight);
}

// 날짜 구분선 추가 여부를 결정하는 함수
function shouldAddDateDivider(newMessageDate) {
    const lastDivider = $chattingcontent.find('.date-divider').last();
    if (lastDivider.length === 0) {
        return true; // 첫 번째 메시지이므로 구분선 추가
    }

    const lastDate = new Date(lastDivider.data('date'));
    return !isSameDay(lastDate, newMessageDate);
}

function updateChatRoomList(roomId) {
    const chatRoomItem = $(`.chat-room-item[data-room-id="${roomId}"]`);
    if (chatRoomItem.length > 0) {
        chatRoomItem.addClass('new-message');
        let badge = chatRoomItem.find('.new-message-badge');
        if (badge.length === 0) {
            chatRoomItem.append('<span class="new-message-badge">새로운 메시지 (1)개가 있습니다.</span>');
        } else {
            let count = parseInt(badge.text().match(/\d+/)[0]) + 1;
            badge.text(`새로운 메시지 (${count})개가 있습니다.`);
        }
    }
}

const sendMessage = () => {
    if (!isConnected) {
        alert("서버와의 연결이 끊어졌습니다. 페이지를 새로고침해 주세요.");
        return;
    }

    const inputData = $("#msg").val();
    const receiverId = $("#receiver-id").text(); // 여기서 receiverId를 제대로 가져옴
    const selectedRoomId = $roomId.text();
    const chatTime = new Date().toISOString();

    if (inputData.length > 0) {
        const msgObj = new Message("send", selectedRoomId, loginId, receiverId, inputData, chatTime);
        server.send(msgObj.convert());
        console.log("메시지 전송:", msgObj);
        $('#msg').val('');
    } else {
        alert("메세지를 입력하세요");
        $("#msg").focus();
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
