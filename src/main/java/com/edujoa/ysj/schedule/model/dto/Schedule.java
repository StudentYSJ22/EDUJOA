
package com.edujoa.ysj.schedule.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    private String schId = UUID.randomUUID().toString(); // 기본값 설정
    private String empId;
    private String schTitle;
    private String schContent;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime schStart;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime schEnd;

    private String schType;
    private String schColor;
    private String calendarType;
    private List<ScheduleSharer> sharers = new ArrayList<>(); // 초기값 설정

    public List<ScheduleSharer> getSharers() {
        return sharers != null ? sharers : new ArrayList<>();
    }

    public void setSharers(List<ScheduleSharer> sharers) {
        this.sharers = sharers;
    }
}

