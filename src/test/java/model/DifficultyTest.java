package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Difficulty Enum Tests") class DifficultyTest {

  @Test
  @DisplayName("EASY difficulty has correct parameters") void testEasyDifficultyParameters() {
    Difficulty easy = Difficulty.EASY;

    assertThat(easy.getDisplayName()).isEqualTo("Легко");
    assertThat(easy.getMinWordLength()).isEqualTo(3);
    assertThat(easy.getMaxWordLength()).isEqualTo(5);
    assertThat(easy.getMaxLives()).isEqualTo(6);
  }

  @Test
  @DisplayName("NORMAL difficulty has correct parameters") void testNormalDifficultyParameters() {
    Difficulty normal = Difficulty.NORMAL;

    assertThat(normal.getDisplayName()).isEqualTo("Нормально");
    assertThat(normal.getMinWordLength()).isEqualTo(6);
    assertThat(normal.getMaxWordLength()).isEqualTo(8);
    assertThat(normal.getMaxLives()).isEqualTo(6);
  }

  @Test
  @DisplayName("HARD difficulty has correct parameters") void testHardDifficultyParameters() {
    Difficulty hard = Difficulty.HARD;

    assertThat(hard.getDisplayName()).isEqualTo("Сложно");
    assertThat(hard.getMinWordLength()).isEqualTo(9);
    assertThat(hard.getMaxWordLength()).isEqualTo(15);
    assertThat(hard.getMaxLives()).isEqualTo(6);
  }

  @Test
  @DisplayName("EASY: words from 3 to 5 letters are suitable") void testIsWordSuitable_easy() {
    Difficulty easy = Difficulty.EASY;

    assertThat(easy.isWordSuitable("кот")).isTrue();
    assertThat(easy.isWordSuitable("слово")).isTrue();
    assertThat(easy.isWordSuitable("аб")).isFalse();
    assertThat(easy.isWordSuitable("программа")).isFalse();
  }

  @Test
  @DisplayName("NORMAL: words from 6 to 8 letters are suitable") void testIsWordSuitable_normal() {
    Difficulty hard = Difficulty.NORMAL;

    assertThat(hard.isWordSuitable("лопата")).isTrue();
    assertThat(hard.isWordSuitable("кухарка")).isTrue();
    assertThat(hard.isWordSuitable("код")).isFalse();
    assertThat(hard.isWordSuitable("программирование")).isFalse();
  }

  @Test
  @DisplayName("HARD: words from 9 to 15 letters are suitable") void testIsWordSuitable_hard() {
    Difficulty hard = Difficulty.HARD;

    assertThat(hard.isWordSuitable("программист")).isTrue();
    assertThat(hard.isWordSuitable("компьютер")).isTrue();
    assertThat(hard.isWordSuitable("код")).isFalse();
    assertThat(hard.isWordSuitable("слово")).isFalse();
  }

  @Test
  @DisplayName("All difficulties have 6 lives") void testAllDifficultiesHaveSameMaxLives() {
    for (Difficulty difficulty : Difficulty.values()) {
      assertThat(difficulty.getMaxLives()).isEqualTo(6);
    }
  }
}
