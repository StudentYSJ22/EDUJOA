package com.edujoa.chs.approval.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edujoa.chs.approval.model.dao.ApprovalDao;
import com.edujoa.chs.approval.model.dto.AfterPayment;
import com.edujoa.chs.approval.model.dto.Approval;
import com.edujoa.chs.approval.model.dto.ApprovalLine;
import com.edujoa.chs.approval.model.dto.ApvAttachment;
import com.edujoa.chs.approval.model.dto.ApvTag;
import com.edujoa.chs.approval.model.dto.CarbonCopy;
import com.edujoa.chs.approval.model.dto.FrequentLine;
import com.edujoa.chs.approval.model.dto.FrequentPerson;
import com.edujoa.chs.approval.model.dto.PaymentList;
import com.edujoa.chs.approval.model.dto.PrePayment;
import com.edujoa.chs.approval.model.dto.Vacay;
import com.edujoa.with.employee.model.dto.Employee;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@RequiredArgsConstructor
public class ApprovalServiceImpl implements ApprovalService{
	private final ApprovalDao dao;
	private final SqlSession session;

	//기안함 결재 문서 전체 수 조회      param으로 결재 상태, 문서 종류로 조건 검색 가능
	@Override
	public int selectMyApprovalCount(Map<String, String> param) {
		return dao.selectMyApprovalCount(session, param);
	}
	//기안함 결재 문서 조회      param으로 결재 상태, 문서 종류, 날짜로 오름 내림 검색 가능
	@Override
	public List<Approval> selectMyApproval(Map<String, Integer> rowbounds, Map<String, String> param) {
		return dao.selectMyApproval(session, rowbounds, param);
	}
	//결재함 결재 문서 전체 수 조회
	@Override
	public int selectApprovalCount(Map<String, String> param) {
		return dao.selectApprovalCount(session, param);
	}
	//결재함 결재 문서 조회
	@Override
	public List<Approval> selectApproval(Map<String, Integer> rowbounds, Map<String, String> param) {
		return dao.selectApproval(session, rowbounds,param);
	}
	//결재 문서 상세 조회
	@Override
	public Approval selectOneApproval(Map<String,String> param) {
		return dao.selectOneApproval(session, param);
	}

	//결재 등록하기      approval에는 결재 기본 정보, 결재 첨부 파일, 결재 양식, 참조인, 결재선이 들어감
	//이 메소드는 트렌잭션을 하나로 묶음.
	@Transactional
	@Override
	public int insertApproval(Map<String, Object> approvalMap) {
		Approval approval = (Approval) approvalMap.get("approval");
		int result = dao.insertApproval(session, approval);
		//mybatis에서 <selectKey>태그를 이용해 시퀀스 값을 불러와 변수에 저장
		String apvId = approval.getApvId();
		ApvAttachment apvattachment = (ApvAttachment)approvalMap.get("apvAttachment");
		if(apvattachment.getFileOriname() != null) {
			ApvAttachment apvAttachment = (ApvAttachment) approvalMap.get("apvAttachment");
			apvAttachment.setApvId(apvId); // 시퀀스 값을 설정
			result = dao.insertApvAttachment(session, apvAttachment);
		}
		switch ((String) approvalMap.get("apvType")) {
		case "0":
			Vacay vacay = (Vacay) approvalMap.get("vacay");
			vacay.setApvId(apvId); // 시퀀스 값을 설정
			result = dao.insertVacay(session, vacay); break;
		case "1":
			PrePayment prePayment = (PrePayment) approvalMap.get("prepayment");
			prePayment.setApvId(apvId); // 시퀀스 값을 설정
			result = dao.insertPrePayment(session, prePayment); break;
		case "2":
			AfterPayment afterPayment = (AfterPayment) approvalMap.get("afterpayment");
			log.debug("{}",afterPayment);
			afterPayment.setApvId(apvId); // 시퀀스 값을 설정
			result = dao.insertAfterPayment(session, afterPayment); break;
		}
		if (approvalMap.get("paymentList") != null) {
		    List<PaymentList> paymentList = (List<PaymentList>) approvalMap.get("paymentList");
		    for (PaymentList payment : paymentList) {
		        payment.setApvId(apvId); // 시퀀스 값을 설정
		        result = dao.insertPaymentList(session, payment);
		    }
		}
		if(approvalMap.get("carboncopy") != null) {
			List<CarbonCopy> carbonCopy = (List<CarbonCopy>)approvalMap.get("carboncopy");
			for(CarbonCopy c : carbonCopy) {
				c.setApvId(apvId); // 시퀀스 값을 설정
				result = dao.insertCarbonCopy(session, c);
			}
		}
		//결재 라인은 여러 명이 올 수 있으니 List로 받아 for문을 돌림
		List<ApprovalLine> approvalLine = (List<ApprovalLine>) approvalMap.get("approvalline");
		for(ApprovalLine al : approvalLine) {
			al.setApvId(apvId); // 시퀀스 값을 설정
			dao.insertApprovalLine(session, al);
		}
		return result;
	}
	//결재 문서 수정하기
	@Override
	public int updateApproval(Map<String, Object> approvalMap) {
		Approval approval = (Approval) approvalMap.get("approval");
		int result = dao.updateApproval(session, approval);
		if(approvalMap.get("apvattachment") != null) {
			ApvAttachment apvAttachment = (ApvAttachment) approvalMap.get("apvattachment");
			result = dao.updateApvAttachment(session, apvAttachment);
		}
		switch ((String) approvalMap.get("apvType")) {
		case "vacay":
			Vacay vacay = (Vacay) approvalMap.get("vacay");
			result = dao.updateVacay(session, vacay); break;
		case "prepayment":
			PrePayment prePayment = (PrePayment) approvalMap.get("prepayment");
			result = dao.updatePrePayment(session, prePayment); break;
		case "afterpayment":
			AfterPayment afterPayment = (AfterPayment) approvalMap.get("afterpayment");
			result = dao.updateAfterPayment(session, afterPayment); break;
		}
		if (approvalMap.get("paymentList") != null) {
		    List<PaymentList> paymentList = (List<PaymentList>) approvalMap.get("paymentList");
		    for (PaymentList payment : paymentList) {
		        result = dao.updatePaymentList(session, payment);
		    }
		}
		if(approvalMap.get("carboncopy") != null) {
			List<CarbonCopy> carbonCopy = (List<CarbonCopy>)approvalMap.get("carboncopy");
			result = dao.deleteCarbonCopy(session, carbonCopy.get(0).getApvId());
			for(CarbonCopy c : carbonCopy) {
				result = dao.insertCarbonCopy(session, c);
			}
		}
		//결재 라인은 여러 명이 올 수 있으니 List로 받아 for문을 돌림
		List<ApprovalLine> approvalLine = (List<ApprovalLine>) approvalMap.get("approvalline");
		dao.deleteApprovalLine(session, approvalLine.get(0).getApvId());
		for(ApprovalLine al : approvalLine) {
			dao.insertApprovalLine(session, al);
		}
		return result;
	}
	//결재 문서 삭제하기
	@Override
	public int deleteApproval(String apvId) {
		return dao.deleteApproval(session, apvId);
	}
	//자주쓰는 결재라인 조회하기
	@Override
	public List<FrequentLine> selectAllFrequenLine(String empId) {
		return dao.selectAllFrequenLine(session,empId);
	}
	//자주쓰는 결재라인 등록하기
	@Transactional
	@Override
	public int insertFrequentLine(FrequentLine frequentLine) {
		
		int result =dao.insertFrequentLine(session, frequentLine);
		String feqId = frequentLine.getFeqId();
		for(FrequentPerson fp : frequentLine.getFrequentperson()) {
			fp.setFeqId(feqId);
			result = dao.insertFrequentPerson(session, fp);
		}
		return result;
	}
	//자주쓰는 결재라인 삭제하기
	@Override
	public int deleteFrequentLine(String feqId) {
		return dao.deleteFrequentLine(session, feqId);
	}
	//결재선 조회하기
	@Override
	public List<Employee> selectAllApprvoalLine(String empId) {
		return dao.selectAllApprovalLine(session, empId);
	}
	//결재 사인 생성하기
	@Override
	public int updateSignatureUrl(Map<String, String> param) {
		return dao.updateSignatureUrl(session, param);
	}
	//태그 불러오기
	@Override
	public ApvTag selectApvTag(String apvType) {
		return dao.selectApvTag(session, apvType);
	}
	
	//결재라인 업데이트하기
	@Transactional
	@Override
	public int updateApprvoalLine(Map<String, String> param) {
		int result = 0;
		try {
			result = dao.updateApprovalLineStatus(session,param);
			result = dao.updateApprovalStatus(session,param);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//파일 다운로드 로직
	@Override
	public ApvAttachment selectFileById(String apvId) {
		return dao.selectFileById(session, apvId);
	}
	
	//결재 대기에 대한 카운트
	@Override
	public int approvalWaitCount(String empId) {
		return dao.approvalWaitCount(session, empId);
	}
	//결재 요청에 대한 카운트
	@Override
	public int myApprovalWaitCount(String empId) {
		return dao.myApprovalWaitCount(session, empId);
	}
}
