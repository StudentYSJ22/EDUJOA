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
				<div>
				</div>
				<div>
					<select id="new-old" name="new-old">
						<option value="new">최근 순</option>
						<option value="old">오래된 순</option>
					</select>
					<select id="rowbounds" name="rowbounds">
						<option value="10">10</option>
						<option value="5">5</option>
						<option value="3">3</option>
					</select>
				</div>

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
					<c:forEach var="a" items="${approvals }">
						<ul class="chs-tbody-body">
							<li style="width:10%; font-weight:bold">
								<c:if test="${a.apvType == 0 }">
									휴가 신청서
								</c:if>
								<c:if test="${a.apvType == 1 }">
									품의서
								</c:if>
								<c:if test="${a.apvType == 2 }">
									지출결의서
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
								<c:if test="${a.apvDone != null }">
									${a.apvDone }
								</c:if>
							</li>
						</ul>
					</c:forEach>
				</div>
		${pageBar }
			</div>
		</div>
	</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
<script>
$(document).ready(function() {
    function loadApprovalData() {
        const dateOrder = $('#new-old').val();
        const numPerpage = $('#rowbounds').val();
        const empId = 'YOUR_EMP_ID'; // 실제 empId 값을 설정

        $.ajax({
            url: `${path}/rest/approval/flagginging`, // Ajax 요청을 보낼 URL
            method: 'GET', // 요청 방식
            data: { date: dateOrder, numPerpage: numPerpage, cPage: 1, empId: empId }, // 요청 데이터
            success: function(response) {
            	console.log(response);
                // 응답 데이터로 테이블 업데이트
                let html = '<ul class="chs-tbody-header">' +
                    '<li style="width:10%">양식명</li>' +
                    '<li style="width:30%">제목</li>' +
                    '<li style="width:10%">기안자</li>' +
                    '<li style="width:20%">기안일</li>' +
                    '<li style="width:10%">상태</li>' +
                    '<li style="width:20%">최근 결재일</li>' +
                    '</ul>';

                response.approvals.forEach(function(a) {
                    html += '<ul class="chs-tbody-body">' +
                        '<li style="width:10%; font-weight:bold">' + (a.apvType === 0 ? '휴가 신청서' : a.apvType === 1 ? '품의서' : '지출결의서') + '</li>' +
                        '<li style="width:30%">' + a.apvTitle + '</li>' +
                        '<li style="width:10%">' + a.employee.empName + '</li>' +
                        '<li style="width:20%">' + a.apvDate + '</li>' +
                        '<li style="width:10%">' + (a.apvStatus === 0 ? '결재 중' : a.apvStatus === 1 ? '반려' : '결재 완료') + '</li>' +
                        '<li style="width:20%">' + (a.apvDone ? a.apvDone : '') + '</li>' +
                        '</ul>';
                });

                html += response.pageBar;
                $('#approval-list').html(html);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error('Error:', textStatus, errorThrown); // 에러 로그 출력
                alert('불러오는 중 오류 발생하였습니다.');
            }
        });
    }

    // Select 요소의 change 이벤트 리스너 추가
    $('#new-old, #rowbounds').change(function() {
        loadApprovalData();
    });

    // 초기 로드
    loadApprovalData();
});

</script>