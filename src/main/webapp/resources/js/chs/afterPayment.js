/**
 * 
 */
 document.addEventListener('DOMContentLoaded', (event) => {
        updateCurrentDate();
});

                let rowCount = 1;

                function addRow() {
                    rowCount++;
                    const table = document.getElementById('detail-table');
                    const newRow = table.insertRow();

                    newRow.innerHTML = `
                        <td>${rowCount}</td>
                        <td><input type="text" name="payList" id="품명${rowCount}"></td>
                        <td><input type="text" name="payAmount" id="금액${rowCount}" oninput="validateAmount(this)" onblur="updateTotalAmount()"></td>
                        <td><input type="text" name="reference" id="비고${rowCount}"></td>
                        <td><button onclick="removeRow(this)">삭제</button></td>
                    `;
                    updateRowNumbers(); // New line added here to update row numbers after adding a new row
                }

                function removeRow(button) {
                    const row = button.parentElement.parentElement;
                    row.parentElement.removeChild(row);
                    rowCount--;
                    updateRowNumbers();
                    updateTotalAmount();
                }

                function updateRowNumbers() {
                    const table = document.getElementById('detail-table');
                    for (let i = 1; i < table.rows.length; i++) {
                        table.rows[i].cells[0].innerText = i;
                    }
                }

                function validateAmount(input) {
                    const value = input.value;
                    if (/[^\d]/.test(value)) {
                        alert("금액에는 숫자만 입력할 수 있습니다.");
                        input.value = "";
                    }
                    const rowNumber = input.id.replace('금액', ''); // Updated this line to get the correct row number from input id
                    const 품명 = document.getElementById(`품명${rowNumber}`);
                    if (!품명.value) {
                        alert("품명을 입력하세요.");
                        품명.focus();
                    }
                }

                function updateTotalAmount() {
                    let total = 0;
                    const table = document.getElementById('detail-table');
                    for (let i = 1; i < table.rows.length; i++) {
                        const amount = table.rows[i].cells[2].children[0].value;
                        if (amount) {
                            total += parseInt(amount, 10);
                        }
                    }
                    document.getElementById('totalAmount').innerText = "₩"+total.toLocaleString();
                }

                function updateCurrentDate() {
                    const today = new Date();
                    const year = today.getFullYear();
                    const month = ('0' + (today.getMonth() + 1)).slice(-2);
                    const day = ('0' + today.getDate()).slice(-2);
                    document.getElementById('current-date').innerText = year+"년 "+month+"월 "+day+"일";
                }
                
      
// 폼 제출 할 때 
    const insertApproval = function(e) {
        e.preventDefault();
        const formData = new FormData();
        const totalAmount = $('#totalAmount').text();
        const approval = $('#approval-principal');
        const payList = $('input[name="payList"]'); // 모든 payList 요소를 선택
        const payAmount = $('input[name="payAmount"]'); // 모든 payAmount 요소를 선택
        const reference = $('input[name="reference"]'); // 모든 reference 요소를 선택

        if (approval.text() == '') {
            alert('결재자를 선택해주세요.');
            return false;
        }

        if (totalAmount == '' || totalAmount == null) {
            alert("지출 내역이 하나 이상 있어야합니다.");
            return false;
        } else {
            payList.each(function(index, element) {
                formData.append('payList[]', element.value);
            });

            payAmount.each(function(index, element) {
                formData.append('payAmount[]', element.value);
            });

            reference.each(function(index, element) {
                formData.append('reference[]', element.value);
            });
        }

        // 숨겨진 필드에 현재 날짜 값을 설정
        const currentDate = $('#current-date').text();
        formData.append('apvDate', currentDate);

        // 결재자, 참조자 정보 추가
        const carbonCopy = JSON.parse($('#refer-field').val());
		const approvalLine = JSON.parse($('#approval-field').val());
	    formData.append('carbonCopy', JSON.stringify(carbonCopy));
	    formData.append('approvalLine', JSON.stringify(approvalLine));
        // 기타 폼 데이터 추가
        formData.append('empId', $('input[name="empId"]').val());
        formData.append('apvId', $('input[name="apvId"]').val());
        formData.append('apvType', $('input[name="apvType"]').val());
        formData.append('apvStatus', $('input[name="apvStatus"]').val());
        formData.append('apvStrg', $('input[name="apvStrg"]').val());
        formData.append('apvTitle', $('input[name="apvTitle"]').val());
        formData.append('apvContent', $('input[name="apvContent"]').val());
        formData.append('payDate', $('input[name="payDate"]').val());
        formData.append('apvCase', $('input[name="apvCase"]').val());

        // 파일 추가
        const fileInput = $('input[name="apvAttachment"]')[0];
        if (fileInput.files.length > 0) {
            formData.append('apvAttachment', fileInput.files[0]);
        }
		  // FormData 내용 확인
        for (let pair of formData.entries()) {
            console.log(pair[0] + ': ' + pair[1]); 
        }
        $.ajax({
            url: `${path}/rest/approval/insert`,
            type: 'POST',
            data: formData,
            contentType: false, // FormData를 사용할 때는 false로 설정
            processData: false, // FormData를 사용할 때는 false로 설정
            success: function(response) {
                alert('결재가 성공적으로 제출되었습니다.');
                location.assign(`${path}/approval/flagginging.do?empId=${empId}`)
                // 성공 시 추가 작업
            },
            error: function(xhr, status, error) {
                alert('결재 제출 중 오류가 발생했습니다.');
                console.error(error);
            }
        });
    };
     // 임시 저장 함수 정의
		    $('#insertApprovalStrg').click(e=>{
		        $('input[name="apvStrg"]').val('1');
		        insertApproval(event);  // event 객체를 전달하여 폼 제출 방지
		    });       
    
 $('form.container').on('submit', insertApproval);  