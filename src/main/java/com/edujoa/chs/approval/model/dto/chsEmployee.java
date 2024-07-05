package com.edujoa.chs.approval.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class chsEmployee {
	private String empId;
	private String empName;
	private String entYn;
	private String empTitle;
	private String oriname;
	private List<ApprovalLine> approvalLine;
	private List<CarbonCopy> carbonCopy;
	private List<Approval> approval;
}
