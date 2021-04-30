package com.example.requestvacation.dto;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.example.requestvacation.domain.entitiy.Vacation;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VacResponseDTO {
	@Schema(description ="일련 번호", example = "1", maxLength = 4)
	private Long vacSeq;
	
	@Schema(description = "신청 일", example = "20210504", maxLength = 8)
	private String vacStrDt;
	
	@Schema(description = "사용일수", example = "2.00", maxLength = 3)
	private Double vacDs;
	
	@Schema(description = "사용자 아이디", example = "zigzagVIP", maxLength = 20)
	private String usrId;
	
	@Schema(description = "코멘트", example = "comments", maxLength = 1000)
	private String vacRmk;
	
	@Schema(description = "최초 등록 일자")
	private LocalDateTime fstEntDt;
	
	@Schema(description = "최종 수정 일자")
	private LocalDateTime lstUptDt;

	public VacResponseDTO(Vacation entity) {
		this.vacSeq = entity.getVacSeq();
		this.vacStrDt = entity.getVacDt();
		this.vacDs = entity.getVacDs();
		this.usrId = entity.getUsrId();
		this.vacRmk = entity.getVacRmk();
		this.fstEntDt = entity.getFstEntDt();
		this.lstUptDt = entity.getLstUpdDt();
	}
	
	@Builder
	public VacResponseDTO(Long vacSeq, String vacStrDt, Double vacDs, String usrId,
			String vacRmk) {
		this.vacSeq = vacSeq;
		this.vacStrDt = vacStrDt;
		this.vacDs = vacDs;
		this.usrId = usrId;
		this.vacRmk = vacRmk;
	}

}
