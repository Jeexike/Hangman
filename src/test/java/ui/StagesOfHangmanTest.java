package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("StagesOfHangman Enum Tests")
class StagesOfHangmanTest {

	@Test
	@DisplayName("getStage returns correct stage for valid input")
	void testGetStage_validInput() {
		assertEquals(StagesOfHangman.STAGE_0, StagesOfHangman.getStage(0));
		assertEquals(StagesOfHangman.STAGE_3, StagesOfHangman.getStage(3));
		assertEquals(StagesOfHangman.STAGE_6, StagesOfHangman.getStage(6));
	}

	@Test
	@DisplayName("getStage clamps negative values to 0")
	void testGetStage_negativeInput() {
		assertEquals(StagesOfHangman.STAGE_0, StagesOfHangman.getStage(-1));
		assertEquals(StagesOfHangman.STAGE_0, StagesOfHangman.getStage(-100));
	}

	@Test
	@DisplayName("getStage clamps values > 6 to 6")
	void testGetStage_tooLargeInput() {
		assertEquals(StagesOfHangman.STAGE_6, StagesOfHangman.getStage(7));
		assertEquals(StagesOfHangman.STAGE_6, StagesOfHangman.getStage(100));
	}

	@Test
	@DisplayName("Each stage has non-empty displayName with gallows")
	void testStagesHaveDisplayName() {
		for (StagesOfHangman stage : StagesOfHangman.values()) {
			assertNotNull(stage.getDisplayName());
			assertFalse(stage.getDisplayName().isEmpty());
			assertTrue(stage.getDisplayName().contains("________"));
		}
	}
}
