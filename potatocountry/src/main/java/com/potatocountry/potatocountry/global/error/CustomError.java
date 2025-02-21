package com.potatocountry.potatocountry.global.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomError {
	//인증 유저 에러
	AUTH_USER_NOT_FOUND_ID(HttpStatus.NOT_FOUND, "AU100", "존재하지 않는 아이디 입니다."),

	//유저 에러
	USER_DUPLICATION_ID(HttpStatus.FORBIDDEN, "UR100", "중복된 아이디 입니다."),
	USER_DUPLICATION_NICKNAME(HttpStatus.FORBIDDEN, "UR101", "중복된 닉네임 입니다."),
	USER_NOT_MATCH(HttpStatus.FORBIDDEN, "UR102", "작성자가 일치하지 않습니다."),

	//이미지 에러
	IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "IM100", "존재하지 않는 이미지 입니다."),

	//게시판 에러
	POST_NOT_FOUND(HttpStatus.NOT_FOUND, "PT100", "존재하지 않는 게시글 입니다.");
	private final HttpStatus status;
	private final String code;
	private final String message;
}
