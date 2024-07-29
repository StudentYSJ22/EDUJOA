package com.edujoa.ysj.attendance.model.dto;

import lombok.Data;

@Data
public class CheckInRequest {
    private String empId;
}

//단순히 컨트롤러에서 API 요청을 받을 때 사용되는 DTO(Data Transfer Object)입니다. 
//따라서 데이터베이스와는 별개의 역할을 하며, JSON 형식의 데이터를 주고받기 위해 사용됩니다.
//
//CheckInRequest 클래스는 근태 체크 요청 시 필요한 데이터를 담는 용도로 사용되며, 
//주로 empId와 같은 필드를 포함합니다. 
//이 클래스는 @RequestBody 어노테이션을 사용하여 JSON 요청을 Java 객체로 변환할 때 사용됩니다.