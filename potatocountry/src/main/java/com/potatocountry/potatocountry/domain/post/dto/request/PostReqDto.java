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

@Schema(description = "게시판 생성 DTO")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostReqDto {

	private PostCategory category;

	private List<Long> imageIds;

	private String title;

	private String content;


	public Post toEntity(User user, PostReqDto postReqDto, ImageCollection imageCollection) {
		return Post.builder()
			.category(postReqDto.getCategory())
			.title(postReqDto.getTitle())
			.content(postReqDto.getContent())
			.user(user)
			.imageCollection(imageCollection)
			.build();
	}
}
