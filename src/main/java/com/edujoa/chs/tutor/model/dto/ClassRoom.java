package com.edujoa.chs.tutor.model.dto;

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
public class ClassRoom {
	private String classId;
	private String ttId;
	private Date classOpen;
	private Date classClose;
	private List<MyClass> myClass;
}
