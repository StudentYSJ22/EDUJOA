package com.edujoa.ssz.webmail.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailAccess {
	private String mailId;
	private String empEmail;
	private String mailCarboncopy;
}
