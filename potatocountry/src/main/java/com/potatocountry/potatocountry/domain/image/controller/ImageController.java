package com.potatocountry.potatocountry.domain.image.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.potatocountry.potatocountry.data.entitiy.Image;
import com.potatocountry.potatocountry.domain.image.dto.ImageResDto;
import com.potatocountry.potatocountry.domain.image.service.ImageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

	private final ImageService imageService;

	@PostMapping
	@Operation(security = {@SecurityRequirement(name = "bearerAuth")})
	public ResponseEntity<ImageResDto> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
		Image image = imageService.imageUpload(file);
		return ResponseEntity.status(HttpStatus.CREATED).body(ImageResDto.toDto(image));
	}
}
