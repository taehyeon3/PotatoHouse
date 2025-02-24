package com.potatocountry.potatocountry.domain.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.potatocountry.potatocountry.data.entitiy.User;
import com.potatocountry.potatocountry.data.repository.UserRepository;
import com.potatocountry.potatocountry.domain.user.dto.response.UserCreateResDto;
import com.potatocountry.potatocountry.domain.user.dto.response.UserInfoResDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
	private final UserRepository userRepository;

	public UserInfoResDto getUserInfo(Long urlId, Long authUserId){
		if (!urlId.equals(authUserId)) {
			throw new IllegalArgumentException("요청한 사용자 정보와 인증된 사용자가 일치하지 않습니다");
		}
		User user = userRepository.getByAuthUserId(authUserId);
		return UserInfoResDto.toDto(user);
	}
}
