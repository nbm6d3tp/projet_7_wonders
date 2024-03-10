package io.github.oliviercailloux.game;

import io.github.oliviercailloux.enums.Resource;
import io.github.oliviercailloux.enums.WonderInfo;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class implemented the functionalities and properties of a Wonder.
 */
public class Wonder {
	/**
	 * Fundamental informations of the wonder: name default resource and
	 * informations on his stages.
	 */
	WonderInfo wonderInfo;
	private int lastUnlockedStage;
	private static final Logger LOGGER = LoggerFactory.getLogger(Wonder.class);

	/**
	 * The list of needed resources to build the next stage of the wonder
	 */
	private List<Resource> neededCostToBuildNextStage;
	/**
	 * The reward of the next stage of the wonder
	 */
	private int rewardNextStage;
	/**
	 * Iterator to iterate on the stages of the wonder, every times the player build
	 * a stage of the wonder
	 */
	private Iterator<Stage> stageIterator;

//	private static final Logger LOGGER = LoggerFactory.getLogger(Wonder.class);

	public Wonder(WonderInfo wonderInfo) {
		lastUnlockedStage = 0;
		this.wonderInfo = wonderInfo;
		stageIterator = wonderInfo.stages.iterator();
		if (stageIterator.hasNext()) {
			Stage stage = stageIterator.next();
			neededCostToBuildNextStage = stage.getCost();
			rewardNextStage = stage.getReward();
		}
	}

	/**
	 * @return 2 random wonders drawed from the list of Wonders of the game
	 */
	public static Wonder[] getTwoRandomWonders() {
		LOGGER.info("Distribuer Merveilles aux 2 joueurs ");

		Random rand = new Random();
		Wonder[] chosenWonders = new Wonder[2];
		int indexRandomWonder1 = rand.nextInt(WonderInfo.size());
		chosenWonders[0] = new Wonder(WonderInfo.getWonderByIndex(indexRandomWonder1));
		int indexRandomWonder2 = rand.nextInt(WonderInfo.size());
		while (indexRandomWonder2 == indexRandomWonder1) {
			indexRandomWonder2 = rand.nextInt(WonderInfo.size());
		}
		chosenWonders[1] = new Wonder(WonderInfo.getWonderByIndex(indexRandomWonder2));
		return chosenWonders;
	}

	/**
	 * Unlock a new stage.
	 *
	 * @throws IllegalStateException if the last unlocked stage is the last stage of
	 *                               the wonder.
	 */
	public void unlockNewStage() {
		LOGGER.info("Déverrouiller un nouveau stage.");
		if (lastUnlockedStage >= wonderInfo.stages.size()) {
			throw new IllegalStateException();
		}
		lastUnlockedStage++;
		if (stageIterator.hasNext()) {
			Stage stage = stageIterator.next();
			neededCostToBuildNextStage = stage.getCost();
			rewardNextStage = stage.getReward();
		}
	}

	/**
	 * @return                       The reward an user can get after unlocking the
	 *                               next stage.
	 * @throws IllegalStateException if the last stage of the wonder is unlocked
	 *                               already.
	 */
	public int getRewardNextStage() {
		LOGGER.info("Get reward pour le stage suivant.");
		if (isLastStageUnlocked()) {
			throw new IllegalStateException();
		}
		return rewardNextStage;
	}

	/**
	 * @return                       The needed cost to unlock the next stage.
	 * @throws IllegalStateException if the last stage of the wonder is unlocked
	 *                               already.
	 */
	public List<Resource> getNeededCostToBuildNextStage() {
		LOGGER.info("Getting cost necessaire pour le stage suivant.");
		if (isLastStageUnlocked()) {
			throw new IllegalStateException();
		}
		return neededCostToBuildNextStage;
	}

	/**
	 * @return the defaut resource of the wonder.
	 */
	public Resource getDefaultResource() {
		return wonderInfo.defaultResource;
	}

	/**
	 * @return the last unlocked stage of the wonder.
	 */
	public int getLastUnlockedStage() {
		return lastUnlockedStage;
	}

	/**
	 * @return if the last stage of the wonder is unlocked or not.
	 */
	public boolean isLastStageUnlocked() {
		return lastUnlockedStage == wonderInfo.stages.size();
	}

	public String getName() {
		return wonderInfo.name;
	}

	public Set<Stage> getStages() {
		return wonderInfo.stages;
	}

	public WonderInfo getWonderInfo() {
		return wonderInfo;
	}

}