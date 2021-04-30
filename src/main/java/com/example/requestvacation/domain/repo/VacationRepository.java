package com.example.requestvacation.domain.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.requestvacation.domain.entitiy.Vacation;

public interface VacationRepository extends JpaRepository<Vacation, Long>{
	
	Vacation save(Vacation vac);

	void deleteByVacSeq(@Param("vac_seq") Long vacSeq);
	
	void delete(Vacation vac);
	
	Vacation findByVacSeq(@Param("vac_seq") Long vacSeq);
	
	Vacation findByVacDt(String vacStrDt);

	List<Vacation> findByUsrIdOrderByVacSeq(String usrId);
	
	@Modifying
	@Query("UPDATE Vacation v set v.vacRmk = ?1 where v.vacSeq = ?2")
	int updateRmk(@Param("vac_rmk") String vacRmk, @Param("vac_seq") Long vacSeq);
}