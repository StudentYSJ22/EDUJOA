<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
   <!-- 모든 내용은 밑에있는 div안에만 설정해야함. -->
   <div>
   	<form action="${pageContext.request.contextPath }/member/loginpage" method="post">
   		<input type="text" name="username"><br/>
   		<input type="password" name="password"><br/>
   		<input type="submit" value="로그인">
   	</form>
   </div>
   <div class="container-xxl flex-grow-1 container-p-y">
   </div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>