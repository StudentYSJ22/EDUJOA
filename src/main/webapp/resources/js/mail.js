$(document).ready(function() {
    // Compose 버튼 클릭 시 이메일 작성 페이지로 이동
    $(document).on('click', '.compose-btn', function() {
        window.location.href = contextPath + '/mailbox/mailsend';
    });

    $(document).on('click', '.compose-btn', function() {
        window.location.href = contextPath + '/mailbox/mailsend';
    });

    // 버튼 레이블 업데이트 함수
    function updateActionButton(label) {
        $('#actionBtn').text(label);
    }

    // 이메일 폴더 로드 함수
    function loadEmails(folder) {
        $.ajax({
            url: contextPath + '/mailbox/' + folder,
            method: 'GET',
            success: function(data) {
                $('.email-list').html(data);
            },
            error: function(xhr, status, error) {
                console.error('Error loading emails:', status, error);
            }
        });
    }

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

    // 삭제 및 복구 버튼 클릭 시
    $('#actionBtn').click(function() {
        let action = $(this).text() === '삭제' ? 'delete' : 'restore';
        let selectedEmails = [];
        $('.email-checkbox:checked').each(function() {
            selectedEmails.push($(this).closest('.email-item').data('email-id'));
        });

        $.ajax({
            url: contextPath + '/mailbox/' + action,
            method: 'POST',
            data: JSON.stringify({ emails: selectedEmails }),
            contentType: 'application/json',
            success: function() {
                if (action === 'delete') {
                    // 선택된 이메일을 삭제메일함으로 이동
                    $('.email-checkbox:checked').each(function() {
                        $(this).closest('.email-item').removeClass('inbox').addClass('trash').hide();
                    });
                } else {
                    // 선택된 이메일을 받은메일함으로 이동
                    $('.email-checkbox:checked').each(function() {
                        $(this).closest('.email-item').removeClass('trash').addClass('inbox').hide();
                    });
                    // 삭제메일함을 다시 로드하여 복구된 이메일을 제거
                    loadEmails('trash');
                }
            },
            error: function(xhr, status, error) {
                console.error('Error updating emails:', status, error);
            }
        });
    });
});