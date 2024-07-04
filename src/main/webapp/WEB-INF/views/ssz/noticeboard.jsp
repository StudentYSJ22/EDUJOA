<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
   <div class="card">
                <h5 class="card-header">Light Table head</h5>
                <div class="table-responsive text-nowrap">
                  <table class="table">
                    <thead class="table-light">
                      <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                        <th>조회수</th>
                      </tr>
                    </thead>
                    <tbody class="table-border-bottom-0">
                      <tr>
                        <td><i class="fab fa-angular fa-lg text-danger me-3"></i> 1</td>
                        <td>공지사항 테스트 1번 게시글</td>
                        <td>admin</td>
                        <td>2024-07-04</td>
                        <td>1</td>
                      </tr>
                      <tr>
                        <td><i class="fab fa-angular fa-lg text-danger me-3"></i> 1</td>
                        <td>공지사항 테스트 2번 게시글</td>
                        <td>admin</td>
                        <td>2024-07-05</td>
                        <td>0</td>
                      </tr>
                      <tr>
                        <td><i class="fab fa-angular fa-lg text-danger me-3"></i> 1</td>
                        <td>공지사항 테스트 3번 게시글</td>
                        <td>admin</td>
                        <td>2024-07-06</td>
                        <td>3</td>
                      </tr>
                      
                    </tbody>
                  </table>
                </div>
              </div>
   <div class="container-xxl flex-grow-1 container-p-y">
   </div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>