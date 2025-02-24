package com.potatocountry.potatocountry.domain.image.dto;

import com.potatocountry.potatocountry.data.entitiy.Image;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "이미지 업로드 응답 DTO")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageResDto {

	@Schema(description = "이미지 고유 아이디", example = "1")
	private Long id;

	@Schema(description = "이미지 이름", example = "example.jpg")
	private String imageName;

	@Builder
	public ImageResDto(Long id, String imageName) {
		this.id = id;
		this.imageName = imageName;
	}

	public static ImageResDto toDto(Image image) {
		return builder()
			.id(image.getId())
			.imageName(image.getImageName())
			.build();
	}

}
