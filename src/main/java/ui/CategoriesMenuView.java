package ui;

import constructor.GameStateConstructor;
import lombok.extern.slf4j.Slf4j;
import model.CategoriesEnum;

@Slf4j
public class CategoriesMenuView implements View {

	@Override
	public void show() {
		log.debug("Displaying category selection menu");
		int counter = 0;
		System.out.println();
		System.out.println("Выбирите категорию: ");
		for (CategoriesEnum category : CategoriesEnum.values()) {
			counter++;
			System.out.printf("%d. ", counter);
			System.out.println(category.getDisplayName());
		}
		counter = 0;
		System.out.print("Ваш выбор: ");
		int choice = handleInput();
		for (CategoriesEnum categories : CategoriesEnum.values()) {
			counter++;
			if (counter == choice) {
				log.info("User selected category: {}", categories.getDisplayName());
				System.out.println("Вы выбрали категорию: " + categories.getDisplayName());
				GameStateConstructor.setCategories(categories);
			}
		}
	}

	@Override
	public int handleInput() {
		log.debug("Processing category input");
		while (true) {
			String input = in.next();
			if (!input.matches("\\d+")) {
				log.warn("Invalid category input: {}", input);
				System.out.print("Ошибка: введите число от 1 до 4: ");
				continue;
			}

			int choice = Integer.parseInt(input);

			if (!(choice >= 1 && choice <= 4)) {
				log.warn("Invalid category choice: {}", choice);
				System.out.print("Ошибка: введите число от 1 до 4: ");
				continue;
			}

			log.debug("Category selected: {}", choice);
			return choice;
		}
	}
}
