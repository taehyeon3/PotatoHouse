package com.potatocountry.potatocountry.data.entitiy.type;

import lombok.Getter;

@Getter
public enum PostStatus {
	SELLING("판매중"),
	DEALING("거래중"),
	COMPLETE("완료"),
	DELETED("삭제");

	private final String value;

	PostStatus(String value) {
		this.value = value;
	}
}
