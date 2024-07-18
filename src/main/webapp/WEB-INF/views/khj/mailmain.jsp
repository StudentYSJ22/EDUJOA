<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mail Summary</title>
</head>
<body>
    <h1>Mail Summary</h1>
    <p>제 목: ${mail.mailTitle}</p>
    <p>발신인: ${mail.senderEmail}</p>
    <p>수신인: ${mail.mailDate}</p>
</body>
</html>