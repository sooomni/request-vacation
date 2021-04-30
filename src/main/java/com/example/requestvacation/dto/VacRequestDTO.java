package com.example.requestvacation.dto;

import com.example.requestvacation.domain.entitiy.Vacation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VacRequestDTO {	
	@Schema(description = "시작 일", example = "20210504", maxLength = 8)
	private String vacStrDt;
	
	@Schema(description = "종료 일", example = "20210505", maxLength = 3)
	private String vacEndDt;
	
	@Schema(description = "사용일수", example = "2.00", maxLength = 3)
	private Double vacDs;
	
	@Schema(description = "사용자 아이디", example = "zigzagVIP", maxLength = 20)
	private String usrId;
	
	@Schema(description = "코멘트", example = "comments", maxLength = 1000)
	private String vacRmk;

	//단일 날짜 휴가 생성
    public Vacation toEntity(){
    	return Vacation.builder()
                .vacDt(vacStrDt)
                .vacDs(vacDs)
                .usrId(usrId)
                .vacRmk(vacRmk)
                .build();
    }
    //다중 날짜 휴가 생성 
    public Vacation toEntity(String date){
    	return Vacation.builder()
                .vacDt(date)
                .vacDs(1.0)
                .usrId(usrId)
                .vacRmk(vacRmk)
                .build();
    }
    
    @Builder
    public VacRequestDTO(String vacStrDt, String vacEndDt, Double vacDS,String usrId,String vacRmk) {
		this.vacStrDt = vacStrDt;
		this.vacEndDt = vacEndDt;
		this.vacDs= vacDS;
		this.usrId =usrId;
		this.vacRmk=vacRmk;
    }

}