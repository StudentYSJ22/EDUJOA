package com.edujoa.ssz.noticeboard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeBoardAttachment {
	private String boardId;
	private String nbaSize;
	private String nbaPath;
	private String nbaOriname;
	private String nbaRename;
}
