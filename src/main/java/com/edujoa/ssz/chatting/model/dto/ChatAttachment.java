package com.edujoa.ssz.chatting.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatAttachment {
	private String roomId;
	private String filePath;
	private String fileOriname;
	private String fileRename;
	private String fileSize;
}
