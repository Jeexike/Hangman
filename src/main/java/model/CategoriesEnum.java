package model;

import lombok.Getter;

@Getter
public enum CategoriesEnum {
	SPORT("Спорт"), FOOD("Еда"), ANIMALS("Животные"), COUNTRIES("Страны"), PROFESSIONS("Профессии"), TECHNOLOGY(
			"Технологии"), NATURE("Природа");

	private final String displayName;

	CategoriesEnum(String displayName) {
		this.displayName = displayName;
	}
}
