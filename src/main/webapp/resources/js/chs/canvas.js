// 서명 모달 열기
function openSignatureModal() {
    document.getElementById('signature-modal').style.display = 'flex';
}

// 서명 모달 닫기
function closeSignatureModal() {
    document.getElementById('signature-modal').style.display = 'none';
}

// 서명 저장
function saveSignature() {
    const canvas = document.getElementById('signature-canvas');
    const dataUrl = canvas.toDataURL('image/png');
    $.ajax({
        type: "POST",
        url: `${path}/rest/approval/upload`,
        data: {
            image: dataUrl,
            empId: empId2
        },
        success: function(response) {
            alert("서명이 저장되었습니다.");
            closeSignatureModal();
            location.reload();
        },
        error: function(error) {
            console.error("Error:", error);
            alert("서명 저장 중 오류가 발생했습니다.");
        }
    });
}

// 캔버스에 서명 기능 추가
const canvas = document.getElementById('signature-canvas');
const ctx = canvas.getContext('2d');
let drawing = false;

canvas.addEventListener('mousedown', (event) => {
    drawing = true;
    draw(event); // 마우스가 눌릴 때도 그리기 시작
});

canvas.addEventListener('mouseup', () => {
    drawing = false;
    ctx.beginPath(); // 새로운 경로 시작
});

canvas.addEventListener('mousemove', (event) => draw(event));

function draw(event) {
    if (!drawing) return;
    const rect = canvas.getBoundingClientRect();
    const x = event.clientX - rect.left;
    const y = event.clientY - rect.top;

    ctx.lineWidth = 2;
    ctx.lineCap = 'round';
    ctx.strokeStyle = 'black';

    ctx.lineTo(x, y);
    ctx.stroke();
    ctx.beginPath();
    ctx.moveTo(x, y);
}
