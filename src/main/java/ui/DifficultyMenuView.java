package ui;

import constructor.GameStateConstructor;
import lombok.extern.slf4j.Slf4j;
import model.DifficultyEnum;

@Slf4j
public class DifficultyMenuView implements View {
	@Override
	public void show() {
		log.debug("Displaying difficulty selection menu");
		int counter = 0;
		System.out.println();
		System.out.println("Выберите сложность: ");
		for (DifficultyEnum difficulty : DifficultyEnum.values()) {
			counter++;
			System.out.printf("%d. ", counter);
			System.out.printf("%s. Длина слова от %d до %d. Попыток: %d\n", difficulty.getDisplayName(),
					difficulty.getMinWordLength(), difficulty.getMaxWordLength(), difficulty.getMaxLives());
		}
		counter = 0;
		System.out.print("Ваш выбор: ");
		int choice = handleInput();
		for (DifficultyEnum difficulty : DifficultyEnum.values()) {
			counter++;
			if (counter == choice) {
				log.info("User selected difficulty: {}", difficulty.getDisplayName());
				System.out.println("Вы выбрали сложность: " + difficulty.getDisplayName());
				GameStateConstructor.setDifficulty(difficulty);
			}
		}
	}

	@Override
	public int handleInput() {
		log.debug("Processing difficulty input");
		while (true) {
			String input = in.next();
			if (!input.matches("\\d+")) {
				log.warn("Invalid difficulty input: {}", input);
				System.out.print("Ошибка: введите число от 1 до 3: ");
				continue;
			}

			int choice = Integer.parseInt(input);

			if (!(choice >= 1 && choice <= 3)) {
				log.warn("Invalid difficulty choice: {}", choice);
				System.out.print("Ошибка: введите число от 1 до 3: ");
				continue;
			}

			log.debug("Difficulty selected: {}", choice);
			return choice;
		}
	}
}
