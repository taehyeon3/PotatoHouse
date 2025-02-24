package com.potatocountry.potatocountry.domain.comment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.potatocountry.potatocountry.data.entitiy.Comment;
import com.potatocountry.potatocountry.data.entitiy.Post;
import com.potatocountry.potatocountry.data.entitiy.User;
import com.potatocountry.potatocountry.data.repository.CommentRepository;
import com.potatocountry.potatocountry.data.repository.PostRepository;
import com.potatocountry.potatocountry.data.repository.UserRepository;
import com.potatocountry.potatocountry.domain.comment.dto.request.CommentCreateReqDto;
import com.potatocountry.potatocountry.domain.comment.dto.response.CommentCreateResDto;
import com.potatocountry.potatocountry.global.error.CustomError;
import com.potatocountry.potatocountry.global.error.CustomException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final CommentRepository commentRepository;

	@Transactional
	public CommentCreateResDto createComment(CommentCreateReqDto commentCreateReqDto, Long authUserId) {
		validateParentId(commentCreateReqDto);
		Post post = postRepository.findById(commentCreateReqDto.getPostId())
			.orElseThrow(() -> new CustomException(CustomError.POST_NOT_FOUND));
		User user = userRepository.getByAuthUserId(authUserId);
		Comment comment = new Comment(user, post, commentCreateReqDto.getParentId(), commentCreateReqDto.getContent());
		commentRepository.save(comment);
		return CommentCreateResDto.toDto(comment);
	}

	private void validateParentId(CommentCreateReqDto commentCreateReqDto) {
		if (commentCreateReqDto.getParentId() != null
			&& !commentRepository.existsById(commentCreateReqDto.getParentId())) {
			throw new CustomException(CustomError.COMMENT_NOT_FOUND);
		}
	}
}
