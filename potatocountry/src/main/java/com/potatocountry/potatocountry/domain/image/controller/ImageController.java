package com.potatocountry.potatocountry.domain.image.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.potatocountry.potatocountry.data.entitiy.Image;
import com.potatocountry.potatocountry.domain.image.dto.ImageResDto;
import com.potatocountry.potatocountry.domain.image.service.ImageService;
import com.potatocountry.potatocountry.global.error.dto.ErrorResDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "이미지", description = "이미지 관련 API")
@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

	private final ImageService imageService;

	@Operation(
		summary = "이미지 저장 API",
		description = "새로운 이미지를 저장합니다.",
		security = {@SecurityRequirement(name = "bearerAuth")})
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "201 성공",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = ImageResDto.class))),
	})
	@PostMapping
	public ResponseEntity<ImageResDto> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
		Image image = imageService.imageUpload(file);
		return ResponseEntity.status(HttpStatus.CREATED).body(ImageResDto.toDto(image));
	}

	@Operation(
		summary = "이미지 수정 API",
		description = "이미지를 수정합니다.",
		security = {@SecurityRequirement(name = "bearerAuth")})
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "200 성공",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = ImageResDto.class))),
		@ApiResponse(responseCode = "IM100", description = "404 존재하지 않는 이미지 입니다.",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResDto.class))),
	})
	@PutMapping("/{id}")
	public ResponseEntity<ImageResDto> updateImage(@RequestParam("file") MultipartFile file,
		@PathVariable @Valid Long id) throws IOException {
		Image image = imageService.imageUpdate(file, id);
		return ResponseEntity.status(HttpStatus.OK).body(ImageResDto.toDto(image));
	}

	@Operation(
		summary = "이미지 삭제 API",
		description = "이미지를 삭제합니다.",
		security = {@SecurityRequirement(name = "bearerAuth")})
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "204 성공",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = ImageResDto.class))),
		@ApiResponse(responseCode = "IM100", description = "404 존재하지 않는 이미지 입니다.",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResDto.class))),
	})
	@PatchMapping("/{id}")
	public ResponseEntity<Void> deleteImage(@PathVariable @Valid Long id) {
		imageService.imageDelete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
