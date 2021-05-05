package com.example.requestvacation;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.example.requestvacation.web.UserController;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest( properties = { "testId=testId", "testName=sooom" } )
@Slf4j
class RequestVacationApplicationTests {
	
	@Value("${testId}") 
	private String testId; 
	
	@Value("${testName}") 
	private String testName; 
	
	@Test void test() throws Exception 
	{ 
		log.info("##### Properties 테스트 #####"); 
		log.info("testId : " + testId); 
		log.info("testName : " + testName); 
	}

}
