package com.edujoa.chs.approval.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

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
import com.edujoa.with.employee.model.dto.Employee;

public interface ApprovalDao {
		//기안함 결재 갯수 조회하기     결재상태, 문서 종류로 조건 검색 가능
		int selectMyApprovalCount(SqlSession session, Map<String,String> param);
		//기안함 결재 문서 조회하기     결재상태, 문서 종류로 조건 검색 가능
		List<Approval> selectMyApproval(SqlSession session, Map<String,Integer> rowbounds, Map<String,String> param);
		//한 개의 결재 문서 조회하기    문서 번호로 조회 가능
		Approval selectOneApproval(SqlSession session, String apvId);
		//결재 문서 등록하기    
		int insertApproval(SqlSession session, Approval approval);
		//결재 문서 수정하기
		int updateApproval(SqlSession session, Approval approval);
		//결재 문서 삭제하기
		int deleteApproval(SqlSession session, String apvId);
		//휴가신청서 등록하기
		int insertVacay(SqlSession session, Vacay vacay);
		//휴가신청서 수정하기
		int updateVacay(SqlSession session, Vacay vacay);
		//품의서(지출) 등록하기
		int insertPrePayment(SqlSession session, PrePayment prePayment);
		//품의서(지출) 수정하기
		int updatePrePayment(SqlSession session, PrePayment prePayment);
		//지출결의서 등록하기
		int insertAfterPayment(SqlSession session, AfterPayment afterPayment);
		//지출결의서 수정하기
		int updateAfterPayment(SqlSession session, AfterPayment afterPayment);
		//참조인 등록하기
		int insertCarbonCopy(SqlSession session, CarbonCopy carbonCopy);
		//참조인 수정하기
		int updateCarbonCopy(SqlSession session, CarbonCopy carbonCopy);
		//참조인 삭제하기
		int deleteCarbonCopy(SqlSession session, String ccId);
		//결재선 등록하기
		int insertApprovalLine(SqlSession session, ApprovalLine approvalLine);
		//결재선 수정하기
		int updateApprovalLine(SqlSession session, ApprovalLine approvalLine);
		//결재선 삭제하기
		int deleteApprovalLine(SqlSession session, String apvLineId);
		//결재 첨부파일 등록하기
		int insertApvAttachment(SqlSession session, ApvAttachment apvAttachment);
		//결재 첨부파일 수정하기
		int updateApvAttachment(SqlSession session, ApvAttachment apvAttachment);
		//결재 첨부파일 삭제하기
		int deleteApvAttachment(SqlSession session, String fileOriname);
		//결재 태그 저장테이블 값 불러오기
		ApvTag selectApvTag(SqlSession session, String apvType);
		//자주쓰는 결재라인 조회하기
		List<FrequentLine> selectAllFrequenLine(SqlSession session, String empId);
		//자주쓰는 결재라인 등록하기
		int insertFrequentLine(SqlSession session, FrequentLine frequentLine);
		//자주쓰는 결재라인 수정하기
		int updateFrequentLine(SqlSession session, FrequentLine frequentLine);
		//자주쓰는 결재라인 삭제하기
		int deleteFrequentLine(SqlSession session, String feqId);
		//자주쓰는 결재라인 인원 등록하기
		int insertFrequentPerson(SqlSession session, FrequentPerson frequentPerson);
		//자주쓰는 결재라인 인원 수정하기
		int updateFrequentPerson(SqlSession session, FrequentPerson frequentPerson);
		//자주쓰는 결재라인 인원 삭제하기
		int deleteFrequentPerson(SqlSession session, String feqpId);
		//결재선 조회하기
		List<Employee> selectAllApprovalLine(SqlSession session, String empId);
}
