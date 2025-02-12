package com.potatocountry.potatocountry.domain.auth.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.potatocountry.potatocountry.data.entitiy.User;
import com.potatocountry.potatocountry.data.repository.UserRepository;
import com.potatocountry.potatocountry.domain.auth.dto.request.JoinReqDto;
import com.potatocountry.potatocountry.domain.auth.dto.response.JoinResDto;
import com.potatocountry.potatocountry.global.error.CustomError;
import com.potatocountry.potatocountry.global.error.CustomException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Transactional
	public JoinResDto joinProcess(JoinReqDto joinReqDto) {
		validateDuplicationId(joinReqDto.getLoginId());
		validateDuplicationNickname(joinReqDto.getNickname());

		User user = new User(joinReqDto.getLoginId(),
			bCryptPasswordEncoder.encode(joinReqDto.getPassword()), joinReqDto.getUsername(),
			joinReqDto.getNickname());
		userRepository.save(user);
		return JoinResDto.toDto(user);
	}

	public void validateDuplicationNickname(String nickname) {
		if (userRepository.existsByNickname(nickname)) {
			throw new CustomException(CustomError.USER_DUPLICATION_NICKNAME);
		}
	}

	public void validateDuplicationId(String loginId) {
		if (userRepository.existsByLoginId(loginId)) {
			throw new CustomException(CustomError.USER_DUPLICATION_ID);
		}
	}
}
