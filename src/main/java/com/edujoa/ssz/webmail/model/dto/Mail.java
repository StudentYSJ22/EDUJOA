package com.edujoa.ssz.webmail.model.dto;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//메일 보낼 때
//내가 보낸 메일 저장하는 DTO 테이블은 MAIL이랑 연결
//임시저장함은 보낼 때니깐 여기서 작성하다가 나가기 버튼 누르면 임시저장하겠냐고 알람 띄우고 예 누르면 그대로 db에 저장
public class Mail {
	private String mailId;
	private String senderEmail;//발신자
	private List<String> sendto;//수신자
	private List<String> ccto;//참조
	private List<String> Bccto;//숨은참조
	private String mailTitle;
	private String mailContent;
	private String mailDate;
	private String mailType;
}
