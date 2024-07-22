<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var ="loginMember" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}"/>

<link rel="stylesheet" href="${path}/resources/css/ysj/attendance.css">

<!-- FullCalendar CSS -->
<link href='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.14/main.min.css' rel='stylesheet' />
<!-- FullCalendar JS -->
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.14/index.global.min.js'></script>
<!-- Custom JS -->
<script src="<%=request.getContextPath()%>/resources/js/ysj/attendance.js"></script>

<div id='calendar'></div>




<script>
  const path = '${pageContext.request.contextPath}';
  console.log(path);
</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>




