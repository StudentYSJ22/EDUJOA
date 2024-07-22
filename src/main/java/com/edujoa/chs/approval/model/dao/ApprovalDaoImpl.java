package com.edujoa.chs.approval.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

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

@Repository
public class ApprovalDaoImpl implements ApprovalDao {
	//기안함  문서 수 조회         결재상태, 문서 종류로 조건 검색 가능,   날짜 기준 오름 내림
	@Override
	public int selectMyApprovalCount(SqlSession session, Map<String, String> param) {
		return session.selectOne("approval.selectMyApprovalCount",param);
	}
	//기안함 문서 조회			결재상태, 문서 종류로 조건 검색 가능
	@Override
	public List<Approval> selectMyApproval(SqlSession session, Map<String, Integer> rowbounds,
			Map<String, String> param) {
		RowBounds rb= new RowBounds((rowbounds.get("cPage")-1)*rowbounds.get("numPerpage"),rowbounds.get("numPerpage"));
		return session.selectList("approval.selectMyApproval",param,rb);
	}
	//문서 상세 조회
	@Override
	public Approval selectOneApproval(SqlSession session, String apvId) {
		return session.selectOne("approval.selectOneApproval",apvId);
	}
	
	//문서 등록
	@Override
	public int insertApproval(SqlSession session, Approval approval) {
		return session.insert("approval.insertApproval",approval);
	}
	//문서 수정
	@Override
	public int updateApproval(SqlSession session, Approval approval) {
		return session.update("approval.updateApproval",approval);
	}
	//문서 삭제
	@Override
	public int deleteApproval(SqlSession session, String apvId) {
		return session.delete("approval.deleteApproval",apvId);
	}
	//휴가신청서 등록
	@Override
	public int insertVacay(SqlSession session, Vacay vacay) {
		return session.insert("approval.insertVacay",vacay);
	}
	//휴가신청서 수정
	@Override
	public int updateVacay(SqlSession session, Vacay vacay) {
		return session.update("approval.updateVacay",vacay);
	}
	//품의서(지출) 등록하기
	@Override
	public int insertPrePayment(SqlSession session, PrePayment prePayment) {
		return session.insert("approval.insertPrePayment",prePayment);
	}
	//품의서(지출) 수정하기
	@Override
	public int updatePrePayment(SqlSession session, PrePayment prePayment) {
		return session.update("approval.updatePrepayment",prePayment);
	}
	//지출결의서 등록하기
	@Override
	public int insertAfterPayment(SqlSession session, AfterPayment afterPayment) {
		return session.insert("approval.insertAfterPayment",afterPayment);
	}
	//지출결의서 수정하기
	@Override
	public int updateAfterPayment(SqlSession session, AfterPayment afterPayment) {
		return session.update("approval.updateAfterPayment",afterPayment);
	}
	//지출 리스트 등록하기
	@Override
	public int insertPaymentList(SqlSession session, PaymentList paymentList) {
		return session.insert("approval.insertPaymentList",paymentList);
	}
	//참조인 등록하기
	@Override
	public int insertCarbonCopy(SqlSession session, CarbonCopy carbonCopy) {
		return session.insert("approval.insertCarbonCopy",carbonCopy);
	}
	//참조인 수정하기
	@Override
	public int updateCarbonCopy(SqlSession session, CarbonCopy carbonCopy) {
		return session.update("approval.updateCarbonCopy",carbonCopy);
	}
	//참조인 삭제하기
	@Override
	public int deleteCarbonCopy(SqlSession session, String ccId) {
		return session.delete("approval.deleteCarbonCopy",ccId);
	}
	//결재선 등록하기
	@Override
	public int insertApprovalLine(SqlSession session, ApprovalLine approvalLine) {
		return session.insert("approval.insertApprovalLine",approvalLine);
	}
	//결재선 수정하기
	@Override
	public int updateApprovalLine(SqlSession session, ApprovalLine approvalLine) {
		return session.update("approval.updateApprovalLine",approvalLine);
	}
	//결재선 삭제하기
	@Override
	public int deleteApprovalLine(SqlSession session, String apvLineId) {
		return session.delete("approval.deleteApprovalLine",apvLineId);
	}
	//결재 첨부파일 등록하기
	@Override
	public int insertApvAttachment(SqlSession session, ApvAttachment apvAttachment) {
		return session.insert("approval.insertApvAttachment",apvAttachment);
	}
	//결재 첨부파일 수정하기
	@Override
	public int updateApvAttachment(SqlSession session, ApvAttachment apvAttachment) {
		return session.update("approval.updateApvAttachment",apvAttachment);
	}
	//결재 첨부파일 삭제하기
	@Override
	public int deleteApvAttachment(SqlSession session, String fileOriname) {
		return session.delete("approval.deleteApvAttachment",fileOriname);
	}
	//결재 태그 저장 테이블에서 값 불러오기
	@Override
	public ApvTag selectApvTag(SqlSession session, String apvType) {
		return session.selectOne("approval.selectApvTag",apvType);
	}
	//자주쓰는 결재라인 조회하기
	@Override
	public List<FrequentLine> selectAllFrequenLine(SqlSession session, String empId) {
		return session.selectList("approval.selectFrequentLine", empId);
	}
	//자주쓰는 결재라인 등록하기
	@Override
	public int insertFrequentLine(SqlSession session, FrequentLine frequentLine) {
		return session.insert("approval.insertFrequentLine",frequentLine);
	}
	//자주쓰는 결재라인 수정하기
	@Override
	public int updateFrequentLine(SqlSession session, FrequentLine frequentLine) {
		return session.update("approval.updateFrequentLine",frequentLine);
	}
	//자주쓰는 결재라인 삭제하기
	@Override
	public int deleteFrequentLine(SqlSession session, String feqId) {
		return session.delete("approval.deleteFrequentLine",feqId);
	}
	//자주쓰는 결재라인 인원 등록하기
	@Override
	public int insertFrequentPerson(SqlSession session,FrequentPerson frequentPerson) {
		return session.insert("approval.insertFrequentPerson",frequentPerson);
	}
	//자주쓰는 결재라인 인원 수정하기
	@Override
	public int updateFrequentPerson(SqlSession session, FrequentPerson frequentPerson) {
		return session.update("approval.updateFrequentPerson",frequentPerson);
	}
	//자주쓰는 결재라인 인원 삭제하기
	@Override
	public int deleteFrequentPerson(SqlSession session, String feqpId) {
		return session.delete("approval.deleteFrequentPerson",feqpId);
	}
	//결재선 조회하기
	@Override
	public List<Employee> selectAllApprovalLine(SqlSession session, String empId) {
		return session.selectList("approval.selectAllApprovalLine", empId);
	}
	//결재 사인 생성하기
	@Override
	public int updateSignatureUrl(SqlSession session, Map<String, String> param) {
		return session.update("approval.updateSignature",param);
	}

}
