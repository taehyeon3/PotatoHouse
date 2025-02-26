package com.potatocountry.potatocountry.domain.post.dto.request;

import java.util.List;

import com.potatocountry.potatocountry.data.entitiy.ImageCollection;
import com.potatocountry.potatocountry.data.entitiy.Post;
import com.potatocountry.potatocountry.data.entitiy.User;
import com.potatocountry.potatocountry.data.entitiy.type.PostCategory;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "게시판 생성 요청 DTO")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCreateReqDto {
	private PostCategory category;

	private List<Long> imageIds;

	private String title;

	private String content;

	public static Post toEntity(User user, PostCreateReqDto postCreateReqDto, ImageCollection imageCollection) {
		return Post.builder()
			.category(postCreateReqDto.getCategory())
			.title(postCreateReqDto.getTitle())
			.content(postCreateReqDto.getContent())
			.user(user)
			.imageCollection(imageCollection)
			.build();
	}
}
