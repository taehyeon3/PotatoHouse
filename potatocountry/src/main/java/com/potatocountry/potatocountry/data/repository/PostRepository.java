package com.potatocountry.potatocountry.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.potatocountry.potatocountry.data.entitiy.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
