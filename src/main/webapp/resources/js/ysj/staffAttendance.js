$(document).ready(function() {
    // 페이지 로딩 시 직원 근태 정보 가져오기
    loadStaffAttendance();

    function loadStaffAttendance() {
        $.ajax({
            url: path + '/api/staffAttendance',
            method: 'GET',
            success: function(data) {
                $('#attendanceTable tbody').empty(); // 기존 테이블 내용 비우기
                if (data.records) {
                    data.records.forEach(function(attendance) {
                        var status = calculateStatus(attendance);
                        $('#attendanceTable tbody').append(
                            '<tr>' +
                            '<td>' + attendance.empId + '</td>' +
                            '<td>' + attendance.empName + '</td>' +
                            '<td>' + attendance.atnDate + '</td>' +
                            '<td>' + attendance.atnIn + '</td>' +
                            '<td>' + attendance.atnOut + '</td>' +
                            '<td>' + status + '</td>' +
                            '</tr>'
                        );
                    });
                }

                if (data.summary) {
                    $('#totalCount').text(data.summary.total + "명");
                    $('#onTimeCount').text(data.summary.onTime + "명");
                    $('#lateCount').text(data.summary.late + "명");
                    $('#absentCount').text(data.summary.absent + "명");
                    $('#earlyLeaveCount').text(data.summary.earlyLeave + "명");
                }
            },
            error: function(err) {
                console.error('Failed to load staff attendance data:', err);
            }
        });
    }

    function calculateStatus(attendance) {
        if (!attendance.atnIn && !attendance.atnOut) {
            return '결근';
        } else if (attendance.atnIn && new Date(attendance.atnIn).getHours() < 9) {
            return '출근';
        } else if (attendance.atnIn && new Date(attendance.atnIn).getHours() >= 9) {
            return '지각';
        } else if (attendance.atnOut && new Date(attendance.atnOut).getHours() < 18) {
            return '조퇴';
        } else {
            return '정상';
        }
    }
});
