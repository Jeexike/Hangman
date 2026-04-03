package org.example;

import lombok.extern.slf4j.Slf4j;
import ui.UiRunner;

@Slf4j
public class Main {

	public static void main(String[] args) {
		log.info("Application starting");
		log.debug("Arguments received: {}", (Object[]) args);
		try {
			UiRunner uiRunner = new UiRunner();
			log.info("UI runner initialized successfully");

			uiRunner.run();
			log.info("Game session completed");

		} catch (Exception e) {
			log.error("Fatal error during application execution", e);
			System.exit(1);
		} finally {
			log.info("Application shutdown");
		}
	}
}
