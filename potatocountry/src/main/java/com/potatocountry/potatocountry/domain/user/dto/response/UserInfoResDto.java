package com.potatocountry.potatocountry.domain.user.dto.response;

import com.potatocountry.potatocountry.data.entitiy.AuthUser;
import com.potatocountry.potatocountry.data.entitiy.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "유저 정보 응답 DTO")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfoResDto {

	@Schema(description = "유저 고유 아이디", example = "1")
	private Long id;

	@Schema(description = "유저 이름", example = "김태현")
	private String username;

	@Schema(description = "유저 닉네임", example = "고죠태현")
	private String nickname;


	@Builder
	private UserInfoResDto(Long id, String username, String nickname)
	{
		this.id = id;
		this.username = username;
		this.nickname = nickname;
	}

	public static UserInfoResDto toDto(User user) {
		return UserInfoResDto.builder()
			.id(user.getId())
			.username(user.getUsername())
			.nickname(user.getNickname())
			.build();
	}
}
