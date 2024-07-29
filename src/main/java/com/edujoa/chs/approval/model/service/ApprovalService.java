package com.edujoa.chs.approval.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.edujoa.chs.approval.model.dto.Approval;
import com.edujoa.chs.approval.model.dto.ApvAttachment;
import com.edujoa.chs.approval.model.dto.ApvTag;
import com.edujoa.chs.approval.model.dto.FrequentLine;
import com.edujoa.chs.approval.model.dto.FrequentPerson;
import com.edujoa.with.employee.model.dto.Employee;

public interface ApprovalService {
	//기안함 결재 갯수 조회하기     결재상태, 문서 종류로 조건 검색 가능
	int selectMyApprovalCount(Map<String,String> param);
	//기안함 결재 문서 조회하기     결재상태, 문서 종류로 조건 검색 가능
	List<Approval> selectMyApproval(Map<String,Integer> rowbounds, Map<String,String> param);
	//결재함 결재 갯수 조회하기     
	int selectApprovalCount(Map<String,String> param);
	//결재함 결재 문서 조회하기
	List<Approval> selectApproval(Map<String,Integer> rowbounds,Map<String,String> param);
	//한 개의 결재 문서 조회하기    문서 번호로 조회 가능
	Approval selectOneApproval(Map<String,String> param);
	//결재 문서 등록하기    Map 안에는 문서 종류(휴가신청서, 품의서, 지출결의서), 결재선, 참조인, 첨부파일
	int insertApproval(Map<String,Object> approvalMap);
	//결재 문서 수정하기    Map 안에는 문서 종류(휴가신청서, 품의서, 지출결의서), 결재선, 참조인, 첨부파일
	int updateApproval(Map<String,Object> approvalMap);
	//결재 문서 삭제하기 
	int deleteApproval(String apvId);
	//자주쓰는 결재라인 조회하기
	List<FrequentLine> selectAllFrequenLine(String empId);
	//자주쓰는 결재라인 등록하기
	int insertFrequentLine(FrequentLine frequentLine);
	//자주쓰는 결재라인 삭제하기
	int deleteFrequentLine(String feqId);
	//결재선 조회하기
	List<Employee> selectAllApprvoalLine(String empId);
	//결재 사인 생성하기
	int updateSignatureUrl(Map<String,String> param);
	//태그 불러오기
	ApvTag selectApvTag(String apvType);
	//결재라인 업데이트하기
	int updateApprvoalLine(Map<String,String> param);
	//파일 다운로드 로직
	ApvAttachment selectFileById(String apvId);
	//결재 요청에 대한 카운트
	int myApprovalWaitCount(String empId);
	//결재 대기에 대한 카운트
	int approvalWaitCount(String empId);
}
