package com.edujoa.ssz.noticeboard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeBoardAttachment {
	private String nbaId;
	private String boardId;
	private String nbaSize;
	private String nbaPath;
	private String nbaOriname;
	private String nbaRename;
}
