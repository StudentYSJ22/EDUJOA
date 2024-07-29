<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<link rel="stylesheet" href="${path }/resources/css/chs/personnel/insert_emp_personnel.css">
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="chs-custom">
    <jsp:include page="/WEB-INF/views/chs/personnel/left_personnel.jsp"/>
    <div class="container" style="width: 100%; display: flex; justify-content: space-around; height: 95%;">
        <!-- 강의 등록 폼 -->
        <div class="form-container" style="width: 35%;">
            <h2 class="title">과목 등록</h2>
            <form method="post" action="${path }/tutor/insertsubjectend" id="tutor-form">
                <hr>
                <div class="form-group">
                    <label for="subName">과목명</label>
                    <input type="text" id="subName" name="subName" required>
                </div>
                <div class="form-group" style="height:30%">
                    <label for="subDetail">과목 설명</label>
                    <textarea id="subDetail" name="subDetail" required style="resize: none; height: 150px;"></textarea>
                </div>
                <div class="btn-container">
                    <input type="submit" value="등록하기" style="">
                    <button type="button" onclick="resetForm('tutor-form')">취소하기</button>
                </div>
            </form>
        </div>
        <!-- 반 등록 폼 -->
        <div class="form-container" style="width: 35%;">
            <h2 class="title">반 등록</h2>
            <form method="post" action="${path }/tutor/insertclassend" id="class-form">
                <hr>
                <div class="form-group">
                    <label for="ttId">강사</label>
                    <select id="ttId" name="ttId">
                        <c:forEach var="t" items="${tutors }">
                            <option value="${t.ttId }">${t.ttName }</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="classOpen">개강일</label>
                    <input type="date" id="classOpen" name="classOpen">
                </div>
                <div class="form-group">
                    <label for="classClose">종강일</label>
                    <input type="date" id="classClose" name="classClose">
                </div>
                <div class="btn-container">
                    <input type="submit" value="등록하기">
                    <button type="button" onclick="resetForm('class-form')">취소하기</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div class='msg'>${msg }</div>
<script>
    window.onload = function() {
        if ($('.msg').text() != '') {
            alert($('.msg').text());
        }
    }
    function resetForm(formId) {
        document.getElementById(formId).reset();
    }
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>