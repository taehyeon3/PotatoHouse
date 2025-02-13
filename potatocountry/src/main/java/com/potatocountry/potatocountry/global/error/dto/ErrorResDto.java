package com.potatocountry.potatocountry.global.error.dto;

import org.springframework.http.ResponseEntity;

import com.potatocountry.potatocountry.global.error.CustomException;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResDto {
	@Schema(description = "에러 메시지", example = "에러가 발생했습니다.")
	private final String message;

	public static ResponseEntity<ErrorResDto> toResponseEntity(final CustomException exception) {
		return ResponseEntity.status(exception.getError().getStatus())
			.body(new ErrorResDto(exception.getError().getMessage()));
	}
}
