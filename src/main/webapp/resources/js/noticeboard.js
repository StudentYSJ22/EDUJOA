$(document).ready(function() {
	getBoardList();

	// 정렬 선택 이벤트 리스너 추가
	$('#sortSelect').on('change', sortTable);

	function getBoardList() {
		$.ajax({
			type: "GET",
			url: "/noticeboard/getboardlist",
			contentType: "application/json",
			dataType: "json",
			success: function(response) {
				console.log("Board list loaded successfully", response);
				updateTable(response);
			},
			fail: console.log("왜 안되는건데")
		});
	}

	function updateTable(boardList) {
		var tbody = $("table tbody");
		tbody.empty(); // 기존 내용을 비웁니다.

		$.each(boardList, function(index, board) {
			var row = $("<tr>").click(function() {
				var MboardId = board.boardId; // 클릭한 행의 boardId를 가져옴
				getBoardDetail(MboardId); // getBoardDetail 함수 호출
			});
			row.append($("<td>").text(board.boardId));
			row.append($("<td>").text(board.boardTitle));
			row.append($("<td>").text(board.employee.empName));
			row.append($("<td>").text(formatDate(board.boardDate)));
			row.append($("<td>").text(board.boardCount));

			tbody.append(row);
		});
	}

	function getBoardDetail(MboardId) {
		// 조회수 증가 요청을 먼저 보냅니다.
		increaseViewCount(MboardId).then(function() {
			// 조회수 증가가 완료된 후 상세 페이지로 이동합니다.
			window.location.href = '/noticeboard/detail?boardId=' + MboardId;
		}).catch(function(error) {
			console.log("Failed to increase view count", error);
			// 오류가 발생해도 페이지 이동은 수행합니다.
			window.location.href = '/noticeboard/detail?boardId=' + MboardId;
		});
	}

	function increaseViewCount(MboardId) {
		return new Promise(function(resolve, reject) {
			$.ajax({
				type: "POST",
				url: "/noticeboard/increaseViewCount",
				contentType: "application/json",
				data: JSON.stringify({
					boardId: MboardId
				}),
				success: function(response) {
					console.log("View count increased successfully", response);
					resolve();
				},
				error: function(error) {
					console.log("Failed to increase view count", error);
					reject(error);
				}
			});
		});
	}

	function sortTable() {
		var sortBy = $('#sortSelect').val();
		if (sortBy === 'default') {
			// 기본 정렬일 경우 초기 데이터를 다시 불러옵니다.
			getBoardList();
			return;
		}
		// AJAX 요청을 통해 서버에서 정렬된 데이터를 가져옵니다.
		$.ajax({
			url: '/noticeboard/sort',
			type: 'GET',
			data: { sortBy: sortBy },
			success: function(response) {
				// 테이블 내용을 업데이트합니다.
				updateTable(response);
			},
			error: function(error) {
				console.error('정렬 중 오류 발생:', error);
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

	$(document).on('click', '.write-btn', function() {
		window.location.href = contextPath + '/noticeboard/boardwrite';
	});

});