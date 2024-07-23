$(document).ready(function() {
    // empId와 path는 JSP에서 전달됩니다.
    const empId = "${loginMember.empId}";
    const path = "${pageContext.request.contextPath}";

    // 출퇴근 기록 데이터를 불러오는 함수
    function loadAttendanceRecords() {
        $.ajax({
            url: path + '/attendance/attendance.do',
            type: 'GET',
            data: { empId: empId },
            dataType: 'json',
            success: function(response) {
                let rows = '';
                response.forEach(record => {
                    const workHours = calculateWorkHours(record.atnIn, record.atnOut);
                    rows += `
                        <tr>
                            <td>${formatDate(record.atnDate)}</td>
                            <td>${getDayOfWeek(record.atnDate)}</td>
                            <td>${record.atnIn ? formatTime(record.atnIn) : '-'}</td>
                            <td>${record.atnInLocation || '-'}</td>
                            <td>${record.atnOut ? formatTime(record.atnOut) : '-'}</td>
                            <td>${record.atnOutLocation || '-'}</td>
                            <td>${workHours.count}</td>
                            <td>${workHours.hours}</td>
                            <td>${record.atnStatus}</td>
                        </tr>
                    `;
                });
                $('#attendance-list').html(rows);
            },
            error: function(xhr, status, error) {
                console.error('Error loading attendance records:', error);
            }
        });
    }

    function formatDate(date) {
        const d = new Date(date);
        return d.getFullYear() + '-' + (d.getMonth() + 1).toString().padStart(2, '0') + '-' + d.getDate().toString().padStart(2, '0');
    }

    function formatTime(date) {
        const d = new Date(date);
        return d.getHours().toString().padStart(2, '0') + ':' + d.getMinutes().toString().padStart(2, '0');
    }

    function getDayOfWeek(date) {
        const days = ['일', '월', '화', '수', '목', '금', '토'];
        const d = new Date(date);
        return days[d.getDay()];
    }

    function calculateWorkHours(atnIn, atnOut) {
        if (!atnIn || !atnOut) return { count: '-', hours: '-' };
        const inTime = new Date(atnIn);
        const outTime = new Date(atnOut);
        const diffMs = outTime - inTime;
        const hours = Math.floor(diffMs / 3600000);
        const minutes = Math.floor((diffMs % 3600000) / 60000);
        return { count: (hours + (minutes / 60)).toFixed(2), hours: hours + '시간 ' + minutes + '분' };
    }

    loadAttendanceRecords();
});
