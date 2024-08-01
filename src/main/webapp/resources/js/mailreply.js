
// 현재 URL에서 쿼리 파라미터를 추출하고 디코딩하는 예시
function getQueryParam(name) {
    const urlParams = new URLSearchParams(window.location.search);
    return decodeURIComponent(urlParams.get(name) || '');
}

// 이메일 제목, 보낸사람, 내용을 가져와서 디코딩
const emailTitle = getQueryParam('encodedTitle');
const emailSender = getQueryParam('encodedSender');
const emailContent = getQueryParam('encodedContent');

console.log('Email Title:', emailTitle);
console.log('Email Sender:', emailSender);
console.log('Email Content:', emailContent);