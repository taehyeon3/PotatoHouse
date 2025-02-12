package com.potatocountry.potatocountry.domain.auth.dto.response;

import com.potatocountry.potatocountry.data.entitiy.User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JoinResDto {
	private Long id;

	private String loginId;

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
