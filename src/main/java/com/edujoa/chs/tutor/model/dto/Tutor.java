package com.edujoa.chs.tutor.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tutor {
	private String ttId;
	private String subId;
	private String ttName;
	private String ttPhone;
	private String ttEmail;
	private Date ttHireDate;
	private String ttProfile;
	private String ttYn;
	private int ttSalary;
	private int ttAnnualSal;
	private ClassRoom class_;
	private Subject subject;
}
