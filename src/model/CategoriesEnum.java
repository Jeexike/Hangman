package model;

public enum CategoriesEnum {
    SPORT("Спорт"),
    FOOD("Еда"),
    ANIMALS("Животные"),
    WEATHER("Погода");

    private final String displayName;

    CategoriesEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
