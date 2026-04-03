package ui;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainMenuView implements View {

	@Override
	public void show() {
		log.debug("Displaying main menu");
		System.out.println("┌────────────────────────────────────┐");
		System.out.println("│           ГЛАВНОЕ МЕНЮ             │");
		System.out.println("├────────────────────────────────────┤");
		System.out.println("│  1. Начать игру                    │");
		System.out.println("│  2. Выход                          │");
		System.out.println("└────────────────────────────────────┘");
		System.out.print("Ваш выбор: ");
		if (handleInput() != 1) {
			log.info("User chose to exit the game");
			System.exit(1);
		} else {
			log.info("User started a new game");
		}
	}

	@Override
	public int handleInput() {
		log.debug("Processing input in main menu");
		while (true) {
			String input = in.next();

			if (!input.matches("\\d+")) {
				log.warn("Invalid input in main menu: {}", input);
				System.out.print("Ошибка: введите число 1 или 2: ");
				continue;
			}

			int choice = Integer.parseInt(input);

			if (choice != 1 && choice != 2) {
				log.warn("Invalid choice in main menu: {}", choice);
				System.out.print("Ошибка: введите число 1 или 2: ");
				continue;
			}

			log.debug("User selected menu item: {}", choice);
			return choice;
		}
	}
}
