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
public class AfterPayment {
	private String apvId;
	private String payCase;
	private String payAmount;
	private Date payDate;
}
