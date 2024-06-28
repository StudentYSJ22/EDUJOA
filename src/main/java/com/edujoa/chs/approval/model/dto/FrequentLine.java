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
public class FrequentLine {
	private String feqId;
	private String empId;
	private String feqName;
	private List<FrequentPerson> frequentperson;
}
