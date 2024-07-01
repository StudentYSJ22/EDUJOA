<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<link rel="stylesheet" href="${path }/resources/css/chs/approval/flagging_ing.css">
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="chs-custom">
		<jsp:include page="/WEB-INF/views/chs/approval/left_approval.jsp"/>
		<div class="chs-table">
			<div class="chs-thead">
				<p>반려</p>
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
					<%for(int i = 0; i < 10; i++){ %>
					<ul class="chs-tbody-body">
						<li style="width:10%; font-weight:bold">휴가 신청서</li>
						<li style="width:30%">이리저리 휴가를 씁니다.</li>
						<li style="width:10%">최헌수</li>
						<li style="width:20%">2024-07-01</li>
						<li style="width:10%">결재 중</li>
						<li style="width:20%">2024-07-02</li>
					</ul>
					<%} %>
				</div>
			</div>
		</div>
		
	</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>