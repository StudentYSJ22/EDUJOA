<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<link rel="stylesheet" href="${path}/resources/css/ysj/salary.css">

<div class="container-xxl flex-grow-1 container-p-y">
    <div class="container">
        <div class="header">
            <h2>급여조회</h2>
            <table>
                <tr>
                    <td rowspan="5"><img src="${path}/resources/images/profile.png" alt="Profile Picture"></td>
                    <th>직원구분</th>
                    <td>정규</td>
                    <th>부서</th>
                    <td>운영팀</td>
                </tr>
                <tr>
                    <th>입사일자</th>
                    <td>2005-09-01</td>
                    <th>사원번호</th>
                    <td>031-123-4567</td>
                </tr>
                <tr>
                    <th>ID</th>
                    <td>daou</td>
                    <th>휴대전화</th>
                    <td>010-1234-5678</td>
                </tr>
                <tr>
                    <th>이메일</th>
                    <td>bslove@bslove</td>
                    <th>회사/부서 대표전화</th>
                    <td>02-1234-5678</td>
                </tr>
                <tr>
                    <th>메신저</th>
                    <td></td>
                    <th>FAX</th>
                    <td>031-789-4561</td>
                </tr>
                
            </table>
        </div>
        
        <div class="salary-details">
            <h3>급여상세</h3>
            <table>
                <tr>
                    <th>급여유형</th>
                    <td>연봉</td>
                    <th>연봉</th>
                    <td><fmt:formatNumber value="58800000" type="currency" currencySymbol="₩" /></td>
                </tr>
                <tr>
                    <th>고정수당</th>
                    <td>식대</td>
                    <th>고정수당 포함여부</th>
                    <td>예</td>
                </tr>
                <tr>
                    <th>월급</th>
                    <td colspan="3"><fmt:formatNumber value="4900000" type="currency" currencySymbol="₩" /></td>
                </tr>
            </table>
        </div>
        
        <div class="salary-slip">
            <h3>급여명세서</h3>
            <table>
                <tr>
                    <th>급(상)여월</th>
                    <th>급(상)여지급일</th>
                    <th>지급합계</th>
                    <th>공제합계</th>
                    <th>공제 후 지급액</th>
                </tr>
                <tr>
                    <td>2022-06</td>
                    <td>2022-07-05</td>
                    <td><fmt:formatNumber value="5200000" type="currency" currencySymbol="₩" /></td>
                    <td><fmt:formatNumber value="852890" type="currency" currencySymbol="₩" /></td>
                    <td class="details"><fmt:formatNumber value="4347110" type="currency" currencySymbol="₩" /></td>
                </tr>
                <tr>
                    <td>2022-05</td>
                    <td>2022-06-05</td>
                    <td><fmt:formatNumber value="5000000" type="currency" currencySymbol="₩" /></td>
                    <td><fmt:formatNumber value="822040" type="currency" currencySymbol="₩" /></td>
                    <td class="details"><fmt:formatNumber value="4177960" type="currency" currencySymbol="₩" /></td>
                </tr>
                <tr>
                    <td>2022-04</td>
                    <td>2022-05-05</td>
                    <td><fmt:formatNumber value="5000000" type="currency" currencySymbol="₩" /></td>
                    <td><fmt:formatNumber value="822040" type="currency" currencySymbol="₩" /></td>
                    <td class="details"><fmt:formatNumber value="4177960" type="currency" currencySymbol="₩" /></td>
                </tr>
                <tr>
                    <td>2022-03</td>
                    <td>2022-04-05</td>
                    <td><fmt:formatNumber value="5000000" type="currency" currencySymbol="₩" /></td>
                    <td><fmt:formatNumber value="822040" type="currency" currencySymbol="₩" /></td>
                    <td class="details"><fmt:formatNumber value="4177960" type="currency" currencySymbol="₩" /></td>
                </tr>
            </table>
        </div>
        
       
    </div>
</div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>