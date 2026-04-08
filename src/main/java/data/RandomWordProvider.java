package data;

import model.CategoriesEnum;
import model.DifficultyEnum;

public interface RandomWordProvider {

	String getRandomWord(CategoriesEnum category, DifficultyEnum difficulty);
}
