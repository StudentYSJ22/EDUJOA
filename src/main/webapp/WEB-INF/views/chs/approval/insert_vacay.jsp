<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 시큐리티에서 가져온 세션값 담기 -->
<%@ taglib  prefix="c" uri="jakarta.tags.core"%>
<c:set var ="loginMember" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}"/>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<link rel="stylesheet" href="${path }/resources/css/chs/approval/insert_approval.css">
<script src="${path }/resources/js/jquery-3.7.1.min.js"></script>

<div class="chs-custom">
	<jsp:include page="/WEB-INF/views/chs/approval/left_approval.jsp" />
    <!-- 서명 캔버스 모달 -->
		<div id="signature-modal" style="display:none; position:fixed; top:0; left:0; width:100%; height:100%; background-color:rgba(0,0,0,0.5); justify-content:center; align-items:center;">
		    <div style="background-color:white; padding:20px; border-radius:10px; text-align:center;">
		        <canvas id="signature-canvas" width="500" height="200" style="border:1px solid #000;"></canvas>
		        <br/>
		        <button onclick="saveSignature()">서명 저장</button>
		        <button onclick="closeSignatureModal()">취소</button>
		    </div>
		</div>
		<!-- 자주쓰는 결재라인 추가 모달 -->
		<div id="approval-line-modal" style="display:none; position:fixed;z-index:100; top:0; left:0; width:100%; height:100%; background-color:rgba(0,0,0,0.5); justify-content:center; align-items:center;">
		    <div style="background-color:white; padding:20px; border-radius:10px; text-align:center;">
		        <p>즐겨찾기 이름을 입력하세요:</p>
		        <input style="border:1px solid black" type="text" id="favorite-name" />
		        <br/>
		        <button onclick="saveApprovalLine()">확인</button>
		        <button onclick="closeApprovalLineModal()">취소</button>
		    </div>
		</div>
	<div class="right-container">
		<!-- 모달창 시작 -->
		<div class="hs-modal-container">
			<div class="modal-custom">
				<div class="modal-header">
					<p>결재선 설정</p>
					<button class="modal-close" onclick="modal_close();">x</button>
				</div>
				<div class="approval-line-select" style="padding: 0 2%">
					<div class="search-approval-line">
						<select>
							<option value="" disabled selected>자주쓰는 결재라인</option>
						</select>
					</div>
					<div>
						<button onclick="addApprovalLine();">결재라인 추가하기</button>
					</div>
				</div>
				<div class="hs-modal-content" style="padding: 0 2%;">
					<div class="content-left">
						<p class="user-top">원장</p>
						<p class="user-top">팀장</p>
						<p class="user-top">매니저</p>
					</div>
					<div class="content-right">
					</div>
					<div class="select-bar">
						<div class="bar-top">
							<p id="approval-right">&gt;</p>
							<p id="approval-left">&lt;</p>
						</div>
						<div class="bar-bottom">
							<p id="refer-right">&gt;</p>
							<p id="refer-left">&lt;</p>
						</div>
					</div>
					<div class="select-employee">
						<p class="select-approval-p">결재</p>
						<div class="select-approval" id="approval-list">
							<!-- 결재 리스트 -->
						</div>
						<p class="select-refer-p">참조</p>
						<div class="select-refer" id="refer-list">
							<!-- 참조 리스트 -->
						</div>
					</div>
				</div>
				<div class="modal-footer" style="padding-bottom: 2%">
					<button id="confirm-button" >확인</button>
					<button onclick="modal_close()">취소</button>
				</div>
			</div>
		<!-- 모달 끝 -->
		</div>
		<div class="right-container-header">
			<div>
				<p>기안서 작성</p>
			</div>
			<div>
				<button onclick="modal_on();">결재선 목록</button>
				<button id='insertApprovalStrg'>임시 저장</button>
				<c:if test="${loginMember.oriname != null }">
					<button onclick="openSignatureModal()">사인 수정하기</button>
				</c:if>
				<c:if test="${loginMember.oriname == null }">
					<button onclick="openSignatureModal()">사인 생성하기</button>
				</c:if>
			</div>
		</div>
		<div class="right-container-body">
		<link rel="stylesheet" href="${path }/resources/css/chs/approval/afterPayment.css">
			<div class="apv-tag">
				<p onclick="apvTag('0');">휴가 신청서</p>
				<p onclick="apvTag('1')">품의서(지출)</p>
				<p onclick="apvTag('2')">지출결의서</p>
			</div>
			<div class="approval-file">
				<form class="container" onsubmit="" enctype="multipart/form-data">
        <!-- 폼 필드 추가 -->
        <input type="hidden" name="empId" value="${loginMember.empId}">
        <input type="hidden" name="apvType" value="0">
        <input type="hidden" name="apvStatus" value="0">
        <input type="hidden" name="apvStrg" value="0">
        <input type="hidden" name="carbonCopy" id="carbonCopy" value="0">
        <input type="hidden" name="approvalLine" id="approvalLine">
        <input type="hidden" name="apvStrg" value="0">
        <input type="hidden" id="approval-field">
        <input type="hidden" id="refer-field">
        <div class="header">휴 가 신 청 서</div>
        <table class="approval-table">
            <tr>
                <th class="apporval-th" rowspan="3">결재</th>
                <th>원장</th>
                <th>팀장</th>
                <th class="apporval-th" rowspan="3">참조</th>
                <th id="refer-title1">팀장</th>
                <th id="refer-title2">매니저</th>
            </tr>
            <tr>
                <td class="approval-td" id="approval-principal"></td>
                <td class="approval-td" id="approval-team-leader"></td>
                <td class="approval-td" id="refer-first"></td>
                <td class="approval-td" id="refer-last"></td>
            </tr>
            <tr>
                <td id=""></td>
                <td id=""></td>
                <td id=""></td>
                <td id=""></td>
            </tr>
        </table>
        <table class="approval-table">
            <tr>
                <th>성명</th>
                <td>${loginMember.empName}</td>
                <th>직책</th>
                <td>
                    <c:if test="${loginMember.empTitle == 'J1'}">
                        원장
                    </c:if>
                    <c:if test="${loginMember.empTitle == 'J2'}">
                        팀장
                    </c:if>
                    <c:if test="${loginMember.empTitle == 'J3'}">
                        매니저
                    </c:if>
                </td>
            </tr>
            <tr>
                <th>제목</th>
                <td id="input-td" colspan="5"><input type="text" name="apvTitle" required></td>
            </tr>
            <tr>
                <th>내용</th>
                <td id="input-td" colspan="5"><input type="text" name="apvContent" required></td>
            </tr>
            <tr>
                <th>총 휴가 일</th>
                <td>${loginMember.empTvacation }</td>
                <th>남은 휴가 일</th>
                <td>${loginMember.empRvacation }</td>
            </tr>
            <tr>
                <th>휴가 종류</th>
                <td  id="input-td" colspan="5" >
	                <select name="vacayType" id="vacayType">
	                	<option value="0">연차</option>
	                	<option value="1">반차</option>
	                </select>
	            </td>
            </tr>
            <tr>
          		<th>휴가 시작일</th>
	            <td id="input-td" colspan="5"><input type="date" id="vacayStart" name="vacayStart" required></td>
	        </tr>
	        <tr>
	            <th>휴가 종료일</th>
	            <td id="input-td" colspan="5"><input type="date" id="vacayEnd" name="vacayEnd" required></td>
	        </tr>
            <tr>
                <th>첨부파일</th>
                <td id="input-td" colspan="5"><input type="file" name="apvAttachment"></td>
            </tr>
        </table>
        <div class="footer">위 금액을 청구하오니 결재 바랍니다.</div>
         <div class="footer">
            <span id="current-date"></span>
            <input type="hidden" name="apvDate" id="apvDate">
        </div>
        <div class="signature">
            <div>
                청구인 : ${loginMember.empName }
                <c:if test="${loginMember.oriname != null}">
                    <img id="oriname" src="${path}/resources/upload/employee_signatures/${loginMember.oriname}">
                </c:if>
            </div>
        </div>
        <input type="submit" value="상신">
    </form>
    <script src="${path}/resources/js/chs/canvas.js"></script>
		</div>
	</div>
	<script src="${path }/resources/js/chs/insert_approval.js"></script>
	<script>
		const empId2 = "${loginMember.empId}";
		const apvTag = function(apvType) {
			switch(apvType){
				case '0' : location.assign(`${path}/approval/insertvacay`); break;
				case '1' : location.assign(`${path}/approval/insertpre`); break;
				case '2' : location.assign(`${path}/approval/insert`); break;
			}
		}
		 function updateCurrentDate() {
             const today = new Date();
             const year = today.getFullYear();
             const month = ('0' + (today.getMonth() + 1)).slice(-2);
             const day = ('0' + today.getDate()).slice(-2);
             document.getElementById('current-date').innerText = year+"년 "+month+"월 "+day+"일";
         }
		 document.addEventListener('DOMContentLoaded', (event) => {
		        updateCurrentDate();
		});
		 
	$(document).ready(function() {

		// 폼 제출 할 때 
		    const insertApproval = function(e) {
		        e.preventDefault();
		        const formData = new FormData();
		        const approval = $('#approval-principal');
		        const empRvacation = ${loginMember.empRvacation}; // 남은 휴가 일수
		        const startDate = new Date(document.getElementById('vacayStart').value);
		        const endDate = new Date(document.getElementById('vacayEnd').value);	
		       
		        if (approval.text() == '') {
		            alert('결재자를 선택해주세요.');
		            return false;
		        }
		  	   // 휴가 일수 계산
		        const vacationDays = (endDate - startDate) / (1000 * 60 * 60 * 24) + 1;
		        if (vacationDays > empRvacation) {
		            alert('남은 휴가 일수보다 많게 설정할 수 없습니다.');
		            return false; // 폼 제출을 막음
		        }

		        // 숨겨진 필드에 현재 날짜 값을 설정
		        const currentDate = $('#current-date').text();
		        formData.append('apvDate', currentDate);

		        // 결재자, 참조자 정보 추가
				formData.append('carbonCopy',$('#refer-field').val());
				const approvalLine = JSON.parse($('#approval-field').val());
			    formData.append('approvalLine', JSON.stringify(approvalLine));
		        // 기타 폼 데이터 추가
		        formData.append('empId', $('input[name="empId"]').val());
		        formData.append('apvType', $('input[name="apvType"]').val());
		        formData.append('apvStatus', $('input[name="apvStatus"]').val());
		        formData.append('apvStrg', $('input[name="apvStrg"]').val());
		        formData.append('apvTitle', $('input[name="apvTitle"]').val());
		        formData.append('apvContent', $('input[name="apvContent"]').val());
		        formData.append('vacayType', $('#vacayType').val());
		        formData.append('vacayStart', $('#vacayStart').val());
		        formData.append('vacayEnd', $('#vacayEnd').val());

		        // 파일 추가
		        const fileInput = $('input[name="apvAttachment"]')[0];
		        if (fileInput.files.length > 0) {
		            formData.append('apvAttachment', fileInput.files[0]);
		        }
				  // FormData 내용 확인
		        for (let pair of formData.entries()) {
		            console.log(pair[0] + ': ' + pair[1]); 
		        }
		        $.ajax({
		            url: `${path}/rest/approval/insert`,
		            type: 'POST',
		            data: formData,
		            contentType: false, // FormData를 사용할 때는 false로 설정
		            processData: false, // FormData를 사용할 때는 false로 설정
		            success: function(response) {
		                alert('결재가 성공적으로 제출되었습니다.');
		                location.assign("${path}/approval/flagginging.do?empId=${loginMember.empId}");
		                // 성공 시 추가 작업;
		            },
		            error: function(xhr, status, error) {
		                alert('결재 제출 중 오류가 발생했습니다.');
		                console.error(error);
		            }
		        });
		    }     
			 // 임시 저장 함수 정의
		    $('#insertApprovalStrg').click(e=>{
		        $('input[name="apvStrg"]').val('1');
		        insertApproval(event);  // event 객체를 전달하여 폼 제출 방지
		    });
		    $('form.container').on('submit', insertApproval);  
			
	});
	</script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />