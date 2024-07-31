<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="loginMember" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}" />
<c:set var="path" value="${pageContext.request.contextPath}" />
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<title>메일 열람</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f9f9f9;
        margin: 0;
        padding: 0;
    }
    .container {
        display: flex;
        width: 100%;
        height: calc(100vh - 30px);
        margin-top: 30px;
    }
    .menubar {
        width: 240px;
        background-color: #15d471;
        color: #ecf0f1;
        padding: 20px;
        box-sizing: border-box;
    }
    .menubar h3 {
        text-align: center;
        margin-bottom: 20px;
    }
    .menulist {
        list-style: none;
        padding: 0;
        margin: 0;
    }
    .menulist li {
        margin: 15px 0;
    }
    .menulist a {
        color: #ecf0f1;
        text-decoration: none;
        display: block;
        padding: 10px;
        border-radius: 4px;
        transition: background-color 0.3s;
    }
    .menulist a:hover {
        background-color: #2e856e;
    }
    .email-content {
        flex-grow: 1;
        padding: 30px;
        box-sizing: border-box;
        background-color: #ffffff;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    .email-header {
        border-bottom: 1px solid #eee;
        padding-bottom: 20px;
        margin-bottom: 20px;
    }
    .email-header h2 {
        margin: 0 0 10px 0;
        color: #333;
    }
    .email-metadata {
        font-size: 14px;
        color: #666;
        margin-bottom: 5px;
    }
    .email-body {
        line-height: 1.6;
    }
</style>

<div class="container">
    <aside class="menubar">
        <h3>메일 메뉴</h3>
        <ul class="menulist">
            <li><a href="#">받은메일함</a></li>
            <li><a href="#">보낸메일함</a></li>
            <li><a href="#">스팸메일함</a></li>
            <li><a href="#">임시저장함</a></li>
            <li><a href="#">즐겨찾기</a></li>
            <li><a href="#">삭제메일함</a></li>
        </ul>
    </aside>
    <main class="email-content">
        <div class="email-header">
            <h2>${email.rcvMailTitle}</h2>
            <p class="email-metadata">보낸사람: ${email.rcvMailSender}</p>
            <p class="email-metadata">받는사람: ${email.rcvMailReceiver}</p>
            <p class="email-metadata">참조: ${email.rcvMailCc}</p>
            <p class="email-metadata">날짜: ${email.rcvMailDate}</p>
            <p class="email-metadata">첨부파일: ${email.rcvMailFileName}</p>
        </div>
        <div class="email-body">
            ${email.rcvMailContent}
        </div>
    </main>
</div>

<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/@emailjs/browser@4/dist/email.min.js"></script>
<script src="${path}/resources/js/mail.js"></script>
<script>
    const empName = "${loginMember.empName}";
    const empTitle = "${loginMember.empTitle}";
</script>

<div class="container-xxl flex-grow-1 container-p-y"></div>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />