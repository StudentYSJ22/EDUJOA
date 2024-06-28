package com.edujoa.chs.approval.model.service;

import java.util.List;
import java.util.Map;

import com.edujoa.chs.approval.model.dto.AfterPayment;
import com.edujoa.chs.approval.model.dto.Approval;
import com.edujoa.chs.approval.model.dto.ApprovalLine;
import com.edujoa.chs.approval.model.dto.ApvAttachment;
import com.edujoa.chs.approval.model.dto.ApvTag;
import com.edujoa.chs.approval.model.dto.CarbonCopy;
import com.edujoa.chs.approval.model.dto.FrequentLine;
import com.edujoa.chs.approval.model.dto.FrequentPerson;
import com.edujoa.chs.approval.model.dto.PrePayment;
import com.edujoa.chs.approval.model.dto.Vacay;

public interface ApprovalService {
	//전체 결재 갯수 조회하기     결재상태, 문서 종류로 조건 검색 가능
	int selectApprovalCount(Map<String,String> param);
	//전체 결재 문서 조회하기     결재상태, 문서 종류로 조건 검색 가능
	List<Approval> selectAllApproval(Map<String,Integer> rowbounds, Map<String,String> param);
	//한 개의 결재 문서 조회하기    문서 번호로 조회 가능
	Approval selectOneApproval(String apvId);
	//결재 문서 등록하기    Map 안에는 문서 종류(휴가신청서, 품의서, 지출결의서), 결재선, 참조인, 첨부파일
	int insertApproval(Map<String,Object> approval);
	//결재 문서 수정하기    Map 안에는 문서 종류(휴가신청서, 품의서, 지출결의서), 결재선, 참조인, 첨부파일
	int updateApproval(Map<String,Object> approval);
	//결재 문서 삭제하기 
	int deleteApproval(String apvId);
	//자주쓰는 결재라인 조회하기
	List<FrequentPerson> selectAllFrequentPerson();
	//자주쓰는 결재라인 등록하기
	int insertFrequentLine(FrequentLine frequentLine);
	//자주쓰는 결재라인 수정하기
	int updateFrequentLine(FrequentLine frequentLine);
	//자주쓰는 결재라인 삭제하기
	int deleteFrequentLine(String feqId);
	//자주쓰는 결재라인 인원 등록하기
	int insertFrequentPerson(FrequentPerson frequentPerson);
	//자주쓰는 결재라인 인원 수정하기
	int updateFrequentPerson(FrequentPerson frequentPerson);
	//자주쓰는 결재라인 인원 삭제하기
	int deleteFrequentPerson(String feqpId);
}
