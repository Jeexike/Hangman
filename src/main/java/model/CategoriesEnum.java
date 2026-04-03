package model;

import lombok.Getter;

@Getter public enum CategoriesEnum {
  SPORT("Спорт"),
  FOOD("Еда"),
  ANIMALS("Животные"),
  WEATHER("Погода");

  private final String displayName;

  CategoriesEnum(String displayName) {
    this.displayName = displayName;
  }
}
