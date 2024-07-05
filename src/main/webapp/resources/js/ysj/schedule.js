/**
 * 
 */
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        events: '<%=request.getContextPath()%>/schedule/events' // 서버에서 이벤트를 가져오는 엔드포인트
    });
    calendar.render();
});