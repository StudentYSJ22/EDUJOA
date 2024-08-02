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
				if(event.schStart != null){
					if (parseInt(event.schStart.substring(5, 7)) === thisMonth || parseInt(event.schEnd.substring(5, 7)) === thisMonth) {
						thisMonthSchedules.push(event);
					}
					if (parseInt(event.schStart.substring(8, 10)) == thisDay) {
						thisDaySchedules.push(event);
					}
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
			/*      const thisMonthSchedulesElement = document.getElementById('thisMonthSchedules');
				  thisDaySchedules.forEach(schedule => {
					  const scheduleElement = document.createElement('div');
					  scheduleElement.classList.add('schedule-list');
					  const $p = document.createElement('p');
					  const $p2 = document.createElement('p');
					  const time = schedule.schStart.split('T')[1].substring(0, 5);
					  const formattedTime = time.replace(':', '시') + '분';
					  $p.append(formattedTime);
					  $p2.append(schedule.schTitle);
					  scheduleElement.appendChild($p);
					  scheduleElement.appendChild($p2);
					  thisMonthSchedulesElement.appendChild(scheduleElement);
				  });*/



			//메인페이지에서 현재 날짜 출력하는 로직
			const thisMonthSchedulesElement = document.getElementById('thisMonthSchedules');
			const scheduleContainer = thisMonthSchedulesElement.querySelector('.schedule-container');

		/*	thisDaySchedules.forEach(schedule => {
				const scheduleElement = document.createElement('div');
				scheduleElement.classList.add('schedule-list');

				const timeElement = document.createElement('div');
				timeElement.classList.add('schedule-time');
				const titleElement = document.createElement('div');
				titleElement.classList.add('schedule-title');

				const time = schedule.schStart.split('T')[1].substring(0, 5);
				const formattedTime = time.replace(':', '시') + '분';

				timeElement.textContent = formattedTime;
				titleElement.textContent = schedule.schTitle;

				scheduleElement.appendChild(timeElement);
				scheduleElement.appendChild(titleElement);

				scheduleContainer.appendChild(scheduleElement);
			});
*/


		// 최대 4개의 일정만 표시
const maxSchedules = Math.min(thisDaySchedules.length, 4);

for (let i = 0; i < maxSchedules; i++) {
  const schedule = thisDaySchedules[i];
  const scheduleElement = document.createElement('div');
  scheduleElement.classList.add('schedule-list');

  const timeElement = document.createElement('span');
  timeElement.classList.add('schedule-time');
  const titleElement = document.createElement('span');
  titleElement.classList.add('schedule-title');

  const time = schedule.schStart.split('T')[1].substring(0, 5);
  const formattedTime = time.replace(':', '시') + '분';

  timeElement.textContent = formattedTime;
  titleElement.textContent = schedule.schTitle;

  scheduleElement.appendChild(timeElement);
  scheduleElement.appendChild(titleElement);

  scheduleContainer.appendChild(scheduleElement);
}

// 4개 미만일 경우 빈 포스트잇 추가
for (let i = maxSchedules; i < 4; i++) {
  const emptyElement = document.createElement('div');
  emptyElement.classList.add('schedule-list');
  emptyElement.textContent = '일정이 없습니다';
  scheduleContainer.appendChild(emptyElement);
}






		},
		error: function(xhr, status, error) {
			console.error('Error loading events:', error); // 디버깅용 로그
			alert('일정을 불러오는데 실패했습니다.');
		}
	});
});