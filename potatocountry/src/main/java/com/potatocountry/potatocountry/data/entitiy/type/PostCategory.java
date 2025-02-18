package com.potatocountry.potatocountry.data.entitiy.type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public enum PostCategory {

	ROOT("카테고리", null),

	//ROOT
	FASHION("패션의류/잡화", ROOT),

	//ROOT -> FASHION
	FASHION_MEN("남성", FASHION),

	//ROOT -> FASHION -> FASHION_MEN
	MEN_T_SHIRT("티셔츠", FASHION_MEN),
	MEN_SWEATSHIRT_HOOD("스웻셔츠/후드", FASHION_MEN),
	MEN_SHIRT("셔츠", FASHION_MEN),
	MEN_SUIT("정장", FASHION_MEN),
	MEN_PANTS("바지", FASHION_MEN),

	//ROOT -> FASHION
	FASHION_WOMEN("여성", FASHION),

	//ROOT -> FASHION -> FASHION_WOMEN
	WOMEN_T_SHIRT("티셔츠", FASHION_WOMEN),
	WOMEN_BLOUSE("블라우스/셔츠", FASHION_WOMEN),
	WOMEN_SWEATSHIRT_HOOD("스웻셔츠/후드", FASHION_WOMEN),
	WOMEN_SUIT("정장", FASHION_WOMEN),
	WOMEN_ONE_PIECE("원피스", FASHION_WOMEN),
	WOMEN_SKIRT("치마", FASHION_WOMEN),
	WOMEN_PANTS("바지", FASHION_WOMEN),

	//ROOT -> FASHION
	UNISEX("남녀공용", FASHION),

	//ROOT -> FASHION -> UNISEX
	UNISEX_T_SHIRT("티셔츠", UNISEX),
	UNISEX_PANTS("바지", UNISEX),

	//ROOT -> FASHION
	BAG_ACC("가방/잡화", FASHION),

	//ROOT -> FASHION -> BAG_ACC
	BAG("가방", BAG_ACC),

	//ROOT -> FASHION -> BAG_ACC -> BAG
	BACKPACK("백팩", BAG),
	CROSS_BAG("크로스백", BAG),
	SHOULDER_BAG("숄더백", BAG),
	MINI_BAG("미니백", BAG),
	ECO_BAG("캔버스/에코백", BAG),

	//ROOT -> FASHION -> BAG_ACC
	WALLET_BELT("지갑/벨트", BAG_ACC),

	//ROOT -> FASHION -> BAG_ACC -> WALLET_BELT
	WALLET("지갑", WALLET_BELT),
	BELT("벨트", WALLET_BELT),

	//ROOT -> FASHION
	SHOES("신발", FASHION),

	//ROOT -> FASHION -> SHOES
	SNEAKERS("운동화/스니커즈", SHOES),
	FLAT_SHOES("단화/플랫", SHOES),
	HEEL("힐", SHOES),
	BOOTS("워커/부츠", SHOES),
	SLIPPER("슬리퍼", SHOES),

	//ROOT -> FASHION
	FASHION_CHILDREN("아동", FASHION),

	//ROOT -> FASHION -> FASHION_CHILDREN
	GIRL_FASHION("여아", FASHION_CHILDREN),
	BOY_FASHION("남아", FASHION_CHILDREN),

	//ROOT
	FOOD("식품", ROOT),

	//ROOT -> FOOD
	INSTANT("가공/즉석식품", FOOD),
	BEVERAGE("생수/음료", FOOD),
	FRESH("신선식품", FOOD),
	MEAT_EGG("축산/계란", FOOD),
	RICE("쌀/잡곡", FOOD),

	//ROOT
	DIGITAL("가전/디지털", ROOT),

	//ROOT -> DIGITAL
	VIDEO("TV/영상가전", DIGITAL),

	//ROOT -> DIGITAL -> VIDEO
	TV("TV", VIDEO),
	PROJECTOR("프로젝터/스크린", VIDEO),

	//ROOT -> DIGITAL
	COMPUTER("컴퓨터/게임/SW", DIGITAL);

	private final String title;

	private final PostCategory parentCategory;

	private final List<PostCategory> childCategories;

	PostCategory(String title, PostCategory parentCategory) {
		this.childCategories = new ArrayList<>();
		this.title = title;
		this.parentCategory = parentCategory;
		if (Objects.nonNull(parentCategory)) {
			parentCategory.childCategories.add(this);
		}
	}

	public String getTitle() {
		return title;
	}

	// 부모카테고리 Getter
	public Optional<PostCategory> getParentCategory() {
		return Optional.ofNullable(parentCategory);
	}

	// 자식카테고리 Getter
	public List<PostCategory> getChildCategories() {
		return Collections.unmodifiableList(childCategories);
	}

	// 마지막 카테고리(상품추가 가능)인지 반환
	public boolean isLeafCategory() {
		return childCategories.isEmpty();
	}

	// 마지막 카테고리(상품추가 가능)들 반환
	public List<PostCategory> getLeafCategories() {
		return Arrays.stream(PostCategory.values())
			.filter(category -> category.isLeafCategoryOf(this))
			.collect(Collectors.toList());
	}

	private boolean isLeafCategoryOf(PostCategory category) {
		return this.isLeafCategory() && category.contains(this);
	}

	public boolean contains(PostCategory category) {
		if (this.equals(category)) {
			return true;
		}
		return Objects.nonNull(category.parentCategory) && this.contains(category.parentCategory);
	}
}




