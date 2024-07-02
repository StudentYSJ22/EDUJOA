<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<link rel="stylesheet" href="${path }/resources/css/chs/approval/flagging_ing.css">
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="chs-custom">
		<jsp:include page="/WEB-INF/views/chs/approval/left_approval.jsp"/>
		<div class="chs-table">
			<div class="chs-thead">
				<p>진행중</p>
			</div>
			<div class="chs-thead-2">
				<select name="new-old">
					<option value="new">최근 순</option>
					<option value="old">오래된 순</option>
				</select>
				<select name="rowbounds">
					<option value="10">10</option>
					<option value="5">5</option>
					<option value="3">3</option>
				</select>
			</div>
			<div class="chs-tbody">
				<div>
					<ul class="chs-tbody-header">
						<li style="width:10%">양식명</li>
						<li style="width:30%">제목</li>
						<li style="width:10%">기안자</li>
						<li style="width:20%">기안일</li>
						<li style="width:10%">상태</li>
						<li style="width:20%">최근 결재일</li>
					</ul>
					<ul class="chs-tbody-body">
						<c:forEach var="a" items="${approvals }">
							<li style="width:10%; font-weight:bold">
								<c:if test="${a.apvType == 0 }">
									휴가 신청서
								</c:if>
							</li>
							<li style="width:30%">${a.apvTitle }</li>
							<li style="width:10%">${a.employee.empName }</li>
							<li style="width:20%">${a.apvDate }</li>
							<li style="width:10%">
								<c:if test="${a.apvStatus == 0}">
									결재 중
								</c:if>
								<c:if test="${a.apvStatus == 1}">
									반려
								</c:if>
								<c:if test="${a.apvStatus == 2}">
									결재 완료
								</c:if>
							</li>
							<li style="width:20%">
								<!-- 최근 결재된 날짜 구해야함. -->
							</li>
						</c:forEach>
					</ul>
				</div>
		${pageBar }
			</div>
		</div>
	</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>