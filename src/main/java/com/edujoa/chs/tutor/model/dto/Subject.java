package com.edujoa.chs.tutor.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subject {
	private String subId;
	private String subName;
	private String subDetail;
}
