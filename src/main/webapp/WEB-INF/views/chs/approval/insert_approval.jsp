<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<link rel="stylesheet"
	href="${path }/resources/css/chs/approval/insert_approval.css">
<script src="${path }/resources/js/jquery-3.7.1.min.js"></script>
<div class="chs-custom">
	<jsp:include page="/WEB-INF/views/chs/approval/left_approval.jsp" />

	<div class="right-container">
		<div class="hs-modal-container">
			<div class="modal-custom">
				<div class="modal-header">
					<p>결재선 설정</p>
					<button class="modal-close" onclick="modal_close();">x</button>
				</div>
				<div class="approval-line-select" style="padding:0 2%">
					<div class="search-radio">
						<label for="name-radio"> <input type="radio" 
							name="select-radio" id="name-radio" checked> <span>이름</span>
						</label> <label for="job-radio"> <input type="radio"
							name="select-radio" id="job-radio"> <span>직급</span>
						</label>
					</div>
					<div class="search-text">
						<input type="text"> <img
							src="https://cdn-icons-png.flaticon.com/512/71/71403.png">
					</div>
					<div class="search-approval-line">
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
						<p class="user-bottom">누구</p>
						<p class="user-bottom">누구</p>
						<p class="user-bottom">누구</p>
						<p class="user-bottom">누구</p>
						<p class="user-bottom">누구</p>
						<p class="user-bottom">누구</p>
						<p class="user-bottom">누구</p>
						<p class="user-bottom">누구</p>
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
				<div class="modal-footer" style="padding-bottom:2%">
					<button >확인</button>
					<button >취소</button>
				</div>
			</div>
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
				</style>
				<div class="container">
					<div class="header">지 출 계 획 서</div>
					<table class="approval-table">
						<tr>
							<th rowspan="2">결재</th>
							<th>담당</th>
							<th>과장</th>
							<th>부장</th>
							<th>이사</th>
							<th>사장</th>
						</tr>
						<tr>
							<td>담당자 서명</td>
							<td>과장 서명</td>
							<td>부장 서명</td>
							<td>이사 서명</td>
							<td>사장 서명</td>
						</tr>
					</table>
					<table class="approval-table">
						<tr>
							<th>성명</th>
							<td>박덕우</td>
							<th>부서</th>
							<td>인사팀</td>
							<th>직책</th>
							<td>사원</td>
						</tr>
						<tr>
							<th>지출계획금액</th>
							<td colspan="5">삼십삼만삼천원정 (₩330,000)</td>
						</tr>
						<tr>
							<th>제목</th>
							<td colspan="5">인사팀 모니터 구입</td>
						</tr>
					</table>
					<table class="detail-table">
						<tr>
							<th>번호</th>
							<th>품명</th>
							<th>금액</th>
							<th>비고</th>
						</tr>
						<tr>
							<td>1</td>
							<td>모니터(Y2030)</td>
							<td>330,000</td>
							<td>인사팀 용도</td>
						</tr>
						<!-- 추가 항목을 여기에 추가 -->
						<tr>
							<td>1</td>
							<td>모니터(Y2030)</td>
							<td>330,000</td>
							<td>인사팀 용도</td>
						</tr>
						<tr>
							<td>1</td>
							<td>모니터(Y2030)</td>
							<td>330,000</td>
							<td>인사팀 용도</td>
						</tr>
						<tr>
							<td>1</td>
							<td>모니터(Y2030)</td>
							<td>330,000</td>
							<td>인사팀 용도</td>
						</tr>

					</table>
					<div class="footer">위 금액을 청구하오니 결재 바랍니다.</div>
					<div class="footer">
						<span>20 년 월 일</span>
					</div>
					<div class="signature">
						<div>작성자 : 김동현 (인)</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<script src="${path }/resources/js/chs/insert_approval.js"></script>




	<jsp:include page="/WEB-INF/views/common/footer.jsp" />