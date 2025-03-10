package com.potatocountry.potatocountry.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.potatocountry.potatocountry.data.entitiy.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByPostId(Long postId);
}
