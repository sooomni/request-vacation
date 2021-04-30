package com.example.requestvacation.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.requestvacation.domain.entitiy.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
	UserInfo findByUsrId(String usrId);
	
	UserInfo save(UserInfo user);
	
	@Modifying
	@Query("UPDATE UserInfo u set u.enrolledVacDs = ?1 where u.usrId = ?2")
	int updateUserVacDs(@Param("enrolled_vac_ds") Double enrolledVacDs, @Param("user_Id") String userId);
}
