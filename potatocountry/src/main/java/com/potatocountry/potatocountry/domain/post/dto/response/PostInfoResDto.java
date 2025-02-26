package com.potatocountry.potatocountry.domain.post.dto.response;

import java.util.List;

import com.potatocountry.potatocountry.data.entitiy.Comment;
import com.potatocountry.potatocountry.data.entitiy.Post;
import com.potatocountry.potatocountry.data.entitiy.type.PostCategory;
import com.potatocountry.potatocountry.data.entitiy.type.PostStatus;
import com.potatocountry.potatocountry.domain.comment.dto.response.CommentInfoResDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "게시판 정보 응답 DTO")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostInfoResDto {
	private Long id;

	private PostCategory category;

	private String title;

	private String content;

	private String userNickname;

	private Long imageCollectionId;

	private Long viewCount;

	private Long likeCount;

	private PostStatus status;

	private List<CommentInfoResDto> comments;

	@Builder
	private PostInfoResDto(Long id, PostCategory category, String title, String content, String userNickname,
		Long imageCollectionId, Long viewCount, Long likeCount, PostStatus status, List<CommentInfoResDto> comments) {
		this.id = id;
		this.category = category;
		this.title = title;
		this.content = content;
		this.userNickname = userNickname;
		this.imageCollectionId = imageCollectionId;
		this.viewCount = viewCount;
		this.likeCount = likeCount;
		this.status = status;
		this.comments = comments;
	}

	public static PostInfoResDto toDto(Post post, List<Comment> comments) {
		return builder()
			.id(post.getId())
			.category(post.getCategory())
			.title(post.getTitle())
			.content(post.getContent())
			.userNickname(post.getUser().getNickname())
			.imageCollectionId(post.getImageCollection().getId())
			.viewCount(post.getViewCount())
			.likeCount(post.getLikeCount())
			.status(post.getStatus())
			.comments(comments.stream().map(CommentInfoResDto::toDto).toList())
			.build();
	}
}
