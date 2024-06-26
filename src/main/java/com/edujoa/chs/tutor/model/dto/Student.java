package com.edujoa.chs.tutor.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
	private String stdId;
	private String stdName;
	private String stdSchool;
	private String stdPhone;
	private String stdParentPhone;
	private int stdPayment;
	private String stdYn;
	private List<MyClass> myClass;
}
