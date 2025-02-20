package com.potatocountry.potatocountry.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.potatocountry.potatocountry.data.entitiy.ImageCollection;

public interface ImageCollectionRepository extends JpaRepository<ImageCollection, Long> {
}
