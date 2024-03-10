package io.github.oliviercailloux.game;

import io.github.oliviercailloux.enums.PurpleCardInfo;
import io.github.oliviercailloux.enums.SpecialEffect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PurpleCard extends Card {
	/**
	 * Special effect that the purple card will bring
	 */
	public SpecialEffect specialEffect;
	private static final Logger LOGGER = LoggerFactory.getLogger(PurpleCard.class);


	public PurpleCard(PurpleCardInfo purpleCardInfo) {
		super(purpleCardInfo.cardInfo);
		specialEffect = purpleCardInfo.specialEffect;
	}

	/**
	 * Applies the effect of granting victory points based on the number of brown
	 * cards of the player and the adversary.
	 */
	private void applyOneVpPerBrown(Player player) {
		int numberOfBrownCards = player.getNumberBrownCards();
		int numberOfAdversaries = player.getAdversary().getNumberBrownCards();
		int victoryPoints = numberOfBrownCards + numberOfAdversaries;
		player.addToVictoryPoints(victoryPoints);
	}

	/**
	 * Applies the effect of granting victory points based on the number of grey
	 * cards of the player and the adversary.
	 */
	private void applyTwoVpPerGrey(Player player) {
		int numberOfGreyCards = player.getNumberGreyCards();
		int numberOfAdversaries = player.getAdversary().getNumberGreyCards();
		int victoryPoints = (numberOfGreyCards + numberOfAdversaries) * 2;
		player.addToVictoryPoints(victoryPoints);
	}

	/**
	 * Applies the effect of granting victory points based on the number of yellow
	 * cards of the player and the adversary.
	 */
	private void applyOneVpPerYellow(Player player) {
		int numberOfYellowCards = player.getNumberYellowCards();
		int numberOfAdversaries = player.getAdversary().getNumberYellowCards();
		int victoryPoints = numberOfYellowCards + numberOfAdversaries;
		player.addToVictoryPoints(victoryPoints);
	}

	/**
	 * Applies the effect of granting victory points based on the number of green
	 * cards of the player and the adversary.
	 */
	private void applyOneVpPerGreen(Player player) {
		int numberOfGreenCards = player.getNumberGreenCards();
		int numberOfAdversaries = player.getAdversary().getNumberGreenCards();
		int victoryPoints = numberOfGreenCards + numberOfAdversaries;
		player.addToVictoryPoints(victoryPoints);
	}

	/**
	 * Applies the effect of granting victory points based on the number of red
	 * cards of the player and the adversary.
	 */
	private void applyOneVpPerRed(Player player) {
		int numberOfRedCards = player.getNumberRedCards();
		int numberOfAdversaries = player.getAdversary().getNumberRedCards();
		int victoryPoints = numberOfRedCards + numberOfAdversaries;
		player.addToVictoryPoints(victoryPoints);
	}

	/**
	 * Applies the effect of granting victory points based on the number of brown,
	 * purple, and grey cards of the player and the adversary.
	 */
	private void applyOneVpPerBrownPurpleGreySelf(Player player) {
		int numberOfBrownPurpleGreyCards = player.getNumberPurpleCards() + player.getNumberGreyCards()
				+ player.getNumberBrownCards();
		player.addToVictoryPoints(numberOfBrownPurpleGreyCards);
	}

	/**
	 * Applies the effect of granting victory points based on the number of blue
	 * cards of the player and the adversary.
	 */
	private void applyOneVpPerBlue(Player player) {
		int numberOfBlueCards = player.getNumberPurpleCards() + player.getAdversary().getNumberBlueCards();
		player.addToVictoryPoints(numberOfBlueCards);
	}

	/**
	 * Applies the effect of granting victory points based on the number of stage
	 * builds of the player and the adversary.
	 */
	private void applyOneVpPerStageBuild(Player player) {
		int numberOfStageBuilds = player.getWonder().getLastUnlockedStage();
		int numberOfAdversaries = player.getAdversary().getWonder().getLastUnlockedStage();
		int victoryPoints = numberOfStageBuilds + numberOfAdversaries;
		player.addToVictoryPoints(victoryPoints);
	}

	private void applyOneVpPerNegativeMilitaryToken(Player player) {
		int numberOfNegativeTokens = player.getNumberOfNegativeMilitaryTokens();
		int numberOfAdversaries = player.getAdversary().getNumberOfNegativeMilitaryTokens();
		int victoryPoints = numberOfNegativeTokens + numberOfAdversaries;
		player.addToVictoryPoints(victoryPoints);
	}

	@Override
	public void bringEffectsTo(Player player) {
		switch (specialEffect) {
		case ONE_VP_PER_BROWN_BOTH_SIDES:
			applyOneVpPerBrown(player);
			break;
		case TWO_VP_PER_GREY_BOTH_SIDES:
			applyTwoVpPerGrey(player);
			break;
		case ONE_VP_PER_YELLOW_BOTH_SIDES:
			applyOneVpPerYellow(player);
			break;
		case ONE_VP_PER_GREEN_BOTH_SIDES:
			applyOneVpPerGreen(player);
			break;
		case ONE_VP_PER_RED_BOTH_SIDES:
			applyOneVpPerRed(player);
			break;
		case ONE_VP_PER_NEGATIVE_MILITARY_TOKEN_BOTH_SIDES:
			applyOneVpPerNegativeMilitaryToken(player);
			break;
		case ONE_VP_PER_BROWN_PURPLE_GREY:
			applyOneVpPerBrownPurpleGreySelf(player);
			break;
		case ONE_VP_PER_BLUE_BOTH_SIDES:
			applyOneVpPerBlue(player);
			break;
		case ONE_VP_PER_STAGE_BUILD_BOTH_SIDES:
			applyOneVpPerStageBuild(player);
			break;
		// $CASES-OMITTED$
		default:
			LOGGER.warn("Effet spécial non traité : ", specialEffect);
			break;

		}
	}

	@Override
	public String getEffectsInString() {
		return specialEffect.toString();
	}
}
