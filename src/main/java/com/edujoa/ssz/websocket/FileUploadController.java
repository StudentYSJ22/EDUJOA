package com.edujoa.ssz.websocket;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.edujoa.ssz.chatting.model.dto.ChatAttachment;

@RestController
public class FileUploadController {
	@Value("${file.upload-dir}")
	private String uploadDir;
	
	@PostMapping("uploadFile")
	public ChatAttachment uploadFile(@RequestParam("file") MultipartFile file) throws IOException{
		if(file.isEmpty()) {
			throw new IOException("파일이 없습니다.");
		}
		Path copyLocation = Paths.get(uploadDir + File.separator + file.getOriginalFilename());
		
		return ChatAttachment.builder()
				.filePath(copyLocation.toString())
				.fileOriname(file.getOriginalFilename())
				.fileRename(file.getOriginalFilename())
				.fileSize(String.valueOf(file.getSize()))
				.build();
	}
}
