$(document).ready(function() {
	// Compose 버튼 클릭 시 이메일 작성 페이지로 이동
	$(document).on('click', '.compose-btn', function() {
		window.location.href = contextPath + '/mailbox/mailsend';
	});


	// 받은메일함 클릭 시
	$('#inbox').click(function() {
		loadEmails('inbox');
		updateActionButton('삭제');
	});

	// 삭제메일함 클릭 시
	$('#trash').click(function() {
		loadEmails('trash');
		updateActionButton('복구');
	});
	$("#delete").click(function(){
		// 선택된 이메일 ID들을 가져옴
        let selectedEmails = [];
        $(".email-checkbox:checked").each(function() {
            selectedEmails.push($(this).closest('.email-item').data('email-id'));
        });

        if (selectedEmails.length === 0) {
            alert("삭제할 이메일을 선택해주세요.");
            return;
        }
		$.ajax({
			type: "POST",
			url: "/mailbox/delete",
			contentType: "application/json",
			data: JSON.stringify({
			mailIds: selectedEmails
		}),
		dataType: "json",
		success: function(response){
			alert("선택한 이메일이 삭제되었습니다.");
			$("#refresh").click();
		},
            error: function(error) {
                // 에러 시 처리할 로직
                console.log("이메일 삭제 중 오류가 발생했습니다.");
                alert("이메일 삭제 중 오류가 발생했습니다.");
            }
        });
    });
	
	$("#refresh").click(function() {
		$.ajax({
			type: "POST",
			url: "/mailbox/refresh",
			success: function(emails) {
				$('.email-list').empty();
                emails.forEach(function(email) {
                    let emailItem = `
                    <a href="contextPath/mailbox/maildetail?emailId=${email.rcvMailId}" class="email-link" style="color: black;">
                        <div class="email-item inbox" data-email-id="${email.rcvMailId}">
                            <input type="checkbox" class="email-checkbox"> 
                            <span class="sender">${email.rcvMailSender}</span> 
                            <span class="subject">${email.rcvMailTitle}</span>
                            <span class="date">${email.rcvMailDate}</span>
                        </div>
                        </a>
                    `;
                    $('.email-list').append(emailItem);
                });
                console.log("메일 목록이 성공적으로 새로고침되었습니다.");
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