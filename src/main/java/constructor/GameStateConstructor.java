package constructor;

import java.util.Set;

// import data.FileWordRepository;
import data.PSQLWordProvider;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import model.CategoriesEnum;
import model.DifficultyEnum;
import model.GameState;

@Slf4j
public class GameStateConstructor {
	@Getter
	private static CategoriesEnum categories;
	@Getter
	private static DifficultyEnum difficulty;
	private static GameState gameState;
	@Getter
	static PSQLWordProvider psqlWordProvider = new PSQLWordProvider();
	// @Getter
	// static FileWordRepository fileWordRepository = new FileWordRepository();

	private GameStateConstructor() {
	}

	static void setGameStatusParam() {
		log.debug("Setting game status parameters");
		log.debug("Selected category: {}, difficulty: {}", categories, difficulty);

		// String word = fileWordRepository.getRandomWord(categories, difficulty);
		String word = psqlWordProvider.getRandomWord(categories, difficulty);
		log.debug("Selected random word (length: {})", word.length());

		gameState = new GameState(word, difficulty);
		log.info("Game state initialized with word length: {}", word.length());
	}

	public static void setCategories(CategoriesEnum categories) {
		log.debug("Setting category: {}", categories);
		GameStateConstructor.categories = categories;

		if (categories != null && difficulty != null) {
			log.info("Category and difficulty set, initializing game");
			setGameStatusParam();
		} else {
			log.debug("Waiting for difficulty to be set before initializing game");
		}
	}

	public static void setDifficulty(DifficultyEnum difficulty) {
		log.debug("Setting difficulty: {}", difficulty);
		GameStateConstructor.difficulty = difficulty;

		if (categories != null && difficulty != null) {
			log.info("Category and difficulty set, initializing game");
			setGameStatusParam();
		} else {
			log.debug("Waiting for category to be set before initializing game");
		}
	}

	public static GameState.InputState InputCharToGameStatus(char inputChar) {
		log.debug("Processing input character: '{}'", inputChar);
		gameState.guessLetter(inputChar);
		GameState.InputState result = gameState.getInputState();
		log.debug("Input result for '{}': {}", inputChar, result);
		return result;
	}

	public static boolean isGameOver() {
		return gameState.getHangmanState() != GameState.HangmanState.IN_PROCESS;
	}

	public static boolean isWon() {
		return gameState.getHangmanState() == GameState.HangmanState.WIN;
	}

	public static String getVisibleWord() {
		return gameState.getVisibleWord();
	}

	public static String getHiddenWord() {
		return gameState.getHiddenWord();
	}

	public static Set<Character> getWrongLetters() {
		return gameState.getWrongInputLetterSet();
	}

	public static int getMaxLives() {
		return difficulty.getMaxLives();
	}
}
