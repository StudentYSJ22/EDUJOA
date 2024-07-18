<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var ="loginMember" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
   <!-- 모든 내용은 밑에있는 div안에만 설정해야함. -->
   <head>
    <meta charset="UTF-8">
    <title>메일 작성</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        #container {
            width: 80%;
            margin: auto;
        }
        #sidebar {
            float: left;
            width: 20%;
        }
        #main {
            float: right;
            width: 75%;
        }
        #main form {
            border: 1px solid #ccc;
            padding: 20px;
            background-color: #f9f9f9;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-group input, .form-group select, .form-group textarea {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .form-group textarea {
            height: 200px;
        }
        .btn-group {
            text-align: right;
        }
        .btn-group button {
            padding: 10px 15px;
            margin-left: 10px;
            border: none;
            background-color: #007bff;
            color: white;
            cursor: pointer;
        }
        .btn-group button:last-child {
            background-color: #6c757d;
        }
    </style>
</head>
<body>
    <div id="container">
        <div id="sidebar">
            <div class="profile">
                <img src="profile.jpg" alt="Profile Picture" style="width: 100%;">
                <p>${loginMember.empId }</p>
                <p>명지대 정보통신공학과</p>
                <p>1,768/2,686</p>
            </div>
            <ul>
                <li><a href="#">받은 편지함 (1)</a></li>
                <li><a href="#">보낸 편지함 (19)</a></li>
                <li><a href="#">내게 쓴 편지 (0)</a></li>
                <li><a href="#">임시 보관함 (0)</a></li>
                <li><a href="#">내 메일함</a></li>
                <li><a href="#">휴지통</a></li>
                <li><a href="#">스팸 편지함</a></li>
                <li><a href="#">자료함</a></li>
                <li><a href="#">환경 설정</a></li>
            </ul>
        </div>
        <div id="main">
            <form action="sendMail.jsp" method="post">
                <div class="form-group">
                    <label for="to">수신자:</label>
                    <input type="text" id="to" name="to">
                </div>
                <div class="form-group">
                    <label for="to">참조:</label>
                    <input type="text" id="cc" name="cc">
                </div>
                <div class="form-group">
                    <label for="subject">편지 제목:</label>
                    <input type="text" id="subject" name="subject">
                </div>
                <div class="form-group">
                    <label for="content">편지 내용:</label>
                    <textarea id="content" name="content"></textarea>
                </div>
                <div class="btn-group">
                    <button type="submit">보내기</button>
                    <button type="reset">다시 쓰기</button>
                </div>
            </form>
        </div>
    </div>
</body>
   <div class="container-xxl flex-grow-1 container-p-y">
   </div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>