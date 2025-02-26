package com.potatocountry.potatocountry.domain.post.dto.request;

import java.util.List;

import com.potatocountry.potatocountry.data.entitiy.type.PostCategory;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "게시판 수정 요청 DTO")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostUpdateReqDto {
	private PostCategory category;

	private List<Long> imageIds;

	private String title;

	private String content;
}
