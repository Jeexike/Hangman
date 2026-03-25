package model;

public enum Categories {
    SPORT("Спорт"),
    FOOD("Еда"),
    ANIMALS("Животные"),
    WEATHER("Погода");

    private final String displayName;

    Categories(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
