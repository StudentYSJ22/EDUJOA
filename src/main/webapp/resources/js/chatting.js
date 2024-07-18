
	/**
 * 채팅서버 기능
 */
//ws:http
//wss:https
const server=new WebSocket("ws://localhost:9090/chattest");
//servlet-context에서 설정한 mapping주소로 맨뒤에 서버주소 설정함.
let reconnectInterval = 5000;

/*$(document).ready(function() {
	$.ajax({
		url:"/chatting/chatresult.do",
		type:"GET",
		data:"text",
		success:(data =>{
			console.log(data);
			data.forEach(e=>{
				console.log(e.empName);
			})
		})
	});
})*/
class Message{
	constructor(chatType="",sender="",senderName="",receiver="",roomName="",chatContent="",chatTime=""){
		this.chatType=chatType;
		this.sender=sender; //발신자ID
		this.senderName=senderName;//발신자이름
		this.receiver=receiver; //수신자ID
		this.roomName=roomName; //수신자이름
		this.chatContent=chatContent;
		this.chatTime=chatTime;
	}
	convert(){
		return JSON.stringify(this);
	}
	static deconvert(data){ //static으로 써서 객체생성 필요 없이 씀
		return JSON.parse(data);
	}
	
}
server.onopen=()=>{ //서버 열렸을 때 실행
	console.log("서버 열림");
	/*console.log(employeeName);*/
	const msg=new Message("open",loginId,loginName,"","","",chatTime);
	console.log(msg);
	server.send(msg.convert());
	//여기서 웹소켓 열렸을 때는 서버에 메세지는 전달 되지만 db에는 저장안해도 됨
	//아직 receiver를 지정하지 않았기 때문에.
}
server.onmessage=response=>{ //메세지 수신했을 때 실행
	const receiveMsg=Message.deconvert(response.data); //서버로부터 받은 데이터 json방식으로 변환
	switch(receiveMsg.chatType){
		//이벤트 종류에 따라서 switch문으로 분기처리
		//case "open" : alertMessage(receiveMsg); break; //새로운 손놈 왔을 때
		case "send" : messagePrint(receiveMsg); break; //메세지가 왔을 때, 채팅방 생성
		case "load" : messagePrint(receiveMsg); break; //참가자가 새로 왔을 때
		case "close" : alertMessage(receiveMsg); break; //참가자가 나갔을 때
		case "make" : break;
	}
};

server.onclose = (event) => { // 연결이 닫혔을 때 실행
    console.log("서버 연결 종료", event);
	console.log(`서버 연결 재시도 중... ${reconnectInterval / 1000}초 후 재연결 시도`);
    setTimeout(connectWebSocket, reconnectInterval); // 일정 시간 후에 다시 연결 시도
};

const closeMessage=()=>{
	server.close(1000,"서버와의 연결이 종료되었습니다.");
	window.close();
}
//직원 목록 더블클릭 했을 때
$(document).on("dblclick", ".employee-item", function() {
    let empId = $(this).data("emp-id");
    let empName = $(this).data("emp-name");
    let empTitle = $(this).data("emp-title");
    $("#idstorage").html(empId);
    $("#namestorage").html(empName);
	$("#receiver-info").html(empName);
	if(empTitle==='J1'){
		empTitle=' 원장';
	}else if(empTitle==='J2'){
		empTitle=' 팀장';
	}else if(empTitle==='J3'){
		empTitle=' 매니저';
	}
	$("#receiver-info").append(empTitle);
	//직원목록 더블클릭 했으니 방 있는지 없는지 체크 필요함.
	const msgObj=new Message("check",loginId,loginName,empId,empName,"",chatTime);
	console.log(msgObj);
	server.send(msgObj.convert());
	
});
//채팅방목록 더블클릭 했을 때
$(document).on("dbclick",".chat-room-item", function(){
	let empId = $(this).data("receiver-id");
	$("#idstorage").html(empId);
	const msgObj=new Message("load",loginId,loginName,empId,"","","").convert();
	server.send(msgObj.convert());
	//이미 채팅방이 있는 상태이기 때문에 db에 저장된 데이터 가져와서 출력해야함
});
//javascript 사용해서 구현
const sendMessage=()=>{
	//전송버튼 눌렀을 때 수신자, 발신자 정보 가지고 서버에 정보 보내서 db에 저장
	const inputData=document.querySelector("#msg").value;
	const timeData=document.getElementById("chatTime").value;
	let receiver=document.getElementById("idstorage").textContent;
	let receiverName=document.getElementById("namestorage").textContent;
	chatTime = new Date().toISOString().slice(0, -8);
	
	if(inputData.length>0){
		const msgObj=new Message("send",loginId,loginName,receiver,receiverName,inputData,chatTime).convert();
		//                        타입 ,   발신자,   수신자,    방이름,   메세지내용
		server.send(msgObj);
		console.log(msgObj);
	}else{
		alert("메세지를 입력하세요");
		document.querySelector("#msg").focus();
	}
};

/*const addAttend=(msg)=>{
	console.log(msg);
	const clients=JSON.parse(msg.chatContent);
	const $attendContainer=document.querySelector("#attendContainer");
	//$attendContainer.innerHTML="";
	const $ul=document.createElement("ul");
	$ul.classList.add("listcontainer");
	clients.map(e=>{
		const $li=document.createElement("li");
		//$li.innerText=e;
		$li.classList.add("listfont");
		return $li;
	}).forEach(e=>{
		//$ul.appendChild(e);
	});
	$attendContainer.appendChild($ul);
}*/
const messagePrint=(msg)=>{
	const $div=document.createElement("div");
	const $profile=document.createElement("img");
	$profile.setAttribute("src",`${path}/resources/upload/IU.jpeg`);
	$profile.style.width="50px";
	$profile.style.height="50px";
	$profile.style.borderRadius="100px";
	$div.appendChild($profile);
	const $sender=document.createElement("sup");
	$sender.innerText=msg.sender;
	//메세지 출력태그
	const $content=document.createElement("span");
	$content.innerText=msg.deconvert(chatContent);
	
	$div.appendChild($sender);
	$div.appendChild($content);
	
	//메세지컨테이너 디자인하기
	$div.classList.add("msgcontainer");
	if(msg.loginId==loginId){
		$div.classList.add("right");
	}else{
		$div.classList.add("left");
	}
	document.querySelector("#chattingcontent").appendChild($div);
}

//여기는 jquery사용해서 구현
/*const alertMessage=(msg)=>{
	const $container=$("<div>").addClass("alertContainer");
	const status=msg.chatType=="open"?"접속":"퇴장";
	const room=msg.room;
	const $content=$("<h4>").text(`${msg.sender}님이 ${status}하셨습니다.`);
	$container.append($content);
	$("#chattingcontent").append($container);
	$("#roomName").append(room);
}*/