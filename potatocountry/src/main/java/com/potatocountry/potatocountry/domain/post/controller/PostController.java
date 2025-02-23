package com.potatocountry.potatocountry.domain.post.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.potatocountry.potatocountry.domain.post.dto.request.PostReqDto;
import com.potatocountry.potatocountry.domain.post.dto.response.PostResDto;
import com.potatocountry.potatocountry.domain.post.service.PostService;
import com.potatocountry.potatocountry.global.security.dto.CustomUserDetails;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "게시판", description = "게시판 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
	private final PostService postService;

	@PostMapping
	@Operation(security = {@SecurityRequirement(name = "bearerAuth")})
	public ResponseEntity<PostResDto> createPost(@AuthenticationPrincipal CustomUserDetails customUserDetails,
		@RequestBody @Valid PostReqDto postReqDto) {
		PostResDto postResDto = postService.postCreate(customUserDetails, postReqDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(postResDto);
	}

	@PutMapping("/{id}")
	@Operation(security = {@SecurityRequirement(name = "bearerAuth")})
	public ResponseEntity<PostResDto> updatePost(@AuthenticationPrincipal CustomUserDetails customUserDetails,
		@RequestBody @Valid PostReqDto postReqDto, @PathVariable @Valid Long id) {
		PostResDto postResDto = postService.postUpdate(customUserDetails, postReqDto, id);
		return ResponseEntity.status(HttpStatus.OK).body(postResDto);
	}

	@PatchMapping("/{id}")
	@Operation(security = {@SecurityRequirement(name = "bearerAuth")})
	public ResponseEntity<PostResDto> deletePost(@AuthenticationPrincipal CustomUserDetails customUserDetails,
		@PathVariable @Valid Long id) {
		postService.postDelete(customUserDetails, id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@GetMapping("/{id}")
	@Operation(security = {@SecurityRequirement(name = "bearerAuth")})
	public ResponseEntity<PostResDto> getPost(@PathVariable @Valid Long id) {
		PostResDto postResDto = postService.postGet(id);
		return ResponseEntity.status(HttpStatus.OK).body(postResDto);
	}
}
