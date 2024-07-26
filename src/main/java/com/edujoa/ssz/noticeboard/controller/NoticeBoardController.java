package com.edujoa.ssz.noticeboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

	@ResponseBody
	@PostMapping("/noticeboard/detail")
	public String openBoardDetail(@RequestBody Map<String, String> param, Model model) {
		System.out.println("ajax로 받아온 데이터는 잘 넘어왔을까요:" + param);
		NoticeBoard board = service.getBoardDetail(param);

		model.addAttribute("board", board);
		return "/noticeboarddetail";
	}

	@GetMapping("/noticeboard/detail")
	public String openBoardDetailPage(@RequestParam("boardId") String boardId, Model model) {
		Map<String, String> param = new HashMap<>();
		param.put("boardId", boardId);
		NoticeBoard board = service.getBoardDetail(param);
		model.addAttribute("board", board);
		return "/ssz/noticeboarddetail"; // 실제 뷰의 이름을 반환
	}

	@PostMapping("/noticeboard/increaseViewCount")
	public ResponseEntity<?> increaseViewCount(@RequestBody Map<String, String> payload) {
		String boardId = payload.get("boardId");
		try {
			service.increaseViewCount(boardId);
			return ResponseEntity.ok().body("View count increased successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to increase view count: " + e.getMessage());
		}
	}

	@GetMapping("/noticeboard/sort")
	public ResponseEntity<List<NoticeBoard>> sortBoardList(@RequestParam String sortBy) {
		try {
			List<NoticeBoard> sortedList;
			switch (sortBy) {
			case "boardId":
				sortedList = service.getBoardListSortedByBoardId();
				break;
			case "boardCount":
				sortedList = service.getBoardListSortedByBoardCount();
				break;
			default:
				sortedList = service.getAllBoardList(); // 기본 정렬
				break;
			}
			return ResponseEntity.ok(sortedList);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
