package com.edujoa.chs.approval.model.dto;

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
public class Document {
	private int docId;
	private String empId;
	private int docType;
	private Date docMade;
	private Date docMdf;
	private String docCnt;
	private Date docDone;
	private int docStt;
	private int docStr;
	private List<DocAttachment> docAttachments;
	private List<CarbonCopy> carbonCopy;
	private List<ApprovalLine> approvalLine;
}
