package data;

import model.CategoriesEnum;
import model.Difficulty;

import java.util.List;

public interface WordRepository {
    final String FILE_PATH = "src/resources/words";

    void getAllWards(String filepath);

    List<String> getWordsByDifficulty(CategoriesEnum category, Difficulty difficulty);

    String getRandomWord(CategoriesEnum category, Difficulty difficulty);
}
