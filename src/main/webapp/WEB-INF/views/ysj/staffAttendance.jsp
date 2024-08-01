<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/common/header.jsp" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ysj/staffAttendance.css">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" />

<div class="container mt-5">
    <h2>직원 근태 현황</h2>
    <form id="searchForm">
        <div class="form-row">
            <div class="form-group col-md-3">
                <label for="empId">사원 ID</label>
                <input type="text" class="form-control" id="empId" placeholder="사원 ID">
            </div>
            <div class="form-group col-md-3">
                <label for="empName">사원 이름</label>
                <input type="text" class="form-control" id="empName" placeholder="사원 이름">
            </div>
            <div class="form-group col-md-3">
                <label for="status">상태</label>
                <select class="form-control custom-select" id="status">
                    <option value="">전체</option>
                    <option value="출근">출근</option>
                    <option value="지각">지각</option>
                    <option value="결근">결근</option>
                </select>
            </div>
            <div class="form-group col-md-3">
                <label for="startDate">시작 날짜</label>
                <input type="date" class="form-control" id="startDate">
            </div>
            <div class="form-group col-md-3">
                <label for="endDate">종료 날짜</label>
                <input type="date" class="form-control" id="endDate">
            </div>
        </div>
        <button type="button" class="btn btn-primary" onclick="searchStaffAttendance()">검색</button>
    </form>
    
    <div class="d-flex justify-content-end mt-3">
        <label for="sortOrder" class="mr-2">정렬:</label>
        <select id="sortOrder" class="form-control w-auto custom-select" onchange="sortStaffAttendance()">
            <option value="">기본</option>
            <option value="nameAsc">이름 오름차순</option>
            <option value="nameDesc">이름 내림차순</option>
            <option value="dateDesc">날짜 최신순</option>
            <option value="dateAsc">날짜 오래된순</option>
        </select>
    </div>
    
    <div class="card mt-3">
        <div class="table-responsive text-nowrap">
            <table id="attendanceTable" class="table table-hover table-rounded">
                <thead>
                    <tr>
                        <th>사원 ID</th>
                        <th>사원 이름</th>
                        <th>날짜</th>
                        <th>출근 시간</th>
                        <th>퇴근 시간</th>
                        <th>상태</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- 데이터가 동적으로 추가될 자리 -->
                </tbody>
            </table>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script>
    var path = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/resources/js/ysj/staffAttendance.js"></script>
