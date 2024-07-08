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
                url: '/schedule/events', // 백엔드에서 이벤트를 가져올 URL
                method: 'GET',
                failure: function() {
                    alert('there was an error while fetching events!');
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
            $('#eventStartDate').val(info.dateStr);
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
            title: $('#eventTitle').val(),
            start: $('#eventStartDate').val() + 'T' + $('#eventStartTime').val(),
            end: $('#eventEndDate').val() + 'T' + $('#eventEndTime').val(),
            allDay: $('#eventAllDay').is(':checked'),
            content: $('#eventContent').val(),
            location: $('#eventLocation').val(),
            empId: '1',  // 임시로 고정된 empId, 실제 구현 시 로그인 사용자로 변경 필요
            ttId: '1'    // 임시로 고정된 ttId, 실제 구현 시 필요에 따라 변경 필요
        };

        $.ajax({
            url: '/schedule/add',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(eventData),
            success: function(response) {
                calendar.addEvent(response);
                $('#addEventModal').modal('hide');
                $('#eventForm')[0].reset();
            },
            error: function() {
                alert('일정 등록에 실패했습니다.');
            }
        });
    });

    // 종일 체크박스 이벤트
    $('#eventAllDay').on('change', function() {
        if ($(this).is(':checked')) {
            $('#eventStartTime, #eventEndTime').hide();
        } else {
            $('#eventStartTime, #eventEndTime').show();
        }
    });

    // 반응형 처리
    $(window).resize(function() {
        calendar.updateSize();
    });
});
