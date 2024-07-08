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
import com.edujoa.chs.approval.model.dto.CarbonCopy;
import com.edujoa.chs.approval.model.dto.FrequentLine;
import com.edujoa.chs.approval.model.dto.FrequentPerson;
import com.edujoa.chs.approval.model.dto.PrePayment;
import com.edujoa.chs.approval.model.dto.Vacay;
import com.edujoa.with.employee.model.dto.Employee;

import lombok.RequiredArgsConstructor;

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
	//결재 문서 상세 조회
	@Override
	public Approval selectOneApproval(String apvId) {
		return dao.selectOneApproval(session, apvId);
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
		if(approvalMap.get("apvattachment") != null) {
			ApvAttachment apvAttachment = (ApvAttachment) approvalMap.get("apvattachment");
			apvAttachment.setApvId(apvId); // 시퀀스 값을 설정
			result = dao.insertApvAttachment(session, apvAttachment);
		}
		switch ((String) approvalMap.get("apvtype")) {
		case "vacay":
			Vacay vacay = (Vacay) approvalMap.get("vacay");
			vacay.setApvId(apvId); // 시퀀스 값을 설정
			result = dao.insertVacay(session, vacay); break;
		case "prepayment":
			PrePayment prePayment = (PrePayment) approvalMap.get("prepayment");
			prePayment.setApvId(apvId); // 시퀀스 값을 설정
			result = dao.insertPrePayment(session, prePayment); break;
		case "afterpayment":
			AfterPayment afterPayment = (AfterPayment) approvalMap.get("afterpayment");
			afterPayment.setApvId(apvId); // 시퀀스 값을 설정
			result = dao.insertAfterPayment(session, afterPayment); break;
		}
		if(approvalMap.get("carboncopy") != null) {
			CarbonCopy carbonCopy = (CarbonCopy) approvalMap.get("carboncopy");
			carbonCopy.setApvId(apvId); // 시퀀스 값을 설정
			result = dao.insertCarbonCopy(session, carbonCopy);
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
		switch ((String) approvalMap.get("apvtype")) {
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
		if(approvalMap.get("carboncopy") != null) {
			CarbonCopy carbonCopy = (CarbonCopy) approvalMap.get("carboncopy");
			result = dao.updateCarbonCopy(session, carbonCopy);
		}
		//결재 라인은 여러 명이 올 수 있으니 List로 받아 for문을 돌림
		List<ApprovalLine> approvalLine = (List<ApprovalLine>) approvalMap.get("approvalline");
		for(ApprovalLine al : approvalLine) {
			dao.updateApprovalLine(session, al);
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
	@Override
	public int insertFrequentLine(FrequentLine frequentLine) {
		return dao.insertFrequentLine(session, frequentLine);
	}
	//자주쓰는 결재라인 수정하기
	@Override
	public int updateFrequentLine(FrequentLine frequentLine) {
		return dao.updateFrequentLine(session, frequentLine);
	}
	//자주쓰는 결재라인 삭제하기
	@Override
	public int deleteFrequentLine(String feqId) {
		return dao.deleteFrequentLine(session, feqId);
	}
	//자주쓰는 결재라인 인원 등록하기
	@Override
	public int insertFrequentPerson(FrequentPerson frequentPerson) {
		return dao.insertFrequentPerson(session, frequentPerson);
	}
	//자주쓰는 결재라인 인원 수정하기
	@Override
	public int updateFrequentPerson(FrequentPerson frequentPerson) {
		return dao.updateFrequentPerson(session, frequentPerson);
	}
	//자주쓰는 결재라인 인원 삭제하기
	@Override
	public int deleteFrequentPerson(String feqpId) {
		return dao.deleteFrequentPerson(session, feqpId);
	}
	//결재선 조회하기
	@Override
	public List<Employee> selectAllApprvoalLine(String empId) {
		return dao.selectAllApprovalLine(session, empId);
	}
}
