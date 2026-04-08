package data;

import java.util.List;

import model.CategoriesEnum;
import model.DifficultyEnum;

public interface WordRepository {
	String FILE_PATH = "src/main/resources/words";

	void getAllWords(String filepath);

	List<String> getWordsByDifficulty(CategoriesEnum category, DifficultyEnum difficulty);

	String getRandomWord(CategoriesEnum category, DifficultyEnum difficulty);
}
