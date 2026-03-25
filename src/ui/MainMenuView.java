package ui;

public class MainMenuView implements View{

    @Override
    public void show() {
        System.out.println("┌────────────────────────────────────┐");
        System.out.println("│           ГЛАВНОЕ МЕНЮ             │");
        System.out.println("├────────────────────────────────────┤");
        System.out.println("│  1. Начать игру                    │");
        System.out.println("│  2. Выход                          │");
        System.out.println("└────────────────────────────────────┘");
        System.out.print("Ваш выбор: ");
        if (handleInput() != 1) {
            System.exit(1);
        }
    }

    @Override
    public int handleInput() {
        while (true) {
            String input = in.next();

            if (!input.matches("\\d+")) {
                System.out.print("Ошибка: введите число 1 или 2: ");
                continue;
            }

            int choice = Integer.parseInt(input);

            if (choice != 1 && choice != 2) {
                System.out.print("Ошибка: введите число 1 или 2: ");
                continue;
            }

            return choice;
        }

    }
}
