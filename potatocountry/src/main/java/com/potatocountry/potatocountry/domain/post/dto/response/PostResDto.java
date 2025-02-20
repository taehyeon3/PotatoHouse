package com.potatocountry.potatocountry.domain.post.dto.response;

import com.potatocountry.potatocountry.data.entitiy.Post;
import com.potatocountry.potatocountry.data.entitiy.type.PostCategory;
import com.potatocountry.potatocountry.data.entitiy.type.PostStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "게시판 응답 DTO")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostResDto {

	private Long id;

	private PostCategory category;

	private String title;

	private String content;

	private String userNickname;

	private Long imageCollectionId;

	private Long viewCount;

	private Long likeCount;

	private PostStatus status;

	@Builder
	public PostResDto(Long id, PostCategory category, String title, String content, String userNickname,
		Long imageCollectionId, Long viewCount, Long likeCount, PostStatus status) {
		this.id = id;
		this.category = category;
		this.title = title;
		this.content = content;
		this.userNickname = userNickname;
		this.imageCollectionId = imageCollectionId;
		this.viewCount = viewCount;
		this.likeCount = likeCount;
		this.status = status;
	}

	public static PostResDto toDto(Post post) {
		return PostResDto.builder()
			.id(post.getId())
			.category(post.getCategory())
			.title(post.getTitle())
			.content(post.getContent())
			.userNickname(post.getUser().getNickname())
			.imageCollectionId(post.getImageCollection().getId())
			.viewCount(post.getViewCount())
			.likeCount(post.getLikeCount())
			.status(post.getStatus())
			.build();
	}
}
