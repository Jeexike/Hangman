package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;
import model.CategoriesEnum;
import model.Difficulty;

@Slf4j
public class FileWordRepository implements WordRepository {
	private final Map<CategoriesEnum, List<String>> categoryMap;
	private final Random random = new Random();
	private String testWordOverride = null;

	public FileWordRepository() {
		log.info("Initializing FileWordRepository");
		this.categoryMap = new EnumMap<>(CategoriesEnum.class);
		getAllWords(FILE_PATH);
		log.info("FileWordRepository initialized with {} categories", categoryMap.size());
	}

	public void setTestWord(String word) {
		this.testWordOverride = word;
	}

	public void clearTestWord() {
		this.testWordOverride = null;
	}

	@Override
	public void getAllWords(String filePath) {
		log.debug("Loading words from file: {}", filePath);
		for (CategoriesEnum category : CategoriesEnum.values()) {
			categoryMap.put(category, new ArrayList<>());
		}

		CategoriesEnum currentCategory = null;

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.isEmpty()) {
					continue;
				}

				if (line.startsWith("#")) {
					String categoryName = line.substring(1).trim().toUpperCase();
					try {
						currentCategory = CategoriesEnum.valueOf(categoryName);
						log.debug("Switched to category: {}", currentCategory);
						continue;
					} catch (IllegalArgumentException e) {
						log.warn("Unknown category in file: {}", categoryName);
						currentCategory = null;
					} catch (Exception e) {
						log.error("Failed to parse category line: '{}'", line, e);
					}
				}

				if (currentCategory != null) {
					String word = line.toLowerCase();
					categoryMap.get(currentCategory).add(word);
				}
			}
		} catch (IOException e) {
			log.error("Failed to read words file: {}", filePath, e);
		}
	}

	// Test Func
	// public void printMap() {
	// for (Map.Entry<Categories, List<String>> entry : wordsByCategory.entrySet())
	// {
	// Categories categories = entry.getKey();
	// List<String> list = entry.getValue();
	// System.out.println(categories + " : " + list);
	// }
	//
	// for (String word : getWordsByDifficulty(Categories.SPORT, Difficulty.EASY)){
	// System.out.println(word);
	// }
	// System.out.println();
	// System.out.println();
	// System.out.println(getRandomWord(Categories.SPORT, Difficulty.EASY));
	// }

	@Override
	public List<String> getWordsByDifficulty(CategoriesEnum category, Difficulty difficulty) {
		log.debug("Getting words for category={}, difficulty={}", category, difficulty);
		List<String> wordsByDifficult = new ArrayList<>(categoryMap.get(category));
		Iterator<String> iterator = wordsByDifficult.iterator();
		while (iterator.hasNext()) {
			String word = iterator.next();
			if (!(word.length() >= difficulty.getMinWordLength() && word.length() <= difficulty.getMaxWordLength())) {
				iterator.remove();
			}
		}

		if (wordsByDifficult.isEmpty()) {
			log.warn("No words found for category={} with difficulty={}, falling back to all words", category,
					difficulty);
			return categoryMap.get(category);
		}

		return wordsByDifficult;
	}

	@Override
	public String getRandomWord(CategoriesEnum category, Difficulty difficulty) {
		if (testWordOverride != null) {
			log.debug("Returning test word override: {}", testWordOverride);
			return testWordOverride;
		}

		log.debug("Getting random word for category={}, difficulty={}", category, difficulty);
		List<String> wordsByDifficulty = getWordsByDifficulty(category, difficulty);
		int randomInt = random.nextInt(wordsByDifficulty.size());
		String selected = wordsByDifficulty.get(randomInt);

		log.info("Selected random word (length: {}) for category={}, difficulty={}", selected.length(), category,
				difficulty);
		return selected;
	}
}
