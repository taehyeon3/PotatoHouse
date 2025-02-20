package com.potatocountry.potatocountry.domain.image.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.potatocountry.potatocountry.data.entitiy.Image;
import com.potatocountry.potatocountry.data.repository.ImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ImageService {

	private final ImageRepository imageRepository;

	@Transactional
	public Image imageUpload(MultipartFile file) throws IOException {
		Image image = Image.builder().imageData(file.getBytes()).imageName(file.getOriginalFilename()).build();
		return imageRepository.save(image);
	}

}
