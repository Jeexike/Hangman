package data;

import model.Categories;
import model.Difficulty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public interface WordRepository {

    void getAllWards(String filepath);

    List<String> getWordsByDifficulty(Categories category, Difficulty difficulty);

    String getRandomWord(Categories category, Difficulty difficulty);
}
