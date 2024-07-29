package com.edujoa.chs.approval.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.edujoa.chs.approval.model.dto.AfterPayment;
import com.edujoa.chs.approval.model.dto.Approval;
import com.edujoa.chs.approval.model.dto.ApprovalLine;
import com.edujoa.chs.approval.model.dto.ApvAttachment;
import com.edujoa.chs.approval.model.dto.ApvTag;
import com.edujoa.chs.approval.model.dto.CarbonCopy;
import com.edujoa.chs.approval.model.dto.FrequentLine;
import com.edujoa.chs.approval.model.dto.PaymentList;
import com.edujoa.chs.approval.model.dto.PrePayment;
import com.edujoa.chs.approval.model.dto.Vacay;
import com.edujoa.chs.approval.model.service.ApprovalService;
import com.edujoa.chs.common.PageFactory;
import com.edujoa.chs.employee.controller.EmployeeController;
import com.edujoa.with.employee.model.dto.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/approval")
public class ApprovalRestController {
	private final ApprovalService service;
	private final PageFactory pageFactory;
	private final EmployeeController employeeController;
	// 진행 중에 대한 결과 조회
	@GetMapping("/flagginging")
	public ResponseEntity<Map<String, Object>> flaggingIng(@RequestParam(defaultValue = "10") int numPerpage,
			@RequestParam(defaultValue = "1") int cPage, String empId, String date) {
		Map<String, String> param = new HashMap<>();
		if ("old".equals(date)) {
			param.put("old", "old");
		}
		param.put("empId", empId);
		param.put("apvStatus", "0");
		param.put("apvStrg", "0");

		// 페이지 데이터 불러오기
		String pageBar = pageFactory.getPage(cPage, numPerpage, service.selectMyApprovalCount(param),
				"/approval/flagginging.do?empId=" + empId + "&date=" + param.get("old") + "&");
		List<Approval> approvals = service.selectMyApproval(Map.of("cPage", cPage, "numPerpage", numPerpage), param);

		// 응답 데이터 구성
		Map<String, Object> response = new HashMap<>();
		response.put("approvals", approvals);
		response.put("pageBar", pageBar);
		response.put("numPerpage", numPerpage);
		if ("old".equals(date)) {
			response.put("old", "old");
		}
		response.put("old", "old");
		return ResponseEntity.ok(response);
	}

	// 반려에 대한 결과 조회
	@GetMapping("/flaggingback")
	public ResponseEntity<Map<String, Object>> flaggingback(@RequestParam(defaultValue = "10") int numPerpage,
			@RequestParam(defaultValue = "1") int cPage, String empId, String date) {
		Map<String, String> param = new HashMap<>();
		if ("old".equals(date)) {
			param.put("old", "old");
		}
		param.put("empId", empId);
		param.put("apvStatus", "1");
		param.put("apvStrg", "0");

		// 페이지 데이터 불러오기
		String pageBar = pageFactory.getPage(cPage, numPerpage, service.selectMyApprovalCount(param),
				"/approval/temporarystorage.do?empId=" + empId + "&date=" + param.get("old") + "&");
		List<Approval> approvals = service.selectMyApproval(Map.of("cPage", cPage, "numPerpage", numPerpage), param);

		// 응답 데이터 구성
		Map<String, Object> response = new HashMap<>();
		response.put("approvals", approvals);
		response.put("pageBar", pageBar);
		response.put("numPerpage", numPerpage);
		if ("old".equals(date)) {
			response.put("old", "old");
		}
		response.put("old", "old");
		return ResponseEntity.ok(response);
	}

	// 진행 중에 대한 결과 조회
	@GetMapping("/flaggingapproval")
	public ResponseEntity<Map<String, Object>> flaggingApproval(@RequestParam(defaultValue = "10") int numPerpage,
			@RequestParam(defaultValue = "1") int cPage, String empId, String date) {
		Map<String, String> param = new HashMap<>();
		if ("old".equals(date)) {
			param.put("old", "old");
		}
		param.put("empId", empId);
		param.put("apvStatus", "2");
		param.put("apvStrg", "0");

		// 페이지 데이터 불러오기
		String pageBar = pageFactory.getPage(cPage, numPerpage, service.selectMyApprovalCount(param),
				"/approval/temporarystorage.do?empId=" + empId + "&date=" + param.get("old") + "&");
		List<Approval> approvals = service.selectMyApproval(Map.of("cPage", cPage, "numPerpage", numPerpage), param);

		// 응답 데이터 구성
		Map<String, Object> response = new HashMap<>();
		response.put("approvals", approvals);
		response.put("pageBar", pageBar);
		response.put("numPerpage", numPerpage);
		if ("old".equals(date)) {
			response.put("old", "old");
		}
		response.put("old", "old");
		return ResponseEntity.ok(response);
	}

	// 임시 저장에 대한 결과 조회
	@GetMapping("/temporarystorage")
	public ResponseEntity<Map<String, Object>> getTemporaryStroage(@RequestParam(defaultValue = "10") int numPerpage,
			@RequestParam(defaultValue = "1") int cPage, String empId, String date) {
		Map<String, String> param = new HashMap<>();
		if ("old".equals(date)) {
			param.put("old", "old");
		}
		param.put("empId", empId);
		param.put("apvStrg", "1");

		// 페이지 데이터 불러오기
		String pageBar = pageFactory.getPage(cPage, numPerpage, service.selectMyApprovalCount(param),
				"/approval/temporarystorage.do?empId=" + empId + "&date=" + param.get("old") + "&");
		List<Approval> approvals = service.selectMyApproval(Map.of("cPage", cPage, "numPerpage", numPerpage), param);

		// 응답 데이터 구성
		Map<String, Object> response = new HashMap<>();
		response.put("approvals", approvals);
		response.put("pageBar", pageBar);
		response.put("numPerpage", numPerpage);
		if ("old".equals(date)) {
			response.put("old", "old");
		}
		response.put("old", "old");
		return ResponseEntity.ok(response);
	}

	// 자주쓰는 결재라인 불러오기
	@GetMapping("/{empId}")
	public ResponseEntity<List<FrequentLine>> getFrequentLine(@PathVariable String empId) {
		List<FrequentLine> result = service.selectAllFrequenLine(empId);
		return ResponseEntity.ok(result);
	}

	// 본인을 제외한 모든 사원 불러오기
	@GetMapping("/line/{empId}")
	public ResponseEntity<List<Employee>> getApprovalLine(@PathVariable String empId) {
		List<Employee> result = service.selectAllApprvoalLine(empId);
		return ResponseEntity.ok(result);
	}

	// 결재 문서 삭제에 대한 처리
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteApproval(@RequestBody String[] apvId) {
		int result = 0;
		String msg = "";
		for (int i = 0; i < apvId.length; i++) {
			result = service.deleteApproval(apvId[i]);
		}
		if (result > 0) {
			msg = "삭제 성공하였습니다.";
		} else {
			msg = "삭제 실패하였습니다";
		}
		return ResponseEntity.ok(msg);
	}

	@PostMapping("/upload")
	public ResponseEntity<String> uploadSignature(@RequestParam("image") String imageData,
			@RequestParam("empId") String empId, HttpServletRequest request) {
		// 업로드 디렉토리 경로 설정
		String uploadDir = request.getServletContext().getRealPath("/") + "resources/upload/employee_signatures/";
		String fileName = "signature_" + empId + ".png";

		// 디렉토리 생성 (존재하지 않는 경우)
		File uploadDirFile = new File(uploadDir);
		if (!uploadDirFile.exists()) {
			uploadDirFile.mkdirs();
		}

		// 파일 경로 설정
		String filePath = uploadDir + fileName;

		// 이미지 데이터에서 Base64 부분 제거
		String base64Image = imageData.split(",")[1];
		byte[] decodedBytes = Base64.getDecoder().decode(base64Image);

		// 파일 저장
		try (OutputStream stream = new FileOutputStream(filePath)) {
			stream.write(decodedBytes);
		} catch (IOException e) {
			return new ResponseEntity<>("Error saving signature", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// DB 업데이트를 위한 파라미터 설정
		Map<String, String> params = new HashMap<>();
		params.put("empId", empId);
		params.put("oriname", fileName);

		String msg = "";
		int result = service.updateSignatureUrl(params);
		msg = result > 0 ? "등록 성공하였습니다." : "등록 실패하였습니다.";

		return ResponseEntity.ok(msg);
	}

	@PostMapping("/insert")
	public ResponseEntity<?> insertApproval(@RequestParam("empId") String empId,
			@RequestParam("apvType") String apvType, @RequestParam("apvTitle") String apvTitle,
			@RequestParam("apvDate") String apvDate, @RequestParam("apvContent") String apvContent,
			@RequestParam("apvStatus") String apvStatus, @RequestParam("apvStrg") String apvStrg,
			@RequestParam("approvalLine") String approvalLine, @RequestParam("carbonCopy") String carbonCopy,
			@RequestParam(required = false, value="payDate") Date payDate, @RequestParam(required = false, value="apvCase") String apvCase,
			@RequestParam(required = false, value="payList[]") List<String> payList, @RequestParam(required = false, value="payAmount[]") List<Integer> payAmount,
			@RequestParam(required = false, value="reference[]") List<String> reference,
			@RequestParam(required = false, value="vacayType") String vacayType,
			@RequestParam(required = false, value="vacayStart") Date vacayStart,
			@RequestParam(required = false, value="vacayEnd") Date vacayEnd,
			@RequestParam(value = "apvAttachment", required = false) MultipartFile apvAttachment,
			HttpServletRequest request) {
		try {
			// jsp에서 보낸 날짜 형식 파싱
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
			// String을 date로 변환
			java.sql.Date apvDateRequest = new java.sql.Date(dateFormat.parse(apvDate).getTime());
			ObjectMapper objectMapper = new ObjectMapper();
			List<Map<String, Object>> approvalLineList = objectMapper.readValue(approvalLine,
					new TypeReference<List<Map<String, Object>>>() {
					});
			List<Map<String, Object>> carbonCopyList = objectMapper.readValue(carbonCopy,
	                new TypeReference<List<Map<String, Object>>>() {});
			List<ApprovalLine> approvalLineRequest = new ArrayList<>();
			// for 루프를 사용하여 각 Map의 값을 출력
			// 결재라인 객체 생성
			for (Map<String, Object> a : approvalLineList) {
				String emp = (String) a.get("empId");
				String order = String.valueOf(a.get("order"));
				approvalLineRequest.add(ApprovalLine.builder().empId(emp).apvSequence(order).build());
			}
			log.debug("{}", approvalLineRequest);
			ApvAttachment attachment = new ApvAttachment();
			// 파일 경로 설정
			String path = request.getServletContext().getRealPath("/") + "resources/upload/chs/approval/";
			// 원본 파일명과 수정 파일명 담을 변수
			String oriname = "";
			String rename = "";
			// 만약 jsp에서 보낸 파일이 없을 경우
			if (apvAttachment != null) {
				oriname = apvAttachment.getOriginalFilename();
				rename = System.currentTimeMillis() + "_" + oriname;
				// 파일 저장 경로 설정
				// 디렉토리 생성 (존재하지 않는 경우)
				File uploadDirFile = new File(path);
				if (!uploadDirFile.exists()) {
					uploadDirFile.mkdirs(); // 디렉토리 생성
				}
				// 파일 객체 생성
				File dest = new File(path + rename);
				// 파일을 지정된 경로로 저장
				attachment.setFileOriname(oriname);
				attachment.setFileRename(rename);
				apvAttachment.transferTo(dest);
			}
			log.debug("{}", attachment);
			

			// 참조인 객체 생성
	        List<CarbonCopy> carbonCopyRequest = new ArrayList<>();
	        for (Map<String, Object> c : carbonCopyList) {
	            String emp = (String) c.get("empId");
	            carbonCopyRequest.add(CarbonCopy.builder().empId(emp).build());
	        }
	        log.debug("{}", carbonCopyRequest);
			
			//품의서, 지출결의서
			int total = 0;
			List<PaymentList> paymentList = new ArrayList<>();
			AfterPayment afterPayment = new AfterPayment();
			PrePayment prePayment = new PrePayment();
			if(apvType.equals("2")) {
				for (int i = 0; i < payList.size(); i++) {
					total += payAmount.get(i);
					log.debug("{}",total);
					paymentList.add(PaymentList.builder()
							// apvId는 selectKey가져와서 처리해야함
							.payList(payList.get(i)).payAmount(payAmount.get(i)).reference(reference.get(i)).build());
				}
					afterPayment = AfterPayment.builder().payCase(apvCase).payAmount(total).payDate(payDate)
						.build();
			}
			if(apvType.equals("1")) {
				for (int i = 0; i < payList.size(); i++) {
					total += payAmount.get(i);
					log.debug("{}",total);
					paymentList.add(PaymentList.builder()
							// apvId는 selectKey가져와서 처리해야함
							.payList(payList.get(i)).payAmount(payAmount.get(i)).reference(reference.get(i)).build());
				}
				prePayment = PrePayment.builder().payCase(apvCase).payAmount(total).payDate(payDate)
						.build();
			}
			log.debug("{}", paymentList);
			log.debug("{}", afterPayment);
			Vacay vacay = new Vacay();
			//휴가신청서
			if(vacayType!=null) {
				vacay.setVacayEnd(vacayEnd);
				vacay.setVacayType(vacayType);
				vacay.setVacayStart(vacayStart);
			}
			log.debug("{}",vacay);
			
			Approval approval = new Approval();
			approval.setEmpId(empId);
			approval.setApvType(apvType); // 문서 종류
			approval.setApvStatus(apvStatus); // 문서 상태 0이면 상신, 1이면 임시 저장
			approval.setApvStrg(apvStrg);
			approval.setApvTitle(apvTitle);
			approval.setApvContent(apvContent);
			approval.setApvDate(apvDateRequest);
			log.debug("{}", approval);
			
			 Map<String, Object> approvalMap = new HashMap<>();
	        approvalMap.put("approval", approval);
	        approvalMap.put("apvAttachment", attachment != null ? attachment : null);
	        approvalMap.put("apvType", apvType);
	        approvalMap.put("carboncopy", carbonCopyRequest != null && !carbonCopyRequest.isEmpty() ? carbonCopyRequest : null);
	        approvalMap.put("approvalline", approvalLineRequest);
	        approvalMap.put("afterpayment", afterPayment != null ? afterPayment : null);
	        approvalMap.put("prepayment", prePayment != null ? prePayment : null);
	        approvalMap.put("paymentList", paymentList != null && !paymentList.isEmpty() ? paymentList : null);
	        approvalMap.put("vacay", approvalMap != null ? vacay : null);
			int result = service.insertApproval(approvalMap);
			if(result<0) { 
				File delFile=new File(path + rename); if(delFile.exists())
				delFile.delete(); 
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/update")
	public ResponseEntity<?> updateApproval(@RequestParam("empId") String empId, String apvId,
			@RequestParam("apvType") String apvType, @RequestParam("apvTitle") String apvTitle,
			@RequestParam("apvDate") String apvDate, @RequestParam("apvContent") String apvContent,
			@RequestParam("apvStatus") String apvStatus, @RequestParam("apvStrg") String apvStrg,
			@RequestParam("approvalLine") String approvalLine, @RequestParam("carbonCopy") String carbonCopy,
			@RequestParam(required = false, value="payDate") Date payDate, @RequestParam(required = false, value="apvCase") String apvCase,
			@RequestParam(required = false, value="payList[]") List<String> payList, @RequestParam(required = false, value="payAmount[]") List<Integer> payAmount,
			@RequestParam(required = false, value="reference[]") List<String> reference,
			@RequestParam(required = false, value="vacayType") String vacayType,
			@RequestParam(required = false, value="vacayStart") Date vacayStart,
			@RequestParam(required = false, value="vacayEnd") Date vacayEnd,
			@RequestParam(value = "apvAttachment", required = false) MultipartFile apvAttachment,
			HttpServletRequest request) {
		try {
			log.debug(carbonCopy);
			// jsp에서 보낸 날짜 형식 파싱
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
			// String을 date로 변환
			java.sql.Date apvDateRequest = null;
			if (apvDate != null && !apvDate.isEmpty()) {
	            // String을 Date로 변환
	            apvDateRequest = new java.sql.Date(dateFormat.parse(apvDate).getTime());
	        }

			ObjectMapper objectMapper = new ObjectMapper();
			List<Map<String, Object>> approvalLineList = objectMapper.readValue(approvalLine,
					new TypeReference<List<Map<String, Object>>>() {
			});
			List<Map<String, Object>> carbonCopyList = objectMapper.readValue(carbonCopy,
	                new TypeReference<List<Map<String, Object>>>() {});
			List<ApprovalLine> approvalLineRequest = new ArrayList<>();
			// for 루프를 사용하여 각 Map의 값을 출력
			// 결재라인 객체 생성
			for (Map<String, Object> a : approvalLineList) {
				String emp = (String) a.get("empId");
				String order = String.valueOf(a.get("order"));
				String apvLineId = (String)a.get("apvLineId");
				approvalLineRequest.add(ApprovalLine.builder().apvLineId(apvLineId).apvId(apvId).empId(emp).apvSequence(order).build());
			}
			log.debug("{}", approvalLineRequest);
			ApvAttachment attachment = new ApvAttachment();
			// 파일 경로 설정
			String path = request.getServletContext().getRealPath("/") + "resources/upload/chs/approval/";
			// 원본 파일명과 수정 파일명 담을 변수
			String oriname = "";
			String rename = "";
			// 만약 jsp에서 보낸 파일이 없을 경우
			if (apvAttachment != null) {
				oriname = apvAttachment.getOriginalFilename();
				rename = System.currentTimeMillis() + "_" + oriname;
				// 파일 저장 경로 설정
				// 디렉토리 생성 (존재하지 않는 경우)
				File uploadDirFile = new File(path);
				if (!uploadDirFile.exists()) {
					uploadDirFile.mkdirs(); // 디렉토리 생성
				}
				// 파일 객체 생성
				File dest = new File(path + rename);
				// 파일을 지정된 경로로 저장
				attachment.setApvId(apvId);
				attachment.setFileOriname(oriname);
				attachment.setFileRename(rename);
				apvAttachment.transferTo(dest);
			}
			log.debug("{}", attachment);
			
			
			// 참조인 객체 생성
	        List<CarbonCopy> carbonCopyRequest = new ArrayList<>();
	        for (Map<String, Object> c : carbonCopyList) {
	            String emp = (String) c.get("empId");
	            String ccId = (String) c.get("ccId");
	            carbonCopyRequest.add(CarbonCopy.builder().ccId(ccId).apvId(apvId).empId(emp).build());
	        }
	        log.debug("{}", carbonCopyRequest);
			
			//품의서, 지출결의서
			int total = 0;
			List<PaymentList> paymentList = new ArrayList<>();
			AfterPayment afterPayment = new AfterPayment();
			PrePayment prePayment = new PrePayment();
			if(apvType.equals("2")) {
				for (int i = 0; i < payList.size(); i++) {
					total += payAmount.get(i);
					log.debug("{}",total);
					paymentList.add(PaymentList.builder()
							// apvId는 selectKey가져와서 처리해야함
							.payList(payList.get(i)).apvId(apvId).payAmount(payAmount.get(i)).reference(reference.get(i)).build());
				}
				afterPayment = AfterPayment.builder().apvId(apvId).payCase(apvCase).payAmount(total).payDate(payDate)
						.build();
			}
			if(apvType.equals("1")) {
				for (int i = 0; i < payList.size(); i++) {
					total += payAmount.get(i);
					log.debug("{}",total);
					paymentList.add(PaymentList.builder()
							// apvId는 selectKey가져와서 처리해야함
							.apvId(apvId).payList(payList.get(i)).payAmount(payAmount.get(i)).reference(reference.get(i)).build());
				}
				prePayment = PrePayment.builder().apvId(apvId).payCase(apvCase).payAmount(total).payDate(payDate)
						.build();
			}
			log.debug("{}", paymentList);
			log.debug("{}", afterPayment);
			Vacay vacay = new Vacay();
			//휴가신청서
			if(vacayType!=null) {
				vacay.setApvId(apvId);
				vacay.setVacayEnd(vacayEnd);
				vacay.setVacayType(vacayType);
				vacay.setVacayStart(vacayStart);
			}
			log.debug("{}",vacay);
			
			Approval approval = new Approval();
			approval.setApvId(apvId);
			approval.setEmpId(empId);
			approval.setApvType(apvType); // 문서 종류
			approval.setApvStatus(apvStatus); // 문서 상태 0이면 상신, 1이면 임시 저장
			approval.setApvStrg(apvStrg);
			approval.setApvTitle(apvTitle);
			approval.setApvContent(apvContent);
			approval.setApvDate(apvDateRequest);
			log.debug("{}", approval);
			
			Map<String, Object> approvalMap = new HashMap<>();
			approvalMap.put("approval", approval);
			approvalMap.put("apvAttachment", attachment != null ? attachment : null);
			approvalMap.put("apvType", apvType);
			approvalMap.put("carboncopy", carbonCopyRequest != null && !carbonCopyRequest.isEmpty() ? carbonCopyRequest : null);
			approvalMap.put("approvalline", approvalLineRequest);
			approvalMap.put("afterpayment", afterPayment != null ? afterPayment : null);
			approvalMap.put("prepayment", prePayment != null ? prePayment : null);
			approvalMap.put("paymentList", paymentList != null && !paymentList.isEmpty() ? paymentList : null);
			approvalMap.put("vacay", approvalMap != null ? vacay : null);
			int result = service.updateApproval(approvalMap);
			if(result<0) { 
				File delFile=new File(path + rename); if(delFile.exists())
					delFile.delete(); 
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//태그 불러오기
	@GetMapping("/selectapvtag")
	public ResponseEntity<ApvTag> selectApvTag(@RequestParam String apvType){
		ApvTag apvTag =service.selectApvTag(apvType);
		return ResponseEntity.ok(apvTag);
	}
	
	//결재라인 추가하기
	@PostMapping("/addApprovalLine")
    public ResponseEntity<String> addApprovalLine(@RequestBody FrequentLine frequentLine) {
        log.debug("{}",frequentLine);
        try {
        	int result =service.insertFrequentLine(frequentLine);
        	if(result<0) {
        		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        	}
        }catch(Exception e) {
        	e.printStackTrace();
        	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return ResponseEntity.ok("결재라인이 성공적으로 추가되었습니다.");
    }
	
	
	//결재 라인 업데이트하기 결재
	//결재와 반련 같이 할 수 있음
	//만약에 반려일 경우
	//팀장 : 결재 라인에서 apvStatus를 1로 변경 , 결재 문서에서는 apvStatus를 1로 변경 (반려), apvDone을 sysdate로 설정
	//결재일 경우 
	//팀장 : 결재 라인에서 apvStatus를 1로 변경, 만약 Sequence가 0이라면 apvDone을 sysdate로 설정 
	@PostMapping("/updateApprovalLineSuccess")
	public ResponseEntity<String> updateApprovalLineSuccess(@RequestBody Map<String,Object> param){
		Map<String,String> param2 = new HashMap<>();
		for (Map.Entry<String, Object> entry : param.entrySet()) {
	        if (entry.getValue() != null) {
	            param2.put(entry.getKey(), entry.getValue().toString());
	        }
	    }
		log.debug("{}",param2);
		int result = service.updateApprvoalLine(param2);
		String msg = result > 0 ? "결재 완료되었습니다.":"결재 실패하였습니다.";
		return ResponseEntity.ok(msg);
	}
	//결재함 조회 필요한 데이터 조회 후 페이지 이동
		@GetMapping("/approvaling.do")
		public ResponseEntity<Map<String,Object>> selectApprovalIng(@RequestParam(defaultValue = "10") int numPerpage,
				@RequestParam(defaultValue = "1") int cPage,
				String date, String empId, String approvalLine,String apvStatus,Model model) {
			Map<String,String> param = new HashMap<>();
			log.debug(date);
			if(date.equals("old")){
				param.put("old", "old");
			}
			param.put("empId", empId);
			param.put("approvalLine", approvalLine);
			param.put("apvStatus", apvStatus);
			param.put("apvStrg", "0");
			String	pageBar=pageFactory.getPage(cPage, numPerpage, service.selectApprovalCount(param), "/approval/approvaling.do?empId="+empId+"&date="+param.get("old")+"&"
					+ "carbonCopy=1&apvStatus=0&");
			List<Approval> approvals = service.selectApproval(Map.of("cPage",cPage,"numPerpage",numPerpage),param);
			// 응답 데이터 구성
			Map<String, Object> response = new HashMap<>();
			response.put("approvals", approvals);
			response.put("pageBar", pageBar);
			response.put("numPerpage", numPerpage);
			if(date!=null){
				response.put("old", "old");
			}
			return ResponseEntity.ok(response);
		}
		//결재함 조회 필요한 데이터 조회 후 페이지 이동
		@GetMapping("/approvalend.do")
		public ResponseEntity<Map<String,Object>> selectApprovalEnd(@RequestParam(defaultValue = "10") int numPerpage,
				@RequestParam(defaultValue = "1") int cPage,
				String date, String empId, String approvalLine,String apvStatus,Model model) {
			Map<String,String> param = new HashMap<>();
			if(date!=null){
				param.put("old", "old");
			}
			param.put("empId", empId);
			param.put("approvalLine", approvalLine);
			param.put("apvStatus", apvStatus);
			param.put("apvStrg", "0");
			String	pageBar=pageFactory.getPage(cPage, numPerpage, service.selectApprovalCount(param), "/approval/approvalend.do?empId="+empId+"&date="+param.get("old")+"&"
					+ "carbonCopy=1&apvStatus=0&");
			List<Approval> approvals = service.selectApproval(Map.of("cPage",cPage,"numPerpage",numPerpage),param);
			// 응답 데이터 구성
			Map<String, Object> response = new HashMap<>();
			response.put("approvals", approvals);
			response.put("pageBar", pageBar);
			response.put("numPerpage", numPerpage);
			if(date!=null){
				response.put("old", "old");
			}
			return ResponseEntity.ok(response);
		}
		//결재함 조회 필요한 데이터 조회 후 페이지 이동
		@GetMapping("/approvalcarbon.do")
		public ResponseEntity<Map<String,Object>> selectApprovalCarbon(@RequestParam(defaultValue = "10") int numPerpage,
				@RequestParam(defaultValue = "1") int cPage,
				String date, String empId, String carbonCopy,String apvStatus,Model model) {
			Map<String,String> param = new HashMap<>();
			if(date!=null){
				param.put("old", "old");
			}
			param.put("empId", empId);
			param.put("carbonCopy", carbonCopy);
			param.put("apvStatus", apvStatus);
			param.put("apvStrg", "0");
			String	pageBar=pageFactory.getPage(cPage, numPerpage, service.selectApprovalCount(param), "/approval/approvaCarbon.do?empId="+empId+"&date="+param.get("old")+"&"
					+ "carbonCopy=1&apvStatus=0&");
			List<Approval> approvals = service.selectApproval(Map.of("cPage",cPage,"numPerpage",numPerpage),param);
			// 응답 데이터 구성
			Map<String, Object> response = new HashMap<>();
			response.put("approvals", approvals);
			response.put("pageBar", pageBar);
			response.put("numPerpage", numPerpage);
			if(date!=null){
				response.put("old", "old");
			}
			return ResponseEntity.ok(response);
		}
		
		@GetMapping("/download/{apvId}")
		public ResponseEntity<Object> downloadFile(@PathVariable String apvId, HttpServletRequest request) {
		    ApvAttachment attachment = service.selectFileById(apvId);
		    if (attachment == null) {
		        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		    }

		    String filePathString = request.getServletContext().getRealPath("/") + "resources/upload/chs/approval/" + attachment.getFileRename();
		    Path filePath = Paths.get(filePathString);

		    try {
		        Resource resource = new InputStreamResource(Files.newInputStream(filePath));
		        File file = new File(filePathString);

		        // 파일 이름 인코딩
		        String encodedFilename = URLEncoder.encode(attachment.getFileOriname(), "UTF-8").replaceAll("\\+", "%20");

		        HttpHeaders headers = new HttpHeaders();
		        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(encodedFilename).build());

		        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
		    } catch (Exception e) {
		        return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		    }
		}

		@GetMapping("/selectcount")
		public ResponseEntity<Map<String,Integer>> selectCount(String empId){
			log.debug(empId);
			int approvalCount = service.approvalWaitCount(empId);
			int myCount = service.myApprovalWaitCount(empId);
			Map<String,Integer> response = new HashMap<>();
			response.put("approvalCount", approvalCount);
			response.put("myCount", myCount);
			return ResponseEntity.ok(response);
		}

}
