package io.github.oliviercailloux.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.oliviercailloux.enums.BrownResource;
import io.github.oliviercailloux.enums.GreyResource;
import io.github.oliviercailloux.enums.Resource;
import io.github.oliviercailloux.enums.WonderInfo;
import io.github.oliviercailloux.game.Wonder;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class WonderTest {
	Wonder wonder = new Wonder(WonderInfo.MH);

	@Test
	public void testGetRewardOfCurrentStage() {
		assertEquals(5, wonder.getRewardNextStage());
		wonder.unlockNewStage();
		assertEquals(8, wonder.getRewardNextStage());
		wonder.unlockNewStage();
		assertEquals(7, wonder.getRewardNextStage());
		wonder.unlockNewStage();
		assertThrows(IllegalStateException.class, () -> wonder.getRewardNextStage());
	}

	@Test
	public void testGetNeededCostToBuildNextStage() {
		List<Resource> costStage1 = new ArrayList<>();
		List<Resource> costStage2 = new ArrayList<>();
		List<Resource> costStage3 = new ArrayList<>();
		costStage1.add(GreyResource.GLASS);
		costStage1.add(GreyResource.LOOM);
		costStage2.add(BrownResource.WOOD);
		costStage2.add(BrownResource.CLAY);
		costStage3.add(BrownResource.STONE);
		costStage3.add(BrownResource.WOOD);
		assertEquals(costStage1, wonder.getNeededCostToBuildNextStage());
		wonder.unlockNewStage();
		assertEquals(costStage2, wonder.getNeededCostToBuildNextStage());
		wonder.unlockNewStage();
		assertEquals(costStage3, wonder.getNeededCostToBuildNextStage());
		wonder.unlockNewStage();
		assertThrows(IllegalStateException.class, () -> wonder.getNeededCostToBuildNextStage());
	}

	@Test
	public void testUnlockNewStage() {
		assertEquals(0, wonder.getLastUnlockedStage());
		assertFalse(wonder.isLastStageUnlocked());
		wonder.unlockNewStage();
		assertFalse(wonder.isLastStageUnlocked());
		assertEquals(1, wonder.getLastUnlockedStage());
		assertFalse(wonder.isLastStageUnlocked());
		wonder.unlockNewStage();
		assertEquals(2, wonder.getLastUnlockedStage());
		assertFalse(wonder.isLastStageUnlocked());
		wonder.unlockNewStage();
		assertTrue(wonder.isLastStageUnlocked());
		assertEquals(3, wonder.getLastUnlockedStage());
		assertThrows(IllegalStateException.class, () -> wonder.unlockNewStage());
	}

	@Test
	public void testOtherFunctions() {
		assertEquals(BrownResource.STONE, wonder.getDefaultResource());
	}
}