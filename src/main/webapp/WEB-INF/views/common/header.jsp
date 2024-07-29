<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<c:set var ="loginMember" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}"/>
<html lang="en" class="light-style layout-menu-fixed" dir="ltr" data-theme="theme-default" data-assets-path="${path}/resources/common/assets/" data-template="vertical-menu-template-free">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0" />
  <title>에듀조아</title>
  <meta name="description" content="" />

  <!-- Favicon -->
  <!-- <link rel="icon" type="image/x-icon" href="${path}/resources/common/assets/img/favicon/favicon.ico" /> -->
  <link rel="icon" type="image/x-icon" href="${path}/resources/common/assets/img/edufavi.png"  />

  <!-- Fonts -->
  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link href="https://fonts.googleapis.com/css2?family=Public+Sans:wght@300;400;500;600;700&display=swap" rel="stylesheet" />

  <!-- Icons -->
  <link rel="stylesheet" href="${path}/resources/common/assets/vendor/fonts/boxicons.css" />

  <!-- Core CSS -->
  <link rel="stylesheet" href="${path}/resources/common/assets/vendor/css/core.css" class="template-customizer-core-css" />
  <link rel="stylesheet" href="${path}/resources/common/assets/vendor/css/theme-default.css" class="template-customizer-theme-css" />
  <link rel="stylesheet" href="${path}/resources/common/assets/css/demo.css" />

  <!-- Custom CSS -->
  <link rel="stylesheet" href="${path}/resources/common/assets/css/custom.css" /> <!-- custom.css에 직접 스타일을 추가 -->

  <!-- Vendors CSS -->
  <link rel="stylesheet" href="${path}/resources/common/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css" />
  <link rel="stylesheet" href="${path}/resources/common/assets/vendor/libs/apex-charts/apex-charts.css" />

  <!-- Helpers -->
  <script src="${path}/resources/common/assets/vendor/js/helpers.js"></script>
  <script src="${path}/resources/common/assets/js/config.js"></script>
  <style>
  	.alarm-item {
    padding: 10px;
    border-bottom: 1px solid #ddd;
    position: relative; /* Position relative for delete button */
    padding-right: 35px; /* Make space for delete button */
}
.alarm-item:last-child {
    border-bottom: none;
}
.alarm-item:hover {
    background-color: lightgray;
    cursor: pointer;
}
.alarm-list {
    max-height: 300px; /* 고정된 높이 */
    overflow-y: auto; /* 높이를 초과하면 스크롤 */
}
.delete-btn {
    position: absolute;
    top: 50%;
    right: 5px;
    transform: translateY(-50%);
    display: none;
    background: transparent;
    border: none;
    font-size: 12px;
    color: #888;
    cursor: pointer;
}
.alarm-item:hover .delete-btn {
    display: block;
}
  </style>
</head>
<body>
  <div class="layout-wrapper layout-content-navbar">
    <div class="layout-container">
      <!-- Menu -->
      <aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme">
        <div class="app-brand demo" style="margin-top: 20px;"> <!-- 로고 아래로 이동 -->
          <a href="${path }/" class="app-brand-link">
            <span class="app-brand-logo demo">
              <img src="${path}/resources/common/assets/img/edulogo.png" alt="Logo" width="170" height="70">
            </span>
            <!-- <span class="app-brand-text demo menu-text fw-bolder ms-2">에듀조아</span> -->
          </a>
          <a href="javascript:void(0);" class="layout-menu-toggle menu-link text-large ms-auto d-block d-xl-none">
            <i class="bx bx-chevron-left bx-sm align-middle"></i>
          </a>
        </div>

        <div class="menu-inner-shadow"></div>

        <ul class="menu-inner py-1" style="margin-top: 20px;"> <!-- 메뉴 아래로 이동 -->
          <!-- Dashboard -->
          <li class="menu-item active">
            <!-- <a href="/WEB-INF/views/index.jsp" class="menu-link">  -->
            <a href="${path }/" class="menu-link">
              <i class="menu-icon tf-icons bx bx-home-circle"></i>
              <div data-i18n="Analytics">홈</div>
            </a>
          </li>

          <!-- Other menu items -->
          <li class="menu-item">
            <a href="${path }/approval/insert" class="menu-link">
              <i class="menu-icon tf-icons bx bx-bar-chart-alt"></i>
              <div data-i18n="Layouts">전자결재</div>
            </a>
          </li>
          <li class="menu-item">
            <a href="${path}/employee/selectall?empYn=0" class="menu-link">
              <i class="menu-icon tf-icons bx bx-group"></i>
              <div data-i18n="Layouts">조직관리</div>
            </a>
          </li>
          <li class="menu-item">
            <a href="${path }/attendance/attendance.do" class="menu-link">
              <i class="menu-icon tf-icons bx bx-time"></i>
              <div data-i18n="Layouts">근태관리</div>
            </a>
          </li>
          <li class="menu-item">
            <a href="${path }/salary/salary.do" class="menu-link">
              <i class="menu-icon tf-icons bx bx-money"></i>
              <div data-i18n="Layouts">급여</div>
            </a>
          </li>
          <li class="menu-item">
            <a href="${path }/schedule/schedule.do" class="menu-link">
              <i class="menu-icon tf-icons bx bx-calendar"></i>
              <div data-i18n="Layouts">일정</div>
            </a>
          </li>
          <li class="menu-item">
            <a href="${path }/noticeboard/board" class="menu-link">
              <i class="menu-icon tf-icons bx bx-message"></i>
              <div data-i18n="Layouts">게시판</div>
            </a>
          </li>
          <li class="menu-item">
            <a href="${path }/chatting/chattestview?empId=${loginMember.empId}" class="menu-link">
              <i class="menu-icon tf-icons bx bx-chat"></i>
              <div data-i18n="Layouts">메신저</div>
            </a>
          </li>
          <li class="menu-item">
            <a href="#" class="menu-link">
              <i class="menu-icon tf-icons bx bx-envelope"></i>
              <div data-i18n="Layouts">메일</div>
            </a>
          </li>
          <li class="menu-item">
            <a href="#" class="menu-link">
              <i class="menu-icon tf-icons bx bx-cog"></i>
              <div data-i18n="Layouts">정보수정</div>
            </a>
          </li>
          <li class="menu-item">
            <a href="${path }/logout.do" class="menu-link">
              <i class="menu-icon tf-icons bx bx-log-out"></i>
              <div data-i18n="Layouts">로그아웃</div>
            </a>
          </li>
        </ul>
      </aside>

      <!-- / Menu -->

      <!-- Layout container -->
      <div class="layout-page">
        <!-- Navbar -->
        <nav class="layout-navbar container-xxl navbar navbar-expand-xl navbar-detached align-items-center bg-navbar-theme" id="layout-navbar">
          <div class="layout-menu-toggle navbar-nav align-items-xl-center me-3 me-xl-0 d-xl-none">
            <a class="nav-item nav-link px-0 me-xl-4" href="javascript:void(0)">
              <i class="bx bx-menu bx-sm"></i>
            </a>
          </div>
          <div class="navbar-nav-right d-flex align-items-center" id="navbar-collapse">
            <!-- Search -->
            <div class="navbar-nav align-items-center">
              <div class="nav-item d-flex align-items-center">
                <i class="bx bx-search fs-4 lh-0"></i>
                <input type="text" class="form-control border-0 shadow-none" placeholder="Search..." aria-label="Search..." />
              </div>
            </div>
            <!-- /Search -->
            <ul class="navbar-nav flex-row align-items-center ms-auto">
              <!-- Notifications -->
              <li class="nav-item navbar-dropdown dropdown-notifications dropdown">
                <a class="nav-link dropdown-toggle hide-arrow" href="javascript:void(0);" data-bs-toggle="dropdown">
                  <i class="bx bx-bell bx-sm"></i>
                  <span class="badge bg-danger rounded-pill badge-notifications">${loginMember.alarm.size() }</span>
                </a>
                <ul class="dropdown-menu dropdown-menu-end">
                  <!-- 알람 출력 -->
                  <c:forEach var="a" items="${loginMember.alarm }">
                  	<li>
                  		 <div class="alarm-item">
	                        <span>${a.alarmContent}</span>
	                         <button class="btn btn-sm  delete-btn" onclick="deleteAlarm(${a.alarmId})">X</button>
            			</div>
                  	</li>
                  </c:forEach>
                  
                </ul>
              </li>
              <!--/ Notifications -->
            </ul>
          </div>
        </nav>
         <script>
         if (typeof(EventSource) !== "undefined") {
        	 let loginEmpId = "${loginMember.empId}";
             const eventSource = new EventSource('/stream?userId=' + loginEmpId);  // SSE 연결 설정
             eventSource.onopen = function(event) {
            	    console.log("SSE connection opened", event);
            	};
             eventSource.onmessage = function(event) {
            	 console.log(event);
                 const alarmCount = document.querySelector('.badge-notifications');
                 const alarmList = document.getElementById('alarmList');
                 const newEvent = document.createElement('li');
                 const alarmId = Date.now();  // You can replace this with the actual ID from the event data
                 newEvent.id = `alarm-${alarmId}`;
                 newEvent.innerHTML = `
                     <div class="alarm-item">
                         <span>${event.data}</span>
                         <button class="btn btn-sm delete-btn" onclick="deleteAlarm(${alarmId})">X</button>
                     </div>`;
                 alarmList.appendChild(newEvent);
                 alarmCount.textContent = (parseInt(alarmCount.textContent) || 0) + 1;
             };
             eventSource.onerror = function(event) {
            	    console.error("SSE error:", event);
            	};
             eventSource.addEventListener("ALERT", function(event) {
                 const alarmCount = document.querySelector('.badge-notifications');
                 const alarmList = document.getElementById('alarmList');
                 const newEvent = document.createElement('li');
                 const alarmId = Date.now();  // You can replace this with the actual ID from the event data
                 newEvent.id = `alarm-${alarmId}`;
                 newEvent.innerHTML = `
                     <div class="alarm-item">
                         <span>${event.data}</span>
                         <button class="btn btn-sm delete-btn" onclick="deleteAlarm(${alarmId})">X</button>
                     </div>`;
                 alarmList.appendChild(newEvent);
                 alarmCount.textContent = (parseInt(alarmCount.textContent) || 0) + 1;
             });
         } else {
             console.log("Your browser does not support SSE.");
         }

         function deleteAlarm(alarmId) {
             const alarmElement = document.getElementById(`alarm-${alarmId}`);
             if (alarmElement) {
                 alarmElement.remove();
                 const alarmCount = document.querySelector('.badge-notifications');
                 alarmCount.textContent = (parseInt(alarmCount.textContent) || 0) - 1;
                 
                 // 서버에서 알람 삭제
                 fetch('/alarms/delete/' + alarmId, {
                     method: 'DELETE'
                 });
             }
         }
     </script>
        <!-- / Navbar -->

        <!-- Content wrapper -->
        <div class="content-wrapper">
          <!-- Content -->