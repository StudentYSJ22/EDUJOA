package com.edujoa.chs.approval.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor	
public class BookMark {
	private int bkId;
	private String empId;
	private String bkName;
	List<BkEmp> bkEmp;
}
