package com.edujoa.chs.approval.model.dto;

import java.sql.Date;
import java.util.List;

import com.edujoa.with.employee.model.dto.Employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Approval {
	private String apvId;
	private String empId;
	private String apvType;
	private String apvTitle;
	private String apvContent;
	private Date apvDate;
	private Date apvModelfy;
	private Date apvDone;
	private String apvStatus;
	private String apvStrg;
	private ApvAttachment apvAttachment;
	private List<CarbonCopy> carbonCopy;
	private List<ApprovalLine> approvalLine;
	private PrePayment prePayment;
	private AfterPayment afterPayment;
	private Vacay vacay;
	private Employee employee;
}
