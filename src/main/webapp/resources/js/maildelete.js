$(document).ready(function() {
	// 전체 선택 체크박스가 체크되었는지 확인
	$('#select-all').change(function() {
		var isChecked = $(this).is(':checked');
		// 모든 이메일 체크박스의 상태를 변경
		$('.email-checkbox').prop('checked', isChecked);
	});

	// 복구 버튼 클릭 이벤트 리스너
	$("#restore").click(function() {
		// 선택된 이메일 ID들을 가져옴
		let selectedEmails = [];
		$(".email-checkbox:checked").each(function() {
			let emailId = $(this).closest('.email-item').data('email-id');
			if (emailId !== undefined) { // emailId가 undefined가 아닌 경우에만 추가
                selectedEmails.push(emailId);
            }
		});

		if (selectedEmails.length === 0) {
			alert("복구할 이메일을 선택해주세요.");
			return;
		}

		console.log("Selected Email IDs:", selectedEmails); // 디버깅용 로그
		$.ajax({
			type: "POST",
			url: "/mailbox/restore",
			contentType: "application/json",
			data: JSON.stringify(selectedEmails),
			dataType: "json",
			success: function(response) {
				console.log("Server Response:", response); // 디버깅용 로그
				if (response > 0) {
					alert("선택한 이메일이 복구되었습니다.");
					window.location.reload();
				} else if (response <= 0) {
					alert("이메일 복구에 실패했습니다.");
				}
			}
		});
	});
});
