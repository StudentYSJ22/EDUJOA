package com.edujoa.ssz.webmail.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailAttachment {
	private String mailId;
	private String filePath;
	private String fileOriname;
	private String fileRename;
	private String fileSize;
	
}
