<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${path }/resources/css/chs/approval/insert_approval.css">
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="chs-custom">
		<jsp:include page="/WEB-INF/views/chs/approval/left_approval.jsp"/>
		<div class="right-container">
			<div class="right-container-header">
				<div>
					<p>기안서 작성</p>
				</div>
				<div>
					<button>결재선 목록</button>
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
					<!-- <style>
				        body {
				            font-family: Arial, sans-serif;
				        }
				        .container {
				            width: 600px;
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
				        .form-table {
				            width: 100%;
				            border-collapse: collapse;
				            margin-bottom: 20px;
				        }
				        .form-table td, .form-table th {
				            border: 1px solid black;
				            padding: 8px;
				        }
				        .form-table th {
				            background-color: #f0f0f0;
				            width: 150px;
				            text-align: left;
				        }
				        .approval-table {
				            width: 100%;
				            border-collapse: collapse;
				            margin-bottom: 20px;
				        }
				        .approval-table td, .approval-table th {
				            border: 1px solid black;
				            padding: 8px;
				            text-align: center;
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
				        <div class="header">
				            휴 가 신 청 서
				        </div>
				        <table class="form-table">
				            <tr>
				                <th>성명</th>
				                <td>박덕우</td>
				            </tr>
				            <tr>
				                <th>부서</th>
				                <td>인사팀</td>
				            </tr>
				            <tr>
				                <th>직책</th>
				                <td>사원</td>
				            </tr>
				            <tr>
				                <th>휴가 종류</th>
				                <td>
				                    <select>
				                        <option>연차</option>
				                        <option>반차</option>
				                        <option>병가</option>
				                        <option>기타</option>
				                    </select>
				                </td>
				            </tr>
				            <tr>
				                <th>휴가 기간</th>
				                <td>
				                    시작일: <input type="date"> <br>
				                    종료일: <input type="date">
				                </td>
				            </tr>
				            <tr>
				                <th>휴가 사유</th>
				                <td><textarea rows="4" style="width: 100%;"></textarea></td>
				            </tr>
				        </table>
				        <table class="approval-table">
				            <tr>
				                <th>결재</th>
				                <th>담당</th>
				                <th>과장</th>
				                <th>부장</th>
				                <th>이사</th>
				                <th>사장</th>
				            </tr>
				            <tr>
				                <td>서명</td>
				                <td>담당자 서명</td>
				                <td>과장 서명</td>
				                <td>부장 서명</td>
				                <td>이사 서명</td>
				                <td>사장 서명</td>
				            </tr>
				        </table>
				        <div class="footer">
				            위와 같이 휴가를 신청하오니 결재 바랍니다.
				        </div>
				        <div class="footer">
				            <span>20 년 월 일</span>
				        </div>
				        <div class="signature">
				            <div>신청인 : 박덕우 (인)</div>
				        </div>
				    </div> -->
				     <!-- <style>
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
				        .approval-table td, .approval-table th, .detail-table td, .detail-table th {
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
				        <div class="header">
				            지 출 결 의 서
				        </div>
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
				                <th>지출금액</th>
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
				            추가 항목을 여기에 추가
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
				        <div class="footer">
				            위 금액을 청구하오니 결재 바랍니다.
				        </div>
				        <div class="footer">
				            <span>20 년 월 일</span>
				        </div>
				        <div class="signature">
				            <div>청구인 : 김동현 (인)</div>
				        </div>
				    </div> -->
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
				        .approval-table td, .approval-table th, .detail-table td, .detail-table th {
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
				        <div class="header">
				            지 출 계 획 서
				        </div>
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
				        <div class="footer">
				            위 금액을 청구하오니 결재 바랍니다.
				        </div>
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
	</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>