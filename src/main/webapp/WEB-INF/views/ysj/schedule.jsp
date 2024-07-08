<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<link rel="stylesheet" href="${path }/resources/css/ysj/schedule.css">

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- Bootstrap CSS -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" />
<!-- Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>

<!-- FullCalendar CSS -->
<link href='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.14/main.min.css' rel='stylesheet' />
<!-- FullCalendar JS -->
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.14/index.global.min.js'></script>

<!-- Custom JS -->
<script src="<%=request.getContextPath()%>/resources/js/ysj/schedule.js"></script>

<!-- 사이드바 수정 -->
<nav id="sidebar" class="col-md-3 col-lg-2 d-md-block bg-light sidebar">
    <div class="position-sticky">
        <h3 class="sidebar-heading">일정관리</h3>
        <button id="addScheduleBtn" class="btn btn-primary mb-3">일정 등록</button>
        
        <div class="calendar-section">
        <!-- 캘린더 체크박스  -->
            <h6>내 캘린더</h6>
            <div class="calendar-item">
                <input type="checkbox" id="myCalendar1" checked>
                <label for="myCalendar1">내 일정(기본)</label>
            </div>
            <div class="calendar-item">
                <input type="checkbox" id="myCalendar2">
                <label for="myCalendar2">개인 일정</label>
            </div>
            
            <!-- 추가 캘린더 체크박스  -->
             <h6>업무 캘린더</h6>
            <div class="calendar-item">
                <input type="checkbox" id="myCalendar3" checked>
                <label for="myCalendar3">운영팀 일정 </label>
            </div>
            <div class="calendar-item">
                <input type="checkbox" id="myCalendar4">
                <label for="myCalendar4">강사 일정</label>
            </div>
             <div class="calendar-item">
                <input type="checkbox" id="myCalendar5" checked>
                <label for="myCalendar5">공지 일정 </label>
            </div>
            
        </div>
        <!-- 다른 캘린더 섹션들... -->
    </div>
</nav>

<!-- 메인 컨텐츠 -->
<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
    <div id='calendar-container' style="width: 80%; margin: 0 auto;">
        <div id='calendar'></div>
    </div>
</main>

<!-- 일정 추가 모달 수정 -->
<div class="modal fade" id="addEventModal" tabindex="-1" role="dialog" aria-labelledby="addEventModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addEventModalLabel">일정 쓰기</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="eventForm">
                    <div class="form-group">
                        <label for="eventCalendar">캘린더</label>
                        <select class="form-control" id="eventCalendar">
                            <option value="myCalendar1">내 일정(기본)</option>
                            <option value="myCalendar2">개인 일정</option>
                            <option value="myCalendar3">운영팀 일정</option>
                            <option value="myCalendar4">강사 일정</option>
                            <option value="myCalendar5">공지 일정</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="eventStart">시작</label>
                        <input type="date" class="form-control" id="eventStartDate" required>
                        <input type="time" class="form-control mt-2" id="eventStartTime" required>
                    </div>
                    <div class="form-group">
                        <label for="eventEnd">종료</label>
                        <input type="date" class="form-control" id="eventEndDate">
                        <input type="time" class="form-control mt-2" id="eventEndTime">
                    </div>
                    <div class="form-group">
                        <input type="checkbox" id="eventAllDay">
                        <label for="eventAllDay">종일</label>
                    </div>
                    <div class="form-group">
                        <label for="eventTitle">제목</label>
                        <input type="text" class="form-control" id="eventTitle" required>
                    </div>
                    <div class="form-group">
                        <label for="eventContent">내용</label>
                        <textarea class="form-control" id="eventContent" rows="3"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="eventLocation">장소</label>
                        <input type="text" class="form-control" id="eventLocation">
                    </div>
                    <button type="submit" class="btn btn-primary">저장</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
