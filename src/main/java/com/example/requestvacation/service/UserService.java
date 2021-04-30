package com.example.requestvacation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import com.example.requestvacation.domain.entitiy.UserInfo;
import com.example.requestvacation.domain.repo.UserRepository;
import com.example.requestvacation.dto.UserLoginDTO;
import com.example.requestvacation.dto.UserRequestDTO;
import com.example.requestvacation.exception.UserDuplicatedException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserInfo loadUserByUsername(String usrId) throws UsernameNotFoundException {
		UserInfo user = userRepository.findByUsrId(usrId);
		if (user == null) {
			throw new UsernameNotFoundException(usrId);
		}
		return user;
	}

	@Transactional
	public UserInfo signUpUser(UserRequestDTO usrReq) {
		if (userRepository.findByUsrId(usrReq.getUsrId()) != null) {
			throw new UserDuplicatedException(usrReq.getUsrId());
		}
		usrReq.setUsrPw(passwordEncoder.encode(usrReq.getUsrPw()));
		UserInfo user = userRepository.save(usrReq.toEntity());
		return user;
	}

	public UserInfo checkUser(UserLoginDTO usrReq) {
		UserInfo user = userRepository.findByUsrId(usrReq.getUsrId());

		if (user == null) {
			throw new NotFoundException(usrReq.getUsrId());
		}
		System.out.println("입력 패스워드 : "+ user.getUsrPw());
		System.out.println("DB 패스워드 : "+ usrReq.getUsrPw());
		
		return passwordEncoder.matches(usrReq.getUsrPw(),user.getUsrPw()) ? user : null;
	}
}