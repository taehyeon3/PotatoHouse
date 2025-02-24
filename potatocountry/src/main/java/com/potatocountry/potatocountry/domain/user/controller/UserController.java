package com.potatocountry.potatocountry.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.potatocountry.potatocountry.domain.user.dto.response.UserCreateResDto;
import com.potatocountry.potatocountry.domain.user.dto.response.UserInfoResDto;
import com.potatocountry.potatocountry.domain.user.service.UserService;
import com.potatocountry.potatocountry.global.security.dto.CustomUserDetails;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "유저정보", description = "유저정보 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
	private final UserService userService;

	@Operation(security = {@SecurityRequirement(name = "bearerAuth")})
	@GetMapping("/{id}")
	public ResponseEntity<UserInfoResDto> userGet(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails customUserDetails)
	{
		UserInfoResDto response = userService.getUserInfo(id, customUserDetails.getId());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
