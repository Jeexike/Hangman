package model;

import java.util.HashSet;
import java.util.Set;

public class GameState {
    private final String hiddenWord;
    private final Set<Character> wrongInputLetter;
    private final Set<Character> correctInputLetter;
    private final Difficulty difficulty;
    HangmanState hangmanState = HangmanState.IN_PROCESS;
    InputState inputState;

    public GameState(String hiddenWord, Difficulty difficulty) {
        if (hiddenWord == null || hiddenWord.isEmpty()) {
            throw new IllegalArgumentException("Слово не может быть пустым!");
        }

        this.hiddenWord = hiddenWord;
        wrongInputLetter = new HashSet<>();
        correctInputLetter = new HashSet<>();
        this.difficulty = difficulty;
    }

    public void guessLetter(char letter) {
        if (wrongInputLetter.contains(letter) || correctInputLetter.contains(letter)) {
            inputState = InputState.EXISTS;
        } else if (hiddenWord.indexOf(letter) >= 0) {
            inputState = InputState.RIGHT;
            correctInputLetter.add(letter);
            isWon();
        } else {
            inputState = InputState.WRONG;
            wrongInputLetter.add(letter);
            isLost();
        }
    }

    public void isWon() {
        for (char c : hiddenWord.toCharArray()) {
            if (!correctInputLetter.contains(c)) {
                return;
            }
        }
        hangmanState = HangmanState.WIN;
    }

    public void isLost() {
        if (wrongInputLetter.size() >= difficulty.getMaxLives()) {
            hangmanState = HangmanState.LOSE;
        }
    }

    public String getVisibleWord() {
        StringBuilder sb = new StringBuilder();
        for (char c : hiddenWord.toCharArray()) {
            if (correctInputLetter.contains(c)) {
                sb.append(c).append(" ");
            } else {
                sb.append("_ ");
            }
        }
        return sb.toString().trim();
    }

    public String getHiddenWord() { return hiddenWord; }
    public Set<Character> getWrongInputCharacter() { return wrongInputLetter; }
    public Set<Character> getCorrectInputCharacter() { return correctInputLetter; }
    public Difficulty getDifficulty() { return difficulty; }
    public HangmanState getHangmanState() { return hangmanState; }
    public InputState getInputState() { return inputState; }

    public enum InputState { RIGHT, WRONG, EXISTS }
    public enum HangmanState { WIN, LOSE, IN_PROCESS }
}
