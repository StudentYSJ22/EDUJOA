package com.edujoa.ssz.webmail.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attachment {
	private String fileName;
    private long fileSize;
}
