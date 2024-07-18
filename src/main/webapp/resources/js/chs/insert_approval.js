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

    function moveItems(fromSelector, toSelector) {
        $(fromSelector).each(function() {
            const itemText = $(this).text();
            // 중복 검사
            if ($(toSelector).find(`p:contains(${itemText})`).length === 0) {
                const newItem = $('<p>').text(itemText).addClass('select-user');
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
        console.log("Confirm button clicked");
        applyApprovalLine();
        saveToForm();
    });

    function applyApprovalLine() {
        console.log("Applying approval line");
        const approvalPrincipal = $('#approval-principal');
        const approvalTeamLeader = $('#approval-team-leader');
        const referTeamLeader = $('#refer-team-leader');
        const referManager = $('#refer-manager');

        const approvalPrincipalSign = $('#approval-principal-sign');
        const approvalTeamLeaderSign = $('#approval-team-leader-sign');
        const referTeamLeaderSign = $('#refer-team-leader-sign');
        const referManagerSign = $('#refer-manager-sign');

        approvalPrincipal.empty();
        approvalTeamLeader.empty();
        referTeamLeader.empty();
        referManager.empty();

        approvalPrincipalSign.empty();
        approvalTeamLeaderSign.empty();
        referTeamLeaderSign.empty();
        referManagerSign.empty();

        // 결재 목록에서 결재자 정보 가져오기
        $('.select-approval p').each(function() {
            const text = $(this).text();
            console.log("Selected approval:", text);
            const signImage = '<img src="/path/to/signature.png" alt="Signature" width="50">';
            if (text.includes('(원장)')) {
                approvalPrincipal.text(text);
                approvalPrincipalSign.html(signImage);
            } else if (text.includes('(팀장)')) {
                approvalTeamLeader.text(text);
                approvalTeamLeaderSign.html(signImage);
            }
        });

        // 참조 목록에서 참조자 정보 가져오기
        $('.select-refer p').each(function() {
            const text = $(this).text();
            console.log("Selected refer:", text);
            const signImage = '<img src="/path/to/signature.png" alt="Signature" width="50">';
            if (text.includes('(팀장)') && referTeamLeader.text() === "") {
                referTeamLeader.text(text);
                referTeamLeaderSign.html(signImage);
            } else if (text.includes('(매니저)') && referManager.text() === "") {
                referManager.text(text);
                referManagerSign.html(signImage);
            }
        });

        // 빈 셀 행 숨기기
        if (approvalPrincipal.text() === "" && approvalTeamLeader.text() === "") {
            $('#approval-principal').closest('tr').hide();
        } else {
            $('#approval-principal').closest('tr').show();
        }

        if (referTeamLeader.text() === "" && referManager.text() === "") {
            $('#refer-team-leader').closest('tr').hide();
        } else {
            $('#refer-team-leader').closest('tr').show();
        }

        modal_close();
    }

    function saveToForm() {
        console.log("Saving to form");
        const approvalList = [];
        const referList = [];

        // 결재 목록에서 결재자 정보 가져오기
        $('.select-approval p').each(function() {
            approvalList.push($(this).text());
        });

        // 참조 목록에서 참조자 정보 가져오기
        $('.select-refer p').each(function() {
            referList.push($(this).text());
        });

        console.log("Approval list:", approvalList);
        console.log("Refer list:", referList);

        // 숨겨진 필드에 저장
        $('#approval-line').val(approvalList.join(','));
        $('#carbon-copy').val(referList.join(','));

        // 폼 제출 (필요시 사용)
        // $('#approval-form').submit();
    }
});

// 모달 열기 및 데이터 로딩
function modal_on() {
    // 결재선 불러오기 전에 select 요소 초기화
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
                    const userElement = `<p class='user-bottom'>${data.empName}(${title})</p>`;
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
                console.log("Selected frequent line:", this.value);
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
                                $('.select-approval').append($('<p>').text(fp.employee.empName + '(' + title + ')').addClass('select-user'));
                            } else {
                                $('.select-refer').append($('<p>').text(fp.employee.empName + '(' + title + ')').addClass('select-user'));
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

    // 모달 열기
    $('.hs-modal-container').css({
        display: 'block',
        "background-color": "rgba(0,0,0,0.6)"
    });
}

// 모달 닫기
const modal_close = () => {
    $('.hs-modal-container').css('display', 'none');
};
