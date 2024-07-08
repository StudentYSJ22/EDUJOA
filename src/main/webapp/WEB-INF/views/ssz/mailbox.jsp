<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<!-- 모든 내용은 밑에있는 div안에만 설정해야함. -->
<head>
    <meta charset="UTF-8">
    <title>받은 편지함</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .clearfix::after {
            content: "";
            clear: both;
            display: table;
        }
        #container-wrapper {
            width: 80%;
            margin: auto;
        }
        #sidebar {
            float: left;
            width: 20%;
            margin-right: 10px;
            margin-top: 50px;
            margin-left: -150px;
        }
        #container {
            float: left;
            width: 75%;
            padding-left: -10px; /* Adjust this value to align the table correctly */
            margin-top: 50px;
        }
        table {
            width: calc(100% - 30px); /* Adjust this value to match the padding and margin */
            border-collapse: collapse;
            margin: 0 auto; /* Center the table */
        }
        table, th, td {
            border: 1px solid #ccc;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .pagination {
            text-align: center;
            margin-top: 20px;
        }
        .pagination a {
            margin: 0 5px;
            text-decoration: none;
        }
        .search {
            display: inline-block;
            margin-left: 20px;
            vertical-align: top;
        }
        .search input[type="text"] {
            padding: 5px;
            margin-right: 5px;
        }
        .search input[type="submit"] {
            padding: 5px 10px;
        }
        #table td{
        	background-color: white;
        }
        .header-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        #table{
        	margin-top:15px;
        	margin-right: 1000px;
        }
    </style>
</head>
<body>
    <div id="container-wrapper" class="clearfix">
        <div id="sidebar">
            <div class="profile">
                <img src="profile.jpg" alt="Profile Picture" style="width: 100%;">
                <p></p>
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
        <div id="container">
            <div class="header-container">
                <h4>받은 편지함</h4>
                <div class="search">
                    <form action="searchMail.jsp" method="get">
                        <label for="search">메일 검색:</label>
                        <input type="text" id="search" name="search">
                        <input type="submit" value="찾기">
                    </form>
                </div>
            </div>
            <table id="table">
                <thead>
                    <tr>
                        <th style="width:50px;">선택</th>
                        <th>보낸이</th>
                        <th>제목</th>
                        <th>수신 날짜</th>
                        <th>크기</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><input type="checkbox"></td>
                        <td>도토리</td>
                        <td>최종 결재자 의견 추가 됨</td>
                        <td>2006.01.04 14:59</td>
                        <td>4 KB</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox"></td>
                        <td>정현정</td>
                        <td>[답장] BF GW 6.7.3.1 Patch ...</td>
                        <td>2004.12.16 11:28</td>
                        <td>4 KB</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox"></td>
                        <td>토토로</td>
                        <td>[답장] 2004년 12월 셋째 주 주간 업무 보고</td>
                        <td>2004.12.16 10:56</td>
                        <td>4 KB</td>
                    </tr>
                </tbody>
            </table>

            <div class="pagination" style="margin-left: 400px;">
                <a href="#">1</a>
                <a href="#">2</a>
                <a href="#">3</a>
                <a href="#">4</a>
                <a href="#">5</a>
            </div>
        </div>
    </div>
</body>
<div class="container-xxl flex-grow-1 container-p-y">
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
