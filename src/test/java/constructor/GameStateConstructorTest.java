package constructor;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.CategoriesEnum;
import model.Difficulty;
import model.GameState;

@DisplayName("GameStateConstructor Tests ") class GameStateConstructorTest {

  @BeforeEach void setUp() {
    GameStateConstructor.setCategories(null);
    GameStateConstructor.setDifficulty(null);
    GameStateConstructor.getFileWordRepository().clearTestWord();
  }

  @AfterEach void tearDown() {
    GameStateConstructor.setCategories(null);
    GameStateConstructor.setDifficulty(null);
    GameStateConstructor.getFileWordRepository().clearTestWord();
  }

  @Test
  @DisplayName("Setting category saves value") void testSetCategories() {
    GameStateConstructor.setCategories(CategoriesEnum.ANIMALS);

    assertThat(GameStateConstructor.getCategories()).isEqualTo(CategoriesEnum.ANIMALS);
  }

  @Test
  @DisplayName("Setting difficulty saves value") void testSetDifficulty() {
    GameStateConstructor.setDifficulty(Difficulty.EASY);

    assertThat(GameStateConstructor.getDifficulty()).isEqualTo(Difficulty.EASY);
  }

  @Test
  @DisplayName("Game initializes when both parameters are set") void testSetCategoriesAndDifficulty_initializesGame() {
    GameStateConstructor.setCategories(CategoriesEnum.ANIMALS);
    GameStateConstructor.setDifficulty(Difficulty.EASY);
    assertThat(GameStateConstructor.getCategories()).isEqualTo(CategoriesEnum.ANIMALS);
    assertThat(GameStateConstructor.getDifficulty()).isEqualTo(Difficulty.EASY);
    assertThat(GameStateConstructor.getMaxLives()).isEqualTo(6);
    assertThat(GameStateConstructor.getHiddenWord()).isNotBlank();
  }

  @Test
  @DisplayName("InputCharToGameStatus returns RIGHT for correct letter") void
      testInputCharToGameStatus_correctLetter() {
    GameStateConstructor.setCategories(CategoriesEnum.ANIMALS);
    GameStateConstructor.setDifficulty(Difficulty.EASY);

    String hidden = GameStateConstructor.getHiddenWord();
    char knownLetter = hidden.charAt(0);

    var result = GameStateConstructor.InputCharToGameStatus(knownLetter);

    assertThat(result).isEqualTo(GameState.InputState.RIGHT);
  }

  @Test
  @DisplayName("InputCharToGameStatus returns WRONG for wrong letter") void testInputCharToGameStatus_wrongLetter() {
    GameStateConstructor.getFileWordRepository().setTestWord("машина");
    GameStateConstructor.setCategories(CategoriesEnum.ANIMALS);
    GameStateConstructor.setDifficulty(Difficulty.EASY);
    var result = GameStateConstructor.InputCharToGameStatus('л');
    assertThat(result).isEqualTo(GameState.InputState.WRONG);
  }

  @Test
  @DisplayName("getWrongLetters returns list of wrong letters") void testGetWrongLetters() {
    GameStateConstructor.getFileWordRepository().setTestWord("машина");
    GameStateConstructor.setCategories(CategoriesEnum.ANIMALS);
    GameStateConstructor.setDifficulty(Difficulty.EASY);
    GameStateConstructor.InputCharToGameStatus('л');
    GameStateConstructor.InputCharToGameStatus('д');

    assertThat(GameStateConstructor.getWrongLetters()).contains('л', 'д');
  }

  @Test
  @DisplayName("getVisibleWord returns word with underscores") void testGetVisibleWord() {
    GameStateConstructor.setCategories(CategoriesEnum.ANIMALS);
    GameStateConstructor.setDifficulty(Difficulty.EASY);

    String visible = GameStateConstructor.getVisibleWord();

    assertThat(visible).contains("_");
    assertThat(visible.split(" ")).hasSizeGreaterThan(0);
  }
}
