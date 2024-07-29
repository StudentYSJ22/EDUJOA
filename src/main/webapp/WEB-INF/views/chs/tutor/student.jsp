<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<c:set var ="loginMember" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}"/>
<link rel="stylesheet" href="${path }/resources/css/chs/tutor/tutor.css">
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="chs-custom">
		<jsp:include page="/WEB-INF/views/chs/personnel/left_personnel.jsp"/>
		<div class="chs-table">
			<div class="chs-thead">
				<p>학생 수 <span>${count }</span></p>
			</div>
			<div class="chs-thead-2">
				<div class="delete-container">
					<c:if test="${loginMember.empTitle == 'J1'}">
		               	 <button id="delete-selected" onclick="deleteStudent('${loginMember.empTitle}');">재적 처리</button>
					</c:if>
            	</div>
				<div>
				<input type="radio" name="stdYn" value="" ${stdYn == '' ? 'checked' : '' } onchange="selectStudent()"> 전체
                <input type="radio" name="stdYn" value="0" ${stdYn == '0' ? 'checked' : '' } onchange="selectStudent()"> 재학
                <input type="radio" name="stdYn" value="1" ${stdYn == '1' ? 'checked' : '' } onchange="selectStudent()"> 제적    
				<select name="type" id="type" style="height:100%;">
					<option value="stdName">이름</option>
					<option value="stdSchool">학교 이름</option>
				</select>
				<input type="text" id="content" onkeyup="selectStudent();">
                <select name="rowbounds" id="rowbounds" style="height:100%">
                   <option value="10" ${numPerpage == 10 ? 'selected' : ''}>10</option>
                    <option value="5" ${numPerpage == 5 ? 'selected' : ''}>5</option>
                    <option value="3" ${numPerpage == 3 ? 'selected' : ''}>3</option>
                </select>
            </div>
        </div>
        <div class="chs-tbody">
            <div id="student-list">
                <ul class="chs-tbody-header">
                	<c:if test="${loginMember.empTitle ==  'J1' || loginMember.empTitle=='J3'}">
                		<li style="width:5%"><input type="checkbox" id="select-all"></li>
                	</c:if>
                    <li style="width:15%">이름</li>
                    <li style="width:15%">학교</li>
                    <li style="width:20%">전화번호</li>
                    <li style="width:20%">보호자 전화번호</li>
                    <li style="width:20%">상태</li>
                    <li style="width:10%">관리</li>
                </ul>
                <c:forEach var="std" items="${students}">
                    <ul class="chs-tbody-body">
                	    <c:if test="${loginMember.empTitle ==  'J1' || loginMember.empTitle=='J3'}">
                    		<li style="width:5%"><input type="checkbox" class="select-row" data-id="${std.stdId}"></li>
                    	</c:if>
                        <li style="width:15%; font-weight:bold" data-stdid="${std.stdId }" class="std-link"><a href="#">${std.stdName }</a></li>                       
                        <li style="width:15%">${std.stdSchool }</li>
                        <li style="width:20%">${std.stdPhone }</li>
                        <li style="width:20%">${std.stdParentPhone }</li>
                        <li style="width:20%">${std.stdYn=='0' ? '재학':'제적' }</li>
                        <li style="width:10%"><button class="btn btn-sm btn-outline-primary" onclick="updateStudent('${std.stdId}','${loginMember.empTitle }');">수정</button></li>
                    </ul>
                </c:forEach>
            </div>
            <div id="page-bar" style="height:60px;">
                ${pagebar}
            </div>
            <!-- Modal HTML -->
				<div class="modal fade" id="stdModal" tabindex="-1" aria-labelledby="studentModalLabel" aria-hidden="true">
				  <div class="modal-dialog modal-lg">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="studentModalLabel">학생 정보</h5>
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				        <!-- tutor details will be loaded here -->
				        <div class="card">
				          <div class="card-body">
				            <div class="row">
				              <div class="col-md-2">
				              </div>
				              <div class="col-md-9">
				                <table class="table table-bordered">
				                  <tbody>
				                    <tr>
				                      <th>학생 번호</th>
				                      <td id="stdId"></td>
				                      <th>결제 금액</th>
				                      <td id="myClass"></td>
				                    </tr>
				                    <tr>
				                      <th>이름</th>
				                      <td id="stdName"></td>
				                      <th>학교</th>
				                      <td id="stdSchool"></td>
				                    </tr>
				                    <tr>
				                      <th>전화번호</th>
				                      <td id="stdPhone" colspan="3"></td>
				                    </tr>
				                    <tr>
				                      <th>보호자 전화번호</th>
				                      <td id="stdParentPhone" colspan="3"></td>
				                    </tr>
				                    <tr>
				                      <th>수강 목록</th>
				                      <td id="myClassList" colspan="3"></td>
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
        
    </div>
    
	</div>
	<div id="msg">${msg }</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
<script>
	const msg = $("#msg").text();
	if(msg != null && msg != ''){
		alert(msg);
	}
	//전체 선택 체크박스 클릭 이벤트
	$('#select-all').click(function() {
	    $('.select-row').prop('checked', this.checked);
	});
	
	// 개별 체크박스 클릭 시 전체 선택 체크박스 상태 변경
	$('.select-row').click(function() {
	    $('#select-all').prop('checked', $('.select-row:checked').length === $('.select-row').length);
	});
	function loadStudent() {
	    const rowBounds = $('#rowbounds').val();
	    const cPage = 1; // 초기 페이지 번호 (필요에 따라 변경 가능)
	    const stdYn = $('input[name="stdYn"]:checked').val();
	    $.ajax({
	        url: "${path}/rest/tutor/selectallstudent",
	        method: 'GET',
	        data: {
	            numPerpage: rowBounds,
	            cPage: cPage,
	            stdYn: stdYn
	        },
	        success: function(response) {
	            // 응답 데이터 처리
	            const students = response.students;
	            const pagebar = response.pagebar;
				
	            // 승인 목록과 페이지 바 업데이트
	            updateStudentList(students);
	            updatePageBar(pagebar);
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	            console.error('Error: ' + textStatus, errorThrown);
	        }
	    });
	}
	
	
	// 드롭다운 변경 시 직원 목록 다시 불러오기
	$('#rowbounds').change(loadStudent);
	
	function updateStudentList(students) {
	    const studentList = document.getElementById('student-list');
	    // chs-tbody-header는 유지하고, 그 이후의 모든 요소를 제거합니다.
	    const children = studentList.children;
	    for (let i = children.length - 1; i >= 1; i--) {
	    	studentList.removeChild(children[i]);
	    }
	    students.forEach(function(st) {
	    	 const ul = "<ul class=\"chs-tbody-body\">" +
	         "<li style=\"width:5%\"><input type=\"checkbox\" class=\"select-row\" data-id=\"" + st.stdId + "\"></li>" +
	         "<li style=\"width:15%; font-weight:bold\" data-stdid=\"" + st.stdId + "\" class='student-link'><a href='#'>" + st.stdName + "</a></li>" +
	         "<li style=\"width:15%\">" + st.stdSchool + "</li>" +
	         "<li style=\"width:20%\">" + st.stdPhone + "</li>" +
	         "<li style=\"width:20%\">" + st.stdParentPhone + "</li>" +
	         "<li style=\"width:20%\">" + (st.stdYn == '0' ? '재학' : '제적') + "</li>" +
	         "<li style=\"width:10%\"><button class=\"btn btn-sm btn-outline-primary\">수정</button></li>" +
	         "</ul>";

	    	studentList.innerHTML += ul;
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
	//수정 버튼 눌렀을 때
	function updateStudent(stdId,empTitle){
		if(empTitle != 'J1' && empTitle!="J3"){
			return alertMsg("권한이 부족합니다.");
		}else{
			location.assign("${path}/tutor/updatestudent?stdId="+stdId);
		}
	}
	
	// 삭제 버튼 클릭 이벤트
	const deleteStudent = function(empTitle){
    	if(empTitle != 'J1' && empTitle!="J3"){
    		return alertMsg("권한이 부족합니다.");
    	}
        const selectedIds = $('.select-row:checked').map(function() {
            return $(this).data('id');
        }).get();
		console.log(selectedIds);
        if (selectedIds.length > 0) {
            $.ajax({
                url: "${path}/rest/tutor/deletestudent",
                method: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(selectedIds),
                success: function(response) {
                    alert(response);
                    location.reload();
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.error('Error: ' + textStatus, errorThrown);
                }
            });
        } else {
            alert('삭제할 항목을 선택하세요.');
        }
    }
	
	//검색 창에 key가 눌릴 때
	const selectStudent = function(){
	    const type = $('#type').val();
	    const content = $('#content').val();
	    const stdYn = $('input[name="stdYn"]:checked').val();
	    const rowbounds = $('#rowbounds').val();
	    const requestData = {
	        stdYn: stdYn,
	        rowbounds: rowbounds
	    };
	    if(type=='stdName'){
	    	requestData.stdName = content;
	    }
	    if(type=='stdShcool'){
	    	requestData.stdShcool = content;
	    }
	    $.ajax({
	        url: "${path}/rest/tutor/selectallstudent",
	        method: 'GET',
	        data: requestData,
	        success: function(response) {
	            console.log(response);
	            // 응답 데이터 처리
	            const students = response.students;
	            const pagebar = response.pagebar;
	            const count = response.count;
	            updateStudentList(students);
	            updatePageBar(pagebar);
	            $('.chs-thead span').text(count);
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	            console.error('Error: ' + textStatus, errorThrown);
	        }
	    });
	}
	
	$('.std-link').on('click', function(e){
	    e.preventDefault();
	    var stdId = $(this).data('stdid');
	    $('#stdId').text('');
	    $('#stdName').text('');
        $('#stdSchool').text('');
        $('#myClass').text('');
        $('#stdPhone').text('');
        $('#stdParentPhone').text('');
	    $.ajax({
			url:"${path}/rest/tutor/selectonestudent",
			method:'GET',
			data: {stdId, stdId},
	        success: function(response) {
	            // 응답 데이터 처리
	            const myClassList = $('#myClassList');
	            const st = response.student;
	            const myClass = response.myClass;
				 $('#stdId').text(st.stdId);
                 $('#stdName').text(st.stdName);
                 $('#stdSchool').text(st.stdSchool);
                 $('#stdPhone').text(st.stdPhone);
                 $('#stdParentPhone').text(st.stdParentPhone);
                 $('#myClass').text(st.stdPayment);
                 myClassList.empty();
                 for(let i = 0; i< myClass.length; i++){
                	 if(st.stdId == myClass[i].stdId){
	                	 myClassList.append('<li>'+myClass[i].classId+"</li>")
                	 }
                 }
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	            console.error('Error: ' + textStatus, errorThrown);
	        }
		});
	   $('#stdModal').modal('show');
	});
</script>