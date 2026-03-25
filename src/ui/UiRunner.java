package ui;

public class UiRunner {
    MainMenuView mainMenuView = new MainMenuView();
    CategoriesMenuView categoriesMenuView = new CategoriesMenuView();
    DifficultyMenuView difficultyMenuView = new DifficultyMenuView();
    HangmanView hangmanView = new HangmanView();

    public UiRunner() {
        while (true) {
            mainMenuView.show();
            categoriesMenuView.show();
            difficultyMenuView.show();
            hangmanView.show();
        }
    }
}
