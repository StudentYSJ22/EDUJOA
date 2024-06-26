package com.edujoa.chs.approval.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApprovalLine {
	private int apvlId;
	private String empId;
	private int docId;
	private Date apvDate;
	private int apvSeq;
	private int apvStt;
	private String apvRs;
}
