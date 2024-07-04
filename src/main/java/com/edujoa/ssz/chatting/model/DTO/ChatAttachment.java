package com.edujoa.ssz.chatting.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatAttachment {
	private String fileId;
	private String roomId;
	private String filePath;
	private String fileOriname;
	private String fileRename;
	private String fileSize;
}
