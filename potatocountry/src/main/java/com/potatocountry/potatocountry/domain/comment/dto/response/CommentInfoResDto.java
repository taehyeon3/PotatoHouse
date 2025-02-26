package com.potatocountry.potatocountry.domain.comment.dto.response;

import com.potatocountry.potatocountry.data.entitiy.Comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "댓글 정보 응답 DTO")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentInfoResDto {
	@Schema(description = "댓글 고유 아이디", example = "1")
	private Long id;

	@Schema(description = "부모 댓글 고유 아이디", example = "2")
	private Long parentId;

	@Schema(description = "유저 고유 아이디", example = "1")
	private Long userId;

	@Schema(description = "게시판 고유 아이디", example = "1")
	private Long postId;

	@Schema(description = "댓글 내용", example = "안녕하세요.")
	private String content;

	@Builder
	private CommentInfoResDto(Long id, Long parentId, Long userId, Long postId, String content) {
		this.id = id;
		this.parentId = parentId;
		this.userId = userId;
		this.postId = postId;
		this.content = content;
	}

	public static CommentInfoResDto toDto(Comment comment) {
		return builder()
			.id(comment.getId())
			.parentId(comment.getParentId())
			.userId(comment.getUser().getId())
			.postId(comment.getPost().getId())
			.content(comment.getContent())
			.build();
	}
}
