package constructor;

import data.FileWordRepository;
import model.Categories;
import model.Difficulty;
import model.GameState;

import java.util.Set;

public class GameStateConstructor {
    static Categories categories;
    static Difficulty difficulty;
    static GameState gameState;
    static FileWordRepository fileWordRepository = new FileWordRepository();

    private GameStateConstructor() {}

    static void setGameStatusParam() {
        gameState = new GameState(fileWordRepository.getRandomWord(categories, difficulty), difficulty);
    }

    public static void setCategories(Categories categories) {
        GameStateConstructor.categories = categories;
        if (categories != null && difficulty != null) {
            setGameStatusParam();
        }
    }

    public static void setDifficulty(Difficulty difficulty) {
        GameStateConstructor.difficulty = difficulty;
        if (categories != null && difficulty != null) {
            setGameStatusParam();
        }
    }

    public static GameState.InputState InputCharToGameStatus(char inputChar) {
        gameState.guessLetter(inputChar);
        return gameState.getInputState();
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
        return gameState.getWrongInputCharacter();
    }

    public static int getMaxLives() {
        return difficulty.getMaxLives();
    }

    public static Categories getCategories() { return categories; }
    public static Difficulty getDifficulty() { return difficulty; }
}
