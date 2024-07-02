package com.edujoa.chs.approval.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApprovalLine {
	private String apvLineId;
	private String empId;
	private String apvId;
	private String apvSequence;
}
