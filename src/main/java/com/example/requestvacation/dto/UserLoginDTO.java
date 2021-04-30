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
public class UserLoginDTO {

	@Schema(description = "사용자 아이디", example = "zigzagVIP", maxLength = 20)
	private String usrId;
	
	@Schema(description = "사용자 비밀번호", example = "pw123", maxLength = 200)
	private String usrPw;


	public UserInfo toEntity(){
		return UserInfo.builder()
				.usrId(usrId)
				.usrPw(usrPw)
				.build();
	}
	
	@Builder
	public UserLoginDTO(String usrId, String usrPw, String usrName, String usrRole) {
		this.usrId = usrId;
		this.usrPw = usrPw;
	}
}
