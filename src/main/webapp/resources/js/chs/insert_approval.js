
$(document).ready(function() {
    // 사용자 선택 및 선택 해제 토글
    $('body').on('click', '.user-bottom, .select-user', function() {
        $(this).toggleClass('selected');
    });

    // 결재 목록으로 이동
    $('#approval-right').click(function() {
        moveItems('.content-right .selected', '.select-approval');
    });

    // 참조 목록으로 이동
    $('#refer-right').click(function() {
        moveItems('.content-right .selected', '.select-refer');
    });

    // 결재 목록에서 콘텐츠 영역으로 이동
    $('#approval-left').click(function() {
        moveItems('.select-approval .selected', '.content-right');
    });

    // 참조 목록에서 콘텐츠 영역으로 이동
    $('#refer-left').click(function() {
        moveItems('.select-refer .selected', '.content-right');
    });

    // 요소를 다른 목록으로 이동
    function moveItems(fromSelector, toSelector) {
        $(fromSelector).each(function() {
            const itemText = $(this).text();
            const empId = $(this).data('empid');
            // 중복 검사
            if ($(toSelector).find(`p:contains(${itemText})`).length === 0) {
                const newItem = $('<p>').text(itemText).addClass('select-user').data('empid', empId);
                $(this).removeClass('selected');
                $(this).remove();
                $(toSelector).append(newItem);
            } else {
                $(this).removeClass('selected');
            }
        });
    }

    // 확인 버튼 클릭 시 결재자와 참조자를 테이블에 반영하고 폼에 저장
    $('#confirm-button').click(function() {
        applyApprovalLine();
        saveToForm();
    });

    // 확인 버튼을 누를 때 폼으로 넘겨지는 로직
    function applyApprovalLine() {
        const approvalPrincipal = $('#approval-principal'); // 결재자 원장
        const approvalTeamLeader = $('#approval-team-leader'); // 결재자 팀장
        const referTitle1 = $('#refer-title1');
        const referTitle2 = $('#refer-title2');
        
        const referFirst = $('#refer-first'); // 참조자 컨테이너
        const referLast = $('#refer-last'); // 참조자 컨테이너
        // 결재자와 참조자 칸 초기화
        approvalPrincipal.empty();
        approvalTeamLeader.empty();
        referTitle1.empty(); 
		referTitle2.empty();
		referFirst.empty();
		referLast.empty();
        let hasPrincipal = false;
        let hasTeamLeader = false;

        // 결재 목록에서 결재자 정보 가져오기
        $('.select-approval p').each(function() {
            const text = $(this).text();
            const empId = $(this).data('empid');
            const title = getTitleFromText(text); // 직급 정보 추출
            if (title === '원장') {
                approvalPrincipal.text(text);
                hasPrincipal = true;
            } else if (title === '팀장') {
                approvalTeamLeader.text(text);
                hasTeamLeader = true;
            }
        });

        if (!hasPrincipal || !hasTeamLeader) {
            alert('결재에는 원장과 팀장이 들어가야 합니다.');
            return;
        }

        // 참조 목록에서 참조자 정보 가져오기
        $('.select-refer p').each(function() {
            const text = $(this).text();
            const empId = $(this).data('empid');
            const title = getTitleFromText(text); // 직급 정보 추출
            
            if(referTitle1.text()==''){
				referTitle1.text(title);
				referFirst.text(text);
			}else if(referTitle2.text()==''){
				referTitle2.text(title);
				referLast.text(text);
			}
        });

        modal_close(); // 모달 닫기
    }
	
    // 폼에 결재자와 참조자 정보 저장
    function saveToForm() {
        console.log("Saving to form");
        const approvalList = [];
        const referList = [];

        // 결재 목록에서 결재자 정보 가져오기
        $('.select-approval p').each(function() {
            const empId = $(this).data('empid');
            const text = $(this).text();
            const title = getTitleFromText(text);
            const order = (title === '원장') ? 0 : 1; // 결재 순번 설정
            approvalList.push({ empId: empId, order: order });
        });

        // 참조 목록에서 참조자 정보 가져오기
        $('.select-refer p').each(function() {
            const empId = $(this).data('empid');
            const text = $(this).text();
             referList.push({empId:empId});
        });

        console.log("Approval list:", approvalList);
        console.log("Refer list:", referList);

        // 숨겨진 필드에 저장 (JSON 형태로 저장)
        $('#approval-field').val(JSON.stringify(approvalList));
        $('#refer-field').val(JSON.stringify(referList));
    }

    // 텍스트에서 직급 정보 추출
    function getTitleFromText(text) {
        if (text.includes('원장')) {
            return '원장';
        } else if (text.includes('팀장')) {
            return '팀장';
        } else if (text.includes('매니저')) {
            return '매니저';
        }
        return '';
    }
});

// 모달 열기
function modal_on() {
    const select = document.querySelector('.search-approval-line select');
    select.innerHTML = '<option value="" disabled selected>자주쓰는 결재라인</option>';

    $.ajax({
        url: `${path}/rest/approval/line/${empId}`,
        dataType: 'json',
        success: function(response) {
            const positions = { '원장': [], '팀장': [], '매니저': [] };

            response.forEach(data => {
                if (data.empTitle === 'J1') {
                    positions['원장'].push(data);
                } else if (data.empTitle === 'J2') {
                    positions['팀장'].push(data);
                } else if (data.empTitle === 'J3') {
                    positions['매니저'].push(data);
                }
            });

            let userTop = $('.user-top');
            userTop.off('click').on('click', function() {
                userTop.removeClass('selected');
                $(this).addClass('selected');
                let text = this.innerText;
                let contentRight = $('.content-right');
                contentRight.empty(); // 클릭할 때마다 내용을 초기화

                // 선택된 직급에 해당하는 데이터를 추가
                positions[text].forEach(data => {
                    let title;
                    switch (data.empTitle) {
                        case 'J1':
                            title = '원장';
                            break;
                        case 'J2':
                            title = '팀장';
                            break;
                        case 'J3':
                            title = '매니저';
                            break;
                    }
                    const userElement = `<p class='user-bottom' data-empid='${data.empId}'>${data.empName}(${title})</p>`;
                    // 중복 검사
                    if ($('.select-approval').find(`p:contains(${data.empName}(${title}))`).length === 0 &&
                        $('.select-refer').find(`p:contains(${data.empName}(${title}))`).length === 0) {
                        contentRight.append(userElement);
                    }
                });
            });
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error('Error:', textStatus, errorThrown);
        }
    });

    $.ajax({
        url: `${path}/rest/approval/${empId}`,
        dataType: 'json',
        success: function(response) {
            response.forEach(frequentLine => {
                select.options.add(new Option(frequentLine.feqName, frequentLine.feqId));
            });

            select.addEventListener('change', function() {
                $('.select-approval').text('');
                $('.select-refer').text('');
                const frqId = this.value;
                response.forEach(data => {
                    if (data.feqId == frqId) {
                        data.frequentperson.forEach(fp => {
                            let title;
                            switch (fp.employee.empTitle) {
                                case 'J1':
                                    title = '원장';
                                    break;
                                case 'J2':
                                    title = '팀장';
                                    break;
                                case 'J3':
                                    title = '매니저';
                                    break;
                            }
                            if (fp.feqType === '0') {
                                $('.select-approval').append($('<p>').text(fp.employee.empName + '(' + title + ')').addClass('select-user').data('empid', fp.employee.empId));
                            } else {
                                $('.select-refer').append($('<p>').text(fp.employee.empName + '(' + title + ')').addClass('select-user').data('empid', fp.employee.empId));
                            }
                        });
                    }
                });
            });
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error('Error:', textStatus, errorThrown);
        }
    });

    $('.hs-modal-container').css({
        display: 'block',
        "background-color": "rgba(0,0,0,0.6)"
    });
}
 // 모달 열기
		    const openApprovalLineModal = function() {
		        document.getElementById('approval-line-modal').style.display = 'flex';
		    }

		    // 모달 닫기
		    const closeApprovalLineModal = function() {
		        document.getElementById('approval-line-modal').style.display = 'none';
		    }

		    // 자주쓰는 결재라인 추가하기
		    const addApprovalLine = function() {
		        openApprovalLineModal();
		    }

		    // 결재라인 추가하기 함수
		    const saveApprovalLine = function() {
		        const frequentperson = [];
		        const checkPerson = [];
		        const favoriteName = document.getElementById('favorite-name').value;
				console.log($('#approval-list .selected'));
		        // 결재 목록에서 결재자 정보 가져오기
		        $('.select-approval p').each(function() {
		            const empId = $(this).data('empid');
		            frequentperson.push({empId:empId,feqType:'0'});
		            checkPerson.push('1');
		        });

		          // 참조 목록에서 참조자 정보 가져오기
		        $('.select-refer p').each(function() {
		            const empId = $(this).data('empid');
		            frequentperson.push({empId:empId,feqType:'1'});
		        });

		        // 결재자와 참조자가 선택되었는지 확인
		        if (checkPerson.length < 2) {
		            alert('결재자는 최소 2명이 있어야 합니다.');
		            console.log(approvalLine);
		            return;
		        }
		        if (!favoriteName) {
		            alert('즐겨찾기 이름을 입력해주세요.');
		            return;
		        }

		        // AJAX로 서버에 데이터 전송
		        $.ajax({
		            url: `${path}/rest/approval/addApprovalLine`,
		            type: 'POST',
		            contentType: 'application/json',
		            data: JSON.stringify({
		                feqName: favoriteName,
		                empId:empId,
		                frequentperson: frequentperson,
		            }),
		            success: function(response) {
		                alert('결재라인이 성공적으로 추가되었습니다.\n새로고침 후 이용하세요.');
		                closeApprovalLineModal();
		                // 추가 작업
		            },
		            error: function(xhr, status, error) {
		                alert('결재라인 추가 중 오류가 발생했습니다.');
		                console.error(error);
		            }
		        });
		    };
		
// 모달 닫기
const modal_close = () => {
    $('.hs-modal-container').css('display', 'none');
};

