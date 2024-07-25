package com.edujoa.ssz.noticeboard.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edujoa.ssz.noticeboard.model.dto.NoticeBoard;
import com.edujoa.ssz.noticeboard.model.service.NoticeBoardServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NoticeBoardController {
		
	private final NoticeBoardServiceImpl service;
	
	@GetMapping("/noticeboard/board")
	public String noticeboard() {
		return "/ssz/noticeboard";
	}
	
	@ResponseBody
	@GetMapping("/noticeboard/getboardlist")
	public List<NoticeBoard> getBoardList() {
		return service.getAllBoardList();
	}
}
