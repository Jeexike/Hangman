package ui;


import constructor.GameStateConstructor;
import model.GameState;

public class HangmanView implements View {
    @Override
    public void show() {
        System.out.println();
        System.out.println("Игра началась!");
        System.out.println("Категория: " + GameStateConstructor.getCategories().getDisplayName());
        System.out.println("Сложность: " + GameStateConstructor.getDifficulty().getDisplayName());
        System.out.println("Максимум ошибок: " + GameStateConstructor.getMaxLives());

        while (!GameStateConstructor.isGameOver()) {
            drowGameState();
            handleInput();
        }
        drowGameState();

        if (GameStateConstructor.isWon()) {
            System.out.println("Победа! Вы угадали слово!");
        } else {
            System.out.println("Поражение! Игра окончена.");
            System.out.println("Слово: " + GameStateConstructor.getHiddenWord());
        }
    }

    @Override
    public int handleInput() {
        System.out.print("Введите русскую букву: ");

        while (true) {
            String input = in.next();

            if (!input.matches("[а-яА-ЯЁё]")) {
                System.out.print("Ошибка: введите русскую букву: ");
                continue;
            }

            char letter = input.toLowerCase().charAt(0);
            GameState.InputState status = GameStateConstructor.InputCharToGameStatus(letter);

            switch (status) {
                case RIGHT -> {
                    System.out.println("Есть такая буква!");
                    return 1;
                }
                case WRONG -> {
                    System.out.println("Такой буквы нет!");
                    return 0;
                }
                case EXISTS -> {
                    System.out.println("Вы уже вводили букву '" + letter + "'");
                    System.out.print("Введите другую букву: ");
                }
            }
        }
    }

    public void drowGameState() {
        int errors = GameStateConstructor.getWrongLetters().size();
        System.out.println(StagesOfHangman.getStage(errors).getDisplayName());
        System.out.println("Слово: " + GameStateConstructor.getVisibleWord());
        System.out.print("Ошибки: ");
        for (char c : GameStateConstructor.getWrongLetters()) {
            System.out.print(c + " ");
        }
        System.out.println();
        System.out.println("Осталось попыток: " + (GameStateConstructor.getMaxLives() - errors));
    }
}
