// 날짜 및 시간 형식 변환 함수 (ISO-8601 형식) ㅉㅉ! 
/*function formatLocalDateTimeToUTC(date) {
   var date = new Date(date);
   return new Date(date.getTime() - (date.getTimezoneOffset() * 60000)).toISOString().slice(0, 19);
   
}*/
const formatLocalDateTimeToUTC = date => {
    const parsedDate = new Date(date);
    return isNaN(parsedDate) ? null : parsedDate.toISOString().slice(0, 19);
};


// 이벤트 색상 업데이트 함수
function updateEventColor() {
    var selectedCalendarType = $('#calendarType').find(':selected');
    var color = selectedCalendarType.data('color');
    $('#schColor').val(color);
}

// 선택된 캘린더 타입을 저장할 배열
let selectedCalendars = [];

$(document).ready(function () {
    // 체크박스 변경 이벤트 핸들러
    $('.calendar-item input[type="checkbox"]').on('change', function () {
        const calendarId = $(this).attr('id');
        const calendarLabel = $(`label[for="${calendarId}"]`).text().trim();
        if ($(this).is(':checked')) {
            selectedCalendars.push(calendarLabel);
        } else {
            selectedCalendars = selectedCalendars.filter(label => label !== calendarLabel);
        }
        console.log('Selected calendars:', selectedCalendars); // 디버깅용 로그
        // 이벤트 새로고침
        calendar.refetchEvents();
    });

    // 선택된 이벤트 정보와 참여자 목록을 저장할 변수
    let clickedEventInfo;
    let selectedSharers = [];
    let isEditMode = false; // 수정 모드인지 여부

    // FullCalendar 요소 초기화
    var calendarEl = document.getElementById('calendar');
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
        events: function (fetchInfo, successCallback, failureCallback) {
            if (selectedCalendars.length === 0) {
                successCallback([]); // 선택된 캘린더가 없으면 빈 이벤트 리스트 반환
                return;
            }

            $.ajax({
                url: `${path}/schedule/events`,
                type: 'GET',
                dataType: 'json',
                data: { calendars: selectedCalendars }, // 선택된 캘린더 타입을 서버로 보냄
                success: function (response) {
                    var events = response.map(function (event) {
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
                                schColor: event.schColor,
                                sharers: event.sharers,
                                repeatType: event.repeatType,
                                repeatEndDate: event.repeatEndDate
                                
                            }
                            
                        };
                    });
                    successCallback(events); // 성공!
                },
                error: function (xhr, status, error) {
                    console.error('Error loading events:', error); // 디버깅용 로그
                    alert('일정을 불러오는데 실패했습니다.');
                }
            });
        },

        height: 'auto', 		  // 높이 자동 조정
        aspectRatio: 1.35, 		  // 화면 비율 설정
        handleWindowResize: true, // 창 크기 조정 시 캘린더 크기 조정

/* 상세정보보기  */
        // 일정 클릭 시 호출되는 함수(상세정보 모달창)
        eventClick: function (info) {
            clickedEventInfo = info;

            $.ajax({
                url: `${path}/schedule/eventDetail`,
                type: 'GET',
                data: { eventId: info.event.id },
                dataType: 'json',
                success: function (event) {
				 console.log("Event data:", event);
					
                    $('#detailEmpId').val(event.empId);
                    $('#detailSchTitle').val(event.schTitle);
                    $('#detailSchContent').val(event.schContent);
                    $('#detailSchStart').val(formatLocalDateTimeToUTC(event.schStart));
                    $('#detailSchEnd').val(formatLocalDateTimeToUTC(event.schEnd));
                    $('#detailSchType').val(event.schType);
                    $('#detailCalendarType').val(event.calendarType);
                    $('#detailSchColor').val(event.schColor);
					$('detailRepeatType').val(event);
                    // 반복 타입과 반복 종료 날짜 
                    $('#detailRepeatType').val(event.repeatType);
                    $('#detailRepeatEndDate').val(event.repeatEndDate ? formatLocalDateTimeToUTC(event.repeatEndDate) : '');

                    // 참여자 목록 로드
                    selectedSharers = event.sharers.length ? event.sharers[0].employee : [];
                    const sharersNames = selectedSharers.map(sharer => sharer.empName).join(', ');
                    $('#detailSharers').val(sharersNames);
                    $('#editDetailSharers').val(sharersNames);

                    // 폼 필드 비활성화
                    $('#eventDetailForm input, #eventDetailForm textarea, #eventDetailForm select').prop('readonly', true).prop('disabled', true);
                    $('#saveEventBtn').hide();
                    $('#editEventBtn').show();

                    $('#eventDetailModal').modal('show');
                },
                error: function () {
                    alert('일정 상세 정보를 불러오는데 실패했습니다.');
                }
            });
        },

        // 날짜 클릭 -> 정보 
        dateClick: function (info) {
            $('#addEventModal').modal('show');
            $('#schStart').val(formatLocalDateTimeToUTC(info.dateStr));
        }
    });

    // 캘린더 렌더링
    calendar.render();

    // 캘린더 타입 변경 시 색상 업데이트
    $('#calendarType').change(updateEventColor);

    // 모달이 열릴 때 참여자 필드 초기화
    $('#addEventModal, #eventDetailModal').on('show.bs.modal', function () {
        $('#detailSharers').val(''); // 참여자 필드 초기화
    });

    // 일정 추가 버튼 클릭 시 모달 열기
    $('#addScheduleBtn').on('click', function () {
        $('#addEventModal').modal('show');
        $('#calendarType').val('내 일정');
        updateEventColor();
        selectedSharers = []; // 참여자 배열 초기화
        $('#detailSharers').val(''); // 참여자 필드 초기화
    });
    
   // 일정 추가 폼 제출 시 이벤트 데이터 처리
    $('#eventForm').on('submit', function (e) {
        e.preventDefault();
        var eventData = {
            empId: $('#empId').val(),
            schTitle: $('#schTitle').val(),
            schContent: $('#schContent').val(),
            schStart: formatLocalDateTimeToUTC($('#schStart').val()),
            schEnd: formatLocalDateTimeToUTC($('#schEnd').val()),
            schType: $('#schType').val(),
            calendarType: $('#calendarType').val(),
            schColor: $('#schColor').val(),
            sharers: selectedSharers.map(emp => ({ empId: emp.empId, empName: emp.empName })),
            repeatType: $('#repeatType').val(),  
            repeatEndDate: formatLocalDateTimeToUTC($('#repeatEndDate').val())
        };

        // 시작 날짜가 종료 날짜보다 이후인 경우 경고
        if (new Date(eventData.schStart) > new Date(eventData.schEnd)) {
            alert('시작 날짜는 종료 날짜보다 빨라야 합니다.');
            return;
        }

        // 일정 추가
        $.ajax({
            url: `${path}/schedule/addevent.do`,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(eventData),
            success: function (response) {
                alert('일정이 성공적으로 등록되었습니다.');
                $('#addEventModal').modal('hide');
                $('#eventForm')[0].reset();
                calendar.refetchEvents();
            },
            error: function (xhr, status, error) {
                alert('일정 등록에 실패했습니다.');
            }
        });
    });


    // 반복 일정 관련 필드 활성화
    $('#schType').change(function () {
        if ($(this).val() === '반복 일정') {
            $('#repeatType').prop('disabled', false);
            $('#repeatEndDate').prop('disabled', false);
        } else {
            $('#repeatType').prop('disabled', true);
            $('#repeatEndDate').prop('disabled', true);
        }
    });

    // 모달이 닫힐 때 슬라이드 패널도 닫기
    $('#addEventModal, #eventDetailModal').on('hide.bs.modal', function () {
        $('#participantPanel').removeClass('open');
    });


    // 모달이 열릴 때 기본적으로 폼을 읽기 전용으로 설정
    $('#eventDetailModal').on('show.bs.modal', function () {
        $('#eventDetailForm input, #eventDetailForm textarea, #eventDetailForm select').prop('readonly', true).prop('disabled', true);
        $('#saveEventBtn').hide();
        $('#editEventBtn').show();
    });


/*질문하기  */ 
/* --------- 수정 -----------  */
    // 이벤트 수정 버튼 클릭 시 폼 활성화
    $('#editEventBtn').on('click', function (e) {
        e.preventDefault(); // 기본 동작 방지
          e.stopPropagation(); // 이벤트 전파 방지
        $('#eventDetailForm input, #eventDetailForm textarea, #eventDetailForm select').prop('readonly', false).prop('disabled', false);
        $('#saveEventBtn').show();
        isEditMode = true; // 수정 모드 활성화
        $(this).hide();
    });
    
    

    // 일정 수정 폼 제출 
    $('#eventDetailForm').on('submit', function (e) {
        e.preventDefault();
        var updatedEventData = {
            schId: clickedEventInfo.event.id,
            empId: $('#detailEmpId').val(),
            schTitle: $('#detailSchTitle').val(),
            schContent: $('#detailSchContent').val(),
            schStart: formatLocalDateTimeToUTC($('#detailSchStart').val()),
            schEnd: formatLocalDateTimeToUTC($('#detailSchEnd').val()),
            schType: $('#detailSchType').val(),
            calendarType: $('#detailCalendarType').val(),
            schColor: $('#detailSchColor').val(),
            sharers: selectedSharers.map(emp => ({ empId: emp.empId, empName: emp.empName })), // 참여자 배열 추가
            repeatType: $('#detailRepeatType').val(),  // 추가된 부분
            repeatEndDate: formatLocalDateTimeToUTC($('#detailRepeatEndDate').val()) // 추가된 부분
        };

        // 일정 수정
        $.ajax({
            url: `${path}/schedule/updateEvent`,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(updatedEventData),
            success: function (response) {
                alert('일정이 성공적으로 수정되었습니다.');
                $('#eventDetailModal').modal('hide');
                calendar.refetchEvents();
                isEditMode = false; // 수정 모드 해제
            },
            error: function (xhr, status, error) {
                alert('일정 수정에 실패했습니다.');
            }
        });
    });

    // 일정 삭제 
    $('#deleteEventBtn').on('click', function () {
        var eventId = clickedEventInfo.event.id;
        $.ajax({
            url: `${path}/schedule/deleteEvent`,
            type: 'POST',
            data: { eventId: eventId },
            success: function (response) {
                alert('일정이 성공적으로 삭제되었습니다.');
                $('#eventDetailModal').modal('hide');
                calendar.refetchEvents();
            },
            error: function (xhr, status, error) {
                alert('일정 삭제에 실패했습니다.');
            }
        });
    });

    // 캘린더 항목 체크박스 변경 시 배경색 업데이트
    $('.calendar-item input[type="checkbox"]').on('change', function () {
        var color = $(this).data('color');
        if ($(this).is(':checked')) {
            $(this).css('background-color', color);
        } else {
            $(this).css('background-color', 'transparent');
        }
    });

    // 참여자 선택 버튼 클릭 시 패널 열기
    $(document).on('click', '#selectParticipants', function () {
        if (isEditMode || $('#addEventModal').hasClass('show')) { // 수정 모드 또는 추가 모드일 때만
            $('#participantPanel').addClass('open');
            loadEmployees();
        }
    });

    // 직원 목록 로드 함수
    function loadEmployees() {
        $.ajax({
            url: `${path}/schedule/employees`,
            method: 'GET',
            success: function (employees) {
				 $('#selectedEmpList').text('');
                console.log(selectedSharers);
                const empList = $('#empList').empty();
                employees.forEach(emp => {
                    if (!selectedSharers.find(e => e.empId === emp.empId)) {
                        empList.append(`<li data-id="${emp.empId}" data-name="${emp.empName}">${emp.empName} (${emp.empTitle})</li>`);
                    } else {
                        $('#selectedEmpList').append(`<li data-id="${emp.empId}" data-name="${emp.empName}">${emp.empName}</li>`);
                    }
                });
            },
            error: function (xhr, status, error) {
                console.error("Error loading employees:", error);
                alert("직원 목록을 불러오는 데 실패했습니다.");
            }
        });
    }

    // 직원 목록에서 직원 선택 
    $(document).on('click', '#empList li', function () {
        const empId = $(this).data('id');
        const empName = $(this).data('name');
        $(this).remove();
        selectedSharers.push({ empId, empName });
        $('#selectedEmpList').append(`<li data-id="${empId}" data-name="${empName}">${empName}</li>`);
    });

    // 선택된 직원 목록에서 직원 제거 
    $(document).on('click', '#selectedEmpList li', function () {
        const empId = $(this).data('id');
        selectedSharers = selectedSharers.filter(emp => emp.empId !== empId);
        $(this).remove();
        loadEmployees();
    });

    // 참여자 확인 버튼 클릭
    $('#confirmParticipants').click(function () {
        const empNames = selectedSharers.map(emp => emp.empName).join(', ');
        // 추가 모달에서 열려있는지 확인
        if ($('#addEventModal').hasClass('show')) {
            $('#detailSharers').val(empNames);
        } else if ($('#eventDetailModal').hasClass('show')) { // 수정 모달에서 열려있는지 확인
            $('#editDetailSharers').val(empNames);
        }
        $('#participantPanel').removeClass('open');
    });

    // 참여자 취소 버튼 클릭 시
    $('#cancelParticipants').click(function () {
        $('#participantPanel').removeClass('open');
    });

    // 모달 닫기 버튼과 취소 버튼 누르면 모달창 닫기
    $('#addEventModal .close, #addEventModal .btn-secondary').click(function () {
        $('#addEventModal').modal('hide');
        isEditMode = false;
    });

 	
});