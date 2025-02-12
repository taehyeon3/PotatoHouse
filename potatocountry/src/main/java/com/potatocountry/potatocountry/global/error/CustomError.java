package com.potatocountry.potatocountry.global.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomError {
	//유저 에러
	USER_DUPLICATION_ID(HttpStatus.FORBIDDEN, "중복된 아이디 입니다."),
	USER_DUPLICATION_NICKNAME(HttpStatus.FORBIDDEN, "중복된 닉네임 입니다.");

	private final HttpStatus status;
	private final String message;
}
