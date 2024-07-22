// 날짜 및 시간 형식 변환 함수 (ISO-8601 형식)
function formatLocalDateTime(date) {
    return date.toISOString().slice(0, 19); // "2024-06-29T15:00:00"
}

// FullCalendar 초기화 및 설정
$(document).ready(function() {
	// FullCalendar 요소를 초기화
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        themeSystem: 'bootstrap', // 테마 설정
        locale: 'ko', 			  // 언어 설정 (한국어)
        //editable: true,         // 이벤트 편집 가능 여부
        selectable: true,         // 날짜 선택 가능 여부
        //droppable: true,        // 드롭 가능 여부
        nowIndicator: true,       // 현재 시간 표시
        dayMaxEvents: true,       // 하루 최대 이벤트 수
        timeZone: 'UTC',          // 시간대 설정
        height: 'auto',			  // 높이 자동 조정
        aspectRatio: 1.35, 		  // 화면 비율 설정
        handleWindowResize: true, // 창 크기 조정 시 캘린더 크기 조정
        
        // 이벤트 로드 함수
        events: function(fetchInfo, successCallback, failureCallback) {
            $.ajax({
                url: `${path}/attendance/events`,
                type: 'GET',
                dataType: 'json',
                success: function(response) {
                    var events = response.map(function(event) {
                        return {
                            id: event.atnId,
                            title: event.atnStatus,
                            start: event.atnIn,
                            end: event.atnOut,
                            description: '출근: ' + event.atnIn + ' 퇴근: ' + event.atnOut
                        };
                    });
                    successCallback(events); //성공! 
                },
                error: function() {
                    alert('근태 데이터를 불러오는데 실패했습니다.');
                }
            });
        }
    });

    calendar.render();
});
