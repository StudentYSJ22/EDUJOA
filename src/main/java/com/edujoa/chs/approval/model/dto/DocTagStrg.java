package com.edujoa.chs.approval.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocTagStrg {
	private int docType;
	private String preApv;
	private String afterApv;
	private String vacay;
}
