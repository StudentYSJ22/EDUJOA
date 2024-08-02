<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<c:set var="loginMember"
	value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}" />
<link rel="stylesheet" href="${path }/resources/css/khj/index_hj.css">
<link rel="stylesheet" href="${path} /resources/common/assets/vendor/fonts/boxicons.css" />
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<script src="${path }/resources/js/jquery-3.7.1.min.js"></script>

<!-- FullCalendar CSS -->
<link
	href='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.14/main.min.css'
	rel='stylesheet' />
<!-- FullCalendar JS -->
<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.14/index.global.min.js'></script>



<c:set var="atnIn"
	value="${attendance!=null&&attendance.atnIn!=null?attendance.atnIn:''}" />
<c:set var="atnOut"
	value="${attendance!=null&&attendance.atnOut!=null?attendance.atnOut:'' }" />



<style>
#calendar {
	max-width: 900px;
/* 	margin: 0 auto; */
	margin: 15px 15px;
}

/* FullCalendar 버튼 스타일 */
.fc .fc-button-primary {
	background-color: #46b25b;
	border-color: #46b25b;
}

.fc .fc-button-primary:hover, .fc .fc-button-primary:focus {
	background-color: darkgreen;
	border-color: darkgreen;
}

/* 활성화된 버튼 스타일 (예: month 버튼이 클릭된 상태) */
.fc .fc-button-primary:not(:disabled).fc-button-active, .fc .fc-button-primary:not(:disabled):active
	{
	background-color: darkgreen !important;
	border-color: darkgreen !important;
}

.fc .fc-button-primary:focus {
	/* box-shadow: 0 0 0 0.1rem rgba(0, 100, 0, 0.5); */
	box-shadow: 0 0 0 0.1rem #46b25b !important;
}
</style>


<!-- <body>안내용만. 모든 내용은 밑에있는 div안에만 설정해야함. -->
<div class="main">
	<div class="first-container">
		<div class="first">
			<div class="mini-container">
				<div class="mini-first-container">
					<div class="emp-info-container">
						<div class="emp-pic-container">
							<div class="emp-pic"
								style="background-image: <%-- url(${pageContext.request.contextPath}/resources/images/bs.png");> --%>
							<%-- url(${path}/resources/upload/chs/employee/"+emp.empProfile);>	 --%>
							url(${pageContext.request.contextPath}/resources/upload/chs/employee/${loginMember.empProfile}">
							</div>
						</div>
						<div class="emp-info"
							style="color: rgb(145, 145, 145); font-size: 15px;">에듀조아</div>
						<%-- <div class="emp-info"
							style="color: rgb(145, 145, 145); font-size: 12px;">${loginMember.empTitle}</div> --%>


						<div class="emp-info"
							style="color: rgb(145, 145, 145); font-size: 12px;">
							<c:choose>
								<c:when test="${loginMember.empTitle eq 'J1'}">
            원장
        </c:when>
								<c:when test="${loginMember.empTitle eq 'J2'}">
            팀장
        </c:when>
								<c:when test="${loginMember.empTitle eq 'J3'}">
            매니저
        </c:when>
								<c:otherwise>
            ${loginMember.empTitle}
        </c:otherwise>
							</c:choose>
						</div>



						<div class="emp-info2">${loginMember.empName}</div>

					</div>
				</div>





				<div class="mini-second-container">
					<div class="attendance-title"
						style="font-size: 20px; color: black;">
						<b style="color: #384551;">근태관리</b>
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
						<input type="hidden" name="empId" id="empIdField"
							value='${loginMember.empId}'>
						<!-- empId를 동적으로 설정할 숨겨진 입력 필드 -->
						<div class="btn-container">
							<div class="time-btn-container">
								<div>
									<button type="button"
										class="attn-btn ${attendance!=null&&attendance.atnIn!=null?'disabled':'' }"
										id="input"
										${attendance!=null&&attendance.atnIn!=null?"disabled":"" }>
										<b><i class='bx bx-time'></i> 출 근</b>
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
									<b><i class='bx bx-time'></i> 퇴 근</b>
								</button>
							</div>
						</div>
				</div>
				</form>
				<div class="time-btn-go">
					<a
						href="${pageContext.request.contextPath}/attendance/attendance.do?empId=${loginMember.empId}">
						<div class="attn-go">+ 근태관리 바로가기</div>
						</button>
				</div>
			</div>
		</div>
	</div>








	<div class="second">
		<div class="mini2-container">
			<div class="hj">
				<div class="mini2-first-container">
					<div class="four-container">
						<a href="${pageContext.request.contextPath}/mailbox">
							<div class="circle-su";>${mailCount }</div>
						</a> <a href="${pageContext.request.contextPath}/mailbox"><div
								class="circle"
								style="background-image: url(${pageContext.request.contextPath}/resources/images/mail-i.png");></div></a>
						<div class="circle-info"></div>
					</div>
					<div class="four-container">
						<a
							href="${pageContext.request.contextPath}/approval/flagginging.do?empId=${loginMember.empId}">
							<div class="circle-su";>${approvalCount }</div>
						</a> <a
							href="${pageContext.request.contextPath}/approval/flagginging.do?empId=${loginMember.empId}">
							<div class="circle"
								style="background-image: url(${pageContext.request.contextPath}/resources/images/aprv-i.png");></div>
						</a>
						<div class="circle-info"></div>
					</div>
					<div class="four-container">
						<a href="${pageContext.request.contextPath }/schedule/schedule.do"><div
								class="circle-su";>N</div></a> <a
							href="${pageContext.request.contextPath }/schedule/schedule.do">
							<div class="circle"
								style="background-image: url(${pageContext.request.contextPath}/resources/images/calendar-i.png");></div>
						</a>
						<div class="circle-info"></div>
					</div>
					<div class="four-container">
						<a
							href="${pageContext.request.contextPath }/chatting/chattestview?empId=${loginMember.empId}"><div
								class="circle-su";>N</div></a> <a
							href="${pageContext.request.contextPath }/chatting/chattestview?empId=${loginMember.empId}"><div
								class="circle"
								style="background-image: url(${pageContext.request.contextPath}/resources/images/messanger-i.png");></div></a>
						<div class="circle-info"></div>
					</div>
				</div>
			</div>
			<div class="mini2-second-container">
				<div class="todo-title">
					<%-- <b>${loginMember.empName} 님의 Monthly Schedule</b> --%>
					<b> &nbsp; M O N T H L Y &nbsp; &nbsp;  S C H E D U L E</b>
				</div>
				<div class="todo-info-container">
					<!-- <div class="todo-info"> -->


					<!-- 선정캘린더 -->

					<!-- <div id='calendar-container'> -->
					<div id="calendar" class="mainCalendar"></div>
					<!-- </div> -->

					<!-- </div> -->
				<!-- 	<div id="thisMonthSchedules" class="todo-info">
						<h4 style="text-align: center">오늘의 일정</h4>
					</div> -->



					<div id="thisMonthSchedules" class="whiteboard">
						<h4>D A I L Y</h4>
						<div class="schedule-container"></div>
					</div>




				</div>
			</div>
		</div>


	</div>
</div>
<!-- <div class="third"> -->
<%-- 	<div class="mini3-container">
		<div class="mini3-first-container">
			<div style="margin: 40px; color: rgb(1, 153, 70);">
				<h5><b>내 전자결재 현황</b></h5>
			</div>
			<div class="myaprv-container">
				<div class="myaprv">문서종류</div>
				<div class="myaprv">제목</div>
				<div class="myaprv">상태</div>
				<div class="myaprv">기안일</div>
			</div>
			<div class="approval-border-line"></div>
			<div class="aprv-mail">
				<c:forEach var="a" items="${approval }">
					<ul class="aprv-ul2">
						<li><c:if test="${a.apvType == 0 }">휴가신청서</c:if> <c:if
								test="${a.apvType == 1 }">품의서</c:if> <c:if
								test="${a.apvType == 2 }">지출결의서</c:if></li>
						<li class="aprv-ul-title">${a.apvTitle}</li>
						<li><c:if test="${a.apvStatus == 0 }">진행 중</c:if> <c:if
								test="${a.apvStatus == 1 }">반려</c:if> <c:if
								test="${a.apvStatus == 2 }">결재완료</c:if></li>
						<li>${a.apvDate }</li>
					</ul>
				</c:forEach>
			</div>
		</div>
		<div class="mini3-second-container">
			<div style="margin: 40px; color: #07a72b9c;">
				<h5><b>받은 메일함</b></h5>
			</div>
			<div class="mymailtitle-container">
				<ul class="aprv-ul">
					<li class="mymail">제 목</li>
					<li class="mymail">송신인</li>
					<li class="mymail">수신일</li>
				</ul>
			</div>
			<div class="border-line"></div>
			<div class="aprv-mail">
				<c:forEach var="m" items="${mails }">
					<ul class="aprv-ul">
						<li class="aprv-ul-title">${m.rcvMailTitle}</li>
						<li>${m.rcvMailSender }</li>
						<li>${m.rcvMailDate}</li>
					</ul>
				</c:forEach>

			</div>
		</div>
	</div> --%>
	
	
	
	
	
	
	<div class="third">
    <div class="mini3-container">
        <div class="mini3-first-container modern-container">
            <h5 class="section-title"><b>내 전자결재 현황</b></h5>
            <div class="content-wrapper">
                <div class="header-row">
                    <span>문서종류</span>
                    <span>제목</span>
                    <span>상태</span>
                    <span>기안일</span>
                </div>
                <div class="scrollable-content">
                    <c:forEach var="a" items="${approval}">
                        <div class="item-row">
                            <span class="doc-type">
                                <c:choose>
                                    <c:when test="${a.apvType == 0}">휴가신청서</c:when>
                                    <c:when test="${a.apvType == 1}">품의서</c:when>
                                    <c:when test="${a.apvType == 2}">지출결의서</c:when>
                                </c:choose>
                            </span>
                            <span class="title">${a.apvTitle}</span>
                            <span class="status">
                                <c:choose>
                                    <c:when test="${a.apvStatus == 0}"><span class="badge in-progress">진행 중</span></c:when>
                                    <c:when test="${a.apvStatus == 1}"><span class="badge rejected">반려</span></c:when>
                                    <c:when test="${a.apvStatus == 2}"><span class="badge completed">결재완료</span></c:when>
                                </c:choose>
                            </span>
                            <span class="date">${a.apvDate}</span>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="mini3-second-container modern-container">
            <h5 class="section-title"><b>받은 메일함</b></h5>
            <div class="content-wrapper">
                <div class="header-row">
                    <span>제목</span>
                    <span>송신인</span>
                    <span>수신일</span>
                </div>
                <div class="scrollable-content">
                    <c:forEach var="m" items="${mails}">
                        <div class="item-row">
                            <span class="title">${m.rcvMailTitle}</span>
                            <span class="sender">${m.rcvMailSender}</span>
                            <span class="date">${m.rcvMailDate}</span>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>


	
	
	
	
</div>
</div>
</div>


<script>
	const empId = `${loginMember.empId}`;
	const path = `${path}`;
	console.log('loginId', empId);
</script>





<script src="${path }/resources/js/khj/attendance.js"></script>
<script src="${path }/resources/js/khj/mainCalendar.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
