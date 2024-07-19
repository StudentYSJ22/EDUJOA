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
</head>
<body>
  <div class="layout-wrapper layout-content-navbar">
    <div class="layout-container">
      <!-- Menu -->
      <aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme">
        <div class="app-brand demo" style="margin-top: 20px;"> <!-- 로고 아래로 이동 -->
          <a href="${path }/login" class="app-brand-link">
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
            <a href="${path }/edujoa" class="menu-link">
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
            <a href="#" class="menu-link">
              <i class="menu-icon tf-icons bx bx-folder"></i>
              <div data-i18n="Layouts">문서함</div>
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
            <a href="#" class="menu-link">
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
                  <span class="badge bg-danger rounded-pill badge-notifications">5</span>
                </a>
                <ul class="dropdown-menu dropdown-menu-end">
                 
                  <li>
                    <a class="dropdown-item" href="#">
                     
                    </a>
                  </li>
                  <li>
                    <div class="dropdown-divider"></div>
                  </li>
                  <li>
                    <a class="dropdown-item" href="#">
                      <i class="bx bx-cog me-2"></i>
                      <span class="align-middle">알람 구현해쥬</span>
                    </a>
                  </li>
                  <li>
                    <a class="dropdown-item" href="#">
                      <span class="d-flex align-items-center align-middle">
                        <i class="flex-shrink-0 bx bx-credit-card me-2"></i>
                        <span class="flex-grow-1 align-middle">알람 구구 </span>
                        <span class="flex-shrink-0 badge badge-center rounded-pill bg-danger w-px-20 h-px-20">4</span>
                      </span>
                    </a>
                  </li>
                  <li>
                    <div class="dropdown-divider"></div>
                  </li>
                  
                </ul>
              </li>
              <!--/ Notifications -->
            </ul>
          </div>
        </nav>
        <!-- / Navbar -->

        <!-- Content wrapper -->
        <div class="content-wrapper">
          <!-- Content -->