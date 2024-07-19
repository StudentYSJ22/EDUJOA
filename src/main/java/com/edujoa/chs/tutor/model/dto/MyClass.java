package com.edujoa.chs.tutor.model.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyClass {
	private String mclId;
	private String stdId;
	private String classId;
	private Student student;
}
