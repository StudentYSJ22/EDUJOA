package com.edujoa.ssz.noticeboard.model.dto;

import java.sql.Date;
import com.edujoa.with.employee.model.dto.Employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NoticeBoard {
	private String boardId;
	private String empId;//작성자
	private String boardTitle;
	private String boardContent;
	private Date boardDate;
	private int boardCount;
	private Employee employee;
//	private List<NoticeBoardAttachment> attachments;
}

