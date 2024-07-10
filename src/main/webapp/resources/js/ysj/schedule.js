$(document).ready(function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth', // 초기 로드 될때 보이는 캘린더 화면 
        headerToolbar: {	//헤더에 표시할 툴 바 
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
        },
        themeSystem: 'bootstrap',
        locale: 'ko', //한국어 설정 
        editable: true,
        selectable : true, //달력 일자 드래그 설정 가능
        droppable : true,
        editable : true,
        nowIndicator : true,  //현재 시간 마크(?)
		dayMaxEvents : true, // 이벤트가 오버되면 높이 제한 (+ 몇 개식으로 표현)
		timeZone : 'UTC',
        /*eventSources: [ //eventSources
            {
                url: `${path}/schedule/events`,
                method: 'GET',
                extraParams: function() {
                    return {
                        calendars: $('input[type=checkbox]:checked').map(function() {
                            return this.id;
                        }).get().join(',')
                    };
                },
                success: function(data) {
					console.log(data);
                    alert('일정을 가져오는 것이 성공했습니다');
                    calendar.addEventSource(data);
                    
                },
                failure: function() {
                    alert('이벤트를 가져오는 중 오류가 발생했습니다!');
                }
            }
        ], */
       // 일정 조회하기 
       events: function(fetchInfo, successCallback, failureCallback) {
            $.ajax({
                url: `${path}/schedule/events`,
                type: 'GET',
                dataType: 'json',
                success: function(response) {
                    var events = response.map(function(event) {
                        return {
                            id: event.schId,
                            title: event.schTitle,
                            start: event.schStart,
                            end: event.schEnd,
                            description: event.schContent,
                            extendedProps: {
                                calendarType: event.calendarType,
                                empId: event.empId,
                                schType: event.schType,
                                schColor: event.schColor
                            }
                        };
                    });
                    successCallback(events);
                    alert('일정을 불러오는데 성공했습니다 ');
                },
                error: function() {
                    alert('일정을 불러오는데 실패했습니다.');
                }
            });
        },
        
        height: 'auto',
        aspectRatio: 1.35,
        handleWindowResize: true,
        eventClick: function(info) {
            alert('Event: ' + info.event.title);
        },
        dateClick: function(info) {
            $('#addEventModal').modal('show');
            $('#schStart').val(info.dateStr);
        }
        
        
    });
    calendar.render();





	// 일정 추가하기  
    $('#addScheduleBtn').on('click', function() {
        $('#addEventModal').modal('show');
    });

    $('#eventForm').on('submit', function(e) {
        e.preventDefault();
        var eventData = {
            empId: $('#empId').val(),
            schTitle: $('#schTitle').val(),
            schContent: $('#schContent').val(),
            schStart: $('#schStart').val(),
            schEnd: $('#schEnd').val(),
            schType: $('#schType').val(),
            calendarType: $('#calendarType').val()
        };
        
        if (new Date(eventData.schStart) > new Date(eventData.schEnd)) {
            alert('시작 날짜는 종료 날짜보다 빨라야 합니다.');
            return;
        }
        
        $.ajax({
            url: `${path}/schedule/addevent.do`,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(eventData),
            success: function(response) {
                alert('일정이 성공적으로 등록되었습니다.');
                $('#addEventModal').modal('hide');
                $('#eventForm')[0].reset();
                calendar.refetchEvents();
            },
            error: function(xhr, status, error) {
                alert('일정 등록에 실패했습니다.');
            }
        });
    });

    $('#eventAllDay').on('change', function() {
        if ($(this).is(':checked')) {
            $('#schStartTime, #schEndTime').hide();
        } else {
            $('#schStartTime, #schEndTime').show();
        }
    });

    $(window).resize(function() {
        calendar.updateSize();
    });
});
