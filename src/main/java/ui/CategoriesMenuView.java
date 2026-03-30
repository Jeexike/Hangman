package ui;

import constructor.GameStateConstructor;
import model.CategoriesEnum;

public class CategoriesMenuView implements View {

    @Override
    public void show() {
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
                System.out.println("Вы выбрали категорию: " + categories.getDisplayName());
                GameStateConstructor.setCategories(categories);
            }
        }
    }

    @Override
    public int handleInput() {
        while (true) {
            String input = in.next();
            if (!input.matches("\\d+")) {
                System.out.print("Ошибка: введите число от 1 до 4: ");
                continue;
            }

            int choice = Integer.parseInt(input);

            if (!(choice >= 1 && choice <= 4)) {
                System.out.print("Ошибка: введите число от 1 до 4: ");
                continue;
            }

            return choice;
        }
    }
}
