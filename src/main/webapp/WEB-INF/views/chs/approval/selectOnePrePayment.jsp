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
							<c:if test="${approval.approvalLine != null }">
								<c:forEach var="al" items="${approval.approvalLine }">
									<p class="select-user" data-apvlineid="${al.apvLineId }" data-empid="${al.employee.empId }">${al.employee.empName }(${al.employee.empTitle == 'J1' ? '원장' : '팀장'})</p>
								</c:forEach>
							</c:if>
						</div>
						<p class="select-refer-p">참조</p>
						<div class="select-refer" id="refer-list">
							<!-- 참조 리스트 -->
							<c:forEach var="cc" items="${approval.carbonCopy }">
								<p class="select-user" data-ccid="${cc.ccId }" data-empid="${cc.employee.empId }">${cc.employee.empName }(${cc.employee.empTitle == 'J2' ? '팀장' : '매니저'})</p>
							</c:forEach>
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
				<c:if test="${loginMember.empId == approval.empId }">
					<button onclick="modal_on();">결재선 목록</button>
				</c:if>
				<c:if test="${loginMember.oriname != null }">
					<button onclick="openSignatureModal()">사인 수정하기</button>
				</c:if>
				<c:if test="${loginMember.oriname == null }">
					<button onclick="openSignatureModal()">사인 생성하기</button>
				</c:if>
				<c:forEach var="al" items="${approval.approvalLine }">
					<c:if test="${al.empId == loginMember.empId && al.apvStatus == 0}">
						<button onclick="approvalend('${loginMember.oriname}','${al.apvId }','${al.apvLineId }','${al.apvSequence }','0');">결재</button>
						<button onclick="approvalend('${loginMember.oriname}','${al.apvId }','${al.apvLineId }','${al.apvSequence }','1');">반려</button>
					</c:if>
				</c:forEach>
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
        <input type="hidden" name="apvId" value="${approval.apvId}">
        <input type="hidden" name="apvType" value="2">
        <input type="hidden" name="apvStatus" value="0">
        <input type="hidden" name="apvStrg" value="0">
        <input type="hidden" name="carbonCopy" id="carbonCopy" value="0">
        <input type="hidden" name="approvalLine" id="approvalLine">
        <input type="hidden" name="apvStrg" value="0">
        <input type="hidden" id="approval-field">
        <input type="hidden" id="refer-field">
        <div class="header">품 의 서</div>
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
	            <td class="approval-td" id="approval-principal" data-empid="${approval.approvalLine[0].employee.empId }">
	            		${approval.approvalLine[0].employee.empName }
	            </td>
	            <td class="approval-td" id="approval-team-leader" data-empid="${approval.approvalLine[1].employee.empId }">
	            		${approval.approvalLine[1].employee.empName }
	            </td>
                <td class="approval-td" id="refer-first" data-empid="${approval.carbonCopy[0].employee.empId}">
                	${approval.carbonCopy[0].employee.empName==null?'':approval.carbonCopy[0].employee.empName}
                </td>
                <td class="approval-td" id="refer-last" data-empid="${approval.carbonCopy[1].employee.empId}">
                	${approval.carbonCopy[1].employee.empName==null?'':approval.carbonCopy[1].employee.empName}
                </td>
            </tr>
            <tr>
                <td style="padding:0;"id="" >
                	<c:if test="${approval.approvalLine[0].apvStatus != null && approval.approvalLine[0].apvStatus == 1}">
                		<img style="width:80px; height:50px;"src="${path }/resources/upload/employee_signatures/${approval.approvalLine[0].employee.oriname }">
                	</c:if>
                </td>
               <td style="padding:0;"id="" >
                	<c:if test="${approval.approvalLine[1].apvStatus != null && approval.approvalLine[1].apvStatus == 1}">
                		<img style="width:80px; height:50px;"src="${path }/resources/upload/employee_signatures/${approval.approvalLine[1].employee.oriname }">
                	</c:if>
                </td>
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
                <th>지출금액</th>
                <td colspan="5" id="totalAmount">${approval.prePayment.payAmount }</td>
            </tr>
            <tr>
                <th>제목</th>
                <td id="input-td" colspan="5"><input type="text" name="apvTitle" value="${approval.apvTitle }" required></td>
            </tr>
            <tr>
                <th>내용</th>
                <td id="input-td" colspan="5"><input type="text" name="apvContent" value="${approval.apvContent }" required></td>
            </tr>
            <tr>
                <th>지출건</th>
                <td id="input-td" colspan="5"><input type="text" name="apvCase"  value="${approval.prePayment.payCase }" required></td>
            </tr>
            <tr>
                <th>지출 날짜</th>
                <td id="input-td" colspan="5"><input type="date" name="payDate" value="${approval.prePayment.payDate }" required></td>
            </tr>
            <tr>
                <th>첨부파일</th>
                <td id="input-td" colspan="5">
				    <input type="file" name="apvAttachment">
				    <c:if test="${approval.apvAttachment != null}">
				        <p><a href="${path}/rest/approval/download/${approval.apvAttachment.apvId}">${approval.apvAttachment.fileOriname}</a></p>
				    </c:if>
				</td>
            </tr>
        </table>
        <table class="detail-table" id="detail-table">
            <tr>
                <th>번호</th>
                <th>품명</th>
                <th>금액</th>
                <th>비고</th>
                <th>작업</th>
            </tr>
           	<c:if test="${approval.prePayment.paymentList != null }">
           	<%	int count = 1; %>
            	<c:forEach var="pl" items="${approval.prePayment.paymentList }">
		            <tr>
	    	            <td>
	    	            	<%=count++ %>
	    	            </td>
		                <td><input type="text" name="payList" id="품명1" value="${pl.payList }"></td>
		                <td><input type="text" name="payAmount" id="금액1" oninput="validateAmount(this)" onblur="updateTotalAmount()" value="${pl.payAmount }"></td>
		                <td><input type="text" name="reference" id="비고1" value="${pl.reference == null ? '' : pl.reference }"></td>
		                <td><button onclick="removeRow(this)">삭제</button></td>
		            </tr>
	           	</c:forEach>
            </c:if>
        </table>
        <div class="btn">
            <button type="button" onclick="addRow()">추가</button>
        </div>
        <div class="footer">위 금액을 청구하오니 결재 바랍니다.</div>
        <div class="footer">
            <span id="current-date"></span>
            <input type="hidden" name="apvDate" id="apvDate">
        </div>
        <div class="signature">
            <div>
                기안자 : ${approval.employee.empName } (인)
                <c:if test="${approval.employee.oriname != null}">
                    <img id="oriname" src="${path}/resources/upload/employee_signatures/${approval.employee.oriname}">
                </c:if>
            </div>
        </div>
        <c:if test="${approval.empId == loginMember.empId && (approval.apvStrg == 1 || approval.approvalLine[0].apvStatus == 0)}">
	        <input type="submit" value="상신">
        </c:if>
    </form>
    <script src="${path}/resources/js/chs/canvas.js"></script>
    <script src="${path}/resources/js/chs/updatePayment.js"></script>
		</div>
	</div>
	<script>
		const empId2 = "${loginMember.empId}";
		const apvTag = function(apvType) {
			switch(apvType){
				case '0' : location.assign(`${path}/approval/insertvacay`); break;
				case '1' : location.assign(`${path}/approval/insertpre`); break;
				case '2' : location.assign(`${path}/approval/insert`); break;
			}
		}
	</script>
	<script src="${path }/resources/js/chs/update_approval.js"></script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />