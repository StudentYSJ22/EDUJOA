package com.edujoa.ssz.noticeboard.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	//게시판으로 이동시키는 메소드
	@GetMapping("/noticeboard/board")
	public String noticeboard() {
		return "/ssz/noticeboard";
	}
	//게시글작성으로 이동시키는 메소드
	@GetMapping("/noticeboard/boardwrite")
	public String noticeBoardWrite() {
		return "/ssz/noticeboardwrite";
	}
	//form태그 받는 메소드
	@PostMapping("/noticeboard/writeNotice")
	public String noticeWrite(@ModelAttribute NoticeBoard noticeBoard, Model model) {

		String boardTitle=noticeBoard.getBoardTitle();
		String boardWriter=noticeBoard.getEmpId();
		String boardContent=noticeBoard.getBoardContent();
		Date boardDate=noticeBoard.getBoardDate();
		int boardCount=noticeBoard.getBoardCount();
		int result=service.insertBoard(noticeBoard);
		if(result>0) {
//			System.out.println("저장 성공!");
			return "redirect:/noticeboard/board";
		}else {
			return "#";
		}
		
	}
	
	//게시판 들어갔을 때 게시글 가져오는 메소드
	@ResponseBody
	@GetMapping("/noticeboard/getboardlist")
	public List<NoticeBoard> getBoardList() {
		return service.getAllBoardList();
	}
	
	//ㄱㅔ시글 클릭했을 때 상세페이지로 이동시키는 메소드
	@ResponseBody
	@PostMapping("/noticeboard/detail")
	public String openBoardDetail(@RequestBody Map<String, String> param, Model model) {
		System.out.println("ajax로 받아온 데이터는 잘 넘어왔을까요:" + param);
		NoticeBoard board = service.getBoardDetail(param);

		model.addAttribute("board", board);
		return "/noticeboarddetail";
	}
	
	//ㄱㅔ시글 클릭했을 때 상세페이지로 이동시키는 메소드
	@GetMapping("/noticeboard/detail")
	public String openBoardDetailPage(@RequestParam("boardId") String boardId, Model model) {
		Map<String, String> param = new HashMap<>();
		param.put("boardId", boardId);
		NoticeBoard board = service.getBoardDetail(param);
		model.addAttribute("board", board);
		return "/ssz/noticeboarddetail"; // 실제 뷰의 이름을 반환
	}
	
	//조회수 증가시키는 메소드
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
	
	//게시글 정렬하는 메소드
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
	//게시글 삭제하는 메소드
	@ResponseBody
	@PostMapping("noticeboard/deleteBoard")
	public int deleteBoard(@RequestBody Map<String,String>param) {
		int result=0;
		result=service.deleteBoard(param);
		if(result>0) {
			return result;
		}else {
			return 0;
		}
	}
}
