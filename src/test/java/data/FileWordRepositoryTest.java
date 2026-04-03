package data;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import model.CategoriesEnum;
import model.Difficulty;

@DisplayName("FileWordRepository Tests") class FileWordRepositoryTest {

  @TempDir Path tempDir;

  private Path wordsFile;

  @BeforeEach void setUp() throws IOException {
    wordsFile = tempDir.resolve("words.txt");

    String content = """
        #ANIMALS
        кот
        собака
        лев
        тигр
        гетеродонтозавр


        #FOOD
        пицца
        суп
        борщ
        """;

    Files.writeString(wordsFile, content);
  }

  @Test
  @DisplayName("Load words from file by category") void testLoadWordsFromFile() {
    FileWordRepository repository = new FileWordRepository();
    repository.getAllWords(wordsFile.toString());

    var animals = repository.getWordsByDifficulty(CategoriesEnum.ANIMALS, Difficulty.EASY);
    assertThat(animals).contains("кот", "лев", "тигр");
    assertThat(animals).doesNotContain("собака");

    var food = repository.getWordsByDifficulty(CategoriesEnum.FOOD, Difficulty.EASY);
    assertThat(food).contains("суп", "борщ");
  }

  @Test
  @DisplayName("Filter words by difficulty - EASY") void testGetWordsByDifficulty_easy() {
    FileWordRepository repository = new FileWordRepository();
    repository.getAllWords(wordsFile.toString());

    var easyWords = repository.getWordsByDifficulty(CategoriesEnum.ANIMALS, Difficulty.EASY);

    assertThat(easyWords).contains("кот", "лев", "тигр").doesNotContain("собака")
        .allMatch(word -> word.length() >= 3 && word.length() <= 5);
  }

  @Test
  @DisplayName("Filter words by difficulty - NORMAL") void testGetWordsByDifficulty_normal() {
    FileWordRepository repository = new FileWordRepository();
    repository.getAllWords(wordsFile.toString());

    var normalWords = repository.getWordsByDifficulty(CategoriesEnum.ANIMALS, Difficulty.NORMAL);

    assertThat(normalWords).contains("собака").allMatch(word -> word.length() >= 6 && word.length() <= 8);
  }

  @Test
  @DisplayName("Filter words by difficulty - HARD") void testGetWordsByDifficulty_hard() {
    FileWordRepository repository = new FileWordRepository();
    repository.getAllWords(wordsFile.toString());

    var hardWords = repository.getWordsByDifficulty(CategoriesEnum.ANIMALS, Difficulty.HARD);

    assertThat(hardWords).contains("гетеродонтозавр").allMatch(word -> word.length() >= 9 && word.length() <= 15);
  }

  @Test
  @DisplayName("getRandomWord returns word from correct category") void testGetRandomWord() {
    FileWordRepository repository = new FileWordRepository();
    repository.getAllWords(wordsFile.toString());

    String word = repository.getRandomWord(CategoriesEnum.FOOD, Difficulty.EASY);

    assertThat(word).isIn("пицца", "суп", "борщ");
    assertThat(word).isLowerCase();
  }

  @Test
  @DisplayName("Fallback to all words when no match by difficulty") void testFallbackWhenNoWordsMatchDifficulty() {
    FileWordRepository repository = new FileWordRepository();
    repository.getAllWords(wordsFile.toString());

    var words = repository.getWordsByDifficulty(CategoriesEnum.FOOD, Difficulty.HARD);

    assertThat(words).contains("пицца", "суп", "борщ");
  }

  @Test
  @DisplayName("All words are in lowercase") void testAllWordsAreLowerCase() {
    FileWordRepository repository = new FileWordRepository();
    repository.getAllWords(wordsFile.toString());

    var allWords = repository.getWordsByDifficulty(CategoriesEnum.ANIMALS, Difficulty.EASY);

    assertThat(allWords).allMatch(word -> word.equals(word.toLowerCase()));
  }
}
