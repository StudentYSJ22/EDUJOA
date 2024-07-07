<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<link rel="stylesheet" href="${path }/resources/css/chs/approval/insert_approval.css">
<script src="${path }/resources/js/jquery-3.7.1.min.js"></script>
<div class="chs-custom">
	<jsp:include page="/WEB-INF/views/chs/approval/left_approval.jsp" />
	<div class="right-container">
		<!-- 모달창 시작 -->
		<div class="hs-modal-container">
			<div class="modal-custom">
				<div class="modal-header">
					<p>결재선 설정</p>
					<button class="modal-close" onclick="modal_close();">x</button>
				</div>
				<div class="approval-line-select" style="padding: 0 2%">
<!-- 					<div class="search-radio">
						<label for="name-radio"><input type="radio" name="select-radio" id="name-radio" checked><span>이름</span></label>
						<label for="job-radio"><input type="radio" name="select-radio" id="job-radio"><span>직급</span></label>
					</div>
					<div class="search-text">
						<input type="text"><img src="https://cdn-icons-png.flaticon.com/512/71/71403.png">
					</div>
 -->					<div class="search-approval-line">
						<select>
							<option value="" disabled selected>자주쓰는 결재라인</option>
						</select>
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
					<button id="confirm-button" onclick="applyApprovalLine()">확인</button>
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
				<button>임시 저장</button>
			</div>
		</div>
		<div class="right-container-body">
			<div class="apv-tag">
				<p>휴가 신청서</p>
				<p>품의서(지출)</p>
				<p>지출결의서</p>
			</div>
			<div class="approval-file">
				<!-- 폼 필드 추가 -->
				<input type="hidden" id="approval-field"> 
				<input 	type="hidden" id="refer-field">
				<style>
					body {
						font-family: Arial, sans-serif;
					}
					
					.container {
						width: 800px;
						margin: 0 auto;
						border: 1px solid black;
						padding: 20px;
					}
					
					.header {
						text-align: center;
						font-size: 24px;
						font-weight: bold;
						margin-bottom: 20px;
					}
					
					.approval-table, .detail-table {
						width: 100%;
						border-collapse: collapse;
					}
					
					.approval-table td, .approval-table th, .detail-table td, .detail-table th
						{
						border: 1px solid black;
						padding: 8px;
						text-align: center;
						box-sizing: border-box;
					}
					
					.approval-table {
						margin-bottom: 20px;
					}
					
					.detail-table th {
						background-color: #f0f0f0;
					}
					
					.footer {
						text-align: center;
						margin-top: 20px;
					}
					
					.signature {
						margin-top: 40px;
						display: flex;
						justify-content: space-between;
						padding: 0 50px;
					}
					
					.btn {
						margin-top: 10px;
						text-align: center;
						display: flex;
   						justify-content: flex-end;
					}
					
					input[type=text] {
						width: 100%;
						height: 100%;
						margin: 0;
						padding: 0;
						border-style: none;
						font-size: 15px;
					}
					</style>
				<form class="container">
					<input type="hidden" name="approvalLine" value="">
					<input type="hidden" name="carbonCopy" value="">
					<div class="header">지 출 결 의 서</div>
					<table class="approval-table">
						<tr>
					        <th rowspan="3">결재</th>
					        <th>원장</th>
					        <th>팀장</th>
					        <th rowspan="3">참조</th>
					        <th>팀장</th>
					        <th>매니저</th>
					    </tr>
					    <tr>
					        <td id="approval-principal"></td>
					        <td id="approval-team-leader"></td>
					        <td id="refer-principal"></td>
					        <td id="refer-team-leader"></td>
					    </tr>
					    <tr>
					    	<td></td>
					    	<td></td>
					    	<td></td>
					    	<td></td>
					    </tr>
					</table>
					<table class="approval-table">
						<tr>
							<th>성명</th>
							<td>${loginMember.empName }</td>
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
							<td colspan="5" id="totalAmount">0</td>
						</tr>
						<tr>
							<th>제목</th>
							<td colspan="5">인사팀 모니터 구입</td>
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
						<tr>
							<td>1</td>
							<td><input type="text" name="품명" id="품명1"></td>
							<td><input type="text" name="금액" id="금액1"
								oninput="validateAmount(this)" onblur="updateTotalAmount()"></td>
							<td><input type="text" name="비고" id="비고1"></td>
							<td><button onclick="removeRow(this)">삭제</button></td>
						</tr>
					</table>
					<div class="btn">
						<button type="button" onclick="addRow()">추가</button>
					</div>
					<div class="footer">위 금액을 청구하오니 결재 바랍니다.</div>
					<div class="footer">
						<span id="current-date"></span>
					</div>
					<div class="signature">
						<div>
							청구인 : 김동현
							<c:if test="loginMember.oriname != null">
								${loginMember.oriname}
							</c:if>
							<c:if test="loginMember.oriname == null">
								<button>사인 생성하기</button>
							</c:if>
						</div>
					</div>
					<input type="submit" value="전송">
				</form>

				<script>
                document.addEventListener('DOMContentLoaded', (event) => {
                    updateCurrentDate();
                });
                let rowCount = 1;

                function addRow() {
                    rowCount++;
                    const table = document.getElementById('detail-table');
                    const newRow = table.insertRow();

                    newRow.innerHTML = `
                        <td>${rowCount}</td>
                        <td><input type="text" name="품명" id="품명${rowCount}"></td>
                        <td><input type="text" name="금액" id="금액${rowCount}" oninput="validateAmount(this)" onblur="updateTotalAmount()"></td>
                        <td><input type="text" name="비고" id="비고${rowCount}"></td>
                        <td><button onclick="removeRow(this)">삭제</button></td>
                    `;
                    updateRowNumbers(); // New line added here to update row numbers after adding a new row
                }

                function removeRow(button) {
                    const row = button.parentElement.parentElement;
                    row.parentElement.removeChild(row);
                    rowCount--;
                    updateRowNumbers();
                    updateTotalAmount();
                }

                function updateRowNumbers() {
                    const table = document.getElementById('detail-table');
                    for (let i = 1; i < table.rows.length; i++) {
                        table.rows[i].cells[0].innerText = i;
                    }
                }

                function validateAmount(input) {
                    const value = input.value;
                    if (/[^\d]/.test(value)) {
                        alert("금액에는 숫자만 입력할 수 있습니다.");
                        input.value = "";
                    }
                    const rowNumber = input.id.replace('금액', ''); // Updated this line to get the correct row number from input id
                    const 품명 = document.getElementById(`품명${rowNumber}`);
                    if (!품명.value) {
                        alert("품명을 입력하세요.");
                        품명.focus();
                    }
                }

                function updateTotalAmount() {
                    let total = 0;
                    const table = document.getElementById('detail-table');
                    for (let i = 1; i < table.rows.length; i++) {
                        const amount = table.rows[i].cells[2].children[0].value;
                        if (amount) {
                            total += parseInt(amount, 10);
                        }
                    }
                    document.getElementById('totalAmount').innerText = "₩"+total.toLocaleString();
                }

                function updateCurrentDate() {
                    const today = new Date();
                    const year = today.getFullYear();
                    const month = ('0' + (today.getMonth() + 1)).slice(-2);
                    const day = ('0' + today.getDate()).slice(-2);
                    document.getElementById('current-date').innerText = year+"년 "+month+"월 "+day+"일";
                }
                
                
                function applyApprovalLine(){
                	
                }
              </script>
			</div>
		</div>
	</div>
	<script src="${path }/resources/js/chs/insert_approval.js"></script>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />