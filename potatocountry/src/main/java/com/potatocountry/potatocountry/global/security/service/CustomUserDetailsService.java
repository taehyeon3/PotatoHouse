package com.potatocountry.potatocountry.global.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.potatocountry.potatocountry.data.entitiy.AuthUser;
import com.potatocountry.potatocountry.data.repository.AuthUserRepository;
import com.potatocountry.potatocountry.global.error.CustomError;
import com.potatocountry.potatocountry.global.security.dto.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private final AuthUserRepository authUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AuthUser authUser = authUserRepository.findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException(CustomError.AUTH_USER_NOT_FOUND_ID.getMessage()));
		return new CustomUserDetails(authUser);
	}
}
