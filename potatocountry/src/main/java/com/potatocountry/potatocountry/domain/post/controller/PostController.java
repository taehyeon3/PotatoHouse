package com.potatocountry.potatocountry.domain.post.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.potatocountry.potatocountry.domain.post.dto.request.PostCreateReqDto;
import com.potatocountry.potatocountry.domain.post.dto.request.PostUpdateReqDto;
import com.potatocountry.potatocountry.domain.post.dto.response.PostCreateResDto;
import com.potatocountry.potatocountry.domain.post.dto.response.PostInfoResDto;
import com.potatocountry.potatocountry.domain.post.dto.response.PostUpdateResDto;
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
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = PostCreateResDto.class)
			)),
	})
	@PostMapping
	public ResponseEntity<PostCreateResDto> createPost(@AuthenticationPrincipal CustomUserDetails customUserDetails,
		@RequestBody @Valid PostCreateReqDto postCreateReqDto) {
		PostCreateResDto postCreateResDto = postService.postCreate(customUserDetails, postCreateReqDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(postCreateResDto);
	}

	@Operation(
		summary = "게시판 수정 API",
		description = "게시판을 수정합니다.",
		security = {@SecurityRequirement(name = "bearerAuth")}
	)
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "200 성공",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = PostUpdateResDto.class)
			)),
		@ApiResponse(responseCode = "PT100", description = "404 존재하지 않은 게시판 입니다.",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResDto.class))),
	})
	@PutMapping("/{id}")
	public ResponseEntity<PostUpdateResDto> updatePost(@AuthenticationPrincipal CustomUserDetails customUserDetails,
		@RequestBody @Valid PostUpdateReqDto postUpdateReqDto, @PathVariable @Valid Long id) {
		PostUpdateResDto postUpdateResDto = postService.postUpdate(customUserDetails, postUpdateReqDto, id);
		return ResponseEntity.status(HttpStatus.OK).body(postUpdateResDto);
	}

	@Operation(
		summary = "게시판 삭제 API",
		description = "게시판을 삭제합니다.",
		security = {@SecurityRequirement(name = "bearerAuth")}
	)
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "204 성공"),
		@ApiResponse(responseCode = "PT100", description = "404 존재하지 않은 게시판 입니다.",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResDto.class))),
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePost(@AuthenticationPrincipal CustomUserDetails customUserDetails,
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
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = PostInfoResDto.class)
			)),
		@ApiResponse(responseCode = "PT100", description = "404 존재하지 않은 게시판 입니다.",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResDto.class))),
	})
	@GetMapping("/{id}")
	public ResponseEntity<PostInfoResDto> getPost(@PathVariable @Valid Long id) {
		PostInfoResDto postInfoResDto = postService.postGet(id);
		return ResponseEntity.status(HttpStatus.OK).body(postInfoResDto);
	}
}
