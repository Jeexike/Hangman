package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("GameState Tests")
class GameStateTest {

	private GameState gameState;

	@BeforeEach
	void setUp() {
		gameState = new GameState("машина", Difficulty.EASY);
	}

	@Test
	@DisplayName("Creating GameState with empty word throws exception")
	void testCreateWithEmptyWord() {
		assertThatThrownBy(() -> new GameState("", Difficulty.EASY)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("Слово не может быть пустым!");
	}

	@Test
	@DisplayName("Initial state")
	void testInitialState() {
		assertThat(gameState.getHangmanState()).isEqualTo(GameState.HangmanState.IN_PROCESS);
		assertThat(gameState.getVisibleWord()).isEqualTo("_ _ _ _ _ _");
		assertThat(gameState.getWrongInputLetterSet().isEmpty()).isTrue();
		assertThat(gameState.getCorrectInputLetterSet().isEmpty()).isTrue();
	}

	@Test
	@DisplayName("Guessed letter is displayed in word")
	void testGuessCorrectLetter() {
		gameState.guessLetter('а');

		assertThat(gameState.getInputState()).isEqualTo(GameState.InputState.RIGHT);
		assertThat(gameState.getVisibleWord()).isEqualTo("_ а _ _ _ а");
		assertThat(gameState.getCorrectInputLetterSet()).contains('а');
		assertThat(gameState.getWrongInputLetterSet()).doesNotContain('а');
	}

	@Test
	@DisplayName("Wrong letter is added to errors")
	void testGuessWrongLetter() {
		gameState.guessLetter('л');

		assertThat(gameState.getInputState()).isEqualTo(GameState.InputState.WRONG);
		assertThat(gameState.getWrongInputLetterSet()).contains('л');
		assertThat(gameState.getVisibleWord()).isEqualTo("_ _ _ _ _ _");
	}

	@Test
	@DisplayName("Repeated letter input returns EXISTS")
	void testGuessAlreadyGuessedLetter() {
		gameState.guessLetter('м');
		gameState.guessLetter('м');

		assertThat(gameState.getInputState()).isEqualTo(GameState.InputState.EXISTS);
	}

	@Test
	@DisplayName("Repeated wrong letter input returns EXISTS")
	void testGuessAlreadyWrongLetter() {
		gameState.guessLetter('л');
		gameState.guessLetter('л');

		assertThat(gameState.getInputState()).isEqualTo(GameState.InputState.EXISTS);
	}

	@Test
	@DisplayName("Win when all letters are guessed")
	void testWinCondition() {
		gameState.guessLetter('м');
		gameState.guessLetter('а');
		gameState.guessLetter('ш');
		gameState.guessLetter('и');
		gameState.guessLetter('н');
		gameState.guessLetter('а');

		assertThat(gameState.getHangmanState()).isEqualTo(GameState.HangmanState.WIN);
		assertThat(gameState.getVisibleWord()).isEqualTo("м а ш и н а");
	}

	@Test
	@DisplayName("Lose after 6 wrong attempts")
	void testLoseCondition() {
		gameState.guessLetter('щ');
		gameState.guessLetter('д');
		gameState.guessLetter('г');
		gameState.guessLetter('ж');
		gameState.guessLetter('е');
		gameState.guessLetter('у');

		assertThat(gameState.getHangmanState()).isEqualTo(GameState.HangmanState.LOSE);
	}

	@Test
	@DisplayName("getVisibleWord shows only guessed letters")
	void testGetVisibleWord() {
		gameState.guessLetter('м');
		gameState.guessLetter('а');

		assertThat(gameState.getVisibleWord()).isEqualTo("м а _ _ _ а");
	}

	@Test
	@DisplayName("getHiddenWord returns full word")
	void testGetHiddenWord() {
		assertThat(gameState.getHiddenWord()).isEqualTo("машина");
	}
}
