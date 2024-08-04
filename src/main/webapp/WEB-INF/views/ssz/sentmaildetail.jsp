<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="loginMember" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}" />
<c:set var="path" value="${pageContext.request.contextPath}" />
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

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
    .btn-group button{
    	background-color: #4CAF50;
    	color: white;
    }
    .btn-reply, .btn-delete {
	padding: 5px 10px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 12px;
	}.btn-delete {
		margin-left: 10px;
	}
</style>

<div class="container">
    <aside class="menubar">
        <ul class="menulist">
				<li><a href="${path }/mailbox" id="inbox">받은메일함</a></li>
				<li><a href="${path }/mailbox/sentbox">보낸메일함</a></li>
				<li><a href="#">스팸메일함</a></li>
				<li><a href="${path }/mailbox/tempbox">임시저장함</a></li>
				<li><a href="#">즐겨찾기</a></li>
				<li><a href="${path }/mailbox/deletebox" id="trash">삭제메일함</a></li>
			</ul>
    </aside>
    <main class="email-content">
    <div class="btn-group" style="margin-bottom:15px; width:100%;">
    	<button class="btn-reply" onclick="replyEmail()">답장</button>
        <button class="btn-delete" onclick="deleteEmail()">삭제</button>
    </div>
        <div class="email-header">
            <h2 id="emailTitle">${email.mailTitle}</h2>
            <p class="email-metadata" id="emailSender">발신인: ${email.senderEmail}</p>
            <p class="email-metadata">수신인: ${email.sendto}</p>
            <p class="email-metadata">참조: ${email.ccto}</p>
            <p class="email-metadata">날짜: ${email.mailDate}</p>
            <p class="email-metadata">첨부파일: 
                <c:choose>
                    <c:when test="${email.mailContent != null}">
                        <a href="${path}/resources/attachments/${email.mailContent}" target="_blank">${email.mailContent}</a>
                    </c:when>
                    <c:otherwise>없음</c:otherwise>
                </c:choose>
            </p>
        </div>
        <div class="email-body"id="emailContent">
            ${email.mailContent}
        </div>
    </main>
</div>

<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/@emailjs/browser@4/dist/email.min.js"></script>
<script src="${path}/resources/js/maildetail.js"></script>
<script>
    const empName = "${loginMember.empName}";
    const empTitle = "${loginMember.empTitle}";
    var emailTitle=document.getElementById("emailTitle").textContent;
    var emailSenderRaw = document.getElementById("emailSender").textContent;
    var emailContent=document.getElementById("emailContent").textContent;
    var emailSender = emailSenderRaw.replace("발신인: ", "").trim();
    var contextPath = "${path}";
</script>

<div class="container-xxl flex-grow-1 container-p-y"></div>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />