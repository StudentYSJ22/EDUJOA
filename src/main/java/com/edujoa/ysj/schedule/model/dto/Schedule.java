package com.edujoa.ysj.schedule.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schedule {
    private String schId;  // 일정 ID
    private String empId;  // 직원 ID
    private String schTitle;  // 일정 제목
    private String schContent;  // 일정 내용
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime schStart;  // 일정 시작 시간
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime schEnd;  // 일정 종료 시간
    private String schType;  // 일정 종류
    private String schColor;  // 일정 색상
    private String calendarType;  // 캘린더 종류
    private List<ScheduleSharer> sharers = new ArrayList<>();  // 일정 공유자 목록
    //추가 
    private String repeatType;  // 반복 타입
    private LocalDateTime repeatEndDate; // 반복 종료 날짜
    
    
    
    // 공유자 목록 반환
    public List<ScheduleSharer> getSharers() {
        return sharers != null ? sharers : new ArrayList<>();
    }

    // 공유자 목록 설정
    public void setSharers(List<ScheduleSharer> sharers) {
        this.sharers = sharers;
    }
    
    
}
