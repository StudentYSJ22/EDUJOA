<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	    <title>게시판 상세보기</title>
    
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f0f0f0;
    margin: 0;
    padding: 20px;
}

.post-container {
    background-color: white;
    border-radius: 8px;
    padding: 20px;
    max-width: 1500px;
    margin: 0 auto;
    box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    margin-top: 30px;
}

.post-header {
    border-bottom: 1px solid #e0e0e0;
    padding-bottom: 10px;
    margin-bottom: 20px;
}

.post-title {
    margin: 0 0 10px 0;
    font-size: 18px;
    color: #333;
}

.post-info {
    font-size: 12px;
    color: #666;
}

.post-info span {
    margin-right: 10px;
}

.post-content {
    min-height: 200px;
}

.post-footer {
    margin-top: 20px;
    text-align: right;
}

.btn-share, .btn-report {
    padding: 5px 10px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 12px;
    margin-left: 10px;
}

.btn-share {
    background-color: #4CAF50;
    color: white;
}

.btn-report {
    background-color: #f44336;
    color: white;
}
    </style>
</head>
<body>
    <div class="post-container" style="width:1900px;">
    
        <div class="post-header">
            <h1 class="post-title">${board.boardTitle }</h1>
            <div class="post-info">
                <span class="author">작성자 ${board.employee.empName }</span><br>
                <span class="date">작성일 ${board.boardDate }</span>
                <span class="views">조회수 ${board.boardCount }</span>
            </div>
        </div>
        <div class="post-content">
            ${board.boardContent }
        </div>
        <div class="post-footer">
            <button class="btn-share">공유</button>
            <button class="btn-report">신고</button>
        </div>
    </div>
</body>
	<script>
		const MboardId="";
	</script>
   <div class="container-xxl flex-grow-1 container-p-y">
   </div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>