
$(document).ready(function() {
	
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
        },
        themeSystem: 'bootstrap',
        locale: 'ko',
        editable: true,
        eventSources: [
            {
                url: `${path}/schedule/events`, // 백엔드에서 이벤트를 가져올 URL
                method: 'GET',
                failure: function() {
                    alert('이벤트를 가져오는 중 오류가 발생했습니다!');
                }
            
            }
        ],
        height: 'auto',
        aspectRatio: 1.35,
        handleWindowResize: true,
        eventClick: function(info) {
            alert('Event: ' + info.event.title);
        },
        dateClick: function(info) {
            $('#addEventModal').modal('show');
            $('#schStartDate').val(info.dateStr);
        }
    });
    calendar.render();
	
	





    // 일정 등록 버튼 클릭 이벤트
    $('#addScheduleBtn').on('click', function() {
        $('#addEventModal').modal('show');
    });

    // 일정 추가 폼 제출 처리
    $('#eventForm').on('submit', function(e) {
        e.preventDefault();
        var eventData = {
            empId: $('#empId').val(),
            schTitle: $('#schTitle').val(),
            schContent: $('#schContent').val(),
            schStart: $('#schStart').val(), 
            schEnd: $('#schEnd').val(),
            schType: $('#schType').val(),
            schColor: $('#schColor').val(),
            calendarType: $('#calendarType').val()
        };
        alert(eventData);
        
        
        // 날짜와 시간의 유효성 검사 
        if (new Date(eventData.schStart) > new Date(eventData.schEnd)) {
            alert('시작 날짜는 종료 날짜보다 빨라야 합니다.');
            return;
        }
        
        
        
        
        $.ajax({
            url: `${path}/schedule/addevent.do`, // 서버의 엔드포인트 URL
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(eventData), // 데이터를 JSON 문자열로 변환하여 전송
            success: function(response) {
                alert('일정이 성공적으로 등록되었습니다.');
                alert(`${response}`);
                $('#addEventModal').modal('hide'); // 모달 닫기
                $('#eventForm')[0].reset(); // 폼 초기화
                calendar.refetchEvents(); // 캘린더 이벤트 새로고침
            },
            error: function(xhr, status, error) {
                alert('일정 등록에 실패했습니다.');
            }
        });
    });

    // 종일 체크박스 이벤트
    $('#eventAllDay').on('change', function() {
        if ($(this).is(':checked')) {
            $('#schStartTime, #schEndTime').hide();
        } else {
            $('#schStartTime, #schEndTime').show();
        }
    });

    // 반응형 처리
    $(window).resize(function() {
        calendar.updateSize();
    });
});
