package com.edujoa.khj.main.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Calendar {
	private String schId;
	private String schTitle;
	private String schContent;
	private LocalDateTime schStart;
	private LocalDateTime schEnd;
	
}
