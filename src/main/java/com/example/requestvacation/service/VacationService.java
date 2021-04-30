package com.example.requestvacation.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import com.example.requestvacation.config.DateConfig;
import com.example.requestvacation.domain.entitiy.UserInfo;
import com.example.requestvacation.domain.entitiy.Vacation;
import com.example.requestvacation.domain.repo.UserRepository;
import com.example.requestvacation.domain.repo.VacationRepository;
import com.example.requestvacation.dto.VacRequestDTO;
import com.example.requestvacation.dto.VacResponseDTO;
import com.example.requestvacation.dto.VacUpdRequestDTO;
import com.example.requestvacation.exception.CustomException;
import com.example.requestvacation.exception.OverVacDsException;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class VacationService {

	private final VacationRepository vacRepository;
	
	private final UserRepository userRepository;
	

	@Transactional
    public HashMap<String, Object> saveVac(VacRequestDTO vacDto) throws ParseException {
		HashMap<String, Object> result = new HashMap();
    	List<VacResponseDTO> list = new ArrayList();
    	//남은 연차 체크
    	UserInfo user = userRepository.findByUsrId(vacDto.getUsrId());
    	
    	if(user == null) {
    		throw new NotFoundException(user.getUsrId());
    	}

    	Double avaDS = user.getAvailableVacDs();
    	Double curDS = user.getEnrolledVacDs();
    	Double updDS = vacDto.getVacDs();
    	if(avaDS-curDS < updDS) {
    		throw new OverVacDsException(updDS-avaDS+curDS);
    	}
    	if(vacDto.getVacEndDt() == null) { 
    		list.add(new VacResponseDTO(vacRepository.save(vacDto.toEntity())));
    	}else {
    		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
    		String [] days = null;
    		try {
    			String from= transFormat.format(transFormat.parse(vacDto.getVacStrDt()));
    			String to = transFormat.format(transFormat.parse(vacDto.getVacEndDt()));
    			days = DateConfig.getDiffDaysFormat(from, to, "yyyyMMdd" ); 
    		}catch (ParseException e) {
				e.printStackTrace();
			}
    
    		for( int i = 0; i < days.length; i++ ) {  	
    			list.add(new VacResponseDTO(vacRepository.save(vacDto.toEntity(days[i])))); 
    		}
    	}
    	
		int res = userRepository.updateUserVacDs(curDS+updDS,user.getUsrId());
		System.out.println("반영 status (정상 : 1)  "+ res);
		
		result.put("vacationList", list);
		result.put("rmdvacDs", avaDS - (curDS +updDS));
		
    	return result;
    }

	@Transactional
    public Boolean updateVac(VacUpdRequestDTO vacDto) throws ParseException {
		HashMap<String, Object> result = new HashMap();
    	List<VacResponseDTO> list = new ArrayList();
    	
    	Vacation vac = vacRepository.findByVacSeq(vacDto.getVacSeq());
    	
    	if(vac == null) {
    		throw new NotFoundException(Long.toString(vac.getVacSeq()));
    	}
    	
    	int res = vacRepository.updateRmk(vacDto.getVacRmk(),vac.getVacSeq());		
    	return res >= 1 ?  true : false;
    }

	@Transactional
    public void deleteVac(Long vacSeq) throws Exception {
    	Vacation vac = vacRepository.findByVacSeq(vacSeq);
    	if(vac == null) {
    		throw new NullPointerException();
    	}else {
    		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
    		Date theday = null,today = null;
    		try{ 
    			theday = transFormat.parse(vac.getVacDt());
    			today = new Date();
    		}catch (ParseException e) {
				e.printStackTrace();
			}
    		if(today.after(theday)){
    			throw new CustomException();
    		}
    		else {
    			UserInfo user = userRepository.findByUsrId(vac.getUsrId());
    			userRepository.updateUserVacDs(user.getEnrolledVacDs() -vac.getVacDs(),user.getUsrId());
    			vacRepository.deleteByVacSeq(vacSeq);
    		}
    	}
	}
  
	
    public List<VacResponseDTO> getVaclist(String usrId) {
    	List<Vacation> vacEntities = vacRepository.findByUsrIdOrderByVacSeq(usrId);
    	
    	if(vacEntities.isEmpty()) throw new NullPointerException();
    	else {
    		List<VacResponseDTO>vacResult = new ArrayList<>();
    		for(Vacation v: vacEntities) {
    			vacResult.add(new VacResponseDTO(v));
    		}
        	return vacResult;    
    	}
    } 
    
    public VacResponseDTO getVac(Long vacSeq) {
    	Vacation vac = vacRepository.findByVacSeq(vacSeq);
    	if(vac == null){
    		throw new NullPointerException();
    	}
    	return new VacResponseDTO(vac);
    }

}