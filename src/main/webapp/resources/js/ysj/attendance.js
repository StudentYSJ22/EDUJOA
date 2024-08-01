$(document).ready(function() {
    // 출퇴근 요약과 출퇴근 기록
    loadAttendanceSummary();
    loadAttendanceRecords();

    // 출퇴근 요약을 불러오는 함수
    function loadAttendanceSummary() {
        $.ajax({
            url: `${path}/attendance/summary`, 
            type: 'GET', 
            dataType: 'json', 
            success: function(response) {
                console.log("Received summary:", response);
                // 응답 데이터를 이용하여 HTML 요소 업데이트
                $('#onTimeCount').text(response.ONTIME + '건');
                $('#lateCount').text(response.LATE + '건');
                $('#absentCount').text(response.ABSENT + '건');
                $('#unprocessedCount').text(response.UNPROCESSED + '건');
                $('#totalCount').text((response.ONTIME + response.LATE + response.ABSENT + response.UNPROCESSED) + '건');
            },
            error: function(xhr, status, error) {
                // 오류 발생 시 콘솔에 오류 메시지 출력
                console.error('Error loading attendance summary:', error);
                console.error('Response Text:', xhr.responseText);
            }
        });
    }

    // 출퇴근 기록을 불러오는 함수
    function loadAttendanceRecords(status = "") {
        $.ajax({
            url: `${path}/attendance/records`, 
            type: 'GET', 
            data: {status: status}, // 요청에 상태 필터링 파라미터 포함
            dataType: 'json', 
            success: function(response) {
                renderAttendanceRecords(response); // 응답 데이터를 이용하여 테이블 렌더링
            },
            error: function(xhr, status, error) {
                // 오류 발생 시 콘솔에 오류 메시지 출력 및 HTML 
                console.error('Error loading attendance records:', error);
                $('#attendance-list').html('<tr><td colspan="4">데이터를 불러오는 중 오류가 발생했습니다.</td></tr>');
            }
        });
    }

    // 날짜를 'YYYY-MM-DD' 형식으로 변환하는 함수
    function formatDate(dateTime) {
        if (!dateTime) return '-';
        const date = new Date(dateTime);
        return date.getFullYear() + '-' + 
               (date.getMonth() + 1).toString().padStart(2, '0') + '-' + 
               date.getDate().toString().padStart(2, '0');
    }

    // 날짜 및 시간을 'YYYY-MM-DD HH:MM:SS' 형식으로 변환하는 함수
    function formatDateTime(dateTime) {
        if (!dateTime) return '-';
        const date = new Date(dateTime);
        return date.getFullYear() + '-' + 
               (date.getMonth() + 1).toString().padStart(2, '0') + '-' + 
               date.getDate().toString().padStart(2, '0') + ' ' + 
               date.getHours().toString().padStart(2, '0') + ':' + 
               date.getMinutes().toString().padStart(2, '0') + ':' + 
               date.getSeconds().toString().padStart(2, '0');
    }

    // 출퇴근 기록을 테이블에 렌더링하는 함수
    function renderAttendanceRecords(records) {
        console.log("Rendering records:", records);
        const tableBody = $('#attendance-list');
        tableBody.empty();

        // 기록이 없으면 '검색 결과가 없습니다.' 메시지 표시
        if (!Array.isArray(records) || records.length === 0) {
            tableBody.html('<tr><td colspan="4">검색 결과가 없습니다.</td></tr>');
            return;
        }

        // 각 기록을 테이블 행으로 변환하여 추가
        records.forEach(record => {
            if (record && typeof record === 'object') {
                console.log("Processing record:", record); 
                const atnDate = formatDate(record.atnDate || record.ATN_DATE);
                const atnIn = formatDateTime(record.atnIn || record.ATN_IN);
                const atnOut = formatDateTime(record.atnOut || record.ATN_OUT);
                const atnStatus = record.atnStatus || record.ATN_STATUS || getStatus(record);

                const row = `
                <tr>
                    <td>${atnDate}</td>
                    <td>${atnIn}</td>
                    <td>${atnOut}</td>
                    <td>${atnStatus}</td>
                </tr>
                `;
                tableBody.append(row);
            } else {
                console.log("Invalid record:", record);
            }
        });
    }

    // 출퇴근 상태를 결정하는 함수
    function getStatus(record) {
        const atnIn = record.atnIn || record.ATN_IN;
        const atnOut = record.atnOut || record.ATN_OUT;

        // 출근 시간과 퇴근 시간이 모두 null인 경우 결근
        if (!atnIn && !atnOut) return '결근';

        // 출근 시간만 있고 퇴근 시간이 null인 경우 미처리
        if (atnIn && !atnOut) return '미처리';

        // 출근 시간과 퇴근 시간이 모두 존재하는 경우 상태 결정
        const inTime = new Date(atnIn);
        const outTime = new Date(atnOut);

        const nineAM = new Date(inTime);
        nineAM.setHours(9, 0, 0, 0);

        const sixPM = new Date(outTime);
        sixPM.setHours(18, 0, 0, 0);

        // 지각: 9시 이후 출근
        if (inTime > nineAM) return '지각';

        // 조퇴: 18시 이전 퇴근
        if (outTime < sixPM) return '조퇴';

        // 출근: 9시 이전 출근 6시 이후 퇴근
        return '출근';
    }

    // 출퇴근 기록을 상태별로 필터링하는 함수
    window.filterAttendance = function(status) {
        loadAttendanceRecords(status);
    }

  // 기간별 출퇴근 기록을 검색하는 함수
window.searchAttendance = function() {
    const startDate = $('#startDate').val();
    const endDate = $('#endDate').val();

    if (new Date(startDate) > new Date(endDate)) {
        alert('시작일은 종료일보다 늦을 수 없습니다.');
        return false;
    }

    $.ajax({
        url: `${path}/attendance/searchByDate`, 
        type: 'GET',
        data: { startDate: startDate, endDate: endDate }, 
        dataType: 'json', 
        success: function(response) {
            console.log("Filtered records:", response);
            renderAttendanceRecords(response); // 응답 데이터를 이용하여 테이블 렌더링
        },
        error: function(xhr, status, error) {
            // 오류 발생 시 콘솔에 오류 메시지 출력 및 HTML 요소 업데이트
            console.error('Error searching attendance:', error);
            console.error('Response Text:', xhr.responseText);
            $('#attendance-list').html('<tr><td colspan="4">데이터를 불러오는 중 오류가 발생했습니다.</td></tr>');
        }
    });
    return false;
}

});