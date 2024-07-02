package com.edujoa.ysz.noticeboard.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeBoard {
	private String boardId;
	private String empId;
	private String boardTitle;
	private String boardContent;
	private Date boradDate;
	private int boardCount;
}
