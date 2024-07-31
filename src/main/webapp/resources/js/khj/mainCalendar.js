var thisMonthSchedules = [];
var thisDaySchedules = [];

$(document).ready(function() {
	var calendarEl = document.getElementById('calendar');
    var dateObject = new Date();
    var thisMonth = dateObject.getMonth() + 1;
    var thisDay = dateObject.getDate();
    
    let empId = '${loginMember.empId}';
	$.ajax({
                url: path + '/schedule/events',
                type: 'GET',
                dataType: 'json',
                data: { calendars: [] }, // 선택된 캘린더 타입을 서버로 보냄
                success: function(response) {
                    var responseEvents = response.map(function(event) {
						if (parseInt(event.schStart.substring(5, 7)) === thisMonth || parseInt(event.schEnd.substring(5, 7)) === thisMonth) {
							thisMonthSchedules.push(event);
						}
						if(parseInt(event.schStart.substring(8, 10)) == thisDay){
							thisDaySchedules.push(event);
						}
                        return {
                            id: event.schId,
                            title: event.schTitle,
                            start: event.schStart,
                            end: event.schEnd,
                            description: event.schContent,
                            backgroundColor: event.schColor,
                            textColor: 'black',
                            borderColor: event.schColor,
                            extendedProps: {
                                calendarType: event.calendarType,
                                empId: event.empId,
                                schType: event.schType,
                                schColor: event.schColor
                            }
                        };
                    });
                    
                    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth', // 초기 뷰 설정
        headerToolbar: { 			 // 캘린더 헤더 설정
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
        },
        themeSystem: 'bootstrap', // 테마 설정
        locale: 'ko', 			  // 언어 설정 (한국어)
        editable: true,           // 이벤트 편집 가능 여부
        selectable: true,         // 날짜 선택 가능 여부
        droppable: true,          // 드롭 가능 여부
        nowIndicator: true,       // 현재 시간 표시
        dayMaxEvents: true,       // 하루 최대 이벤트 수
        timeZone: 'UTC',          // 시간대 설정

        // 이벤트 로드 함수
        events: responseEvents
        });
        
        calendar.render();
        
        //메인페이지에서 현재 날짜 출력하는 로직
        const thisMonthSchedulesElement = document.getElementById('thisMonthSchedules');
        thisDaySchedules.forEach(schedule => {
			const scheduleElement = document.createElement('div');
			const time = schedule.schStart.split('T')[1].substring(0, 5);
			const formattedTime = time.replace(':', '시') + '분';
			scheduleElement.append(formattedTime + " " + schedule.schTitle);
			thisMonthSchedulesElement.appendChild(scheduleElement);
		});
                },
                error: function(xhr, status, error) {
                    console.error('Error loading events:', error); // 디버깅용 로그
                    alert('일정을 불러오는데 실패했습니다.');
                }
            });
});