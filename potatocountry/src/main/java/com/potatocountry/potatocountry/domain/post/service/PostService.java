package com.potatocountry.potatocountry.domain.post.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.potatocountry.potatocountry.data.entitiy.Image;
import com.potatocountry.potatocountry.data.entitiy.ImageCollection;
import com.potatocountry.potatocountry.data.entitiy.Post;
import com.potatocountry.potatocountry.data.entitiy.User;
import com.potatocountry.potatocountry.data.repository.ImageCollectionRepository;
import com.potatocountry.potatocountry.data.repository.ImageRepository;
import com.potatocountry.potatocountry.data.repository.PostRepository;
import com.potatocountry.potatocountry.data.repository.UserRepository;
import com.potatocountry.potatocountry.domain.post.dto.request.PostReqDto;
import com.potatocountry.potatocountry.domain.post.dto.response.PostResDto;
import com.potatocountry.potatocountry.global.error.CustomError;
import com.potatocountry.potatocountry.global.error.CustomException;
import com.potatocountry.potatocountry.global.security.dto.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final ImageRepository imageRepository;
	private final ImageCollectionRepository imageCollectionRepository;

	@Transactional
	public PostResDto postCreate(CustomUserDetails customUserDetails, PostReqDto postReqDto) {
		User user = userRepository.getByAuthUserId(customUserDetails.getId());

		// imageCollection mapping하기
		ImageCollection imageCollection = new ImageCollection();
		imageCollectionRepository.save(imageCollection);
		List<Long> imageIds = postReqDto.getImageIds();
		List<Image> imageLists = imageRepository.findByIdIn(imageIds);
		for (Image image : imageLists) {
			image.mappingImageCollection(imageCollection);
		}
		Post post = postReqDto.toEntity(user, postReqDto, imageCollection);
		postRepository.save(post);
		return PostResDto.toDto(post);
	}

	@Transactional
	public PostResDto postUpdate(CustomUserDetails customUserDetails, PostReqDto postReqDto, Long id) {
		User user = userRepository.getByAuthUserId(customUserDetails.getId());
		Post post = postRepository.findById(id).orElseThrow(() -> new CustomException(CustomError.POST_NOT_FOUND));

		validAuthor(user, post);
		post.updatePost(postReqDto);
		return PostResDto.toDto(post);
	}

	public void validAuthor(User user, Post post) {
		User checkUser = post.getUser();
		if (checkUser == null || !checkUser.equals(user)) {
			throw new CustomException(CustomError.USER_NOT_MATCH);
		}
	}
}
