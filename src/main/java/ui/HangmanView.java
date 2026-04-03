package ui;

import constructor.GameStateConstructor;
import lombok.extern.slf4j.Slf4j;
import model.GameState;

@Slf4j
public class HangmanView implements View {
	@Override
	public void show() {
		log.info("Starting Hangman game");
		System.out.println();
		System.out.println("Игра началась!");
		System.out.println("Категория: " + GameStateConstructor.getCategories().getDisplayName());
		System.out.println("Сложность: " + GameStateConstructor.getDifficulty().getDisplayName());
		System.out.println("Максимум ошибок: " + GameStateConstructor.getMaxLives());

		try {
			while (!GameStateConstructor.isGameOver()) {
				drowGameState();
				handleInput();
			}
			drowGameState();

			if (GameStateConstructor.isWon()) {
				log.info("Player won! Word guessed successfully");
				System.out.println("Победа! Вы угадали слово!");
			} else {
				log.info("Player lost. The word was: {}", GameStateConstructor.getHiddenWord());
				System.out.println("Поражение! Игра окончена.");
				System.out.println("Слово: " + GameStateConstructor.getHiddenWord());
			}
		} catch (Exception e) {
			log.error("Error during gameplay", e);
		}
	}

	@Override
	public int handleInput() {
		log.debug("Waiting for user letter input");
		System.out.print("Введите русскую букву: ");

		while (true) {
			String input = in.next();

			if (!input.matches("[а-яА-ЯЁё]")) {
				log.warn("Invalid letter input: {}", input);
				System.out.print("Ошибка: введите русскую букву: ");
				continue;
			}

			char letter = input.toLowerCase().charAt(0);
			log.debug("User entered letter: {}", letter);
			GameState.InputState status = GameStateConstructor.InputCharToGameStatus(letter);

			switch (status) {
				case RIGHT -> {
					log.debug("Letter '{}' guessed correctly", letter);
					System.out.println("Есть такая буква!");
					return 1;
				}
				case WRONG -> {
					log.debug("Letter '{}' not found", letter);
					System.out.println("Такой буквы нет!");
					return 0;
				}
				case EXISTS -> {
					log.debug("Letter '{}' already entered", letter);
					System.out.println("Вы уже вводили букву '" + letter + "'");
					System.out.print("Введите другую букву: ");
				}
			}
		}
	}

	public void drowGameState() {
		int errors = GameStateConstructor.getWrongLetters().size();
		int attemptsRemaining = GameStateConstructor.getMaxLives() - errors;

		log.debug("Game state: errors={}, attemptsRemaining={}", errors, attemptsRemaining);
		log.debug("Visible word: {}", GameStateConstructor.getVisibleWord());
		log.debug("Wrong letters: {}", GameStateConstructor.getWrongLetters());

		System.out.println(StagesOfHangman.getStage(errors).getDisplayName());
		System.out.println("Слово: " + GameStateConstructor.getVisibleWord());
		System.out.print("Ошибки: ");
		for (char c : GameStateConstructor.getWrongLetters()) {
			System.out.print(c + " ");
		}
		System.out.println();
		System.out.println("Осталось попыток: " + attemptsRemaining);
	}
}
