package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Difficulty Enum Tests")
class DifficultyEnumTest {

	@Test
	@DisplayName("EASY: words from 3 to 5 letters are suitable")
	void testIsWordSuitable_easy() {
		DifficultyEnum easy = DifficultyEnum.EASY;

		assertThat(easy.isWordSuitable("кот")).isTrue();
		assertThat(easy.isWordSuitable("слово")).isTrue();
		assertThat(easy.isWordSuitable("аб")).isFalse();
		assertThat(easy.isWordSuitable("программа")).isFalse();
	}

	@Test
	@DisplayName("NORMAL: words from 6 to 8 letters are suitable")
	void testIsWordSuitable_normal() {
		DifficultyEnum hard = DifficultyEnum.NORMAL;

		assertThat(hard.isWordSuitable("лопата")).isTrue();
		assertThat(hard.isWordSuitable("кухарка")).isTrue();
		assertThat(hard.isWordSuitable("код")).isFalse();
		assertThat(hard.isWordSuitable("программирование")).isFalse();
	}

	@Test
	@DisplayName("HARD: words from 9 to 15 letters are suitable")
	void testIsWordSuitable_hard() {
		DifficultyEnum hard = DifficultyEnum.HARD;

		assertThat(hard.isWordSuitable("программист")).isTrue();
		assertThat(hard.isWordSuitable("компьютер")).isTrue();
		assertThat(hard.isWordSuitable("код")).isFalse();
		assertThat(hard.isWordSuitable("слово")).isFalse();
	}

	@Test
	@DisplayName("All difficulties have 6 lives")
	void testAllDifficultiesHaveSameMaxLives() {
		for (DifficultyEnum difficulty : DifficultyEnum.values()) {
			assertThat(difficulty.getMaxLives()).isEqualTo(6);
		}
	}
}
