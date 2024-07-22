<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var ="loginMember" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}"/>
<link rel="stylesheet" href="${path }/resources/css/khj/index_hj.css">
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- <body>안내용만. 모든 내용은 밑에있는 div안에만 설정해야함. -->
	
  <div class="main">
        <div class="first-container">
            <div class="first">
                <div class="mini-container">
                    <div class="mini-first-container">
                        <div class="emp-info-container">
                            <div class="emp-pic" style="background-image: url(${pageContext.request.contextPath}/resources/images/bs.png");></div>
                            <div class="emp-info" style="color: rgb(145, 145, 145); font-size: 15px;">교육운영팀</div>
                            <div class="emp-info" style="color: rgb(145, 145, 145); font-size: 12px;">매니저</div>
                            <div class="emp-info2" style="font-size: 20px; color: black;"><b>유병승</b></div>
                        </div>
                    </div>
                    <div class="mini-second-container">
                        <div class="attendance-title" style="font-size: 20px; color:black;"><b>근태관리</b></div>
                        <div class="attendance-systime" style="color: #7f7f7f;">2024년 7월 19일(목)12:42</div>
                        <div class="time-office-container">
                            <div class="time">
                                <div class="time-title">출근시간</div>
                                <div class="time-info"><b>(DB)00:00:00</b></div>
                            </div>
                            <div class="time">
                                <div class="time-title">퇴근시간</div>
                                <div class="time-info"><b>(DB)00:00:00</b></div>
                            </div>
                        </div>
                        <div class="time-btn-container">
                            <div><button class="attn-btn"><b>출근하기</b></button></div>
                            <div><button class="attn-btn"><b>퇴근하기</b></button></div>
                        </div>
                        <div class="time-btn-go"><button class="attn-btn-go"><b>+ 근태관리 바로가기</b></button></div>
                    </div>
                </div>
            </div>
            <div class="second">
                <div class="mini2-container">
                    <div class="mini2-first-container">
                        <div class="five-container">
                            <a href="test.html"><div class="circle-su";>5</div></a>
                            <a href="test.html"><div class="circle" style="background-image: url(${pageContext.request.contextPath}/resources/images/mail-icon.png");></div></a>
                            <div class="circle-info">우편</div>
                        </div>
                        <div class="five-container">
                      		<a href="test.html"><div class="circle-su";>12</div></a>
                            <a href="test.html"><div class="circle" style="background-image: url(${pageContext.request.contextPath}/resources/images/approval-icon.png");></div></a>
                            <div class="circle-info">결재</div>
                            </div>
                        <div class="five-container">                        
                      		<a href="test.html"><div class="circle-su" style="background-color:gray";>0</div></a>
                            <a href="test.html"><div class="circle" style="background-image: url(${pageContext.request.contextPath}/resources/images/docu-icon.png");></div></a>
                            <div class="circle-info">문서</div>
                            </div>
                        <div class="five-container">
                      		<a href="test.html"><div class="circle-su";>102</div></a>
                            <a href="test.html"><div class="circle" style="background-image: url(${pageContext.request.contextPath}/resources/images/calendar-icon.png");></div></a>
                            <div class="circle-info">일정</div>
                            </div>
                        <div class="five-container">
                      		<a href="test.html"><div class="circle-su";>51</div></a>
                            <a href="test.html"><div class="circle" style="background-image: url(${pageContext.request.contextPath}/resources/images/chat-icon.png");></div></a>
                            <div class="circle-info">메신저</div>
                        </div>
                    </div>
                    <div class="mini2-second-container">
                        <div class="todo-title"><b>유병승님의 Monthly Schedule</b></div>
                        <div class="todo-info-container">
                        <div class="todo-info"></div>
                        <div class="todo-info"></div>
                        </div>
                    </div>
                </div>


            </div>
        </div>
        <div class="third">
            <div class="mini3-container">
                <div class="mini3-first-container">
                    <div style="margin: 25px; color:black;"><b>내 전자결재 현황</b></div>
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
                    <div style="margin: 25px;  color:black;"><b>받은메일함</b></div>
                    <div class="mymailtitle-container">
                    <div class="mymail-container">
                        <div class="mymail">제  목</div>
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
		
		
		
	
		
		
	
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
