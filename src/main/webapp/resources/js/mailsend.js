// EmailJS 초기화
emailjs.init('v9lm7nLdEFvfqqZPc');

// 폼 제출 이벤트 리스너
document.querySelector('.email-form').addEventListener('submit', function(event) {
    event.preventDefault();

    const btn = document.getElementById('button');
    btn.textContent = '전송 중...';

    let now = new Date();
    let formattedTime = now.getFullYear().toString().substr(-2) + '-' +
        ('0' + (now.getMonth() + 1)).slice(-2) + '-' +
        ('0' + now.getDate()).slice(-2) + ' ' +
        ('0' + now.getHours()).slice(-2) + ':' +
        ('0' + now.getMinutes()).slice(-2);

    document.getElementById('mailDate').value = formattedTime;

    const serviceID = 'service_z7zbjsp';
    const templateID = 'template_u98ywyj';

    emailjs.sendForm(serviceID, templateID, this)
        .then(() => {
            btn.textContent = '보내기';
            alert('메일이 성공적으로 전송되었습니다.');
            sendDataToServer(this);
        }, (err) => {
            btn.textContent = '보내기';
            console.log('FAILED...', err);
            alert('메일 전송에 실패했습니다. 다시 시도해주세요.');
        });
});
function sendDataToServer(form) {
    // FormData 객체를 일반 객체로 변환
    const formData = new FormData(form);
    const object = {};
    formData.forEach((value, key) => object[key] = value);

    // JSON 문자열로 변환
    const jsonData = JSON.stringify(object);

    fetch('/mailbox/sendmail', {  // 실제 컨트롤러 엔드포인트를 입력하세요
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: jsonData
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('서버 응답이 실패했습니다.');
        }
        return response.json();
    })
    .then(data => {
        console.log('서버 응답:', data);
        document.getElementById('button').textContent = '보내기';
        // 필요하다면 여기에 폼 리셋 또는 다른 작업을 추가할 수 있습니다
    })
}