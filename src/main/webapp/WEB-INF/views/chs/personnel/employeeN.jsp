<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var ="loginMember" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}"/>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<link rel="stylesheet" href="${path }/resources/css/chs/personnel/personnel.css">
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="chs-custom">
		<jsp:include page="/WEB-INF/views/chs/personnel/left_personnel.jsp"/>
		<div class="chs-table">
			<div class="chs-thead">
				<p>퇴사자 <span>${count }</span></p>
			</div>
			<div class="chs-thead-2">
				<div class="delete-container">
            	</div>
				<div>
                <select name="rowbounds" id="rowbounds">
                   <option value="10" ${numPerpage == 10 ? 'selected' : ''}>10</option>
                    <option value="5" ${numPerpage == 5 ? 'selected' : ''}>5</option>
                    <option value="3" ${numPerpage == 3 ? 'selected' : ''}>3</option>
                </select>
            </div>
        </div>
        <div class="chs-tbody">
            <div id="employee-list">
                <ul class="chs-tbody-header">
                    <li style="width:15%">이름</li>
                    <li style="width:15%">직책</li>
                    <li style="width:20%">퇴사 일자</li>
                    <li style="width:20%">메일 주소</li>
                    <li style="width:30%">주소</li>
                </ul>
                <c:forEach var="e" items="${employees}">
                    <ul class="chs-tbody-body">
                        <li style="width:15%; font-weight:bold" data-empid='${e.empId}' class='employee-link'><a href="#">${e.empName }</a></li>                       
                        <li style="width:15%">
                        	<c:if test="${e.empTitle == 'J1'}">원장</c:if>
                        	<c:if test="${e.empTitle == 'J2'}">팀장</c:if>
                        	<c:if test="${e.empTitle == 'J3'}">매니저</c:if>
                        </li>
                        <li style="width:20%">${e.empRetireDate }</li>
                        <li style="width:20%">${e.empEmail }</li>
                        <li style="width:30%">${e.empAddress }</li>
                    </ul>
                </c:forEach>
            </div>
            <div id="page-bar">
                ${pagebar}
            </div>
        </div>
    </div>
                <!-- Modal HTML -->
				<div class="modal fade" id="employeeModal" tabindex="-1" aria-labelledby="employeeModalLabel" aria-hidden="true">
				  <div class="modal-dialog modal-lg">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="employeeModalLabel">인사 카드</h5>
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				        <!-- Employee details will be loaded here -->
				        <div class="card">
				          <div class="card-body">
				            <div class="row">
				              <div class="col-md-3">
				                <img  alt="Profile Picture" class="img-fluid">
				              </div>
				              <div class="col-md-9">
				                <table class="table table-bordered">
				                  <tbody>
				                    <tr>
				                      <th>사번</th>
				                      <td id="empId"></td>
				                      <th>입사일자</th>
				                      <td id="empHireDate"></td>
				                    </tr>
				                    <tr>
				                      <th>직급</th>
				                      <td id="empTitle"></td>
				                      <th>재직 구분</th>
				                      <td id="empStatus"></td>
				                    </tr>
				                    <tr>
				                      <th>주소</th>
				                      <td id="empAddress" colspan="3"></td>
				                    </tr>
				                    <tr>
				                      <th>성명(한글)</th>
				                      <td id="empName"></td>
				                      <th>개인 메일 주소</th>
				                      <td id="empEmail" colspan="3"></td>
				                    </tr>
				                  </tbody>
				                </table>
				              </div>
				            </div>
				          </div>
				        </div>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
				      </div>
				    </div>
				  </div>
				</div>
    
	</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
<script>
	function loadEmployee() {
	    const rowBounds = $('#rowbounds').val();
	    const cPage = 1; // 초기 페이지 번호 (필요에 따라 변경 가능)
	
	    $.ajax({
	        url: "${path}/rest/employee/selectall",
	        method: 'GET',
	        data: {
	            numPerpage: rowBounds,
	            cPage: cPage,
	            empYn:'1',
	        },
	        success: function(response) {
	            // 응답 데이터 처리
	            const employees = response.employees;
	            const pagebar = response.pagebar;
	            console.log(employees);
	            console.log(pagebar);
	
	            // 승인 목록과 페이지 바 업데이트
	            updateEmployeeList(employees);
	            updatePageBar(pagebar);
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	            console.error('Error: ' + textStatus, errorThrown);
	        }
	    });
	}
	
	
	// 드롭다운 변경 시 직원 목록 다시 불러오기
	$('#rowbounds').change(loadEmployee);
	
	function updateEmployeeList(employees) {
	    const employeeList = document.getElementById('employee-list');
	    
	    // chs-tbody-header는 유지하고, 그 이후의 모든 요소를 제거합니다.
	    const children = employeeList.children;
	    for (let i = children.length - 1; i >= 1; i--) {
	    	employeeList.removeChild(children[i]);
	    }
	
	    employees.forEach(function(e) {
	    	let empTitle = function(title){
	    		switch(title){
		    		case 'J1' : return "원장";
		            case 'J2' : return "팀장";
		            case 'J3' : return "매니저";
	    		}
	    	}
	    	const ul = "<ul class=\"chs-tbody-body\">" +
	        "<li style=\"width:15%; font-weight:bold data-empid="+e.empId+" class='employee-link'\"><a href='#'>" + e.empName + "</a></li>" +
	        "<li style=\"width:15%\">" + empTitle(e.empTitle) + "</li>" +
	        "<li style=\"width:20%\">" + e.empRetireDate + "</li>" +
	        "<li style=\"width:20%\">" + e.empEmail + "</li>" +
	        "<li style=\"width:30%\">" + e.empAddress + "</li>" +
	    	"</ul>";

	        employeeList.innerHTML += ul;
	    });
	}
	
	
	// 페이지 바 업데이트 함수
	function updatePageBar(pagebar) {
	    $('#page-bar').html(pagebar);
	}
	
	//alert창 띄울 때
	function alertMsg(msg){
		alert(msg);
	}
	
	$('.employee-link').on('click', function(e){
	    e.preventDefault();
	    var empId = $(this).data('empid');
	    $.ajax({
			url:"${path}/rest/employee/selectone",
			method:'GET',
			data: {empId, empId},
	        success: function(response) {
	            // 응답 데이터 처리
	            const emp = response;
	            let title;
	            switch(emp.empTitle){
	            case "J1" : title="원장";
	            case "J2" : title="팀장";
	            case "J3" : title="매니저";
	            }
	            console.log(emp);
				 $('#empId').text(emp.empId);
                 $('#empHireDate').text(emp.empHireDate);
                 $('#empStatus').text(emp.empYn=='0'?'재직':'퇴직');
                 $('#empTitle').text(title);
                 $('#empAddress').text(emp.empAddress);
                 $('#empName').text(emp.empName);
                 $('#empEmail').text(emp.empEmail);
                 if(emp.empProfile!=null){
	                 $('.img-fluid').attr('src', path+"/resources/upload/chs/employee/"+empProfile);
                 }else{
	                 $('.img-fluid').attr('src', path+"/resources/upload/chs/employee/user.png");
                 }
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	            console.error('Error: ' + textStatus, errorThrown);
	        }
		});
	   $('#employeeModal').modal('show');
	});
</script>