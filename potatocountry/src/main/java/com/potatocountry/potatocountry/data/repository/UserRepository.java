package com.potatocountry.potatocountry.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.potatocountry.potatocountry.data.entitiy.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByNickname(String nickName);
}
