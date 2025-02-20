package com.potatocountry.potatocountry.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.potatocountry.potatocountry.data.entitiy.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

	List<Image> findByIdIn(List<Long> imageIds);


}
