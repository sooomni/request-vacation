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
public class VacUpdRequestDTO {

	@Schema(description ="일련 번호", example = "1", maxLength = 4)
	private Long vacSeq;
	
	@Schema(description = "코멘트", example = "comments", maxLength = 1000)
	private String vacRmk;


    public Vacation toEntity(){
    	return Vacation.builder()
                .vacSeq(vacSeq)
                .vacRmk(vacRmk)
                .build();
    }

	@Builder
	public VacUpdRequestDTO(Long vacSeq, String vacRmk) {
		super();
		this.vacSeq = vacSeq;
		this.vacRmk = vacRmk;
	}
		
}
