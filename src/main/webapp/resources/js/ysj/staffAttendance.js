$(document).ready(function() {
    var originalRecords = [];

    // 페이지 로딩 시 직원 근태 정보 가져오기
    loadAllStaffAttendance();

    function loadAllStaffAttendance() {
        $.ajax({
            url: path + '/api/allStaffAttendance',
            method: 'GET',
            success: function(data) {
                originalRecords = data.records || [];
                renderAttendanceTable(originalRecords);
            },
            error: function(err) {
                console.error('Failed to load staff attendance data:', err);
            }
        });
    }

    function renderAttendanceTable(records) {
        $('#attendanceTable tbody').empty(); // 기존 테이블 내용 비우기
        if (records) {
            records.forEach(function(attendance) {
                if (attendance && attendance.empId) {
                    var status = calculateStatus(attendance);
                    $('#attendanceTable tbody').append(
                        '<tr>' +
                        '<td>' + (attendance.empId || '') + '</td>' +
                        '<td>' + (attendance.empName || '') + '</td>' +
                        '<td>' + (attendance.atnDate || '') + '</td>' +
                        '<td>' + (attendance.atnIn || '') + '</td>' +
                        '<td>' + (attendance.atnOut || '') + '</td>' +
                        '<td>' + status + '</td>' +
                        '</tr>'
                    );
                }
            });
        }
    }

    function calculateStatus(attendance) {
        if (!attendance.atnIn) {
            return '결근';
        } else if (new Date(attendance.atnIn).getHours() < 9) {
            return '출근';
        } else {
            return '지각';
        }
    }

    window.searchStaffAttendance = function() {
        var empId = $('#empId').val();
        var empName = $('#empName').val();
        var status = $('#status').val();
        var startDate = $('#startDate').val();
        var endDate = $('#endDate').val();

        $.ajax({
            url: path + '/api/allStaffAttendance',
            method: 'GET',
            data: {
                empId: empId,
                empName: empName,
                status: status,
                startDate: startDate,
                endDate: endDate
            },
            success: function(data) {
                originalRecords = data.records || [];
                renderAttendanceTable(originalRecords);
            },
            error: function(err) {
                console.error('Failed to search staff attendance data:', err);
            }
        });
    }

    window.sortStaffAttendance = function() {
        var sortOrder = $('#sortOrder').val();
        var sortedRecords = originalRecords.slice(); // 원본 배열을 복사하여 사용

        if (sortOrder === '') {
            // 기본 정렬 (원본 순서)
            sortedRecords = originalRecords;
        } else {
            sortedRecords.sort(function(a, b) {
                if (sortOrder === 'nameAsc') {
                    return a.empName.localeCompare(b.empName);
                } else if (sortOrder === 'nameDesc') {
                    return b.empName.localeCompare(a.empName);
                } else if (sortOrder === 'dateAsc') {
                    return new Date(a.atnDate) - new Date(b.atnDate);
                } else if (sortOrder === 'dateDesc') {
                    return new Date(b.atnDate) - new Date(a.atnDate);
                }
            });
        }

        renderAttendanceTable(sortedRecords);
    }
});
