package com.potatocountry.potatocountry.domain.comment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "댓글 생성 요청 DTO")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentCreateReqDto {
	@Schema(description = "게시판 고유 아이디", example = "1")
	@NotNull
	private Long postId;

	@Schema(description = "부모 댓글 고유 아이디", example = "2")
	private Long parentId;

	@Schema(description = "댓글 내용", example = "안녕하세요.")
	@NotBlank
	private String content;
}
