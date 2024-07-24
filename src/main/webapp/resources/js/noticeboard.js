var contextPath = "${path}";
$(document).ready(function() {
	getBoardList();
function getBoardList() {
        $.ajax({
            type: "GET",
            url: "/noticeboard/getboardlist",
            contentType: "application/json",
            dataType: "json",
            success: function(boardList) {
                var tbody = $("table tbody");
                tbody.empty(); // 기존 내용을 비웁니다.

                $.each(boardList, function(index, board) {
                    var row = $("<tr>");
                    row.append($("<td>").text(board.boardId));
                    row.append($("<td>").html('<a href="' + contextPath + '/noticeboard/detail/' + board.boardId + '">' + board.boardTitle + '</a>'));
                    row.append($("<td>").text(board.empId));
                    row.append($("<td>").text(formatDate(board.boardDate)));
                    row.append($("<td>").text(board.boardCount));

                    tbody.append(row);
                });
            },
            error: function() {
                alert("불러오기 실패!");
            }
        });
    }

// 날짜 형식을 변환하는 함수
function formatDate(dateString) {
    var date = new Date(dateString);
    var year = date.getFullYear();
    var month = ("0" + (date.getMonth() + 1)).slice(-2);
    var day = ("0" + date.getDate()).slice(-2);
    return year + "-" + month + "-" + day;
}
});