package com.edujoa.ssz.webmail.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailAttachment {
	private String fileName;
    private String contentType;
    private String fileUrl;
	
}
