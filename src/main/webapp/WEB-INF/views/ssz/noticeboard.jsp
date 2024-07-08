<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
   <div class="card d-flex align-items-center container shadow-none" style="background-color: #f5fff1;">
                <h5 class="card-header"><strong>공지사항</strong></h5>
                <div class="table-responsive text-nowrap" style="background-color: white;">
                  <table class="table table-striped table-hover">
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
                    <!-- 나중에 foreach돌려서 있는 만큼 불러오기 -->
                      <tr>
                        <td><i class="fab fa-angular fa-lg text-danger me-3"></i> 1</td>
                        <td>공지사항 테스트 1번 게시글</td>
                        <td>admin</td>
                        <td>2024-07-04</td>
                        <td>1</td>
                      </tr>
                      <tr>
                        <td><i class="fab fa-angular fa-lg text-danger me-3"></i> 2</td>
                        <td>공지사항 테스트 2번 게시글</td>
                        <td>admin</td>
                        <td>2024-07-05</td>
                        <td>0</td>
                      </tr>
                      <tr>
                        <td><i class="fab fa-angular fa-lg text-danger me-3"></i> 3</td>
                        <td>공지사항 테스트 3번 게시글</td>
                        <td>admin</td>
                        <td>2024-07-06</td>
                        <td>3</td>
                      </tr>
                      
                    </tbody>
                  </table>
                  <button class="btn btn-outline-success">글쓰기</button>
                </div>
              </div>
   <div class="container-xxl flex-grow-1 container-p-y">
   </div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>