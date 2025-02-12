package com.potatocountry.potatocountry.global.error.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.potatocountry.potatocountry.global.error.CustomException;
import com.potatocountry.potatocountry.global.error.dto.ErrorResDto;

@ControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(CustomException.class)
	protected ResponseEntity<ErrorResDto> handleCustomException(final CustomException exception) {
		return ErrorResDto.toResponseEntity(exception);
	}
}
