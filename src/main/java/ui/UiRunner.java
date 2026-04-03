package ui;

import lombok.extern.slf4j.Slf4j;

@Slf4j public class UiRunner {
  MainMenuView mainMenuView;
  CategoriesMenuView categoriesMenuView;
  DifficultyMenuView difficultyMenuView;
  HangmanView hangmanView;

  public UiRunner() {
    log.info("Initializing UI components");
    mainMenuView = new MainMenuView();
    categoriesMenuView = new CategoriesMenuView();
    difficultyMenuView = new DifficultyMenuView();
    hangmanView = new HangmanView();
    log.info("UI components initialized");
  }

  public void run() {
    log.info("Starting game loop");
    try {
      while (true) {
        mainMenuView.show();
        categoriesMenuView.show();
        difficultyMenuView.show();
        hangmanView.show();
      }
    } catch (Exception e) {
      log.error("Critical error in game loop", e);
    } finally {
      log.info("Game loop terminated");
    }
  }
}
