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

@Tag(name = "게시판", description = "게시판 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
	private final PostService postService;

	@Operation(
		summary = "게시판 생성 API",
		description = "새로운 게시판을 생성합니다.",
		security = {@SecurityRequirement(name = "bearerAuth")}
	)
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "201 성공",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostResDto.class))),
	})
	@PostMapping
	public ResponseEntity<PostResDto> createPost(@AuthenticationPrincipal CustomUserDetails customUserDetails,
		@RequestBody @Valid PostReqDto postReqDto) {
		PostResDto postResDto = postService.postCreate(customUserDetails, postReqDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(postResDto);
	}

	@Operation(
		summary = "게시판 수정 API",
		description = "게시판을 수정합니다.",
		security = {@SecurityRequirement(name = "bearerAuth")}
	)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "200 성공",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostResDto.class))),
		@ApiResponse(responseCode = "PT100", description = "404 존재하지 않은 게시판 입니다.",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResDto.class))),
	})
	@PutMapping("/{id}")
	public ResponseEntity<PostResDto> updatePost(@AuthenticationPrincipal CustomUserDetails customUserDetails,
		@RequestBody @Valid PostReqDto postReqDto, @PathVariable @Valid Long id) {
		PostResDto postResDto = postService.postUpdate(customUserDetails, postReqDto, id);
		return ResponseEntity.status(HttpStatus.OK).body(postResDto);
	}

	@Operation(
		summary = "게시판 삭제 API",
		description = "게시판을 삭제합니다.",
		security = {@SecurityRequirement(name = "bearerAuth")}
	)
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "204 성공",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostResDto.class))),
		@ApiResponse(responseCode = "PT100", description = "404 존재하지 않은 게시판 입니다.",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResDto.class))),
	})
	@PatchMapping("/{id}")
	public ResponseEntity<PostResDto> deletePost(@AuthenticationPrincipal CustomUserDetails customUserDetails,
		@PathVariable @Valid Long id) {
		postService.postDelete(customUserDetails, id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@Operation(
		summary = "게시판 상세 내용 API",
		description = "게시판의 내용을 가져옵니다.",
		security = {@SecurityRequirement(name = "bearerAuth")}
	)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "200 성공",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = PostResDto.class))),
		@ApiResponse(responseCode = "PT100", description = "404 존재하지 않은 게시판 입니다.",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResDto.class))),
	})
	@GetMapping("/{id}")
	public ResponseEntity<PostResDto> getPost(@PathVariable @Valid Long id) {
		PostResDto postResDto = postService.postGet(id);
		return ResponseEntity.status(HttpStatus.OK).body(postResDto);
	}
}
