<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%@page import="java.util.List"%>
<%@page import="com.edujoa.khj.main.dto.Calendar"%>

<c:set var ="loginMember" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}"/>

<!DOCTYPE html>
<html>
<head>
<link href='fullcalendar/core/main.css' rel='stylesheet' />
<link href='fullcalendar/daygrid/main.css' rel='stylesheet' />
<script src='fullcalendar/core/main.js'></script>
<script src='fullcalendar/daygrid/main.js'></script>
<script>
/*     document.addEventListener('DOMContentLoaded', function() {
      var calendarEl = document.getElementById('calendar');
    
      var calendar = new FullCalendar.Calendar(calendarEl, {
        plugins: [ 'dayGrid' ]
      });
    
      calendar.render();
    }); */
    
    document.addEventListener('DOMContentLoaded', function() {
    	var calendarEl = document.getElementById('calendar');
    	var calendar = new FullCalendar.Calendar(calendarEl, {
    		initialView : 'dayGridMonth',
    		locale : 'ko', // 한국어 설정
    		headerToolbar : {
            	start : "",
                center : "prev title next",
                end : 'dayGridMonth,dayGridWeek,dayGridDay'
                },
    	selectable : true,
    	droppable : true,
    	editable : true,
    	events : [ 
        	    <%List<Calendar> calendarList = (List<Calendar>) request.getAttribute("calendarMain");%>
                <%if (calendarList != null) {%>
                <%for (Calendar vo : calendarList) {%>
                {
                	title : '<%=vo.getSchTitle()%>',
                    start : '<%=vo.getSchStart()%>',
                    end : '<%=vo.getSchEnd()%>',
                    color : '#' + Math.round(Math.random() * 0xffffff).toString(16)
                 },
    	<%}
    }%>
    				]
    				
    			});
    			calendar.render();
    		});
    
    
</script>
</head>
<body>
 
<div id='calendar'></div>
 
</body>
</html> --%>