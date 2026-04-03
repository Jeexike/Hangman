package data;

import java.util.List;

import model.CategoriesEnum;
import model.Difficulty;

public interface WordRepository {
  final String FILE_PATH = "src/main/resources/words";

  void getAllWords(String filepath);

  List<String> getWordsByDifficulty(CategoriesEnum category, Difficulty difficulty);

  String getRandomWord(CategoriesEnum category, Difficulty difficulty);
}
