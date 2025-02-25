package com.potatocountry.potatocountry.domain.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.potatocountry.potatocountry.data.entitiy.AuthUser;
import com.potatocountry.potatocountry.data.entitiy.User;
import com.potatocountry.potatocountry.data.repository.AuthUserRepository;
import com.potatocountry.potatocountry.data.repository.UserRepository;
import com.potatocountry.potatocountry.domain.user.dto.response.UserInfoResDto;
import com.potatocountry.potatocountry.global.error.CustomError;
import com.potatocountry.potatocountry.global.error.CustomException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
	private final UserRepository userRepository;

	private final AuthUserRepository authUserRepository;

	public UserInfoResDto getUserInfo(Long userId, Long authUserId) {

		if (userId.equals(authUserId)) {
			throw new CustomException(CustomError.USER_NOT_MATCH);
		}
		User user = userRepository.getByAuthUserId(authUserId);
		return UserInfoResDto.toDto(user);
	}

	@Transactional
	public void deleteUser(Long authUserId) {
		AuthUser authUser = authUserRepository.getReferenceById(authUserId);
		authUser.deactivate();
	}
}
