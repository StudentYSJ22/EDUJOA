$(document).ready(function() {
	// 기본적으로 'inbox' 타입의 메일을 보여줍니다.
	$('.email-item').each(function() {
		if ($(this).hasClass('delete')) {
			$(this).hide();
		}
	});

	$('#inbox').click(function() {
		$('.email-item').show(); // 모든 메일 항목을 표시
	});

	$('#trash').click(function() {
		$('.email-item').hide(); // 모든 메일 항목을 숨김
		$('.email-item.delete').show(); // 삭제된 메일만 표시
	});
	// Compose 버튼 클릭 시 이메일 작성 페이지로 이동
	$(document).on('click', '.compose-btn', function() {
		window.location.href = contextPath +'/mailbox/mailsend';
	});

	$('#select-all').change(function() {
		// 전체 선택 체크박스가 체크되었는지 확인
		var isChecked = $(this).is(':checked');
		// 모든 이메일 체크박스의 상태를 변경
		$('.email-checkbox').prop('checked', isChecked);
	});

	$("#delete").click(function() {
		// 선택된 이메일 ID들을 가져옴
		let selectedEmails = [];
		$(".email-checkbox:checked").each(function() {
			let emailId = $(this).closest('.email-item').data('email-id');
			if (emailId !== undefined) { // emailId가 undefined가 아닌 경우에만 추가
                selectedEmails.push(emailId);
            }
		});

		if (selectedEmails.length === 0) {
			alert("삭제할 이메일을 선택해주세요.");
			return;
		}

		console.log("Selected Email IDs:", selectedEmails); // 디버깅용 로그
		$.ajax({
			type: "POST",
			url: contextPath +"/mailbox/delete",
			contentType: "application/json",
			data: JSON.stringify(selectedEmails),
			dataType: "json",
			success: function(response) {
				console.log("Server Response:", response); // 디버깅용 로그
				if (response > 0) {
					alert("선택한 이메일이 삭제되었습니다.");
					window.location.reload();
				} else if (response <= 0) {
					alert("이메일 삭제에 실패했습니다.")
				}
			}
		});
	});


	$("#refresh").click(function() {
		$.ajax({
			type: "POST",
			url: contextPath +"/mailbox/refresh",
			success: function(emails) {
				$('.email-list').empty();
				emails.forEach(function(email) {
					let subjectHtml = email.mailRead === 0
						? `<strong>${email.rcvMailTitle}</strong>`
						: email.rcvMailTitle;
					//백틱안에 변수 쓰려면 ${}에 감싸야함
					let emailItem = `                   
                    <a href="${contextPath}/mailbox/maildetail?emailId=${email.rcvMailId}" class="email-link" style="color: black;">
                        <div class="email-item inbox" data-email-id="${email.rcvMailId}">
                            <input type="checkbox" class="email-checkbox"> 
                            <span class="sender">${email.rcvMailSender}</span> 
                            <span class="subject">${subjectHtml}</span>
                            <span class="date">${email.rcvMailDate}</span>
                        </div>
                    </a>
                    `;
					$('.email-list').append(emailItem);
				});
				console.log("메일 목록이 성공적으로 새로고침되었습니다.");
				// 페이지를 새로 고침
				window.location.reload();
			},
			error: function(error) {
				// 에러 시 처리할 로직
				alert("메일 목록 새로고침 중 오류가 발생했습니다.");
			}
		});
	});

	$(document).on('click', '.email-checkbox', function(event) {
		event.stopPropagation();
	});
});