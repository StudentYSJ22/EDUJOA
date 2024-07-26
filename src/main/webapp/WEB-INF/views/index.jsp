<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<c:set var="loginMember"
	value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}" />
<link rel="stylesheet" href="${path }/resources/css/khj/index_hj.css">
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<script src="${path }/resources/js/jquery-3.7.1.min.js"></script>

<!-- FullCalendar CSS -->
<link href='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.14/main.min.css' rel='stylesheet' />
<!-- FullCalendar JS -->
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.14/index.global.min.js'></script>



<c:set var="atnIn"
	value="${attendance!=null&&attendance.atnIn!=null?attendance.atnIn:''}" />
<c:set var="atnOut"
	value="${attendance!=null&&attendance.atnOut!=null?attendance.atnOut:'' }" />



  <style>
    #calendar {
      max-width: 900px;
      margin: 0 auto;
    }
  </style>


<!-- <body>안내용만. 모든 내용은 밑에있는 div안에만 설정해야함. -->
<div class="main">
	<div class="first-container">
		<div class="first">
			<div class="mini-container">
				<div class="mini-first-container">
					<div class="emp-info-container">
						<div class="emp-pic"
							style="background-image: url(${pageContext.request.contextPath}/resources/images/bs.png");></div>
						<div class="emp-info"
							style="color: rgb(145, 145, 145); font-size: 15px;">${loginMember.empId}</div>
						<div class="emp-info"
							style="color: rgb(145, 145, 145); font-size: 12px;">${loginMember.empTitle}</div>
						<div class="emp-info2" style="font-size: 20px; color: black;">${loginMember.empName}</div>
						<div class="emp-name" style="">${loginMember.empId}</div>
					</div>
				</div>
				
				
				


				<div class="mini-second-container">
					<div class="attendance-title"
						style="font-size: 20px; color: black;">
						<b>근태관리</b>
					</div>
					<div class="attendance-systime" style="color: #7f7f7f;"
						id="sysdate"></div>
					<div class="time-office-container">
						<div class="time">
							<div class="time-title">출근시간</div>
							<div class="time-info" id="inputTime">
								<b><fmt:formatDate type="time"
										value="${attendance!=null&&attendance.atnIn!=null?attendance.atnInConvertToDate():''}" /></b>
							</div>
						</div>
						<div class="time">
							<div class="time-title">퇴근시간</div>
							<div class="time-info" id="outputTime">
								<b><fmt:formatDate type="time"
										value="${attendance!=null&&attendance.atnOut!=null?attendance.atnOutConvertToDate():'' }" /></b>
							</div>
						</div>
					</div>
					<!-- 출근 폼 -->
					<form id="inputForm" action="/submitInputTime" method="post">
						<input type="hidden" name="inputTime" id="inputTimeField">
						<input type="hidden" name="empId" value='${loginMember.empId}'>
						<!-- empId를 동적으로 설정할 숨겨진 입력 필드 -->
						<div class="time-btn-container">
							<div>
								<button type="button"
									class="attn-btn ${attendance!=null&&attendance.atnIn!=null?'disabled':'' }"
									id="input"
									${attendance!=null&&attendance.atnIn!=null?"disabled":"" }>
									<b>출근하기</b>
								</button>
							</div>
						</div>
					</form>
					<!-- 퇴근 폼 -->
					<form id="outputForm" action="/submitOutputTime" method="post">
						<input type="hidden" name="outputTime" id="outputTimeField">
						<input type="hidden" name="empId" id="empIdFieldOutput"
							value='${loginMember.empId}'>
						<!-- 퇴근 폼용 empId 필드 -->
						<div class="time-btn-container">
							<div>
								<button type="button"
									class="attn-btn ${attendance!=null&&attendance.atnOut!=null?'disabled':''}"
									id="output"
									${attendance!=null&&attendance.atnOut!=null?"disabled":""}>
									<b>퇴근하기</b>
								</button>
							</div>
						</div>
					</form>
					<div class="time-btn-go">
						<button type="button" class="attn-btn-go">
							<b>+ 근태관리 바로가기</b>
						</button>
					</div>
				</div>
			</div>
		</div>








		<div class="second">
			<div class="mini2-container">
				<div class="mini2-first-container">
					<div class="five-container">
						<a href="test.html"><div class="circle-su";>5</div></a> <a
							href="test.html"><div class="circle"
								style="background-image: url(${pageContext.request.contextPath}/resources/images/mail-icon.png");></div></a>
						<div class="circle-info">우편</div>
					</div>
					<div class="five-container">
						<a href="test.html"><div class="circle-su";>${approvalCount }</div></a>
						<a href="test.html"><div class="circle"
								style="background-image: url(${pageContext.request.contextPath}/resources/images/approval-icon.png");></div></a>
						<div class="circle-info">결재</div>
					</div>
					<div class="five-container">
						<a href="test.html"><div class="circle-su"
								style="background-color: gray";>0</div></a> <a href="test.html"><div
								class="circle"
								style="background-image: url(${pageContext.request.contextPath}/resources/images/docu-icon.png");></div></a>
						<div class="circle-info">문서</div>
					</div>
					<div class="five-container">
						<a href="test.html"><div class="circle-su";>102</div></a> <a
							href="test.html"><div class="circle"
								style="background-image: url(${pageContext.request.contextPath}/resources/images/calendar-icon.png");></div></a>
						<div class="circle-info">일정</div>
					</div>
					<div class="five-container">
						<a href="test.html"><div class="circle-su";>51</div></a> <a
							href="test.html"><div class="circle"
								style="background-image: url(${pageContext.request.contextPath}/resources/images/chat-icon.png");></div></a>
						<div class="circle-info">메신저</div>
					</div>
				</div>
				<div class="mini2-second-container">
					<div class="todo-title">
						<b>${loginMember.empName} 님의 Monthly Schedule</b>
					</div>
					<div class="todo-info-container">
						<!-- <div class="todo-info"> -->
						
						
						<!-- 선정캘린더 -->
							
								<!-- <div id='calendar-container'> -->
									<div id="calendar" class="mainCalendar"></div>
								<!-- </div> -->
							
												<!-- </div> --> 
						<div class="todo-info"></div>
						
						
					</div>
				</div>
			</div>


		</div>
	</div>
	<div class="third">
		<div class="mini3-container">
			<div class="mini3-first-container">
				<div style="margin: 25px; color: black;">
					<b>내 전자결재 현황</b>
				</div>
				<div class="myaprv-container">
					<div class="myaprv">문서종류</div>
					<div class="myaprv">제목</div>
					<div class="myaprv">상태</div>
					<div class="myaprv">기안일</div>
				</div>
				<div class="aprv-mail">
					<ul>
						<li>지출결의서</li>
						<li>2</li>
						<li>3</li>
						<li>4</li>
						<li>5</li>
						<li>6</li>
					</ul>
					<ul>
						<li>[강사] 비품요청 상신의 건</li>
						<li>2</li>
						<li>3</li>
						<li>4</li>
						<li>5</li>
						<li>6</li>
					</ul>
					<ul>
						<li>결재중</li>
						<li>2</li>
						<li>3</li>
						<li>4</li>
						<li>5</li>
						<li>6</li>
					</ul>
					<ul>
						<li>2024.02.13(월)</li>
						<li>2</li>
						<li>3</li>
						<li>4</li>
						<li>5</li>
						<li>6</li>
					</ul>
				</div>
			</div>
			<div class="mini3-second-container">
				<div style="margin: 25px; color: black;">
					<b>받은메일함</b>
				</div>
				<div class="mymailtitle-container">
					<div class="mymail-container">
						<div class="mymail">제 목</div>
						<div class="mymail">송신인</div>
						<div class="mymail">수신일</div>
					</div>
				</div>
				<div class="aprv-mail">
					<ul>
						<li>[공지] 전사 2024 하반기 워크샵 안내</li>
						<li>2</li>
						<li>3</li>
						<li>4</li>
						<li>5</li>
						<li>6</li>
					</ul>
					<ul>
						<li>빼빼로 매니저</li>
						<li>2</li>
						<li>3</li>
						<li>4</li>
						<li>5</li>
						<li>6</li>
					</ul>
					<ul>
						<li>2024.08.07(수)</li>
						<li>2</li>
						<li>3</li>
						<li>4</li>
						<li>5</li>
						<li>6</li>
					</ul>


				</div>
			</div>
		</div>
	</div>
</div>
</div>


<script>
	const empId = `${loginMember.empId}`;
	console.log(empId);
</script>


 <script>
    document.addEventListener('DOMContentLoaded', function() {
      var calendarEl = document.getElementById('calendar');
      
      // FullCalendar 인스턴스 생성
      var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth'
      });

      // 달력 렌더링
      calendar.render();
    });
  </script>





<script src="${path }/resources/js/khj/attendance.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
