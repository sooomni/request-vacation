package com.example.requestvacation.web;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import com.example.requestvacation.domain.entitiy.UserInfo;
import com.example.requestvacation.dto.UserLoginDTO;
import com.example.requestvacation.dto.UserRequestDTO;
import com.example.requestvacation.dto.UserResponseDTO;
import com.example.requestvacation.exception.UserDuplicatedException;
import com.example.requestvacation.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "UserController", description = "로그인 API")
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Operation(summary = "로그인", description = "로그인을 진행합니다.", responses = {
			@ApiResponse(responseCode = "200", description = "인증 완료", content = @Content(mediaType = "application/json",examples = { @ExampleObject(value = "{ \n\t result : \"\",\n}")})),
			@ApiResponse(responseCode = "501", description = "존재하지 않는 리소스", content = @Content(mediaType = "application/json",examples = { @ExampleObject(value = "{ \n\t errorCode : \"501\",\n\t errorMsg : \"존재하지 않는 아이디입니다.\"\n}")})),
			@ApiResponse(responseCode = "401", description = "인증 실패", content = @Content(mediaType = "application/json",examples = { @ExampleObject(value = "{ \n\t errorCode : \"401\",\n\t errorMsg : \"로그인에 실패했습니다.\"\n}")})) })		
	@PostMapping(value = "/auth")
    public ResponseEntity<HashMap<String, Object>> login(@RequestBody UserLoginDTO usrLogin){
		HashMap<String, Object> result = new HashMap();
	   try{
		   UserInfo user = userService.checkUser(usrLogin);
		   if(user == null) {
				result.put("errorCode", "401");
				result.put("errorMsg", "로그인에 실패했습니다.");
			   return new ResponseEntity(result,HttpStatus.UNAUTHORIZED);
		   }   
		   result.put("result",new UserResponseDTO(user));
		   return new ResponseEntity(result,HttpStatus.OK);
	   }catch(NotFoundException e){
			result.put("errorCode", "501");
			result.put("errorMsg", "존재하지 않는 아이디입니다.");
			return new ResponseEntity(result,HttpStatus.NOT_IMPLEMENTED);
	   }
	}

	@Operation(summary = "유저 생성", description = "회원 가입을 진행합니다.", responses = {
			@ApiResponse(responseCode = "200", description = "생성 완료", content = @Content(mediaType = "application/json",examples = { @ExampleObject(value = "{ \n\t result : \"\",\n}")})),
			@ApiResponse(responseCode = "409", description = "생성 실패", content = @Content(mediaType = "application/json",examples = { @ExampleObject(value = "{ \n\t errorCode : \"409\",\n\t errorMsg : \"생성 실패 : 이미 존재하는 아이디입니다.\"\n}")})) })	
	@PostMapping(value = "/new")
	public ResponseEntity<HashMap<String, Object>> signUp(@RequestBody UserRequestDTO usrReq) {
		HashMap<String, Object> result = new HashMap();
		try {
			result.put("result", userService.signUpUser(usrReq));
			return new ResponseEntity(result,HttpStatus.OK);
		} catch (UserDuplicatedException e) {
			result.put("errorCode", "409");
			result.put("errorMsg", "생성 실패 : 이미 존재하는 아이디입니다.");
			return new ResponseEntity(result,HttpStatus.CONFLICT);
		}
	}

}