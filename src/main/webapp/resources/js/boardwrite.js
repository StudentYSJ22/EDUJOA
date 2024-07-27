$(document).ready(function() {


	// 목록으로 버튼 클릭 이벤트
	$('.btn-gotolist').click(function() {
		history.back();  // 이전 페이지로 돌아갑니다.
	});


	// 삭제 버튼 클릭 이벤트
	$('.btn-delete').click(function() {
		if (confirm("해당 게시글을 삭제하시겠습니까?")) {
			// 사용자가 '예'를 선택한 경우
			// 여기에 게시글 삭제 로직을 추가합니다.
			// 예: AJAX를 사용하여 서버에 삭제 요청을 보냅니다.
			$.ajax({
				type: "POST",
				url: "/noticeboard/deleteBoard",
				contentType: "application/json",
				dataType: "json",
				data: JSON.stringify({
					boardId: boardId
				}),
				success: function(response) {
					if (response > 0) {
						alert("게시글이 삭제되었습니다.");
						window.location.href = contextPath + '/noticeboard/board';  // 게시글 목록 페이지로 이동
					} else if (response <= 0) {
						alert("게시글 삭제 실패!");
					}
				}
			});
		}
	});
	// 수정버튼 부분
	const editBtn = $('.btn-edit');
	const saveBtn = $('.btn-save');
	const titleElement = $('.post-title');
	const contentElement = $('.post-content');
	// 초기 버튼 상태 설정
	if (loginId !== boardWriter) {
		editBtn.prop('disabled', true).css('opacity', '0.5');
	}

	editBtn.on('click', function() {
		if (loginId !== boardWriter) {
			alert("수정 권한이 없습니다.");
			return;
		}

		// 제목을 편집 가능한 input으로 변경
        const titleInput = $('<input>').val(titleElement.text()).addClass('edit-title');
        titleElement.replaceWith(titleInput);

        // 내용을 편집 가능한 textarea로 변경
        const contentTextarea = $('<textarea>').val(contentElement.text()).addClass('edit-content');
        contentElement.replaceWith(contentTextarea);

        // 버튼 표시 변경
        editBtn.hide();
        saveBtn.show();
    });

    saveBtn.on('click', function() {
        const newTitle = $('.edit-title').val();
        const newContent = $('.edit-content').val();

        // 서버로 수정된 내용을 전송
        updatePost(boardId, newTitle, newContent);

        // UI 업데이트
        titleElement.text(newTitle);
        contentElement.text(newContent);

        // 원래 요소로 되돌리기
        $('.edit-title').replaceWith(titleElement);
        $('.edit-content').replaceWith(contentElement);

        // 버튼 표시 변경
        editBtn.show();
        saveBtn.hide();
    });

    function updatePost(boardId, title, content) {
        $.ajax({
            type: "POST",
            url: contextPath + "/noticeboard/updateBoard",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify({
                boardId: boardId,
                boardTitle: title,
                boardContent: content
            }),
            success: function(response) {
                if (response > 0) {
                    alert('게시글이 성공적으로 수정되었습니다.');
                } else {
                    alert("게시글 수정 실패, 관리자에게 문의하세요.");
                }
            },
            error: function(xhr, status, error) {
                alert("게시글 수정 중 오류가 발생했습니다. 관리자에게 문의하세요.");
                console.error("Error:", error);
            }
        });
    }
});