package com.potatocountry.potatocountry.data.entitiy;

import com.potatocountry.potatocountry.data.entitiy.type.PostCategory;
import com.potatocountry.potatocountry.data.entitiy.type.PostStatus;
import com.potatocountry.potatocountry.domain.post.dto.request.PostReqDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, columnDefinition = "VARCHAR(100)")
	private PostCategory category;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "image_collection_id")
	private ImageCollection imageCollection;

	@Column(nullable = false)
	private Long viewCount;

	@Column(nullable = false)
	private Long likeCount;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, columnDefinition = "VARCHAR(20)")
	private PostStatus status;

	@Builder
	public Post(PostCategory category, String title, String content, User user, ImageCollection imageCollection) {
		this.category = category;
		this.title = title;
		this.content = content;
		this.user = user;
		this.viewCount = 0L;
		this.likeCount = 0L;
		this.imageCollection = imageCollection;
		this.status = PostStatus.SELLING;
	}

	public void updatePost(PostReqDto postReqDto) {
		if (postReqDto.getCategory() != null) {
			this.category = postReqDto.getCategory();
		}
		if (postReqDto.getTitle() != null) {
			this.title = postReqDto.getTitle();
		}
		if (postReqDto.getContent() != null) {
			this.content = postReqDto.getContent();
		}
	}

	public void deletePost() {
		this.status = PostStatus.DELETED;
	}
}
