package com.potatocountry.potatocountry.domain.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.potatocountry.potatocountry.domain.auth.dto.request.JoinReqDto;
import com.potatocountry.potatocountry.domain.auth.dto.response.JoinResDto;
import com.potatocountry.potatocountry.domain.auth.service.AuthService;
import com.potatocountry.potatocountry.global.error.dto.ErrorResDto;
import com.potatocountry.potatocountry.global.security.dto.CustomUserDetails;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "회원", description = "회원 관련 API")
@RestController
@RequiredArgsConstructor
public class AuthController {

	private final AuthService userService;

	@Operation(
		summary = "회원가입 API",
		description = "새로운 유저를 생성합니다.",
		security = {}
	)
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "201 성공",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = JoinResDto.class))),
		@ApiResponse(responseCode = "UR100", description = "403 아이디가 중복입니다.",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResDto.class))),
		@ApiResponse(responseCode = "UR101", description = "403 닉네임이 중복입니다.",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResDto.class))),
	})
	@PostMapping("/join")
	public ResponseEntity<JoinResDto> join(@RequestBody @Valid JoinReqDto joinReqDto) {
		JoinResDto joinResDto = userService.joinProcess(joinReqDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(joinResDto);
	}

	@Operation(security = {@SecurityRequirement(name = "bearerAuth")})
	@GetMapping("/test")
	public String test(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
		System.out.println("customUserDetails.getUsername() = " + customUserDetails.getUsername());
		System.out.println(
			"customUserDetails.getAuthorities() = " + customUserDetails.getAuthorities());
		System.out.println("customUserDetails.getPassword() = " + customUserDetails.getPassword());
		return "test";
	}
}
