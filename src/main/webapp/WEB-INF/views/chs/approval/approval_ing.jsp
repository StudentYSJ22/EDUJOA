<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var ="loginMember" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}"/>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<link rel="stylesheet" href="${path }/resources/css/chs/approval/flagging_ing.css">
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="chs-custom">
		<jsp:include page="/WEB-INF/views/chs/approval/left_approval.jsp"/>
		<div class="chs-table">
			<div class="chs-thead">
					<p>미결</p>
			</div>
			<div class="chs-thead-2">
				<div>
				</div>
				<div>
                <select name="new-old" id="new-old">
                    <option value="new" ${old != "old" ? 'selected' : '' }>최근 순</option>
                    <option value="old" ${old == "old"? 'selected' : '' }>오래된 순</option>
                </select>
                <select name="rowbounds" id="rowbounds">
                   <option value="10" ${numPerpage == 10 ? 'selected' : ''}>10</option>
                    <option value="5" ${numPerpage == 5 ? 'selected' : ''}>5</option>
                    <option value="3" ${numPerpage == 3 ? 'selected' : ''}>3</option>
                </select>
            </div>
        </div>
        <div class="chs-tbody">
            <div id="approval-list">
                <ul class="chs-tbody-header">
                    <li style="width:10%">양식명</li>
                    <li style="width:30%">제목</li>
                    <li style="width:10%">기안자</li>
                    <li style="width:20%">기안일</li>
                    <li style="width:10%">상태</li>
                    <li style="width:20%">최종 결재일</li>
                </ul>
                <c:forEach var="a" items="${approvals}">
                    <ul class="chs-tbody-body">
                        <li style="width:10%; font-weight:bold">
                            <c:if test="${a.apvType == 0}">휴가 신청서</c:if>
                            <c:if test="${a.apvType == 1}">품의서</c:if>
                            <c:if test="${a.apvType == 2}">지출결의서</c:if>
                        </li>
                        <li style="width:30%"><a href="${path }/approval/selectone?apvId=${a.apvId}&apvType=${a.apvType}">${a.apvTitle}</a></li>
                        <li style="width:10%">${a.employee.empName}</li>
                        <li style="width:20%">${a.apvDate}</li>
                        <li style="width:10%">진행 중</li>
                        <li style="width:20%">
                            <c:if test="${a.apvDone != null}">${a.apvDone}</c:if>
                        </li>
                    </ul>
                </c:forEach>
            </div>
            <div id="page-bar">
                ${pageBar}
            </div>
        </div>
    </div>
	</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
<script>
	function loadApprovals() {
	    const empId = "${loginMember.empId}"; // 현재 로그인한 사용자의 ID
	    const dateOrder = $('#new-old').val();
	    const rowBounds = $('#rowbounds').val();
	    const cPage = 1; // 초기 페이지 번호 (필요에 따라 변경 가능)
	    $.ajax({
	        url: "${path}/rest/approval/approvaling.do",
	        method: 'GET',
	        data: {
	            numPerpage: rowBounds,
	            cPage: cPage,
	            empId: empId,
	            date: dateOrder,
	            approvalLine:'1',
	            apvStatus:'0'
	        },
	        success: function(response) {
	            // 응답 데이터 처리
	            const approvals = response.approvals;
	            const pageBar = response.pageBar;
	            console.log(approvals);
	            console.log(pageBar);
	
	            // 승인 목록과 페이지 바 업데이트
	            updateApprovalList(approvals);
	            updatePageBar(pageBar);
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	            console.error('Error: ' + textStatus, errorThrown);
	        }
	    });
	}
	
	
	// 드롭다운 변경 시 승인 목록 다시 불러오기
	$('#new-old, #rowbounds').change(loadApprovals);
	
	function updateApprovalList(approvals) {
	    const approvalList = document.getElementById('approval-list');
	    
	    // chs-tbody-header는 유지하고, 그 이후의 모든 요소를 제거합니다.
	    const children = approvalList.children;
	    for (let i = children.length - 1; i >= 1; i--) {
	        approvalList.removeChild(children[i]);
	    }
	
	    approvals.forEach(function(a) {
	        let approvalType;
	        switch (a.apvType) {
	            case '0':
	                approvalType = '휴가 신청서';
	                break;
	            case '1':
	                approvalType = '품의서';
	                break;
	            case '2':
	                approvalType = '지출결의서';
	                break;
	        }
	
	        const ul = '<ul class="chs-tbody-body">' +
	            '<li style="width:10%; font-weight:bold">' + approvalType + '</li>' +
	            '<li style="width:30%"><a href="${path }/approval/selectone?apvId=' + a.apvId + "&apvType="+a.apvType+'">'+a.apvTitle+"</a></li>" +
	            '<li style="width:10%">' + a.employee.empName + '</li>' +
	            '<li style="width:20%">' + a.apvDate + '</li>' +
	            '<li style="width:10%">미결</li>' +
	            '<li style="width:20%">' + (a.apvDone != null ? a.apvDone : '') + '</li>' +
	            '</ul>';
	
	        approvalList.innerHTML += ul;
	    });
	}
	
	
	// 페이지 바 업데이트 함수
	function updatePageBar(pageBar) {
	    $('#page-bar').html(pageBar);
	}
</script>