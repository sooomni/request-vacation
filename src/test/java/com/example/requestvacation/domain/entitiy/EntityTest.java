package com.example.requestvacation.domain.entitiy;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class EntityTest {
	@Test 
	public void getUsrId(){ 
		
		final UserInfo user = UserInfo.builder() 
				.usrId("네덜란드123")
				.usrPw("2345")
				.usrName("김수민")
				.usrRole("admin")
				.availableVacDs(15.0)
				.enrolledVacDs(0.0)
				.build();
		
		final String id = user.getUsrId(); 
		assertEquals("네덜란드123", id); 
	}
	
	@Test 
	public void getUsName(){ 
		
		final UserInfo user = UserInfo.builder() 
				.usrId("네덜란드123")
				.usrPw("2345")
				.usrName("김수민")
				.usrRole("admin")
				.availableVacDs(15.0)
				.enrolledVacDs(0.0)
				.build();
		
		final String id = user.getUsrName(); 
		assertEquals("네덜란드", id); 
		//assertSame(user,user); 		
	}
}
