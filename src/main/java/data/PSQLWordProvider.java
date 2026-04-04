package data;

import config.DatabaseConfig;
import lombok.extern.slf4j.Slf4j;
import model.CategoriesEnum;
import model.DifficultyEnum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class PSQLWordProvider implements RandomWordProvider {
	private String testWord = null;

	public void PSQLSetTestWord(String word) {
		this.testWord = word;
	}

	public void PSQLClearTestWord() {
		this.testWord = null;
	}

	private static int toDbDifficulty(DifficultyEnum difficulty) {
		return switch (difficulty) {
			case EASY -> 1;
			case NORMAL -> 2;
			case HARD -> 3;
		};
	}

	@Override
	public String getRandomWord(CategoriesEnum category, DifficultyEnum difficulty) {
		log.debug("Fetching random word: category={}, difficulty={}", category, difficulty);
		if (testWord != null) {
			log.debug("Returning test word override: {}", testWord);
			return testWord;
		}

		int diff = toDbDifficulty(difficulty);

		String word = fetchRandom(category.name(), diff);
		if (word != null) {
			log.info("Word selected successfully: category={}, difficulty={}, length={}", category, difficulty,
					word.length());
			return word;
		}

		log.debug("Exact difficulty match not found, triggering fallback...");
		word = fetchRandom(category.name(), null);
		if (word != null) {
			log.info("Word selected successfully (fallback): category={}, length={}", category, word.length());
			return word;
		}

		log.info("No words available for category={} and difficulty={}", category, difficulty);
		throw new IllegalStateException(
				"No words found for category=%s, difficulty=%s".formatted(category, difficulty));
	}

	private String fetchRandom(String category, Integer difficulty) {
		String sql = difficulty == null ? """
				SELECT word FROM hangman_words
				WHERE category = ? AND is_active = TRUE
				ORDER BY RANDOM() LIMIT 1
				""" : """
				SELECT word FROM hangman_words
				WHERE category = ? AND difficulty = ? AND is_active = TRUE
				ORDER BY RANDOM() LIMIT 1""";

		String diffLabel = (difficulty == null) ? "any" : String.valueOf(difficulty);
		log.debug("Executing query: category='{}', difficulty={}", category, diffLabel);

		try (Connection conn = DatabaseConfig.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, category);
			if (difficulty != null) {
				stmt.setInt(2, difficulty);
			}

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					String result = rs.getString("word");
					log.debug("Query returned a word, length: {}", result.length());
					return result;
				}
			}
		} catch (SQLException e) {
			log.debug("Database query failed: {}", e.getMessage());
			throw new RuntimeException("Database error while fetching word", e);
		}
		return null;
	}
}
