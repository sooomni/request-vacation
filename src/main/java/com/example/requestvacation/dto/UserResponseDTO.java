package com.example.requestvacation.dto;

import com.example.requestvacation.domain.entitiy.UserInfo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDTO {
	@Schema(description = "사용자 아이디", example = "zigzagVIP", maxLength = 20)
	private String usrId;

	@Schema(description = "사용자 이름", example = "김아무개", maxLength = 10)
	private String usrName;

	@Schema(description = "사용자 권한", example = "USER", maxLength = 10)
	private String usrRole;

	@Schema(description = "사용 가능 연차", example = "15", maxLength = 10)
	private Double availableVacDs;

	@Schema(description = "신청 및 사용완료된 연차", example = "0", maxLength = 10)
	private Double enrolledVacDs;

	@Builder
	public UserResponseDTO(UserInfo entity) {
		this.usrId = entity.getUsername();
		this.usrName = entity.getUsrName();
		this.usrRole = entity.getUsrRole();
		this.availableVacDs = entity.getAvailableVacDs();
		this.enrolledVacDs = entity.getEnrolledVacDs();
	}
	
	@Builder
	public UserResponseDTO(String usrId, String usrName, String usrRole, Double availableVacDs,
			Double enrolledVacDs) {
		this.usrId = usrId;
		this.usrName = usrName;
		this.usrRole = usrRole;
		this.availableVacDs = availableVacDs;
		this.enrolledVacDs = enrolledVacDs;
	}
}
