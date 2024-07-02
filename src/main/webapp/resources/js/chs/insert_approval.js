// 모달 열기
const modal_on = () => {
    $('.hs-modal-container').css({
        display: 'block',
        "background-color": "rgba(0,0,0,0.6)"
    });
};

// 모달 닫기
const modal_close = () => {
    $('.hs-modal-container').css("display", "none");
};

// 항목 이동 함수
function moveItems(fromSelector, toSelector) {
    const fromElement = document.querySelector(fromSelector);
    const toElement = document.querySelector(toSelector);

    const selectedItems = fromElement.querySelectorAll('.user-bottom.selected, .select-user.selected');

    // 결재와 참조 리스트의 최대 항목 수 제한 (2명)
    if (toSelector === '#approval-list' && toElement.children.length >= 2) {
        alert('결재는 최대 2명까지 선택할 수 있습니다.');
        return;
    }
    if (toSelector === '#refer-list' && toElement.children.length >= 2) {
        alert('참조는 최대 2명까지 선택할 수 있습니다.');
        return;
    }

    selectedItems.forEach(item => {
        item.classList.remove('selected');
        toElement.appendChild(item);
    });

    // 최대 인원 초과 시 경고창 띄우기
    if (toSelector === '#approval-list' && toElement.children.length > 2) {
        alert('결재는 최대 2명까지 선택할 수 있습니다.');
        const excessItems = Array.from(toElement.children).slice(2);
        excessItems.forEach(item => {
            item.classList.remove('selected');
            fromElement.appendChild(item);
        });
    }
    if (toSelector === '#refer-list' && toElement.children.length > 2) {
        alert('참조는 최대 2명까지 선택할 수 있습니다.');
        const excessItems = Array.from(toElement.children).slice(2);
        excessItems.forEach(item => {
            item.classList.remove('selected');
            fromElement.appendChild(item);
        });
    }
}

// 문서가 로드된 후
document.addEventListener('DOMContentLoaded', function() {
    // ID가 올바르게 설정되었는지 확인
    const approvalRightButton = document.getElementById('approval-right');
    const approvalLeftButton = document.getElementById('approval-left');
    const referRightButton = document.getElementById('refer-right');
    const referLeftButton = document.getElementById('refer-left');
    const modalOpenButton = document.getElementById('modal-open');
    const modalCloseButton = document.getElementById('modal-close');
    const cancelButton = document.getElementById('cancel-button');

    if (approvalRightButton) {
        approvalRightButton.addEventListener('click', function() {
            moveItems('.content-right', '#approval-list');
        });
    } else {
        console.error('Approval Right Button not found.');
    }

    if (approvalLeftButton) {
        approvalLeftButton.addEventListener('click', function() {
            moveItems('#approval-list', '.content-right');
        });
    } else {
        console.error('Approval Left Button not found.');
    }

    if (referRightButton) {
        referRightButton.addEventListener('click', function() {
            moveItems('.content-right', '#refer-list');
        });
    } else {
        console.error('Refer Right Button not found.');
    }

    if (referLeftButton) {
        referLeftButton.addEventListener('click', function() {
            moveItems('#refer-list', '.content-right');
        });
    } else {
        console.error('Refer Left Button not found.');
    }

    if (modalOpenButton) {
        modalOpenButton.addEventListener('click', modal_on);
    } else {
        console.error('Modal Open Button not found.');
    }

    if (modalCloseButton) {
        modalCloseButton.addEventListener('click', modal_close);
    } else {
        console.error('Modal Close Button not found.');
    }

    if (cancelButton) {
        cancelButton.addEventListener('click', () => {
            // 결재 리스트와 참조 리스트의 항목을 원래의 리스트로 이동
            document.querySelectorAll('#approval-list .user-bottom, #approval-list .select-user').forEach(item => {
                item.classList.remove('selected');
                document.querySelector('.content-right').appendChild(item);
            });

            document.querySelectorAll('#refer-list .user-bottom, #refer-list .select-user').forEach(item => {
                item.classList.remove('selected');
                document.querySelector('.content-right').appendChild(item);
            });

            // 모달 닫기
            modal_close();
        });
    } else {
        console.error('Cancel Button not found.');
    }

    // 클릭 시 항목 선택 토글
    document.querySelectorAll('.user-bottom, .select-user').forEach(item => {
        item.addEventListener('click', function() {
            this.classList.toggle('selected');
        });
    });

    // 모달 외부 클릭 시 모달 닫기
    document.querySelector('.hs-modal-container').addEventListener('click', function(event) {
        if (event.target === this) {
            modal_close();
        }
    });

    // 모달 내부 클릭 시 이벤트 전파 방지
    document.querySelector('.modal-custom').addEventListener('click', function(event) {
        event.stopPropagation();
    });
});
