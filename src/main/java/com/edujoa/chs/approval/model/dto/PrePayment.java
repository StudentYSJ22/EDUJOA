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
public class PrePayment {
	private String apvId;
	private String payCase;
	private Integer payAmount;
	private Date payDate;
	private List<PaymentList> paymentList;
}
