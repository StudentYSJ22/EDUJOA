/*


*/
$(document).ready(function() {
    // 출퇴근 기록 데이터를 불러오는 함수
    function loadAttendanceRecords() {
        $.ajax({
            url: `${path}/attendance/records`, 
            type: 'GET', // HTTP GET 메소드 사용
            dataType: 'json', 
            success: function(response) { 
                let rows = ''; // 테이블 행 초기화
                response.forEach(record => { // 응답받은 출퇴근 기록 데이터 반복 처리
                    const atnDate = formatDate(record.atnIn); // 출근 날짜 포맷팅
                    const atnIn = record.atnIn ? formatDateTime(record.atnIn) : '-'; // 출근 시간 포맷팅, 값이 없으면 '-'로 표시
                    const atnOut = record.atnOut ? formatDateTime(record.atnOut) : '-'; // 퇴근 시간 포맷팅, 값이 없으면 '-'로 표시
                    rows += `
                        <tr>
                            <td>${atnDate}</td> <!-- 출근 날짜 -->
                            <td>${atnIn}</td> <!-- 출근 시간 -->
                            <td>${atnOut}</td> <!-- 퇴근 시간 -->
                            <td>${record.atnStatus ? record.atnStatus : '-'}</td> <!-- 출퇴근 상태값이 없으면 '-'로 표시 -->
                        </tr>
                    `;
                });
                $('#attendance-list').html(rows); // 완성된 테이블 행들을 HTML에 삽입 -> 없으면 data로만 뜸 
            },
            error: function(xhr, status, error) { 
                console.error('Error loading attendance records:', error); // 에러 메시지 확인 
            }
        });
    }

    // 날짜 "YYYY-MM-DD" 형식으로 변환
    function formatDate(dateTime) {
        const date = new Date(dateTime);
        return date.getFullYear() + '-' + (date.getMonth() + 1).toString().padStart(2, '0') + '-' + date.getDate().toString().padStart(2, '0');
    }

    // 날짜 시간을 "YYYY-MM-DD HH:MM:SS" 형식으로 변환 
    function formatDateTime(dateTime) {
        const date = new Date(dateTime);
        return date.getFullYear() + '-' + (date.getMonth() + 1).toString().padStart(2, '0') + '-' + date.getDate().toString().padStart(2, '0')
            + ' ' + date.getHours().toString().padStart(2, '0') + ':' + date.getMinutes().toString().padStart(2, '0') + ':' + date.getSeconds().toString().padStart(2, '0');
    }

    loadAttendanceRecords(); // 페이지가 로드될 때 출퇴근 기록을 불러오는 함수 호출
});
