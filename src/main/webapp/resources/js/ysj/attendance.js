$(document).ready(function() {
    loadAttendanceSummary();
    loadAttendanceRecords();

    /*function loadAttendanceSummary() {
        $.ajax({
            url: `${path}/attendance/summary`, 
            type: 'GET', 
            dataType: 'json', 
            success: function(response) {
                $('#onTimeCount').text(response.onTime + '건');
                $('#lateCount').text(response.late + '건');
                $('#absentCount').text(response.absent + '건');
                $('#unprocessedCount').text(response.unprocessed + '건');
                $('#totalCount').text((response.onTime + response.late + response.absent + response.unprocessed) + '건');
            },
            error: function(xhr, status, error) {
                console.error('Error loading attendance summary:', error);
            }
        });
    }*/
    
  /*  function loadAttendanceSummary() {
    $.ajax({
        url: `${path}/attendance/summary`, 
        type: 'GET', 
        dataType: 'json', 
        success: function(response) {
            console.log("Summary data: ", response); // 로그 추가
            $('#onTimeCount').text(response.onTime + '건');
            $('#lateCount').text(response.late + '건');
            $('#absentCount').text(response.absent + '건');
            $('#unprocessedCount').text(response.unprocessed + '건');
            $('#totalCount').text((response.onTime + response.late + response.absent + response.unprocessed) + '건');
        },
        error: function(xhr, status, error) {
            console.error('Error loading attendance summary:', error);
        }
    });
} */

function loadAttendanceSummary() {
    $.ajax({
        url: `${path}/attendance/summary`,
        type: 'GET',
        dataType: 'json',
        success: function(response) {
            console.log("Received summary:", response);
            $('#onTimeCount').text(response.ONTIME + '건');
            $('#lateCount').text(response.LATE + '건');
            $('#absentCount').text(response.ABSENT + '건');
            $('#unprocessedCount').text(response.UNPROCESSED + '건');
            $('#totalCount').text((response.ONTIME + response.LATE + response.ABSENT + response.UNPROCESSED) + '건');
        },
        error: function(xhr, status, error) {
            console.error('Error loading attendance summary:', error);
            console.error('Response Text:', xhr.responseText);
        }
    });
}


    function loadAttendanceRecords(status = "") {
        $.ajax({
            url: `${path}/attendance/records`, 
            type: 'GET', 
            data: {status: status}, 
            dataType: 'json', 
            success: function(response) { 
                let rows = ''; 
                response.forEach(record => { 
                    const atnDate = formatDate(record.atnIn); 
                    const atnIn = record.atnIn ? formatDateTime(record.atnIn) : '-'; 
                    const atnOut = record.atnOut ? formatDateTime(record.atnOut) : '-'; 
                    rows += `
                        <tr>
                            <td>${atnDate}</td> 
                            <td>${atnIn}</td> 
                            <td>${atnOut}</td> 
                            <td>${record.atnStatus ? record.atnStatus : '-'}</td> 
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

    function formatDate(dateTime) {
        const date = new Date(dateTime);
        return date.getFullYear() + '-' + (date.getMonth() + 1).toString().padStart(2, '0') + '-' + date.getDate().toString().padStart(2, '0');
    }

    function formatDateTime(dateTime) {
        const date = new Date(dateTime);
        return date.getFullYear() + '-' + (date.getMonth() + 1).toString().padStart(2, '0') + '-' + date.getDate().toString().padStart(2, '0')
            + ' ' + date.getHours().toString().padStart(2, '0') + ':' + date.getMinutes().toString().padStart(2, '0') + ':' + date.getSeconds().toString().padStart(2, '0');
    }

    window.filterAttendance = function(status) {
        loadAttendanceRecords(status);
    }
});
