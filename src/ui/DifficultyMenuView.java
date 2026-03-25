package ui;

import constructor.GameStateConstructor;
import model.Difficulty;

public class DifficultyMenuView implements View{
    @Override
    public void show() {
        int counter = 0;
        System.out.println();
        System.out.println("Выберите сложность: ");
        for (Difficulty difficulty : Difficulty.values()) {
            counter++;
            System.out.printf("%d. ", counter);
            System.out.printf("%s. Длина слова от %d до %d. Попыток: %d\n", difficulty.getDisplayName()
            , difficulty.getMinWordLength(), difficulty.getMaxWordLength(), difficulty.getMaxLives());
        }
        counter = 0;
        System.out.print("Ваш выбор: ");
        int choice = handleInput();
        for (Difficulty difficulty : Difficulty.values()) {
            counter++;
            if (counter == choice) {
                System.out.println("Вы выбрали сложность: " + difficulty.getDisplayName());
                GameStateConstructor.setDifficulty(difficulty);
            }
        }
    }

    @Override
    public int handleInput() {
        while (true) {
            String input = in.next();
            if (!input.matches("\\d+")) {
                System.out.print("Ошибка: введите число от 1 до 3: ");
                continue;
            }

            int choice = Integer.parseInt(input);

            if (!(choice >= 1 && choice <= 3)) {
                System.out.print("Ошибка: введите число от 1 до 3: ");
                continue;
            }

            return choice;
        }
    }
}
