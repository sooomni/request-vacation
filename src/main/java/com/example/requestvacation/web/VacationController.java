package com.example.requestvacation.web;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import com.example.requestvacation.dto.VacRequestDTO;
import com.example.requestvacation.dto.VacResponseDTO;
import com.example.requestvacation.dto.VacUpdRequestDTO;
import com.example.requestvacation.exception.CustomException;
import com.example.requestvacation.exception.OverVacDsException;
import com.example.requestvacation.service.VacationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "VacationController", description = "휴가 신청 API")
@RestController
@AllArgsConstructor
@RequestMapping("/vacation")
public class VacationController {

	private VacationService vacationService;

	@Operation(summary = "신청한 모든 휴가 조회", description = "userId를 이용해 특정 회원이 생성한 모든 vacation 레코드를 조회합니다.", responses = {
			@ApiResponse(responseCode = "200", description = "휴가 목록 조회 성공", content = @Content(mediaType = "application/json",examples = { @ExampleObject(value = "{ \n\t result : \"\",\n}")})),
			@ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(mediaType = "application/json",examples = { @ExampleObject(value = "{ \n\t errorCode : \"404\",\n\t errorMsg : \"존재하지 않는 리소스에 접근했습니다.\"\n}")})) })
	@GetMapping("/list/{usrId}")
	public ResponseEntity<HashMap<String, Object>> getVaclist(@Parameter(required = true, example = "zigzagVIP") @PathVariable String usrId) {
		HashMap<String, Object> result = new HashMap();
		try {
			List<VacResponseDTO> vacationList = vacationService.getVaclist(usrId);
			result.put("result", vacationList);
			return new ResponseEntity(result,HttpStatus.OK);
		} catch (NullPointerException e) {
			result.put("errorCode", "404");
			result.put("errorMsg", "존재하지 않는 리소스에 접근했습니다.");
			return new ResponseEntity(result,HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "특정 휴가 조회", description = "vacSeq에 대응하는 특정 휴가를 조회합니다.", responses = {
			@ApiResponse(responseCode = "200", description = "특정 휴가 조회 성공", content = @Content(mediaType = "application/json",examples = { @ExampleObject(value = "{ \n\t result : \"\",\n}")})),
			@ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(mediaType = "application/json",examples = { @ExampleObject(value = "{ \n\t errorCode : \"404\",\n\t errorMsg : \"존재하지 않는 리소스에 접근했습니다.\"\n}")})) })
		@GetMapping("/detail/{seq}")
	public ResponseEntity<HashMap<String, Object>> getVac(@Parameter(required = true, example = "1") @PathVariable Long seq) {
		HashMap<String, Object> result = new HashMap();
		try {
			VacResponseDTO vacDto = vacationService.getVac(seq);
			result.put("result", vacDto);
			return new ResponseEntity(result,HttpStatus.OK);
		} catch (NullPointerException e) {
			result.put("errorCode", "404");
			result.put("errorMsg", "존재하지 않는 리소스에 접근했습니다.");
			return new ResponseEntity(result,HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "휴가 생성", description = "새로운 휴가를 생성합니다.", responses = {
			@ApiResponse(responseCode = "200", description = "생성 완료", content = @Content(mediaType = "application/json",examples = { @ExampleObject(value = "{ \n\t result : \"\",\n}")})),
			@ApiResponse(responseCode = "400", description = "생성 실패 : 휴가 일수 초과 ",content = @Content(mediaType = "application/json",examples = { @ExampleObject(value = "{ \n\t errorCode : \"400\",\n\t errorMsg : \"존재하지 않는 리소스에 접근했습니다.\"\n}")})), 
			@ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(mediaType = "application/json",examples = { @ExampleObject(value = "{ \n\t errorCode : \"404\",\n\t errorMsg : \"3일 초과로 신청에 실패했습니다.\"\n}")})),
			@ApiResponse(responseCode = "403", description = "생성 실패", content = @Content(mediaType = "application/json",examples = { @ExampleObject(value = "{ \n\t errorCode : \"500\",\n\t errorMsg : \"서버 오류로 저장에 실패했습니다. \"\n}")})) })
	@PostMapping("/resources")
	public ResponseEntity<HashMap<String, Object>> saveVac(@RequestBody VacRequestDTO vacReqDto) throws ParseException {
		HashMap<String, Object> result = new HashMap();
		try {
			HashMap<String, Object> service = vacationService.saveVac(vacReqDto);
			result.put("result", service.get("vacationList"));
			result.put("rmdvacDs", service.get("rmdvacDs"));
			return new ResponseEntity(result,HttpStatus.OK);
		} catch (NotFoundException e) {
			result.put("errorCode", "404");
			result.put("errorMsg", "존재하지 않는 리소스에 접근했습니다.");
			return new ResponseEntity(result,HttpStatus.NOT_FOUND);
		}catch (OverVacDsException e) {
			result.put("errorCode", "400");
			result.put("errorMsg", e.getVacDs() +"일 초과로 신청에 실패했습니다.");
			return new ResponseEntity(result,HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			result.put("errorCode", "500");
			result.put("errorMsg", "서버 오류로 저장에 실패했습니다.");
			return new ResponseEntity(result,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "휴가 수정", description = "휴가 코멘트를 수정합니다.", responses = {
			@ApiResponse(responseCode = "200", description = "수정 성공", content = @Content(mediaType = "application/json",examples = { @ExampleObject(value = "{ \n\t result : \"\",\n}")})),
			@ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(mediaType = "application/json",examples = { @ExampleObject(value = "{ \n\t errorCode : \"404\",\n\t errorMsg : \"존재하지 않는 리소스에 접근했습니다.\"\n}")})),
			@ApiResponse(responseCode = "500", description = "수정 실패", content = @Content(mediaType = "application/json",examples = { @ExampleObject(value = "{ \n\t errorCode : \"500\",\n\t errorMsg : \"수정에 실패했습니다.\"\n}")})) })
	@PutMapping("/resources")
	public ResponseEntity<HashMap<String, Object>> updateVac(@RequestBody VacUpdRequestDTO vacReqDto) {
		HashMap<String, Object> result = new HashMap();
		Boolean check;
		try {
			check = vacationService.updateVac(vacReqDto);
		} catch (NotFoundException e) {
			result.put("errorCode", "404");
			result.put("errorMsg", "존재하지 않는 리소스에 접근했습니다.");
			return new ResponseEntity(result,HttpStatus.NOT_FOUND);
		}
		catch (Exception e) {
			result.put("errorCode", "500");
			result.put("errorMsg", "수정에 실패했습니다.");
			return new ResponseEntity(result,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(check) {
			result.put("result", "Success");
			return new ResponseEntity(result,HttpStatus.OK);
		}
		else {
			result.put("errorCode", "500");
			result.put("errorMsg", "수정에 실패했습니다.");
			return new ResponseEntity(result,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "휴가 취소", description = "생성한 휴가를 삭제합니다.", responses = {
			@ApiResponse(responseCode = "200", description = "삭제 성공", content = @Content(mediaType = "application/json",examples = { @ExampleObject(value = "{ \n\t errorCode : \"404\",\n\t resultMsg : \"삭제 성공입니다.\"\n}")})),
			@ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(mediaType = "application/json",examples = { @ExampleObject(value = "{ \n\t errorCode : \"404\",\n\t errorMsg : \"존재하지 않는 리소스에 접근했습니다.\"\n}")})),
			@ApiResponse(responseCode = "403", description = "삭제 실패", content = @Content(mediaType = "application/json",examples = { @ExampleObject(value = "{ \n\t errorCode : \"403\",\n\t errorMsg : \"취소 가능 날짜가 아닙니다.\"\n}")})) })	
	@DeleteMapping("/resources/{seq}")
	public ResponseEntity<HashMap<String, Object>>deleteVac(@Parameter(required = true, example = "1") @PathVariable Long seq) throws Exception {
		HashMap<String, Object> result = new HashMap();
		try {
			vacationService.deleteVac(seq);
			result.put("resultMsg", "삭제 성공입니다.");
			return new ResponseEntity(result,HttpStatus.OK);
		} catch (NullPointerException e) {
			result.put("errorCode", "404");
			result.put("errorMsg", "존재하지 않는 리소스에 접근했습니다.");
			return new ResponseEntity(result,HttpStatus.NOT_FOUND);
		} catch (CustomException e) {
			result.put("errorCode", "403");
			result.put("errorMsg", "취소 가능 날짜가 아닙니다.");
			return new ResponseEntity(result,HttpStatus.FORBIDDEN);
		}

	}
}
