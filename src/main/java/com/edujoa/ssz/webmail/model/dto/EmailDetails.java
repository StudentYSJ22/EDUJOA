package com.edujoa.ssz.webmail.model.dto;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDetails {
	private String subject;
    private String from;
    private List<String> to;
    private Date date;
    private String content;
    private List<Attachment> attachments;
}
