<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
   <!-- 모든 내용은 밑에있는 div안에만 설정해야함. -->
   <div class="container-xxl flex-grow-1 container-p-y">
   
   <div id='calendar'> </div>
   </div>



<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.14/index.global.min.js'></script>
<script src="<%=request.getContextPath()%>/js/schedule.js"></script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>