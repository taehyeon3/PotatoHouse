package com.potatocountry.potatocountry.domain.auth.dto.response;

import com.potatocountry.potatocountry.data.entitiy.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "회원가입 응답 DTO")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JoinResDto {
	@Schema(description = "회원 고유 번호", example = "1")
	private Long id;

	@Schema(description = "로그인 아이디", example = "user")
	private String loginId;

	@Schema(description = "닉네임", example = "배고픈감자")
	private String nickname;

	@Builder
	private JoinResDto(Long id, String loginId, String nickname) {
		this.id = id;
		this.loginId = loginId;
		this.nickname = nickname;
	}

	public static JoinResDto toDto(User user) {
		return builder()
			.id(user.getId())
			.loginId(user.getLoginId())
			.nickname(user.getNickname())
			.build();
	}
}
