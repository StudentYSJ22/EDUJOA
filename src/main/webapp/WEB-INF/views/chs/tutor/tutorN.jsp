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
				<p>퇴직자 <span>${count }</span></p>
			</div>
			<div class="chs-thead-2">
				<div class="delete-container">
            	</div>
				<div>
				<select name="type" id="type" style="height:100%;">
					<option value="empName">이름</option>
				</select>
				<input type="text" id="content" onkeyup="selectTutor();">
                <select name="rowbounds" id="rowbounds" style="height:100%">
                   <option value="10" ${numPerpage == 10 ? 'selected' : ''}>10</option>
                    <option value="5" ${numPerpage == 5 ? 'selected' : ''}>5</option>
                    <option value="3" ${numPerpage == 3 ? 'selected' : ''}>3</option>
                </select>
            </div>
        </div>
        <div class="chs-tbody">
            <div id="tutor-list">
                <ul class="chs-tbody-header">
                    <li style="width:15%">이름</li>
                    <li style="width:15%">과목</li>
                    <li style="width:20%">입사 일자</li>
                    <li style="width:20%">전화번호</li>
                    <li style="width:20%">이메일</li>
                </ul>
                <c:forEach var="t" items="${tutors}">
                    <ul class="chs-tbody-body">
                        <li style="width:15%; font-weight:bold" data-ttid="${t.ttId }" class="tutor-link"><a href="#">${t.ttName }</a></li>                       
                        <li style="width:15%">${t.subject.subName }</li>
                        <li style="width:20%">${t.ttHireDate }</li>
                        <li style="width:20%">${t.ttPhone }</li>
                        <li style="width:20%">${t.ttEmail }</li>
                    </ul>
                </c:forEach>
            </div>
            <div id="page-bar" style="height:60px;">
                ${pagebar}
            </div>
            <!-- Modal HTML -->
				<div class="modal fade" id="tutorModal" tabindex="-1" aria-labelledby="tutorModalLabel" aria-hidden="true">
				  <div class="modal-dialog modal-lg">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="tutorModalLabel">인사 카드</h5>
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				        <!-- tutor details will be loaded here -->
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
				                      <td id="ttId"></td>
				                      <th>입사일자</th>
				                      <td id="ttHireDate"></td>
				                    </tr>
				                    <tr>
				                      <th>담당 과목</th>
				                      <td id="ttSubject"></td>
				                      <th>재직 구분</th>
				                      <td id="ttStatus"></td>
				                    </tr>
				                    <tr>
				                      <th>전화번호</th>
				                      <td id="ttPhone" colspan="3"></td>
				                    </tr>
				                    <tr>
				                      <th>성명(한글)</th>
				                      <td id="ttName"></td>
				                      <th>개인 메일 주소</th>
				                      <td id="ttEmail" colspan="3"></td>
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
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
<script>
	
	//전체 선택 체크박스 클릭 이벤트
	$('#select-all').click(function() {
	    $('.select-row').prop('checked', this.checked);
	});
	
	// 개별 체크박스 클릭 시 전체 선택 체크박스 상태 변경
	$('.select-row').click(function() {
	    $('#select-all').prop('checked', $('.select-row:checked').length === $('.select-row').length);
	});
	function loadtutor() {
	    const rowBounds = $('#rowbounds').val();
	    const cPage = 1; // 초기 페이지 번호 (필요에 따라 변경 가능)
	
	    $.ajax({
	        url: "${path}/rest/tutor/selectall",
	        method: 'GET',
	        data: {
	            numPerpage: rowBounds,
	            cPage: cPage,
	            ttYn:'1',
	        },
	        success: function(response) {
	            // 응답 데이터 처리
	            const tutors = response.tutors;
	            const pagebar = response.pagebar;
	            console.log(tutors);
	            console.log(pagebar);
	
	            // 승인 목록과 페이지 바 업데이트
	            updatetutorList(tutors);
	            updatePageBar(pagebar);
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	            console.error('Error: ' + textStatus, errorThrown);
	        }
	    });
	}
	
	
	// 드롭다운 변경 시 직원 목록 다시 불러오기
	$('#rowbounds').change(loadtutor);
	
	function updatetutorList(tutors) {
	    const tutorList = document.getElementById('tutor-list');
	    
	    // chs-tbody-header는 유지하고, 그 이후의 모든 요소를 제거합니다.
	    const children = tutorList.children;
	    for (let i = children.length - 1; i >= 1; i--) {
	    	tutorList.removeChild(children[i]);
	    }
	
	    tutors.forEach(function(t) {
	    	const ul = "<ul class=\"chs-tbody-body\">" +
	        "<li style=\"width:15%; font-weight:bold\" data-ttid=" + t.ttId + " class='tutor-link'><a href='#'>" + t.ttName + "</a></li>" +
	        "<li style=\"width:15%\">" + t.subject.subName + "</li>" +
	        "<li style=\"width:20%\">" + t.ttHireDate + "</li>" +
	        "<li style=\"width:20%\">" + t.ttPhone + "</li>" +
	        "<li style=\"width:20%\">" + t.ttEmail + "</li>" +
	        "<li style=\"width:10%\"><button class=\"btn btn-sm btn-outline-primary\">수정</button></li>" +
	    	"</ul>";

	        tutorList.innerHTML += ul;
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
	function updatetutor(empId,empTitle){
		if(empTitle != 'J1'){
			return alertMsg("권한이 부족합니다.");
		}
	}
	
	// 삭제 버튼 클릭 이벤트
	const deletetutor = function(empTitle){
    	if(empTitle != 'J1'){
    		return alertMsg("권한이 부족합니다.");
    	}
        const selectedIds = $('.select-row:checked').map(function() {
            return $(this).data('id');
        }).get();
		console.log(selectedIds);
        if (selectedIds.length > 0) {
            $.ajax({
                url: "${path}/rest/tutor/delete",
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
	const selectTutor = function(){
		const type = $('#type').val();
		const content = $('#content').val();
		// 필요한 변수를 미리 정의
		var requestData = {
		    ttYn: '1'
		};

		// 조건에 따라 데이터를 추가
		if (type == 'subId') {
		    requestData.subId = content;
		} else {
		    requestData.ttName = content;
		}
		$.ajax({
			url:"${path}/rest/tutor/selectall",
			method:'GET',
			data: requestData,
	        success: function(response) {
	            // 응답 데이터 처리
	            const tutors = response.tutors;
	            const pagebar = response.pagebar;
	            console.log(tutors);
	            console.log(pagebar);
	
	            // 승인 목록과 페이지 바 업데이트
	            updatetutorList(tutors);
	            updatePageBar(pagebar);
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	            console.error('Error: ' + textStatus, errorThrown);
	        }
		});
	}
	
	$('.tutor-link').on('click', function(e){
	    e.preventDefault();
	    var ttId = $(this).data('ttid');
	    $('#ttId').text('');
        $('#ttHireDate').text('');
        $('#ttStatus').text('');
        $('#ttSubject').text('');
        $('#ttPhone').text('');
        $('#ttName').text('');
        $('#ttEmail').text('');
        $('#classId').text('');
        $('#classOpen').text('');
        $('#classClose').text('');
        $('.img-fluid').attr('src','');
	    $.ajax({
			url:"${path}/rest/tutor/selectone",
			method:'GET',
			data: {ttId, ttId},
	        success: function(response) {
	        	 console.log(response);
	            // 응답 데이터 처리
	            const tt = response;
	            if(tt.ttProfile!='' || tt.ttProfile!=null){
	                 $('.img-fluid').attr('src', path+"/resources/upload/chs/tutor/"+tt.ttProfile);
                }else{
	                 $('.img-fluid').attr('src', path+"/resources/upload/chs/tutor/user.png");
                }
				 $('#ttId').text(tt.ttId);
                 $('#ttHireDate').text(tt.ttHireDate);
                 $('#ttStatus').text(tt.ttYn=='0'?'재직':'퇴직');
                 $('#ttSubject').text(tt.subject.subName);
                 $('#ttPhone').text(tt.ttPhone);
                 $('#ttName').text(tt.ttName);
                 $('#ttEmail').text(tt.ttEmail);
                 $('#classId').text(tt.class_.classId);
                 $('#classOpen').text(tt.class_.classOpen);
                 $('#classClose').text(tt.class_.classClose);
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	            console.error('Error: ' + textStatus, errorThrown);
	        }
		});
	   $('#tutorModal').modal('show');
	});
</script>