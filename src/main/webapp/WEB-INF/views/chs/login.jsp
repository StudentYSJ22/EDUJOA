<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form method="post" action="${pageContext.request.contextPath }/chs/logintest.do">
	<input type="text" name="id">
	<input type="password" name="password">
	<input type="submit" value="전송"> 
</form>