package ui;

public class UiRunner {
    MainMenuView mainMenuView;
    CategoriesMenuView categoriesMenuView;
    DifficultyMenuView difficultyMenuView;
    HangmanView hangmanView;

    public UiRunner() {
        mainMenuView = new MainMenuView();
        categoriesMenuView = new CategoriesMenuView();
        difficultyMenuView = new DifficultyMenuView();
        hangmanView = new HangmanView();
    }

    public void run() {
        while (true) {
            mainMenuView.show();
            categoriesMenuView.show();
            difficultyMenuView.show();
            hangmanView.show();
        }
    }
}
