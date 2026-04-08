package model;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class GameState {
	private final String hiddenWord;
	private final Set<Character> wrongInputLetterSet;
	private final Set<Character> correctInputLetterSet;
	private final DifficultyEnum difficulty;
	private HangmanState hangmanState;
	private InputState inputState;

	public GameState(String hiddenWord, DifficultyEnum difficulty) {
		if (hiddenWord.isEmpty()) {
			log.error("Attempted to create GameState with empty word");
			throw new IllegalArgumentException("Слово не может быть пустым!");
		}

		this.hiddenWord = hiddenWord;
		wrongInputLetterSet = new HashSet<>();
		correctInputLetterSet = new HashSet<>();
		this.difficulty = difficulty;
		hangmanState = HangmanState.IN_PROCESS;

		log.info("GameState created successfully");
	}

	public void guessLetter(char letter) {
		if (wrongInputLetterSet.contains(letter) || correctInputLetterSet.contains(letter)) {
			inputState = InputState.EXISTS;
		} else if (hiddenWord.indexOf(letter) >= 0) {
			inputState = InputState.RIGHT;
			correctInputLetterSet.add(letter);
			checkWinCondition();
		} else {
			inputState = InputState.WRONG;
			wrongInputLetterSet.add(letter);
			checkLoseCondition();
		}
	}

	public void checkWinCondition() {
		for (char c : hiddenWord.toCharArray()) {
			if (!correctInputLetterSet.contains(c)) {
				return;
			}
		}
		hangmanState = HangmanState.WIN;
		log.info("Player won! All letters guessed");
	}

	public void checkLoseCondition() {
		if (wrongInputLetterSet.size() >= difficulty.getMaxLives()) {
			hangmanState = HangmanState.LOSE;
			log.info("Player lost: max attempts reached");
		}
	}

	public String getVisibleWord() {
		StringBuilder sb = new StringBuilder();
		for (char c : hiddenWord.toCharArray()) {
			if (correctInputLetterSet.contains(c)) {
				sb.append(c).append(" ");
			} else {
				sb.append("_ ");
			}
		}
		return sb.toString().trim();
	}

	public enum InputState {
		RIGHT, WRONG, EXISTS
	}

	public enum HangmanState {
		WIN, LOSE, IN_PROCESS
	}
}
