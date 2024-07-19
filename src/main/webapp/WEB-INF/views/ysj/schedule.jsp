<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var ="loginMember" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}"/>

<link rel="stylesheet" href="${path}/resources/css/ysj/schedule.css">

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

<div class="container-fluid">
  <div class="row">
    <nav id="sidebar" class="col-md-3 col-lg-2 d-md-block bg-light sidebar">
      <div class="position-sticky">
        <h3 class="sidebar-heading">일정관리</h3>
        <button id="addScheduleBtn" class="btn btn-primary mb-3">일정 등록</button>

        <div class="calendar-section">
          <h6>내 캘린더</h6>
          <div class="calendar-item">
            <input type="checkbox" id="myCalendar1" data-color="#FFADAD" checked>
            <label for="myCalendar1">내 일정(기본)</label>
          </div>
          <div class="calendar-item">
            <input type="checkbox" id="myCalendar2" data-color="#FDFFB6">
            <label for="myCalendar2">개인 일정</label>
          </div>

          <h6>업무 캘린더</h6>
          <div class="calendar-item">
            <input type="checkbox" id="myCalendar3" data-color="#CAFFBF" checked>
            <label for="myCalendar3">운영팀 일정</label>
          </div>
          <div class="calendar-item">
            <input type="checkbox" id="myCalendar4" data-color="#A0C4FF">
            <label for="myCalendar4">강사 일정</label>
          </div>
          <div class="calendar-item">
            <input type="checkbox" id="myCalendar5" data-color="#FFC6FF" checked>
            <label for="myCalendar5">공지 일정</label>
          </div>
        </div>
      </div>
    </nav>

    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
      <div id='calendar-container'>
        <div id='calendar'></div>
      </div>
    </main>
  </div>
</div>

<!-- 일정 추가 모달 -->
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
        <form id="eventForm" action="${path}/schedule/addevent.do" method="post">
          <div class="form-group">
            <label for="empId">직원 ID</label>
            <input type="text" class="form-control" id="empId" name="empId" value="${loginMember.empId}" readonly>
          </div>
          <div class="form-group">
            <label for="schTitle">제목</label>
            <input type="text" class="form-control" id="schTitle" name="schTitle" required>
          </div>
          <div class="form-group">
            <label for="schContent">내용</label>
            <textarea class="form-control" id="schContent" name="schContent" rows="3"></textarea>
          </div>
          <div class="form-group">
            <label for="schStart">시작</label>
            <input type="datetime-local" class="form-control" id="schStart" name="schStart" required>
          </div>
          <div class="form-group">
            <label for="schEnd">종료</label>
            <input type="datetime-local" class="form-control" id="schEnd" name="schEnd">
          </div>
          <div class="form-group">
            <label for="schType">일정 종류</label>
            <select class="form-control" id="schType" name="schType">
              <option value="일반 일정">일반 일정</option>
              <option value="반복 일정">반복 일정</option>
            </select>
          </div>
          <div class="form-group">
            <label for="calendarType">캘린더</label>
            <select class="form-control" id="calendarType" name="calendarType" onchange="updateEventColor()">
              <option value="내 일정" data-color="#FFADAD">내 일정(기본)</option>
              <option value="개인 일정" data-color="#FDFFB6">개인 일정</option>
              <option value="운영팀 일정" data-color="#CAFFBF">운영팀 일정</option>
              <option value="강사 일정" data-color="#A0C4FF">강사 일정</option>
              <option value="공지 일정" data-color="#FFC6FF">공지 일정</option>
            </select>
          </div>
          <div class="form-group">
            <label for="detailSharers">참여자</label>
            <div class="input-group">
              <input type="text" class="form-control" id="detailSharers" name="detailSharers" placeholder="참여자 선택" readonly>
              <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="button" id="selectParticipants">선택</button>
              </div>
            </div>
          </div>
          <input type="hidden" id="schColor" name="schColor" value="#FFADAD">
          <button type="submit" class="btn btn-primary">저장</button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
        </form>
      </div>
    </div>
  </div>
</div>

<!-- 일정 상세 모달 -->
<div class="modal fade" id="eventDetailModal" tabindex="-1" role="dialog" aria-labelledby="eventDetailModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="eventDetailModalLabel">일정 상세</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form id="eventDetailForm">
          <div class="form-group">
            <label for="detailEmpId">직원 ID</label>
            <input type="text" class="form-control" id="detailEmpId" readonly>
          </div>
          <div class="form-group">
            <label for="detailSchTitle">제목</label>
            <input type="text" class="form-control" id="detailSchTitle" required>
          </div>
          <div class="form-group">
            <label for="detailSchContent">내용</label>
            <textarea class="form-control" id="detailSchContent" rows="3"></textarea>
          </div>
          <div class="form-group">
            <label for="detailSchStart">시작</label>
            <input type="datetime-local" class="form-control" id="detailSchStart" required>
          </div>
          <div class="form-group">
            <label for="detailSchEnd">종료</label>
            <input type="datetime-local" class="form-control" id="detailSchEnd">
          </div>
          <div class="form-group">
            <label for="detailSchType">일정 종류</label>
            <select class="form-control" id="detailSchType">
              <option value="일반 일정">일반 일정</option>
              <option value="반복 일정">반복 일정</option>
            </select>
          </div>
          <div class="form-group">
            <label for="detailCalendarType">캘린더</label>
            <select class="form-control" id="detailCalendarType" onchange="updateDetailEventColor()">
              <option value="내 일정" data-color="#FFADAD">내 일정(기본)</option>
              <option value="개인 일정" data-color="#FDFFB6">개인 일정</option>
              <option value="운영팀 일정" data-color="#CAFFBF">운영팀 일정</option>
              <option value="강사 일정" data-color="#A0C4FF">강사 일정</option>
              <option value="공지 일정" data-color="#FFC6FF">공지 일정</option>
            </select>
          </div>
          <input type="hidden" id="detailSchColor" name="detailSchColor" value="#FFADAD">
          <button type="button" id="editEventBtn" class="btn btn-secondary">수정</button>
          <button type="submit" id="saveEventBtn" class="btn btn-primary" style="display: none;">저장</button>
          <button type="button" id="deleteEventBtn" class="btn btn-danger">삭제</button>
        </form>
      </div>
    </div>
  </div>
</div>

<!-- 참여자 선택 슬라이드 패널 -->
<div id="participantPanel" class="participant-panel">
  <h3>참여자 선택</h3>
  <div class="participant-list">
    <h4>직원 목록</h4>
    <ul id="empList"></ul>
  </div>
  <div class="selected-participants">
    <h4>참여자</h4>
    <ul id="selectedEmpList"></ul>
  </div>
  <button id="confirmParticipants" class="btn btn-primary">확인</button>
  <button id="cancelParticipants" class="btn btn-secondary">취소</button>
</div>

<script>
  const path = '${pageContext.request.contextPath}';
  console.log(path);
</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>