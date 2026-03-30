package model;

import java.util.HashSet;
import java.util.Set;

public class GameState {
    private final String hiddenWord;
    private final Set<Character> wrongInputLetterSet;
    private final Set<Character> correctInputLetterSet;
    private final Difficulty difficulty;
    private HangmanState hangmanState;
    private InputState inputState;

    public GameState(String hiddenWord, Difficulty difficulty) {
        if (hiddenWord == null || hiddenWord.isEmpty()) {
            throw new IllegalArgumentException("Слово не может быть пустым!");
        }

        this.hiddenWord = hiddenWord;
        wrongInputLetterSet = new HashSet<>();
        correctInputLetterSet = new HashSet<>();
        this.difficulty = difficulty;
        hangmanState = HangmanState.IN_PROCESS;
    }

    public void guessLetter(char letter) {
        if (wrongInputLetterSet.contains(letter) || correctInputLetterSet.contains(letter)) {
            inputState = InputState.EXISTS;
        } else if (hiddenWord.indexOf(letter) >= 0) {
            inputState = InputState.RIGHT;
            correctInputLetterSet.add(letter);
            isWon();
        } else {
            inputState = InputState.WRONG;
            wrongInputLetterSet.add(letter);
            isLost();
        }
    }

    public void isWon() {
        for (char c : hiddenWord.toCharArray()) {
            if (!correctInputLetterSet.contains(c)) {
                return;
            }
        }
        hangmanState = HangmanState.WIN;
    }

    public void isLost() {
        if (wrongInputLetterSet.size() >= difficulty.getMaxLives()) {
            hangmanState = HangmanState.LOSE;
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

    public String getHiddenWord() { return hiddenWord; }
    public Set<Character> getWrongInputCharacter() { return wrongInputLetterSet; }
    public Set<Character> getCorrectInputCharacter() { return correctInputLetterSet; }
    public Difficulty getDifficulty() { return difficulty; }
    public HangmanState getHangmanState() { return hangmanState; }
    public InputState getInputState() { return inputState; }

    public enum InputState { RIGHT, WRONG, EXISTS }
    public enum HangmanState { WIN, LOSE, IN_PROCESS }
}
