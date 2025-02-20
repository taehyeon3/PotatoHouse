package com.potatocountry.potatocountry.data.entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "imageCollection_id")
	private ImageCollection imageCollection;

	@Lob
	private byte[] imageData;

	private String imageName;

	private boolean status;

	@Builder
	public Image(byte[] imageData, String imageName) {
		this.imageData = imageData;
		this.imageName = imageName;
		this.status = true;
	}

	public void mappingImageCollection(ImageCollection imageCollection) {
		this.imageCollection = imageCollection;
	}
}
