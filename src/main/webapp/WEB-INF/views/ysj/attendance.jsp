<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<!-- 모든 내용은 밑에있는 div안에만 설정해야함. -->
<div class="container-xxl flex-grow-1 container-p-y">
    <div id='calendar' style="height: 600px;"></div>
</div>

<!-- FullCalendar CSS -->
<link href='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.14/main.min.css' rel='stylesheet' />
<!-- FullCalendar JS -->
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.14/index.global.min.js'></script>
<!-- Custom JS -->
<script src="<%=request.getContextPath()%>/js/ysj/attendance.js"></script>




<div id='calendar'></div>


<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
<script src="${path }/resources/js/ysj/attendance.js"></script>



