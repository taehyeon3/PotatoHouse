package com.potatocountry.potatocountry.domain.comment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.potatocountry.potatocountry.domain.comment.dto.request.CommentCreateReqDto;
import com.potatocountry.potatocountry.domain.comment.dto.response.CommentCreateResDto;
import com.potatocountry.potatocountry.domain.comment.service.CommentService;
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

@Tag(name = "댓글", description = "댓글 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
	private final CommentService commentService;

	@Operation(
		summary = "댓글 생성 API",
		description = "새로운 댓글을 생성합니다.",
		security = {@SecurityRequirement(name = "bearerAuth")}
	)
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "201 성공",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = CommentCreateResDto.class)
			)),
		@ApiResponse(responseCode = "PT100", description = "404 존재하지 않은 게시판 입니다.",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResDto.class))),
		@ApiResponse(responseCode = "CM100", description = "404 부모 댓글이 존재하지 않습니다.",
			content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResDto.class))),
	})
	@PostMapping
	public ResponseEntity<CommentCreateResDto> commentCreate(
		@AuthenticationPrincipal CustomUserDetails customUserDetails,
		@RequestBody @Valid CommentCreateReqDto commentCreateReqDto) {
		CommentCreateResDto response = commentService.createComment(commentCreateReqDto, customUserDetails.getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
