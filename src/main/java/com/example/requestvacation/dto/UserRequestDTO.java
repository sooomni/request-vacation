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
public class UserRequestDTO {
	@Schema(description = "사용자 아이디", example = "zigzagVIP", maxLength = 20)
	private String usrId;

	@Schema(description = "사용자 비밀번호", example = "pw123", maxLength = 200)
	private String usrPw;

	@Schema(description = "사용자 이름", example = "크로키 김", maxLength = 10)
	private String usrName;

	@Schema(description = "사용자 권한", example = "USER", maxLength = 10)
	private String usrRole;


	public UserInfo toEntity(){
		return UserInfo.builder()
				.usrId(usrId)
				.usrPw(usrPw)
				.usrName(usrName)
				.usrRole(usrRole)
				.build();
	}
	
	@Builder
	public UserRequestDTO(String usrId, String usrPw, String usrName, String usrRole) {
		this.usrId = usrId;
		this.usrPw = usrPw;
		this.usrName = usrName;
		this.usrRole = usrRole;
	}
}
