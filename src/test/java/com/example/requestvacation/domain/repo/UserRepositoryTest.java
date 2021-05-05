package com.example.requestvacation.domain.repo;

import static org.assertj.core.api.BDDAssertions.then;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.requestvacation.domain.entitiy.UserInfo;

@DataJpaTest 
//@Transactional(propagation = Propagation.NOT_SUPPORTED) 
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
	@Autowired 
	private UserRepository userRepository; 

	@Test 
	void findByUsrId() { 
		UserInfo user  = userRepository.findByUsrId("sooomni"); 
		
		then(user != null); 

		then("sooomni").isEqualTo(user.getUsrId()); 
		then("김둘리").isEqualTo(user.getUsrName()); 
			
	}
	
	@Test 
	void save() { 
		String id = "sooomni";
		String pw = "test1234";
		String name = "김둘리";
		String role = "admin";

		UserInfo user  = userRepository.save(new UserInfo(id,pw,name,role,15.0, 0.0)); 
		
		then(user != null); 

		then("sooomni").isEqualTo(user.getUsrId()); 
		then("김둘리").isEqualTo(user.getUsrName()); 
			
	}

}
