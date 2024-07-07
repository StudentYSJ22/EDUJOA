<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>


<!-- FullCalendar CSS -->
<link href='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.14/main.min.css' rel='stylesheet' />
<!-- FullCalendar JS -->
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.14/index.global.min.js'></script>
<!-- Custom JS -->
<script src="<%=request.getContextPath()%>/js/ysj/schedule.js"></script>



<!-- 도전  -->
<!-- 서브메뉴 사이드바 -->
<nav id="sub-sidebar" class="col-md-2 col-lg-2 d-md-block bg-light sidebar">
  <div class="position-sticky pt-3">
    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
      <span>일정 관리</span>
    </h6>
    <ul class="nav flex-column">
      <li class="nav-item">
        <a class="nav-link active" href="#">
          월간 일정
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">
          주간 일정
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">
          일정 등록
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">
          일정 관리
        </a>
      </li>
    </ul>
  </div>
</nav>

    <!-- 메인 컨텐츠 -->
    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
      <div id='calendar' style="height: 400px;"></div>
    </main>



    

    



<!-- /도 -->

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
<script src="${path }/resources/js/ysj/schedule.js"></script>
