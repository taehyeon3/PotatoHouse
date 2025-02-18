package com.potatocountry.potatocountry.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.potatocountry.potatocountry.data.entitiy.AuthUser;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
	boolean existsByUsername(String username);

	Optional<AuthUser> findByUsername(String username);
}
