package ui;

import java.util.Scanner;

public interface View {
	Scanner in = new Scanner(System.in);

	void show();

	int handleInput();
}
