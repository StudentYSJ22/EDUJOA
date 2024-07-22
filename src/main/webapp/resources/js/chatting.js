$(document).ready(function() {
	getMyChatRooms();
});
function escapeHTML(str) {
    return str.replace(/[&<>'"]/g, 
        tag => ({
            '&': '&amp;',
            '<': '&lt;',
            '>': '&gt;',
            "'": '&#39;',
            '"': '&quot;'
        }[tag] || tag)
    );
};
/**
* 채팅서버 기능
*/
//ws:http
//wss:https
const server = new WebSocket("ws://localhost:9090/chattest");
//servlet-context에서 설정한 mapping주소로 맨뒤에 서버주소 설정함.
let reconnectInterval = 5000;

function getMyChatRooms() {
	$.ajax({
		type: "GET",
		url: "/chatting/getMyChatRooms", 
		data: { empId: loginId },
		dataType: "json",
		success: function(chatrooms) {
			var html = '';
			if (chatrooms && chatrooms.length > 0) {
				chatrooms.forEach(function(room) {
					// 직급 분기 처리
					var position = "";
					switch (room.empTitle) {
						case "J1":
							position = "원장";
							break;
						case "J2":
							position = "팀장";
							break;
						case "J3":
							position = "매니저";
							break;
						default:
							position = "직원";
					}
					html += `
						  <div class="chat-room-item" 
						       data-room-id="${room.roomId}" 
						       data-room-emp-id="${room.empId}">
						    <img src="${path}/resources/upload/${room.empProfile}" 
						         style="width: 30px; height: 30px; border-radius: 100%;">
						    ${room.empName} ${position}<br>
						  </div>
						`;

				});
			} else {
				html = '<p>채팅방이 없습니다.</p>';
			}
			$('#chatRoomList').html(html);

			$('.chat-room-item').on('click', function() {
                const roomId = $(this).data('room-id');
                const receiver = $(this).data('room-emp-id');
                getChatRecords(roomId, receiver);
            });
        },
		error: function(xhr, status, error) {
			console.error("채팅방 목록을 불러오는데 실패했습니다:", error);
			$('#chatRoomList').html('<p>채팅방 목록을 불러오는데 실패했습니다.</p>');
		}
	});
}
$(document).ready(function() {
	// 직원 클릭 이벤트
	$('.employee-item').on('click', function() {
		var empName = $(this).data('emp-name');
		$('#namestorage').html(empName);
		var empTitle = $(this).data('emp-title');
		$('#titlestorage').html(empTitle);
		var empId = $(this).data('emp-id');
		var empProfile = $(this).find('img').attr('src');
		$('#profilestorage').html(empProfile);
		var empEmail = $(this).data('emp-email');

		// 직급 한글화
		var koreanTitle;
		switch (empTitle) {
			case 'J1': koreanTitle = '원장'; break;
			case 'J2': koreanTitle = '팀장'; break;
			case 'J3': koreanTitle = '매니저'; break;
			default: koreanTitle = empTitle;
		}

		// 직원 정보 표시
		var infoHtml = `
     		<p><strong>이름:</strong> ${empName}</p>
            <p><strong>직급:</strong> ${koreanTitle}</p>
            <p><strong>직원 ID:</strong> ${empId}</p>
            <p><strong>이메일:</strong> ${empEmail}</p>
            <img src="${empProfile}" alt="${empName}" style="width:100px; height:100px; border-radius:50%;">
        `;

		$('#employeeInfo').html(infoHtml);
		$('#employeeModal').show();
	});

	// 모달 닫기 버튼 이벤트
	$('#closeModal').on('click', function() {
		$('#employeeModal').hide();
	});

	// 1:1 대화하기 버튼 이벤트
	// 화면 clear랑 채팅 옆에 사람정보 추가
	$('#chatButton').on('click', function() {
		var receiverI = $('#employeeInfo').find('p:nth-child(3)').text().split(': ')[1];
		console.log('1:1 대화하기 버튼 클릭, empId:', receiverI);
		getMyChatRecords(receiverI);
		$('#employeeModal').hide();
		$('.chat-window').show();
	});

	// 모달 외부 클릭 시 닫기
	$(window).on('click', function(event) {
		if (event.target == $('#employeeModal')[0]) {
			$('#employeeModal').hide();
		}
	});
});

// 채팅 내역 가져오기
function getMyChatRecords(roomId, receiver) {
	$.ajax({
		type: "GET",
		url: "/chatting/getMyChatRecords",
		contentType: "application/json",
		data: JSON.stringify({
			roomId: roomId,
			sender: loginId,
			receiver: receiver
		}),
		dataType: "json",
		success: function(chatHistory) {
			clearChatWindow();
			if (chatHistory === null) {
                // 채팅 내역이 없는 경우 새로운 채팅방 추가
                addNewChatRoom(empId);
            } else {
                displayChatHistory(chatHistory, empId);
            }
        },
		error: function(xhr, status, error) {
			console.error("채팅 내역을 불러오는데 실패했습니다:", error);
		}
	});
}
// 채팅 내역 표시
function displayChatHistory(chatHistory, empId) {
	$('#receiver-info').text('대화 상대: ' + empId);
	var chatContent = $('#chattingcontent');
	chatContent.empty();

	if (chatHistory && chatHistory.length > 0) {
		chatHistory.forEach(function(record) {
			var messageHtml = createMessageHtml(record);
			chatContent.append(messageHtml);
		});
	} else {
		chatContent.append('<p>대화 내역이 없습니다.</p>');
	}

	chatContent.scrollTop(chatContent[0].scrollHeight);
}
// 메시지 HTML 생성
function createMessageHtml(message) {
    var messageClass = message.empId === loginId ? 'sent' : 'received';
    return `
        <div class="message-container ${messageClass}">
            <div class="message">
                <div class="message-content">${message.content}</div>
                <div class="message-time">${formatDateTime(message.chatTime)}</div>
            </div>
        </div>
    `;
}
// 타임스탬프 형식 지정
function formatDateTime(timestamp) {
    const date = new Date(timestamp);
    const hours = date.getHours();
    const minutes = date.getMinutes();
    const ampm = hours >= 12 ? '오후' : '오전';
    const formattedHours = hours % 12 || 12;
    const formattedMinutes = minutes < 10 ? '0' + minutes : minutes;
    return `${ampm} ${formattedHours}:${formattedMinutes}`;
}

server.onopen = () => { //서버 열렸을 때 실행
	console.log("서버 열림");
	/*console.log(employeeName);*/
	const msg = new Message("open", loginId, loginName, "", "", "", new Date().toISOString());
	console.log(msg);
	server.send(msg.convert());
	//여기서 웹소켓 열렸을 때는 서버에 메세지는 전달 되지만 db에는 저장안해도 됨
	//아직 receiver를 지정하지 않았기 때문에.
}
server.onmessage = response => { //메세지 수신했을 때 실행
	const receiveMsg = Message.deconvert(response.data); //서버로부터 받은 데이터 json방식으로 변환
	switch (receiveMsg.chatType) {
		//이벤트 종류에 따라서 switch문으로 분기처리
		//case "open" : alertMessage(receiveMsg); break; //새로운 손놈 왔을 때
		case "send": messagePrint(receiveMsg); break; //메세지가 왔을 때, 채팅방 생성
		case "load": messagePrint(receiveMsg); break; //참가자가 새로 왔을 때
		case "close": alertMessage(receiveMsg); break; //참가자가 나갔을 때
		case "make": break;
	}
};

server.onclose = event => { // 연결이 닫혔을 때 실행
	console.log("서버 연결 종료", event);
	console.log(`서버 연결 재시도 중... ${reconnectInterval / 1000}초 후 재연결 시도`);
	setTimeout(connectWebSocket, reconnectInterval); // 일정 시간 후에 다시 연결 시도
};

const closeMessage = () => {
	server.close(1000, "서버와의 연결이 종료되었습니다.");
	window.close();
}

//javascript 사용해서 구현
const sendMessage = () => {
	//전송버튼 눌렀을 때 수신자, 발신자 정보 가지고 서버에 정보 보내서 db에 저장
	const inputData = document.querySelector("#msg").value;
	const timeData = document.getElementById("chatTime").value;
	let receiver = document.getElementById("idstorage").textContent;
	let receiverName = document.getElementById("namestorage").textContent;
	chatTime = new Date().toISOString().slice(0, -11);

	if (inputData.length > 0) {
		const msgObj = new Message("send", loginId, loginName, receiver, receiverName, inputData, chatTime).convert();
		//                        	타입 ,   발신자,   수신자,    방이름,   메세지내용
		server.send(msgObj);
		console.log(msgObj);
	} else {
		alert("메세지를 입력하세요");
		document.querySelector("#msg").focus();
	}
};

const messagePrint = msg => {
	console.log(msg);
	const $div = document.createElement("div");
	const $profile = document.createElement("img");
	$profile.setAttribute("src", `${path}/resources/upload/IU.jpeg`); //db정보 넣어야함
	$profile.style.width = "50px";
	$profile.style.height = "50px";
	$profile.style.borderRadius = "100px";
	$div.appendChild($profile);
	const $sender = document.createElement("sup");
	$sender.innerText = msg.sender;
	$div.appendChild($sender);
	//메세지 출력태그
	const $content = document.createElement("span");
	$content.innerText = msg.chatContent;
	$div.appendChild($content);



	//메세지컨테이너 디자인하기
	$div.classList.add("msgcontainer");
	if (msg.loginId === loginId) {
		$div.classList.add("right");
	} else {
		$div.classList.add("left");
	}
	document.querySelector("#chattingcontent").appendChild($div);
}
class Message {
	constructor(chatType = "", sender = "", senderName = "", receiver = "", roomName = "", chatContent = "", chatTime = "") {
		this.chatType = chatType;
		this.sender = sender; //발신자ID
		this.senderName = senderName;//발신자이름
		this.receiver = receiver; //수신자ID
		this.roomName = roomName; //수신자이름
		this.chatContent = chatContent;
		this.chatTime = chatTime;
	}
	convert() {
		return JSON.stringify(this);
	}
	static deconvert(data) { //static으로 써서 객체생성 필요 없이 씀
		return JSON.parse(data);
	}

}
function clearChatWindow() {
    $('#chattingcontent').empty();
}

