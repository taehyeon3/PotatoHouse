package com.potatocountry.potatocountry.domain.post.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.potatocountry.potatocountry.data.entitiy.Comment;
import com.potatocountry.potatocountry.data.entitiy.Image;
import com.potatocountry.potatocountry.data.entitiy.ImageCollection;
import com.potatocountry.potatocountry.data.entitiy.Post;
import com.potatocountry.potatocountry.data.entitiy.User;
import com.potatocountry.potatocountry.data.repository.CommentRepository;
import com.potatocountry.potatocountry.data.repository.ImageCollectionRepository;
import com.potatocountry.potatocountry.data.repository.ImageRepository;
import com.potatocountry.potatocountry.data.repository.PostRepository;
import com.potatocountry.potatocountry.data.repository.UserRepository;
import com.potatocountry.potatocountry.domain.post.dto.request.PostCreateReqDto;
import com.potatocountry.potatocountry.domain.post.dto.request.PostUpdateReqDto;
import com.potatocountry.potatocountry.domain.post.dto.response.PostCreateResDto;
import com.potatocountry.potatocountry.domain.post.dto.response.PostInfoResDto;
import com.potatocountry.potatocountry.domain.post.dto.response.PostUpdateResDto;
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
	private final CommentRepository commentRepository;

	@Transactional
	public PostCreateResDto postCreate(CustomUserDetails customUserDetails, PostCreateReqDto postCreateReqDto) {
		User user = userRepository.getByAuthUserId(customUserDetails.getId());

		// imageCollection mapping하기
		ImageCollection imageCollection = new ImageCollection();
		imageCollectionRepository.save(imageCollection);
		List<Long> imageIds = postCreateReqDto.getImageIds();
		List<Image> imageLists = imageRepository.findByIdIn(imageIds);
		for (Image image : imageLists) {
			image.mappingImageCollection(imageCollection);
		}
		Post post = PostCreateReqDto.toEntity(user, postCreateReqDto, imageCollection);
		postRepository.save(post);
		return PostCreateResDto.toDto(post);
	}

	@Transactional
	public PostUpdateResDto postUpdate(CustomUserDetails customUserDetails, PostUpdateReqDto postUpdateReqDto,
		Long id) {
		User user = userRepository.getByAuthUserId(customUserDetails.getId());
		Post post = postRepository.findById(id).orElseThrow(() -> new CustomException(CustomError.POST_NOT_FOUND));

		validAuthor(user, post);
		post.update(postUpdateReqDto.getCategory(), postUpdateReqDto.getTitle(), postUpdateReqDto.getContent());
		return PostUpdateResDto.toDto(post);
	}

	@Transactional
	public void postDelete(CustomUserDetails customUserDetails, Long id) {
		User user = userRepository.getByAuthUserId(customUserDetails.getId());
		Post post = postRepository.findById(id).orElseThrow(() -> new CustomException(CustomError.POST_NOT_FOUND));

		validAuthor(user, post);
		post.delete();
	}

	public PostInfoResDto postGet(Long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new CustomException(CustomError.POST_NOT_FOUND));
		List<Comment> comments = commentRepository.findByPostId(post.getId());
		return PostInfoResDto.toDto(post, comments);
	}

	public void validAuthor(User user, Post post) {
		User checkUser = post.getUser();
		if (checkUser == null || !checkUser.equals(user)) {
			throw new CustomException(CustomError.USER_NOT_MATCH);
		}
	}
}
