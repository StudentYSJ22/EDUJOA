let inputTime = null;
let outputTime = null;

window.onload = function() {
	
            document.getElementById('empIdField').value = empId;
            console.log(document.getElementById('empIdField').value);
            document.getElementById('empIdFieldOutput').value = empId; // 퇴근 폼에도 empId 값을 설정

            document.getElementById('input').addEventListener('click', function(event) {
                setInputTime(event);
            });

            document.getElementById('output').addEventListener('click', function(event) {
                setOutputTime(event);
            });
        };

// 시간을 '오전 09:10:12' 또는 '오후 03:22:45' 형식으로 반환하는 함수
//view단 에서 쓰는 함수
function formatTime(date) {
    const hours = date.getHours();
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const seconds = String(date.getSeconds()).padStart(2, '0');
    const period = hours >= 12 ? '오후' : '오전';
    const formattedHours = String(hours % 12 || 12).padStart(2, '0');
    
    return `${period} ${formattedHours}:${minutes}:${seconds}`;
}
// 시간을 'yyyy-MM-ddTHH:mm:ss' 형식으로 반환하는 함수
//java단에서 쓰는 함수
function formatTimeForServer(date) {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const seconds = String(date.getSeconds()).padStart(2, '0');
    return `${year}-${month}-${day}T${hours}:${minutes}:${seconds}`;
}



// 폼 제출 함수
function submitForm(formId) {
    const form = document.getElementById(formId);
    const formData = new FormData(form);

    fetch(form.action, {
        method: 'POST',
        body: formData
    })
    .then(response => {
        if (!response.ok) {
            return response.text().then(text => { throw new Error(text) });
        }
        return response.json();
    })
    .then(data => {
        console.log('Success:', data.message);
        // 성공 시 UI 업데이트
    })
    .catch(error => {
        console.error('Error:', error);
        // 오류 메시지 표시
    });
}

// 출근 버튼 클릭 이벤트
document.getElementById('input').addEventListener('click', function(event) {
    event.preventDefault();
    if (!inputTime) {
        const now = new Date();
        inputTime = formatTime(now);
        document.getElementById('inputTime').querySelector('b').innerText = inputTime;
        document.getElementById('inputTimeField').value = formatTimeForServer(now);
        submitForm('inputForm');
        this.classList.add('disabled');
        this.disabled = true;
    }
});



// 퇴근 버튼 클릭 이벤트
document.getElementById('output').addEventListener('click', function(event) {
    event.preventDefault();
    if (!outputTime) {
        const now = new Date();
        outputTime = formatTime(now);
        document.getElementById('outputTime').querySelector('b').innerText = outputTime;
        document.getElementById('outputTimeField').value = formatTimeForServer(now);
        submitForm('outputForm');
        this.classList.add('disabled');
        this.disabled = true;
    }
});


    // 폼 데이터 설정
    document.getElementById('inputTimeField').value = formatTimeForServer(now);
    // 폼 제출
    document.getElementById('inputForm').submit();




// 현재 시간을 형식에 맞게 반환하는 함수
function getCurrentTimeFormatted() {
    const now = new Date();
    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, '0');
    const date = String(now.getDate()).padStart(2, '0');
    const day = now.toLocaleDateString('ko-KR', { weekday: 'short' });
    const hours = String(now.getHours()).padStart(2, '0');
    const minutes = String(now.getMinutes()).padStart(2, '0');
    const seconds = String(now.getSeconds()).padStart(2, '0');
    return `${year}년 ${month}월 ${date}일(${day}) ${hours}:${minutes}:${seconds}`;
}

// 현재 시간을 sysdate 요소에 설정
function updateSysdate() {
    const sysdateElement = document.getElementById('sysdate');
    sysdateElement.textContent = getCurrentTimeFormatted();
}

// 페이지가 로드될 때 현재 시간을 설정하고, 출근/퇴근 버튼에 이벤트 리스너를 추가
window.onload = function() {
    updateSysdate();
    setInterval(updateSysdate, 1000); // 1초마다 갱신

    document.getElementById('empIdField').value = empId;
    document.getElementById('empIdFieldOutput').value = empId; // 퇴근 폼에도 empId 값을 설정

    document.getElementById('input').addEventListener('click', function(event) {
        setInputTime(event);
    });

    document.getElementById('output').addEventListener('click', function(event) {
        setOutputTime(event);
    });
};

// 출근시간 설정
function setInputTime(event) {
    const inputTimeElement = document.getElementById('inputTime');
    if (!inputTimeElement.textContent) {
        const now = new Date();
        inputTimeElement.textContent = getCurrentTimeFormatted();
        document.getElementById('input').disabled = true;
        document.getElementById('input').classList.add('disabled');
        document.getElementById('inputTimeField').value = formatTimeForServer(now);
        submitForm('inputForm');
    }
}

// 퇴근시간 설정
function setOutputTime(event) {
    const outputTimeElement = document.getElementById('outputTime');
    if (!outputTimeElement.textContent) { // 비어있을 때만 설정
        const now = new Date(); // now 변수를 정의
        outputTimeElement.textContent = getCurrentTimeFormatted();
        document.getElementById('output').disabled = true; // 버튼 비활성화
        document.getElementById('output').classList.add('disabled'); // 버튼 스타일 변경
        document.getElementById('outputTimeField').value = formatTimeForServer(now); // 서버로 보낼 시간 설정
        submitForm('outputForm'); // 폼 제출
    }
}


