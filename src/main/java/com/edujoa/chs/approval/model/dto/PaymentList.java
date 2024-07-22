package com.edujoa.chs.approval.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentList {
	private String apvId;
	private String payList; //품명
	private Integer payAmount; //금액
	private String reference; //비고
}
