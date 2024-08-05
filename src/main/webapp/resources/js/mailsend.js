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
			window.location.href = `${contextPath}/mailbox`;
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

	fetch(`${contextPath}`+'/mailbox/sendmail', {  // 실제 컨트롤러 엔드포인트를 입력하세요
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
$(document).ready(function() {
	// 현재 날짜와 시간을 원하는 형식으로 포맷팅하는 함수
	function formatDate(date) {
		const day = String(date.getDate()).padStart(2, '0');
		const month = String(date.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1
		const year = String(date.getFullYear()).slice(-2); // 마지막 두 자리
		const hours = String(date.getHours()).padStart(2, '0');
		const minutes = String(date.getMinutes()).padStart(2, '0');

		return `${year}-${month}-${day} ${hours}:${minutes}`;
	}
	// 현재 날짜와 시간을 포맷팅하여 mailDate 필드에 설정
	const now = new Date();
	const formattedDate = formatDate(now);
	$('#mailDate').val(formattedDate);

	$('#tempsubmit').on('click', function() {
		const mailTitle = $('#mailTitle').val();
		const sendto = $('#sendto').val();
		const ccto = $('#ccto').val();
		const Bccto = $('#Bccto').val();
		const message = $('#message').val();
		const senderEmail = $("#empEmail").val();
		const mailDate = $("#mailDate").val() || ""; // mailDate가 null일 경우 빈 문자열로 설정

		if (mailTitle) {
			if (confirm('임시저장하시겠습니까?')) {
				$.ajax({
					type: 'POST',
					url: `${contextPath}`+'/mailbox/saveDraft',
					contentType: 'application/json',
					data: JSON.stringify({
						mailTitle: mailTitle,
						sendto: sendto,
						ccto: ccto,
						Bccto: Bccto,
						message: message,
						mailDate: mailDate,
						empEmail: senderEmail
					}),
					success: function(response) {
						if (response > 0) {
							console.log('Draft saved successfully');
							alert("임시저장이 완료되었습니다.");
							window.location.href = `${contextPath}`+'/mailbox';
						} else {
							console.log('Draft failed to save');
							alert("임시저장 실패.");
						}
					},
					error: function(xhr, status, error) {
						console.error('Error saving draft:', error);
					}
				});
			}
		} else {
			alert("메일 제목을 입력해 주세요.");
		}
	});
});