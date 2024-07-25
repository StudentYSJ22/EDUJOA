package com.edujoa.ssz.noticeboard.model.dto;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
//import lombok.Setter;


@Getter
public class NoticeBoard {
	private String boardId;
	private String empId;//작성자
	private String boardTitle;
	private String boardContent;
	private Date boardDate;
	private int boardCount;
//	private List<NoticeBoardAttachment> attachments;
	//dto는 데이터접근해서 가져오는 용도, requestresponse를 따로 만들어서 출력용도로 request는 받는 용도
}

