    function replyEmail() {
    // URL 인코딩을 적용하여 특수 문자가 포함된 경우에도 안전하게 처리합니다.
    var encodedTitle = encodeURIComponent(emailTitle);
    var encodedSender = encodeURIComponent(emailSender);
    var encodedContent = encodeURIComponent(emailContent);

    // 이메일 답장 페이지로 이동
    window.location.href = `${contextPath}`+`/mailbox/replymail?emailTitle=${encodedTitle}&emailSender=${encodedSender}&emailContent=${encodedContent}`;
}

    function deleteEmail() {
        // 삭제 버튼 클릭 시 동작을 정의합니다.
        if (confirm("삭제하시겠습니까?")) {
            window.location.href = `${contextPath}`+`/delete?emailId=${email.rcvMailId}`; // 예시 URL
        }
    }

