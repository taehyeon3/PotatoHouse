package com.potatocountry.potatocountry.domain.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.potatocountry.potatocountry.domain.auth.dto.request.JoinReqDto;
import com.potatocountry.potatocountry.domain.auth.dto.response.JoinResDto;
import com.potatocountry.potatocountry.domain.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	private final AuthService userService;

	@PostMapping("/join")
	public JoinResDto join(@RequestBody JoinReqDto joinReqDto) {
		return userService.joinProcess(joinReqDto);
	}
}
