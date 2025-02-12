package com.potatocountry.potatocountry.global.error.dto;

import org.springframework.http.ResponseEntity;

import com.potatocountry.potatocountry.global.error.CustomException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResDto {
	private final String message;

	public static ResponseEntity<ErrorResDto> toResponseEntity(final CustomException exception) {
		return ResponseEntity.status(exception.getError().getStatus())
			.body(new ErrorResDto(exception.getError().getMessage()));
	}
}
