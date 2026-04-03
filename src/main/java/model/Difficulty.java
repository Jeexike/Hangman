package model;

import lombok.Getter;

@Getter public enum Difficulty {
  EASY("Легко", 3, 5, 6),
  NORMAL("Нормально", 6, 8, 6),
  HARD("Сложно", 9, 15, 6);

  private final String displayName;
  private final int minWordLength;
  private final int maxWordLength;
  private final int maxLives;

  Difficulty(String displayName, int minWordLength, int maxWordLength, int maxLives) {
    this.displayName = displayName;
    this.minWordLength = minWordLength;
    this.maxWordLength = maxWordLength;
    this.maxLives = maxLives;
  }

  public boolean isWordSuitable(String word) {
    int length = word.length();
    return length >= minWordLength && length <= maxWordLength;
  }
}
