<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="loginMember"
	value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}" />
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<c:set var="path" value="${pageContext.request.contextPath }" />
<title>공지사항</title>
<style>
:root {
	--primary-color: #4CAF50;
	--secondary-color: #45a049;
	--background-color: #f1f8e9;
	--text-color: #333;
}

body {
	font-family: 'Noto Sans KR', sans-serif;
	background-color: var(--background-color);
	color: var(--text-color);
	line-height: 1.6;
	margin: 0;
	padding: 20px;
}

.container {
	max-width: 1000px;
	margin: 0 auto;
	margin-top: 30px;
	background-color: white;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	padding: 20px;
}

h1 {
	color: var(--primary-color);
	text-align: center;
	margin-bottom: 30px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 20px;
}

th, td {
	padding: 12px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

th {
	background-color: var(--primary-color);
	color: white;
	font-weight: bold;
}

tr:hover {
	background-color: #f5f5f5;
}

.btn {
	background-color: var(--primary-color);
	color: white;
	padding: 10px 15px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.btn:hover {
	background-color: var(--secondary-color);
}

@media ( max-width : 768px) {
	table, thead, tbody, th, td, tr {
		display: block;
	}
	thead tr {
		position: absolute;
		top: -9999px;
		left: -9999px;
	}
	tr {
		margin-bottom: 15px;
	}
	td {
		border: none;
		position: relative;
		padding-left: 50%;
	}
	td:before {
		position: absolute;
		top: 6px;
		left: 6px;
		width: 45%;
		padding-right: 10px;
		white-space: nowrap;
		content: attr(data-label);
		font-weight: bold;
	}
}
</style>
</head>
<body>
	<div class="container">
		<h1>공지사항</h1>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<!-- 여기에 JSTL이나 AJAX로 데이터를 추가할 수 있습니다 -->
			</tbody>
		</table>
		<button class="btn">글쓰기</button>
	</div>
</body>
<script>
	const empId = "${loginMember.empId}";
	console.log(empId);
</script>
<script src="${path }/resources/js/noticeboard.js"></script>
<div class="container-xxl flex-grow-1 container-p-y"></div>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />