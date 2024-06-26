package com.edujoa.chs.alram;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Alram {
	private String alrId;
	private String empId;
	private String alrRead;
	private Date alrCreate;
	private int alrType;
}
